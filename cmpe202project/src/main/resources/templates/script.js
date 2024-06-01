    const searchForm = document.getElementById('searchForm');
    const searchResults = document.getElementById('searchResults');
    const facultyFirstN = document.getElementById('firstName');
    const facultyLastN = document.getElementById('lastName');
    const semesterInput = document.getElementById('semester');

    // Function to fetch and display all courses
    function fetchAndDisplayAllCourses() {
        fetch('http://localhost:8080/courses/all')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(courses => {
                console.log('Fetched course data:', courses); // Log fetched course data

                // Create a new card for each course
                courses.forEach((course, index) => {
                    const card = document.createElement('div');
                    card.className = 'card course' + (index + 1); // Add course color

                    const h3 = document.createElement('h3');
                    h3.textContent = course.courseName;
                    const h5 = document.createElement('h5');
                    h5.textContent = course.semester;

                    card.appendChild(h3);
                    card.appendChild(h5);
                    searchResults.appendChild(card);
                });
            })
            .catch(error => console.error('Error:', error));

    }

    function filterCourses() {
        fetch(`http://localhost:8080/faculty?firstName=${firstName}&lastName=${lastName}`)
                .then(response => response.json())
                .then(faculty => {
                    const facultyId = faculty ? faculty.id : null; // Extract faculty ID
                    // Fetch courses based on faculty ID and semester
                    return fetch(`http://localhost:8080/courses?facultyId=${facultyId}&semester=${semester}`);
                })
                .then(response => response.json())
                .then(data => displayCourses(data))
                .catch(error => console.error('Error fetching courses:', error));
    }

    // Display all courses when the page loads
    document.addEventListener('DOMContentLoaded', fetchAndDisplayAllCourses);



    // Function to display search results
    function displayCourses(courses) {
        searchResults.innerHTML = '';
        if (courses.length === 0) {
            searchResults.innerHTML = '<p>No courses found.</p>';
        } else {
            while (searchResults.firstChild) {
                searchResults.firstChild.remove();
            }
            courses.forEach((course, index) => {
                const card = document.createElement('div');
                card.className = 'card course' + (index + 1); // Add course color

                const h3 = document.createElement('h3');
                h3.textContent = course.courseName;
                const h5 = document.createElement('h5');
                h5.textContent = course.semester;
                const viewStudentsButton = document.createElement('button');
                viewStudentsButton.textContent = 'View Students';
                viewStudentsButton.addEventListener('click', () => showStudents(course.id, course.courseName));

                card.appendChild(h3);
                card.appendChild(h5);
                card.appendChild(viewStudentsButton);
                searchResults.appendChild(card);
            });
        }
    }