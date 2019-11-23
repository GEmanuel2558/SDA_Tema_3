package demo.project.SimpleApp;

import demo.project.SimpleApp.business.services.user.IUser;
import demo.project.SimpleApp.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SimpleAppApplication implements CommandLineRunner {

    @Autowired
    private IUser service;

    public static void main(String[] args) {
        SpringApplication.run(SimpleAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        service.insertTheUser(new UserEntity("Anton", "Mihai", "anton.mihai@gmail.com", 35));
        service.insertTheUser(new UserEntity("Victor", "Stefan", "victor.stefan@yahoo.com", 50));
        service.insertTheUser(new UserEntity("Mihahache", "Maria", "mihalache.maria@imc.com", 25));
        service.insertTheUser(new UserEntity("Dominte", "Oana", "dominte.oana@skype.com", 42));
        service.insertTheUser(new UserEntity("Mititelu", "George", "mititelu.george@gmail.com", 18));

        System.out.println("All users that had been inserted into the DB");
        //service.getAllUsersFromDb().forEach(System.out::println);

        List<UserEntity> allUserEntities = service.getAllUsersFromDb();
        if (!allUserEntities.isEmpty()) {
            for (int i = 0; i < allUserEntities.size(); i++) {
                System.out.println(allUserEntities.get(i));
            }
        }
        System.out.println("The DB is ready to be used");
    }
}
