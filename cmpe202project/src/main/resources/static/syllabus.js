document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('syllabusForm');
    const responseArea = document.getElementById('responseArea');

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        const syllabusData = {
            firstname: form.firstname.value,
            lastname: form.lastname.value,
            title: form.title.value,
            code: form.code.value,
            semester: form.semester.value,
            officeLocation: form.officeLocation.value,
            officeHours: form.officeHours.value,
            classDaysTime: form.classDaysTime.value,
            classroom: form.classroom.value,
            syllabusContent: form.syllabusContent.value
        };

        fetch('http://localhost:8080/api/syllabus/', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(syllabusData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                responseArea.innerHTML = `
                <h1>Syllabus Created Successfully</h1>
                <h2>${data.title}</h2>
                <p>Instructor Name: ${data.firstname} ${data.lastname}</p>
                <p>Code: ${data.code}</p>
                <p>Semester: ${data.semester}</p>
                <p>Office Location: ${data.officeLocation}</p>
                <p>Office Hours: ${data.officeHours}</p>
                <p>Class Days/Time: ${data.classDaysTime}</p>
                <p>Classroom: ${data.classroom}</p>
                <p>Syllabus Content:</p>
                <pre>${data.syllabusContent}</pre>
                <button onclick="location.reload();">Create Another Syllabus</button>
            `;
                form.style.display = 'none'; // Hide the form
            })
            .catch(error => {
                console.error('Error:', error);
                alert(`Failed to create syllabus: ${error.message}`);
            });
    });
});
