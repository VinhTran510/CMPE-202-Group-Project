function fetchCourses(url, tabId) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tabContent = document.getElementById(tabId);
            const link = document.createElement('a');
            const courseList = data.map(course => `
                <li onclick="location.href='facultyCourse.html?id=${course.id}'" class="list-group-item">
                    ${course.semester}: ${course.courseName}
                </li>
            `).join('');
            tabContent.innerHTML = `<ul class="list-group mt-2">${courseList}</ul>`;
        })
        .catch(error => console.error('Error fetching courses:', error));
}

function searchCourses() {
    var input = document.getElementById('searchInput').value.toLowerCase();
    var tabs = ['nav-all', 'nav-current', 'nav-previous', 'nav-future'];
    tabs.forEach(tab => {
        var items = document.querySelector('#' + tab + ' .list-group').children;
        Array.from(items).forEach(item => {
            if (item.textContent.toLowerCase().includes(input)) {
                item.style.display = "";
            } else {
                item.style.display = "none";
            }
        });
    });
}

// Fetch courses for each tab when the page loads
window.onload = function() {
    fetchCourses('/api/homepageCourse', 'nav-all');
    fetchCourses('/api/homepageCourse/current', 'nav-current');
    fetchCourses('/api/homepageCourse/previous', 'nav-previous');
    fetchCourses('/api/homepageCourse/future', 'nav-future');  // Added line for future courses
};
