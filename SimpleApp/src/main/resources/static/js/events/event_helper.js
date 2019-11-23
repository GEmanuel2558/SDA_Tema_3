import {networkHandler} from '../network/network_helper.js';
import {UserDto} from "../network/modules/UserDto.js";

class ButtonListener {
    constructor() {
        this.insertNewUser = document.getElementById('submit-new-user-button');
        this.insertNewUser.addEventListener("click", function (ev) {
            console.log('The user is inserting a new user into the DB')
            const inputFirstName = document.getElementById('input-first-name').value;
            const inputLastName = document.getElementById('input-last-name').value;
            const email = document.getElementById('input-email').value;
            const inputAge = parseInt(document.getElementById('input-age').value);
            const newUserToInsert = new UserDto(-1, inputFirstName, inputLastName, email, inputAge);
            networkHandler.insertNewUser(newUserToInsert);
        });
    }
}


export const generalButtonListener = new ButtonListener();
