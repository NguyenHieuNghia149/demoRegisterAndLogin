
// function này dùng để băt sk click khono load la trang
function sendCode() {
    const name = document.querySelector('input[name="name"]').value;
    const email = document.querySelector('input[name="email"]').value;

    if (!name || !email) {
        document.querySelector('p.message').innerText = 'Please enter your name and email before sending the verification code.';
        return;
    }

    fetch('do-register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            action: 'sendCode',
            name: name,
            email: email
        })
    })
        .then(response => response.text())
        .then(data => {
            // Hiển thị phản hồi từ servlet
            document.querySelector('p.message').innerText = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.querySelector('p.message').innerText = 'Error sending verification code. Please try again.';
        });
}


