document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('courseForm');
    const responseArea = document.getElementById('responseArea');

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        var email = document.getElementById('email').value.toString();
//        console.log(email);

        fetch(`http://localhost:8080/faculty/email/${(email)}`)
            .then(response => response.json())
            .then(faculty => {
                console.log('Fetched faculty data:', faculty); // Log fetched course data
                const courseData = {
                    courseName: form.courseName.value,
                    facultyID: faculty.id,
                    semester: form.semester.value,
                    semesterID: 5,
                    isCurrent: false
                };

                fetch(`http://localhost:8080/courses/create`, {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(courseData)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    responseArea.innerHTML = `
                    <h1>Course Assigned Successfully</h1>
                    <h2>${data.courseName}</h2>
                    <p>Semester: ${data.semester}</p>
                    <p>Faculty ID: ${data.facultyID}</p>
                    <button onclick="location.reload();">Assign Another Course</button>
                `;
                    form.style.display = 'none'; // Hide the form
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert(`Failed to create course: ${error.message}`);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                alert(`Failed to create course: Faculty not found`);
            });
    });
});