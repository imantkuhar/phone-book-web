var loginForm = $('.login-form');

function setHeader(xhr) {
    xhr.setRequestHeader('Authorization', 'Basic d2ViY2xpZW50Og==');
}

loginForm.submit(function (event) {
    event.preventDefault();
    var userData = {
        "login": $('#login').val().trim(),
        "password": $('#password').val().trim()
    };
    $.ajax({
        type: 'POST',
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded',
        url: `http://localhost:8010/oauth/token?grant_type=password&username=${userData["login"]}&password=${userData["password"]}`,
        data: JSON.stringify(userData),
        success: function (data) {
            console.log(data)
            var token = data.access_token;
            if (token) {
                localStorage.setItem('token', token);
                window.location.href = 'http://localhost:8010/phone-book.html';
            } else {
                console.log('Something went wrong');
            }
        }, error: function (xhr, str) {
            console.log(xhr)
            var errorResponse = JSON.parse(xhr.responseText).error_description;
            alertify.error(errorResponse);
        },
        beforeSend: setHeader
    });
});