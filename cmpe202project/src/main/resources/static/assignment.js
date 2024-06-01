const urlParams = new URLSearchParams(window.location.search);
const assignmentId = urlParams.get('id');


let assignment;
fetch(`http://localhost:8080/assignments/${assignmentId}`)
    .then(response => response.json())
    .then(data => {
        assignment = data;
        console.log('Fetched assignment data:', data); // Log fetched student data
        const assignmentName = document.createElement('h1');
        assignmentName.textContent = data.title;
        const courseContainer = document.getElementById('courseContainer');
        if (courseContainer) {
            courseContainer.appendChild(assignmentName);
        } else {
            console.error('Could not find the courseContainer element');
        }

    })
    .catch(error => console.error('Error:', error)); // Log any errors


fetch(`http://localhost:8080/assignments/${assignmentId}/work`)
    .then(response => response.json())
    .then(work => {
        console.log('Fetched work data:', work); // Log fetched course data

        // Append the elements to the course container
        const courseContainer = document.getElementById('courseContainer');
        if (courseContainer) {
            // courseContainer.appendChild(studentName);
            // courseContainer.appendChild(studentGrade);
        } else {
            console.error('Could not find the courseContainer element');
        }

        Promise.all(Object.keys(work).map(studentId =>
            fetch(`http://localhost:8080/students/${studentId}`)
                .then(response => response.json())
        ))
            .then(students => {
                console.log('Fetched students:', students); // Log fetched assignments

                // Create a table for assignments
                const table = document.createElement('table');

                // Create table header
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['ID', 'Student Name', 'Grade'].forEach(text => {
                    const th = document.createElement('th');
                    th.textContent = text;
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);



                const tbody = document.createElement('tbody');
                Promise.all(Object.keys(work).map(studentId =>
                    fetch(`http://localhost:8080/students/${studentId}`)
                        .then(response => response.json())
                )).then(students => {
                    students.forEach(student => {
                        const row = document.createElement('tr');
                        const id = student.id;
                        const name = student.firstName + " " + student.lastName;
                        const grade = work[student.id];
                        [id, name, grade].forEach(text => {
                            const td = document.createElement('td');
                            td.textContent = text;
                            row.appendChild(td);
                        });
                        tbody.appendChild(row);
                    });
                    table.appendChild(tbody);
                }).catch(error => console.error('Error:', error));


                // Append the table to the course container
                if (courseContainer) {
                    courseContainer.appendChild(table);
                } else {
                    console.error('Could not find the courseContainer element');
                }
            })
            .catch(error => console.error('Error:', error)); // Log any errors
    })
    .catch(error => console.error('Error:', error)); // Log any errors

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

function setAssignmentId() {
    document.getElementById("assignmentId").setAttribute('value', assignmentId);
}
