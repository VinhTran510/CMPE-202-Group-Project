
localStorage.setItem('studentId', "1");

const studentId = localStorage.getItem('studentId');

// ... rest of your fetch code ...
fetch(`http://localhost:8080/students/${studentId}/courses`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(courseIds => {
        console.log('Fetched course IDs:', courseIds); // Log fetched course IDs

        // Fetch the details for each course
        const coursePromises = courseIds.map(courseId =>
            fetch(`http://localhost:8080/courses/${courseId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
        );

        return Promise.all(coursePromises);
    })
    .then(courses => {
        console.log('Fetched course data:', courses); // Log fetched course data

        // Get the main element
        const main = document.querySelector('main');

        // Remove all existing course cards
        while (main.firstChild) {
            main.firstChild.remove();
        }

        // Create a new card for each course
        courses.forEach((course, index) => {
            // Create a link to the course details page
            const link = document.createElement('a');
            link.href = `course_details.html?id=${course.id}`;

            const card = document.createElement('div');
            card.className = 'card course' + (index + 1); // Add course color

            const h3 = document.createElement('h3');
            h3.textContent = course.courseName;

            card.appendChild(h3);
            link.appendChild(card);
            main.appendChild(link);
        });
    })
    .catch(error => console.error('Error:', error)); // Log any errors