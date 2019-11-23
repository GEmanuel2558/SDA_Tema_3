import {UserDto} from './modules/UserDto.js'
import {constants} from '../constants/ApplicationConstants.js'

class NetworkHandler {

    getAllRecordsFromTheDb() {
        console.log('Calling get all records from the db to display something to the user')
        const legendOfTheList = document.getElementById('section-display-all-legend');
        const referenceToFirstUl = document.querySelector('#section-display-all-legend ul');
        if (referenceToFirstUl) {
            legendOfTheList.removeChild(referenceToFirstUl);
        }
        fetch('http://localhost:9090/user-management/user/', {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function (serverResponse) {
            const serverBody = serverResponse.json();
            console.log(serverBody)
            serverBody.then(function (responseBack) {
                const filledUl = responseBack.reduce((ul, currentUser) => {
                    const newLi = document.createElement('li');
                    newLi.user = new UserDto(currentUser.userId, currentUser.firstName, currentUser.lastName, currentUser.email, currentUser.age);
                    newLi.innerText = `${currentUser.firstName} ${currentUser.lastName} with the age of ${currentUser.age}`;
                    ul.appendChild(newLi);
                    return ul;
                }, document.createElement('ul'));
                legendOfTheList.appendChild(filledUl);
            }).catch(reason => {
                console.error(reason);
            });
        });
    }

    insertNewUser(userToInsertInDb) {
        console.log("Insert a new user in to the DB. The value is " + JSON.stringify(userToInsertInDb));
        fetch('http://localhost:9090/user-management/user/', {
            method: 'POST',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(userToInsertInDb)
        }).then(serverResponse => {
            if (constants.CREATED_STATUS === serverResponse.status) {
                this.getAllRecordsFromTheDb();
            }
        }).catch(reason => {
            console.error(reason);
        })
    }
}

export const networkHandler = new NetworkHandler();

networkHandler.getAllRecordsFromTheDb();
