document.addEventListener('DOMContentLoaded', function() {
    var quill = new Quill('#editor-container', {
        theme: 'snow'
    });

    const submitBtn = document.getElementById('submitBtn');
    const form = document.querySelector('form');
    const responseArea = document.getElementById('responseArea');

    submitBtn.addEventListener('click', function(e) {
        const formData = new FormData();
        formData.append('title', document.getElementById('title').value);
        formData.append('content', quill.root.innerHTML);
        formData.append('postTo', document.getElementById('postTo').value);
        const attachment = document.getElementById('attachment').files[0];
        if (attachment) {
            formData.append('attachment', attachment);
        }

        fetch('http://localhost:8080/api/announcements/', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                responseArea.innerHTML = `
                <div class="announcement-response">
                   
                    <h2>${data.title}</h2>
                    <p>Content:</p>
                    <div class="announcement-content">${data.content}</div>
                    <p>Posted To: ${data.postTo}</p>
                    <p>Attachment Path: ${data.attachmentPath || 'No attachment'}</p>
                    <button onclick="location.reload();">Post Another Announcement</button>
                </div>
            `;
                var formContainer = document.querySelector('.form-container');
                if (formContainer) {
                    formContainer.style.display = 'none';
                }
                window.scrollTo(0, 0); // Corrected: This line should be outside the if block.
                form.style.display = 'none'; // Optionally hide the form after successful submission

                // Center the announcement response box
                var announcementResponse = document.querySelector('.announcement-response');
                announcementResponse.style.display = 'flex';
                announcementResponse.style.alignItems = 'center';
                announcementResponse.style.justifyContent = 'center';
                announcementResponse.style.height = '100vh';
                announcementResponse.style.flexDirection = 'column';

                // Add some modern styling to the response box
                announcementResponse.style.background = 'rgba(255, 255, 255, 0.9)';
                announcementResponse.style.borderRadius = '15px';
                announcementResponse.style.boxShadow = '0 4px 6px rgba(0, 0, 0, 0.1), 0 1px 3px rgba(0, 0, 0, 0.08)';
                announcementResponse.style.padding = '20px';
                announcementResponse.style.maxWidth = '600px';
                announcementResponse.style.margin = 'auto';
                announcementResponse.style.overflow = 'auto'; // Enable scrolling if content exceeds box height
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to post announcement: ' + error.message);
            });
    });
});
