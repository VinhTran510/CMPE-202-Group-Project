// Function to load all courses from the backend and update the UI
function loadCourses() {
    fetch('/courses/current')
        .then(response => response.json())
        .then(courses => {
            updateCourseLists(courses);
        })
        .catch(error => console.error('Error loading courses:', error));
}

// Helper function to update the UI with course data
function updateCourseLists(courses) {
    const listAll = document.getElementById('list-all');
    const listPublished = document.getElementById('list-published');
    const listUnpublished = document.getElementById('list-unpublished');

    listAll.innerHTML = ''; // Clear existing lists
    listPublished.innerHTML = '';
    listUnpublished.innerHTML = '';

    courses.forEach(course => {
        const listItem = createListItem(course, 'all'); // Always add to "All" list
        if (course.published) {
            listPublished.appendChild(listItem);
        } else {
            listUnpublished.appendChild(listItem);
        }
        listAll.appendChild(listItem.cloneNode(true)); // Add to the "All" list
    });
}

// Function to create a list item for a course
function createListItem(course, listType) {
    const item = document.createElement('li');
    item.className = `list-group-item ${course.published ? '' : 'unpublished'}`;

    let dropdownContent = `<div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button"
            id="dropdownMenuButton${course.id}"
            data-bs-toggle="dropdown" 
            aria-expanded="false">
            &#8230;
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton${course.id}">`;

    if (listType === 'all' || listType === 'unpublished') {
        dropdownContent += `<li><a class="dropdown-item" href="#" onclick="toggleCoursePublish(${course.id}, true)">Publish</a></li>`;
    }
    if (listType === 'all' || listType === 'published') {
        dropdownContent += `<li><a class="dropdown-item" href="#" onclick="toggleCoursePublish(${course.id}, false)">Unpublish</a></li>`;
    }

    dropdownContent += `</ul></div>`;
    item.innerHTML = `${course.semester} - ${course.courseName} ${dropdownContent}`; // Updated to have semester first
    return item;
}



// Function to toggle the publish status of a course
function toggleCoursePublish(courseId, publish) {
    const endpoint = publish ? `/courses/publish/${courseId}` : `/courses/unpublish/${courseId}`;

    fetch(endpoint, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
        .then(response => {
            if (response.ok) {
                loadCourses();  // Reload the list to reflect changes
            } else {
                throw new Error('Failed to update course status');
            }
        })
        .catch(error => console.error('Error updating course:', error));
}



// Event listener to load courses when the document is ready
document.addEventListener('DOMContentLoaded', loadCourses);
