const URL = 'http://localhost:8080';
let entries = [];
let jwt;

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + jwt
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

function deleteEntry(id) {

    console.log(id)

    const response = fetch( `${URL}/entries/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization' : 'Bearer ' + jwt
        }
    }).then((result) => {
        indexEntries();
    })

}

const updateEntry = (e) => {
    const formData = new FormData(e.target);
    const entry = {};
    entry['id'] = document.getElementById("id").value
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDateUpdate'), formData.get('checkInTimeUpdate'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDateUpdate'), formData.get('checkOutTimeUpdate'));

    fetch(`${URL}/entries`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + jwt
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        indexEntries();
    });
}

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + jwt
        }
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};



const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.innerHTML = "Delete";
        button.id = entry.id;
        button.onclick = function () { deleteEntry(this.id) };
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(button)
        display.appendChild(row);
    });
};

function hideLogin(){
    document.getElementById("loginpage").style.display = "none";
    document.getElementById("mainpage").style.display = "block";
    indexEntries();
}

function hideMainPage(){
    document.getElementById("mainpage").style.display = "none"
    document.getElementById("loginpage").style.display = "block";
}

async function validate () {

        const formData = new FormData(document.getElementById("login"));

        let loginViewModel = {};

        loginViewModel['email'] = formData.get("email");
        loginViewModel['password'] = formData.get("password");

        const response = await fetch(`${URL}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginViewModel)
        });

        jwt = await response.json();

        console.log(jwt)

        if (jwt != null) {

            jwt = jwt.token;
            await hideLogin();

        }

}

async function registerUser(){
    const formData = new FormData(document.getElementById("signUp"));

    let user = {};

    user['firstname'] = formData.get("firstname");
    user['lastname'] = formData.get("lastname");
    user['email'] = formData.get("emailSignUp");
    user['password'] = formData.get("password");

    console.log(user)

    const response = await fetch( `${URL}/auth/signUp`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });
}

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    hideMainPage();
});


