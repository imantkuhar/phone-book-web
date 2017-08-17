var navBar = $('#nav-mobile');

if(localStorage.getItem('access_token') !== null){
    // Replace login and registration links if user is logged in
    navBar.empty();
    navBar.append('<li><a href="phone-book.html" >Phone book</a></li>');
    navBar.append('<li><a href="#" id="logout-main">Logout</a></li>');
    navBar.show();
    navBar.on('click', '#logout-main', function(event){
        logout(event);
    });

    function logout(event){
        event.preventDefault();
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        window.location.href = getBaseUrl() + '/login.html';
    }
} else {
    //Simply show navbar if user is not logged in
    navBar.show();
};
