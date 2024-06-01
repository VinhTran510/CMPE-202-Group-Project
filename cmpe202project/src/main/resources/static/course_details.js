// Get the course ID from the URL
const urlParams = new URLSearchParams(window.location.search);
const courseId = urlParams.get('id');

const studentId = localStorage.getItem('studentId');

// Fetch the student data
// Fetch the student data
let student;
fetch(`http://localhost:8080/students/${studentId}`)
    .then(response => response.json())
    .then(data => {
        student = data;
        console.log('Fetched student data:', data); // Log fetched student data
    })
    .catch(error => console.error('Error:', error)); // Log any errors

// Fetch the course details
fetch(`http://localhost:8080/courses/${courseId}`)
    .then(response => response.json())
    .then(course => {
        console.log('Fetched course data:', course); // Log fetched course data

        // Create elements for course name, semester, and faculty
        const courseName = document.createElement('h1');
        courseName.textContent = course.courseName;
        courseName.id = 'courseName';

        const semester = document.createElement('h2');
        semester.textContent = course.semester;
        semester.id = 'semester';

        const faculty = document.createElement('h2');
        faculty.textContent = course.faculty;
        faculty.id = 'faculty';

        // Append the elements to the course container
        const courseContainer = document.getElementById('courseContainer');
        if (courseContainer) {
            courseContainer.appendChild(courseName);
            courseContainer.appendChild(semester);
            courseContainer.appendChild(faculty);
        } else {
            console.error('Could not find the courseContainer element');
        }

        Promise.all(course.assignmentList.map(assignmentId =>
            fetch(`http://localhost:8080/assignments/${assignmentId}`)
                .then(response => response.json())
        ))
            .then(assignments => {
                console.log('Fetched assignments:', assignments); // Log fetched assignments

                // Create a table for assignments
                const table = document.createElement('table');

                // Create table header
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['Assignment Name', 'Assignment Type', 'Grade'].forEach(text => {
                    const th = document.createElement('th');
                    th.textContent = text;
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                // Create table body
                const tbody = document.createElement('tbody');
                Promise.all(course.assignmentList.map(assignmentId =>
                    fetch(`http://localhost:8080/assignments/${assignmentId}`)
                        .then(response => response.json())
                )).then(assignments => {
                    assignments.forEach(assignment => {
                        const row = document.createElement('tr');
                        const grade = student.assignments[assignment.id] ? student.assignments[assignment.id] : 'N/A';
                        const assignmentType = assignment.quiz ? 'Quiz' : 'Assignment';
                        [assignment.title, assignmentType, grade].forEach(text => {
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