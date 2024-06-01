const urlParams = new URLSearchParams(window.location.search);
const courseId = urlParams.get('id');

console.log(courseId);

// ... rest of your fetch code ...
fetch(`http://localhost:8080/courses/${courseId}/assignments`)
    .then(response => {
        console.log(response);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(assignmentIds => {
        console.log('Fetched Assignment IDs:', assignmentIds); // Log fetched course IDs

        // Fetch the details for each course
        const coursePromises = assignmentIds.map(assignmentId =>
            fetch(`http://localhost:8080/assignments/${assignmentId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
        );

        return Promise.all(coursePromises);
    })
    .then(assignments => {
        console.log('Fetched assignment data:', assignments); // Log fetched course data

        // Get the main element
        const obj = document.querySelector('#listOfAssignments');

        // Remove all existing course cards
        while (obj.firstChild) {
            obj.firstChild.remove();
        }

        // Create a new card for each course
        assignments.forEach((assignment, index) => {
            // Create a link to the course details page
            const link = document.createElement('a');
            link.href = `assignment.html?id=${assignment.id}`;

            const card = document.createElement('h3');

            if(assignment.quiz){
                card.className = 'card design2';
                card.textContent = `Quiz: ${assignment.title}`;
            }
            else{
                card.className = 'card design1';
                card.textContent = assignment.title;
            }


            console.log(assignment.title);

            link.appendChild(card);
            obj.appendChild(link);
        });
    })
    .catch(error => console.error('Error:', error)); // Log any errors

function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

function setCourseId() {
    document.getElementById("courseId").setAttribute('value', courseId);
}

function handleClick(cb) {
    cb.value = cb.checked ? 1 : 0;
    console.log(cb.value);
}

function setAssignmentLink() {

    var a = document.getElementById('assignment');
    a.href = `assignments.html?id=${courseId}`
}

function setStudentLink() {
    var a = document.getElementById('student');
    a.href = `student_list.html?id=${courseId}`
}


