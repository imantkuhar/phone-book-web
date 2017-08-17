if(localStorage.getItem('access_token') !== null){
    window.location.href = getBaseUrl() + '/phone-book.html';
} else {
    $(document.body).show();
}

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
        url: getBaseUrl() +'/users',
        data: JSON.stringify(userData),
        success: function (data) {
            console.log(data);
            window.location.href = getBaseUrl() + '/login.html';
        }, error: function (xhr, str) {
            console.log(xhr)
            var errorResponse = JSON.parse(xhr.responseText).description;
            alertify.error(errorResponse);
        },
    });
});