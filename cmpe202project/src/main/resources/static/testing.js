fetch('http://localhost:8080/assignments/1')
    .then(response => response.json())
    .then((work) => {
        console.log('Fetched courses data:', work); // Log fetched courses data

    });;