

const urlParams = new URLSearchParams(window.location.search);
const courseId =  urlParams.get('id');
console.log(courseId);

let course;
fetch(`http://localhost:8080/courses/${courseId}`)
    .then(response => response.json())
    .then(data => {
        course = data;
        console.log('Fetched course data:', data); // Log fetched student data
        const courseName = document.createElement('h1');
        courseName.textContent = course.courseName;
        const courseContainer = document.getElementById('courseContainer');
        if (courseContainer) {
            courseContainer.appendChild(courseName);
        } else {
            console.error('Could not find the courseContainer element');
        }
    })
    .catch(error => console.error('Error:', error)); // Log any errors


fetch(`http://localhost:8080/courses/${courseId}/studentNames`)
    .then(response => response.json())

    .then(students => {

        console.log('Fetched student data:', students); // Log fetched course data

                // Create a table for assignments
        const table = document.createElement('table');

                // Create table header
        const thead = document.createElement('thead');
        const headerRow = document.createElement('tr');
        ['Student Name'].forEach(text => {
            const th = document.createElement('th');
            th.textContent = text;
            headerRow.appendChild(th);
        });
        thead.appendChild(headerRow);
        table.appendChild(thead);

        console.log(students);
        const tbody = document.createElement('tbody');
        students.forEach(student => {
            const row = document.createElement('tr');
            const name = student;

                [name].forEach(text => {
                const td = document.createElement('td');
                td.textContent = text;
                row.appendChild(td);
            });
            tbody.appendChild(row);
        });
        table.appendChild(tbody);

        const courseContainer = document.getElementById('courseContainer');

                // Append the table to the course container
        if (courseContainer) {
            courseContainer.appendChild(table);
        } else {
            console.error('Could not find the courseContainer element');
        }

    })
    .catch(error => console.error('Error:', error)); // Log any errors

function setAssignmentLink() {

    var a = document.getElementById('assignment');
    a.href = `assignments.html?id=${courseId}`
}

function setStudentLink() {
    var a = document.getElementById('student');
    a.href = `student_list.html?id=${courseId}`
}



