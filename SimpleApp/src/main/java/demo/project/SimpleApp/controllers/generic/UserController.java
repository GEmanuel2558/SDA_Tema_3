package demo.project.SimpleApp.controllers.generic;

import demo.project.SimpleApp.business.services.user.IUser;
import demo.project.SimpleApp.controllers.generic.models.UserDto;
import demo.project.SimpleApp.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user-management")
public class UserController {

    @Autowired
    private IUser userService;

    @RequestMapping(value = "/user", method = RequestMethod.OPTIONS)
    public ResponseEntity controllerOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE).build();
    }

    @GetMapping(value = {"/user/{id}", "/user"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllUsers(@PathVariable(required = false) final Long id) {
        if (null == id) {
            List<UserEntity> listOfUsersFromTheDb = userService.getAllUsersFromDb();
            if (listOfUsersFromTheDb.isEmpty()) {
                return ResponseEntity.ok().build();
            } else {
                //Forma simpla de a le scrie
                List<UserDto> lisOfUsersToReturnToTheClient = new ArrayList<>();
                for (int i = 0; i < listOfUsersFromTheDb.size(); i++) {
                    lisOfUsersToReturnToTheClient.add(new UserDto(listOfUsersFromTheDb.get(i)));
                }
                return ResponseEntity.ok(lisOfUsersToReturnToTheClient);
                //Forma avansata
                //List<UserDto> lisOfUsersToReturnToTheClient = listOfUsersFromTheDb.parallelStream().map(user -> new UserDto(user)).collect(Collectors.toList());
                //return ResponseEntity.ok(lisOfUsersToReturnToTheClient);
            }
        } else {
            UserEntity foundUserEntity = userService.getAllUsersFromDb(id);
            if (null == foundUserEntity) {
                return ResponseEntity.notFound().build();
            } else {
                UserDto returnObjectToTheClient = new UserDto(foundUserEntity);
                return ResponseEntity.ok(returnObjectToTheClient);
            }
        }
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertUser(@RequestBody @Valid final UserDto newUser, HttpServletRequest request) {
        UserEntity theNewUserFromTheDb = userService.insertTheUser(newUser.toUserEntity());
        //Acum construim raspunsul inapoi catre clienti
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create(request.getRequestURL().toString() + "/" + theNewUserFromTheDb.getUserId())).build();
    }

    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editUser(@RequestBody @Valid final UserDto newUser) {
        userService.updateExistingUser(newUser.toUserEntity());
        //Acum construim raspunsul inapoi catre clienti
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestBody @Valid final UserDto newUser) {
        if (userService.deleteExistingUser(newUser.toUserEntity())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

}
