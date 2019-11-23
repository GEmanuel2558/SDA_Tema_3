export class UserDto {
    constructor(userId, firstName, lastName, email, age) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    get getFullName() {
        return `${this.firstName} ${this.lastName}`;
    }
}
