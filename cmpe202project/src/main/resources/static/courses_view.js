// Fetch the course IDs when the page loads
localStorage.setItem('studentId', "1");

const studentId = localStorage.getItem('studentId');

// Fetch current semester courses
fetch(`http://localhost:8080/students/${studentId}/courses`)
    .then(response => response.json())
    .then(courseIds => {
        const coursePromises = courseIds.map(courseId =>
            fetch(`http://localhost:8080/courses/${courseId}`)
                .then(response => response.json())
        );

        return Promise.all(coursePromises);
    })
    .then(courses => {
        console.log('Fetched current semester course data:', courses); // Log fetched course data
        document.getElementById('currentSemesterCourses').appendChild(createTable(courses));
    })
    .catch(error => console.error('Error:', error)); // Log any errors

// Hardcoded past semester courses
fetch(`http://localhost:8080/courses/4`)
    .then(response => response.json())
    .then(course => {
        console.log('Fetched past semester course data:', course); // Log fetched course data
        document.getElementById('pastSemesterCourses').appendChild(createTable([course]));
    })
    .catch(error => console.error('Error:', error)); // Log any errors

console.log('Past semester course data:', pastSemesterCourses); // Log past semester course data
document.getElementById('pastSemesterCourses').appendChild(createTable(pastSemesterCourses));

function createTable(courses) {
    const table = document.createElement('table');

    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    ['Course Name', 'Semester', 'Faculty'].forEach(text => {
        const th = document.createElement('th');
        th.textContent = text;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    const tbody = document.createElement('tbody');
    courses.forEach(course => {
        const row = document.createElement('tr');
        [course.courseName, course.semester, course.faculty].forEach(text => {
            const td = document.createElement('td');
            td.textContent = text;
            row.appendChild(td);
        });
        tbody.appendChild(row);
    });
    table.appendChild(tbody);

    return table;
}