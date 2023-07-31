// Assume 'user' contains the username and password entered by the user
const user = {
    username: "user123",
    password: "pass456"
};

fetch("/login", {
    method: "POST",
    headers: {
        "Content-Type": "application/json" // Set the correct content type to 'application/json'
    },
    body: JSON.stringify(user)
})
    .then(response => response.text())
    .then(message => console.log(message))
    .catch(error => console.error(error));
