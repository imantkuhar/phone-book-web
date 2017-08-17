var registrationForm = $('.registration-form');
registrationForm.submit(function (event) {
    event.preventDefault();
    var userData = {
        "login": $('#login').val().trim(),
        "password": $('#password').val().trim(),
        "full_name": $('#full_name').val().trim()
    };

    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json',
        url: 'http://localhost:8010/user',
        data: JSON.stringify(userData),
        success: function (data) {
            console.log(data);
            window.location.href = 'http://localhost:8010/login.html';
        }, error: function (xhr, str) {
            console.log(xhr)
            var errorResponse = JSON.parse(xhr.responseText).description;
            alertify.error(errorResponse);
        },
    });
});


