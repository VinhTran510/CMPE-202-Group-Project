// Get the course ID from the URL
const urlParams = new URLSearchParams(window.location.search);
const courseId = urlParams.get('id');

var table = document.getElementById("studentTable");
var tbody = table.getElementsByTagName('tbody')[0] || table.appendChild(document.createElement('tbody'));

// ... rest of your fetch code ...
fetch(`http://localhost:8080/courses/${courseId}/students`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(studentIds => {
        console.log('Fetched course IDs:', studentIds); // Log fetched student IDs
        // Fetch the details for each course
        const coursePromises = studentIds.map(studentId =>
            fetch(`http://localhost:8080/students/${studentId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
        );

        return Promise.all(coursePromises);
    })
    .then(students => {
        console.log('Fetched student data:', students); // Log fetched course data

//        // Clear existing rows
//        tbody.innerHTML = '';

        // Create a new table row for each student
        students.forEach((student, index) => {
            var row = tbody.insertRow();
            row.insertCell(0).appendChild(document.createTextNode(student.id));
            row.insertCell(1).appendChild(document.createTextNode(student.firstName));
            row.insertCell(2).appendChild(document.createTextNode(student.lastName));
            row.insertCell(3).appendChild(document.createTextNode(student.email));
        });
    })
    .catch(error => console.error('Error:', error)); // Log any errors