const urlParams = new URLSearchParams(window.location.search);
const courseId = urlParams.get('id');

console.log(courseId);

let course;
fetch(`http://localhost:8080/courses/${courseId}`)
    .then(response => response.json())
    .then(data => {
        course = data;
        console.log('Fetched course data:', data); // Log fetched student data
        const courseName = document.createElement('h1');
        const facultyName = document.createElement('h2');
        courseName.textContent = `Welcome to ${course.courseName}`;
        facultyName.textContent = course.faculty;
        const courseContainer = document.getElementById('courseContainer');
        if (courseContainer) {
            courseContainer.appendChild(courseName);
            courseContainer.appendChild(facultyName);
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



