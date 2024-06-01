// Fetch the course IDs when the page loads
const studentId = localStorage.getItem('studentId');

fetch(`http://localhost:8080/students/${studentId}/courses`)
    .then(response => response.json())
    .then(courseIds => {
        console.log('Fetched course IDs:', courseIds); // Log fetched course IDs

        // Fetch the details for each course
        const coursePromises = courseIds.map(courseId =>
            fetch(`http://localhost:8080/courses/${courseId}`)
                .then(response => response.json())
        );

        return Promise.all(coursePromises);
    })
    .then(courses => {
        console.log('Fetched course data:', courses); // Log fetched course data

        // Create a table
        const table = document.createElement('table');

        // Create table header
        const thead = document.createElement('thead');
        const headerRow = document.createElement('tr');
        ['Course Name', 'Semester', 'Faculty', 'Grade'].forEach(text => {
            const th = document.createElement('th');
            th.textContent = text;
            headerRow.appendChild(th);
        });
        thead.appendChild(headerRow);
        table.appendChild(thead);

        // Create table body
        const tbody = document.createElement('tbody');
        courses.forEach(course => {
            // Fetch the grades for each course
            fetch(`http://localhost:8080/courses/${course.id}/grades`)
                .then(response => response.json())
                .then(grades => {
                    console.log('Fetched course data:', grades)
                    const row = document.createElement('tr');
                    const grade = grades['2'] ? grades['2'] : 'N/A'; // Check if grades is not null before accessing it
                    [course.courseName, course.semester, course.faculty, grade].forEach(text => {
                        const td = document.createElement('td');
                        td.textContent = text;
                        row.appendChild(td);
                    });
                    tbody.appendChild(row);
                })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
        });
        table.appendChild(tbody);

        // Append the table to the courses container
        document.getElementById('coursesContainer').appendChild(table);
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });