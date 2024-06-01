localStorage.setItem('studentId', "1");

const studentId = localStorage.getItem('studentId');

// ... rest of your fetch code ...
fetch(`http://localhost:8080/students/${studentId}`)// Replace '1' with the current student's ID
    .then(response => response.json())
    .then(student => {
// Display the student's data
        document.getElementById('displayName').textContent = student.firstName + ' ' + student.lastName;
        document.getElementById('displayEmail').textContent = student.email;
        //document.getElementById('displayPassword').textContent = student.password;

// Add event listener to the edit button
        document.getElementById('editButton').addEventListener('click', function() {
            // Show the form
            document.getElementById('profileForm').style.display = 'block';

            // Populate the form with the current data
            document.getElementById('name').value = student.firstName;
            document.getElementById('lastName').value = student.lastName;
            document.getElementById('email').value = student.email;
            //document.getElementById('password').value = student.password;
        });
    });

// Handle form submission
document.getElementById('profileForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form from being submitted

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Fetch the current student's data
    fetch(`http://localhost:8080/students/${studentId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            firstName: name,
            email: email,
            password: password
        }),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});

// Fetch the current student's data
fetch(`http://localhost:8080/students/${studentId}`)
    .then(response => response.json())
    .then(student => {
        // Display the student's data
        document.getElementById('displayName').textContent = student.firstName + ' ' + student.lastName;
        document.getElementById('displayEmail').textContent = student.email;

        // Populate the notification settings
        document.getElementById('smsNotification').checked = student.notifications.sms;
        document.getElementById('emailNotification').checked = student.notifications.email;
        document.getElementById('pushNotification').checked = student.notifications.push;

        // Add event listeners to the switches
        ['sms', 'email', 'push'].forEach(notificationType => {
            document.getElementById(notificationType + 'Notification').addEventListener('change', function() {
                // Update the notification settings in the student object
                student.notifications[notificationType] = this.checked;

                // Send a PUT request to the server to update the student's data
                fetch(`http://localhost:8080/students/${studentId}/notifications`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(student.notifications),
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Success:', data);
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            });
        });

        // Add event listener to the edit button
        document.getElementById('editButton').addEventListener('click', function() {
            // Show the form
            document.getElementById('profileForm').style.display = 'block';

            // Populate the form with the current data
            document.getElementById('name').value = student.firstName;
            document.getElementById('lastName').value = student.lastName;
            document.getElementById('email').value = student.email;
        });
    });

// Handle form submission
document.getElementById('profileForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form from being submitted

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Fetch the current student's data
    fetch(`http://localhost:8080/students/${studentId}`)
        .then(response => response.json())
        .then(student => {
            // Update the student's data
            student.firstName = name;
            student.email = email;
            student.password = password;

            // Update the notification settings
            student.notifications.text = document.getElementById('textNotification').checked;
            student.notifications.email = document.getElementById('emailNotification').checked;
            student.notifications["push notification"] = document.getElementById('pushNotification').checked;

            // Send a PUT request to the server to update the student's data
            fetch(`http://localhost:8080/students/${studentId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(student),
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        });
});