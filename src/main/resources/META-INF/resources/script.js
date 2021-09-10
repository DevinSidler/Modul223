const URL = 'http://localhost:8080';
let entries = [];
let users = [];
let activities = [];
let special = [];

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
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
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

    const response = fetch( `${URL}/entries/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
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
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
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
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
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
        button.classList.add("btn")
        button.classList.add("btn-danger")
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(button)
        display.appendChild(row);
    });
};

const createUser = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['firstname'] = formData.get("firstnameCreate");
    user['lastname'] = formData.get("lastnameCreate");
    user['email'] = formData.get("emailCreate");
    user['password'] = formData.get("passwordCreate");

    fetch(`${URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.json().then((user) => {
            users.push(user);
            renderUsers()
        });
    });
};

function deleteUser(id) {

    const response = fetch( `${URL}/users/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        indexUsers();
    })

}

const updateUser = (e) => {
    const formData = new FormData(e.target);
    const user = {};
    user['id'] = formData.get("idUpdate")
    user['firstname'] = formData.get("firstnameUpdate");
    user['lastname'] = formData.get("lastnameUpdate");
    user['email'] = formData.get("emailUpdate");
    user['password'] = formData.get("passwordUpdate");

    fetch(`${URL}/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(user)
    }).then((result) => {
        indexUsers();
    });
}

const renderUsers = () =>{
    const display = document.querySelector('#userDisplay');
    display.innerHTML = '';

    users.forEach((user) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.innerHTML = "Delete";
        button.id = user.id;
        button.onclick = function () { deleteUser(this.id) };
        button.classList.add("btn")
        button.classList.add("btn-danger")
        row.appendChild(createCell(user.id));
        row.appendChild(createCell(user.firstname));
        row.appendChild(createCell(user.lastname));
        row.appendChild(createCell(user.email));
        row.appendChild(button)
        display.appendChild(row);
    })
}

const indexUsers = () => {
    fetch(`${URL}/users`, {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        result.json().then((result) => {
            users = result;
            renderUsers();
        });
    });
    renderUsers();
};





const createActivity = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const activity = {};
    activity['name'] = formData.get("createActivityName");

    fetch(`${URL}/activities`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(activity)
    }).then((result) => {
        result.json().then((activity) => {
            activities.push(activity);
            renderActivities()
        });
    });
};

function deleteActivity(id) {

    const response = fetch( `${URL}/activities/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        indexActivities();
    })

}

const updateActivity = (e) => {
    const formData = new FormData(e.target);
    const activity = {};
    activity['id'] = formData.get("idActivityUpdate")
    activity['name'] = formData.get("activityNameUpdate");

    fetch(`${URL}/activities`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(activity)
    }).then((result) => {
        indexActivities();
    });
}

const renderActivities = () =>{
    const display = document.querySelector('#activityDisplay');
    display.innerHTML = '';

    activities.forEach((activity) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.innerHTML = "Delete";
        button.id = activity.id;
        button.onclick = function () { deleteActivity(this.id) };
        button.classList.add("btn")
        button.classList.add("btn-danger")
        row.appendChild(createCell(activity.id));
        row.appendChild(createCell(activity.name));
        row.appendChild(button)
        display.appendChild(row);
    })
}

const indexActivities = () => {
    fetch(`${URL}/activities`, {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        result.json().then((result) => {
            activities = result;
            renderActivities();
        });
    });
    renderActivities();
};


const indexSpecial = () => {
    fetch(`${URL}/users/special`, {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        result.json().then((result) => {
            special = result;
            renderSpecial();
        });
    });
    renderSpecial();
};

const renderSpecial = () =>{
    const display = document.querySelector('#specialDisplay');
    display.innerHTML = '';

    special.forEach((spec) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(spec.id));
        row.appendChild(createCell(spec.firstname));
        row.appendChild(createCell(spec.lastname));
        row.appendChild(createCell(spec.email));
        display.appendChild(row);
    })
}


function hideLogin(){
    document.getElementById("loginpage").style.display = "none";
    document.getElementById("mainpage").style.display = "block";
    indexEntries();
    indexUsers();
    indexActivities();
    indexSpecial();
}

function hideMainPage(){
    document.getElementById("mainpage").style.display = "none"
    document.getElementById("loginpage").style.display = "block";
}

async function validate () {

        const formData = new FormData(document.getElementById("login"));

        let jwt;
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

        if (jwt != null) {

            localStorage.setItem('token',jwt.token)
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

    const response = await fetch( `${URL}/auth/signUp`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });
}

function logout(){
    localStorage.clear()
    hideMainPage();
}

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);

    const createUserForm = document.querySelector('#createUser')
    createUserForm.addEventListener('submit',createUser)

    const createActivityForm = document.querySelector('#createActivity')
    createActivityForm.addEventListener('submit',createActivity)

    const updateEntryForm = document.querySelector('#updateEntryForm')
    updateEntryForm.addEventListener('submit', updateEntry)

    const updateUserForm = document.querySelector('#updateUser')
    updateUserForm.addEventListener('submit', updateUser)

    const updateActivityForm = document.querySelector('#updateActivity')
    updateActivityForm.addEventListener('submit', updateActivity)



    if (localStorage.getItem('token') == null){
        hideMainPage()
    }else {
        hideLogin()
    }

});


