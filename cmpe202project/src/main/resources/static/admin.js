
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('searchForm');
    const searchResults = document.getElementById('searchResults');

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        var firstName = document.getElementById('firstName').value.toString();
        var lastName = document.getElementById('lastName').value.toString();
        var semester = document.getElementById('semester').value.toString();
        console.log(firstName, lastName, semester);

        var url1 = 'http://localhost:8080/faculty/filter?firstName=' + encodeURIComponent(firstName) + '&lastName=' + encodeURIComponent(lastName);
        fetch(url1)
            .then(response => response.json())
            .then(facultyData => {
                 var coursePromises = facultyData.map(faculty => {
                    var url2 = 'http://localhost:8080/courses/filter?facultyID=' + faculty.id + '&semester=' + encodeURIComponent(semester);
                    return fetch(url2)
                        .then(response => response.json());
                });
                return Promise.all(coursePromises);
            })
            .then (allCourses => {
              var mergedCourses = [].concat.apply([], allCourses);
              if (mergedCourses.length === 0) {
                  displayNotFound();
              } else {
                  displayCourses(mergedCourses);
              }
            })
            .catch(error => {
                alert(`No courses found: Check Information Entered`);
            });
    });
});

function displayNotFound() {
    while (searchResults.firstChild) {
        searchResults.firstChild.remove();
    }

    var notFoundElement = document.createElement('h4');
    notFoundElement.textContent = 'Not Found';
    searchResults.appendChild(notFoundElement);
}

function displayCourses(courses) {
    // Log faculties data in the console
    console.log("Fetched courses:", courses);

    // Remove all existing course cards
    while (searchResults.firstChild) {
        searchResults.firstChild.remove();
    }

    courses.forEach((course, index) => {
        // Create a link to the course details page
        const link = document.createElement('a');
        link.href = `studentList.html?id=${course.id}`;

        const card = document.createElement('div');
        card.className = 'card course' + (index + 1); // Add course color
//        card.textContent = course.facultyId;
        const h3 = document.createElement('h3');
        h3.textContent = course.courseName;
        const h4 = document.createElement('h4');
        h4.textContent = "Faculty ID" + course.facultyID;

        card.appendChild(h3);
        card.appendChild(h4);
        link.appendChild(card);
        searchResults.appendChild(link);
    });
}