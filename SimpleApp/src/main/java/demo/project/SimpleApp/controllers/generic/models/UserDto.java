package demo.project.SimpleApp.controllers.generic.models;

import demo.project.SimpleApp.data.entity.UserEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {

    @NotNull(message = "Please set value userId before sending the request")
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

    public UserDto() {
    }

    public UserDto(UserEntity userToParse) {
        this.userId = userToParse.getUserId();
        this.age = userToParse.getAge();
        this.email = userToParse.getEmail();
        this.firstName = userToParse.getFirstName();
        this.lastName = userToParse.getLastName();
    }

    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity(this.firstName, this.lastName, this.email, this.age);
        userEntity.setUserId(this.userId);
        return userEntity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userId, userDto.userId) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(age, userDto.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, age);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
