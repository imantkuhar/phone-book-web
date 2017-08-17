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
        url: getBaseUrl() + `/oauth/token?grant_type=password&username=${userData["login"]}&password=${userData["password"]}`,
        data: JSON.stringify(userData),
        success: function (data) {
            console.log(data);
            var access_token = data.access_token;
            var refresh_token = data.refresh_token;
            if (access_token) {
                localStorage.setItem('access_token', access_token);
                localStorage.setItem('refresh_token', refresh_token);
                window.location.href = getBaseUrl() + '/phone-book.html';
            } else {
                var errorResponse = 'Something went wrong';
                alertify.error(errorResponse);
            }
        }, error: function (xhr, str) {
            console.log(xhr)
            var errorResponse = JSON.parse(xhr.responseText).error_description;
            alertify.error(errorResponse);
        },
        beforeSend: setHeader
    });
});

var getBaseUrl = function () {
    return 'http://localhost:8010';
}