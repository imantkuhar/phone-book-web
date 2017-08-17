$(document).ready(function () {

    $('.collapsible').collapsible();

    // List of user contacts
    var contacts = [];

    var contactFilter = document.querySelector('#contact_filter');
    var logoutBtn = document.querySelector('#logout');

    function setHeader1(xhr) {
        xhr.setRequestHeader('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
    }

    function setHeader2(xhr) {
        xhr.setRequestHeader('Authorization', `${localStorage.getItem('refresh_token')}`);
    }


    // get new access_token using refresh_token
    //
    // $.ajax({
    //     type: 'POST',
    //     dataType: 'json',
    //     contentType: 'application/x-www-form-urlencoded',
    //     url: getBaseUrl() + `/oauth/token?grant_type=refresh_token&refresh_token=refresh_token`,
    //     data: JSON.stringify(userData),
    //     success: function (data) {
    //         console.log(data);
    //         var access_token = data.access_token;
    //         var refresh_token = data.refresh_token;
    //         if (access_token) {
    //             localStorage.setItem('access_token', access_token);
    //             localStorage.setItem('refresh_token', refresh_token);
    //             window.location.href = getBaseUrl() + '/phone-book.html';
    //         } else {
    //             var errorResponse = 'Something went wrong';
    //             alertify.error(errorResponse);
    //         }
    //     }, error: function (xhr, str) {
    //         console.log(xhr)
    //         var errorResponse = JSON.parse(xhr.responseText).error_description;
    //         alertify.error(errorResponse);
    //     },
    //     beforeSend: setHeader2
    // });


    $.ajax({
        type: 'GET',
        url: getBaseUrl() + '/contacts',
        success: function (data) {
            // If token is correct show contact page and generate contact items
            $(document.body).show();
            contacts = data;
            contacts.forEach(function (contact) {
                generateContactItem(contact);
            })
        },
        error: function (xhr, str) {
            //If token is incorrect redirect user to login page
            window.location.href = getBaseUrl() + '/login.html';
        },
        beforeSend: setHeader1
    });


    contacts.forEach(function (contact) {
        generateContactItem(contact);
    })

    contactFilter.addEventListener('keyup', function (event) {
        filterContacts(event.target.value);
    })

    function filterContacts(value) {
        document.querySelector('.phone-book-list').innerHTML = '';
        var filteredContacts = contacts.filter(function (contact) {
            var fullName = `${contact.name} ${contact.surname}`;
            // Condition for filtering contacts
            return contact.mobile_phone.indexOf(value) !== -1 || contact.name.indexOf(value) !== -1 || contact.surname.indexOf(value) !== -1 || fullName.indexOf(value) !== -1;
        })
        // Re-rendering contact list after filtering
        filteredContacts.forEach(function (contact) {
            generateContactItem(contact);
        })
    }

    function editContactInfo(target) {
        // Getting contact id from data-edit attribute
        var contactId = $(target).data('edit')
        var inputFields = $(`[data-info=${contactId}] input`);
        var saveButton = $(`[data-save='${contactId}']`);
        saveButton.prop('disabled', false);
        // Making edited contact fields enabled after saving
        inputFields.each(function () {
            $(this).prop('disabled', false);
        });
    }

    function saveContactInfo(target) {
        // Getting user id from data-save attribute
        var contactId = $(target).data('save');
        var contactData = {
            "id": contactId,
            "name": $('#contact-item-' + contactId + ' #name').val().trim(),
            "surname": $('#contact-item-' + contactId + ' #surname').val().trim(),
            "mobile_phone": $('#contact-item-' + contactId + ' #mobile_phone').val().trim(),
            "home_phone": $('#contact-item-' + contactId + ' #home_phone').val().trim(),
            "address": $('#contact-item-' + contactId + ' #address').val().trim(),
            "email": $('#contact-item-' + contactId + ' #email').val().trim()
        };
        $.ajax({
            contentType: 'application/json',
            type: 'PUT',
            dataType: 'json',
            url: getBaseUrl() + '/contacts',
            data: JSON.stringify(contactData),
            success: function (data) {
                console.log(data);
                // Making edited contact fields disabled after saving
                var inputFields = $(`[data-info=${contactId}] input`);
                var saveButton = $(`[data-save='${contactId}']`);
                saveButton.prop('disabled', 'disabled');
                inputFields.each(function () {
                    $(this).prop('disabled', true);
                });
                alertify.success('Contact was updated!')
            }, error: function (xhr, str) {
                var errorResponse = JSON.parse(xhr.responseText).description;
                alertify.error(errorResponse);
            },
            beforeSend: setHeader1
        });
    }

    function addNewContact() {
        var newContactWrapper = document.querySelector('.added-contact-info');
        newContactWrapper.innerHTML = `
            <div class="card-panel">
                ${createForm({}, false, true)}
                <div class="row interaction-buttons">
                    <button class="btn save-new-contact">Add
                    </button>
                    <button class="btn red lighten-1 cancel-adding">Cancel
                    </button>
                </div>
            </div>
    `;
    }

    function saveNewContact() {
        var contactData = {
            "name": $('.new-contact #name').val().trim(),
            "surname": $('.new-contact #surname').val().trim(),
            "mobile_phone": $('.new-contact #mobile_phone').val().trim(),
            "home_phone": $('.new-contact #home_phone').val().trim(),
            "address": $('.new-contact #address').val().trim(),
            "email": $('.new-contact #email').val().trim()
        };
        $.ajax({
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            url: getBaseUrl() + '/contacts',
            data: JSON.stringify(contactData),
            success: function (data) {
                // Clearing new contact form
                $('.added-contact-info').addClass('hidden');
                $('.added-contact-info').html('');
                contacts.push(data);
                generateContactItem(data);
                alertify.success('Contact was saved!')
            }, error: function (xhr, str) {
                var errorResponse = JSON.parse(xhr.responseText).description;
                alertify.error(errorResponse);
            },
            beforeSend: setHeader1
        });

    }

    // Function for creating contact form depending on is it new

    function createForm(contact, disabled, newContactForm) {
        disabled = disabled ? 'disabled' : '';
        newContactForm = newContactForm ? 'new-contact' : '';
        return `
            <form class="contact-form ${newContactForm}">
                <div class="input-field col s12">
                    <input id="name" name="name" type="text" ${disabled} value=${contact.name || ""}>
                    <label for="name">Name</label>
                </div>
                <div class="input-field col s12">
                    <input id="surname" name="surname" type="text" ${disabled} value=${contact.surname || ""}>
                    <label for="surname">Surname</label>
                </div>
                <div class="input-field col s12">
                    <input id="mobile_phone" name="mobile_phone" type="text" ${disabled} value=${contact.mobile_phone || ""}>
                    <label for="mobile_phone">Mobile Phone</label>
                </div>
                <div class="input-field col s12">
                    <input id="home_phone" name="home_phone" type="text" ${disabled} value=${contact.home_phone || ""}>
                    <label for="home_phone">Home Phone</label>
                </div>
                <div class="input-field col s12">
                    <input id="address" name="address" type="text" ${disabled} value=${contact.address || ""}>
                    <label for="address">Address</label>
                </div>
                <div class="input-field col s12">
                    <input id="email" name="email" type="email" ${disabled} value=${contact.email || ""}>
                    <label for="email">E-mail</label>
                </div>
            </form>`
    }

    // Function for generating contact list

    function generateContactItem(contact) {
        var phoneBookList = document.querySelector('.phone-book-list');
        var contactListItem = document.createElement('li');
        contactListItem.className = 'contact';
        contactListItem.id = `contact-item-${contact.id}`;
        contactListItem.innerHTML = `
            <div class="collapsible-header">
                <i class="material-icons">phone</i>
                ${contact.name} ${contact.surname}
                <div class="delete-contact" data-delete="${contact.id}"></div>
            </div>
            <div class="collapsible-body" data-info="${contact.id}">
                    ${createForm(contact, true, false)}
                    <div class="row interaction-buttons">
                        <button class="btn edit-contact-info" data-edit="${contact.id}">Edit
                            <i class="material-icons right">edit</i>
                        </button>
                        <button class="btn save-contact-info" data-save="${contact.id}" disabled>Save
                            <i class="material-icons right">save</i>
                        </button>
                    </div>
            </div>`;
        phoneBookList.appendChild(contactListItem);
        Materialize.updateTextFields();
    }


    // Entering contact edit mode

    $('.phone-book-list').on('click', '.edit-contact-info', function (event) {
        editContactInfo(event.target);
    });

    // Saving existing contact to the database

    $('.phone-book-list').on('click', '.save-contact-info', function (event) {
        saveContactInfo(event.target);
    });

    // Listener for adding new contact

    $('.phone-book-activity').on('click', '.add-contact-button', function () {
        $('.added-contact-info').removeClass('hidden');
        addNewContact();
    });

    $('.added-contact-info').on('click', '.cancel-adding', function () {
        $('.added-contact-info').addClass('hidden');
        $('.added-contact-info').html('');
    })

    // Saving new contact to the database

    $('.added-contact-info').on('click', '.save-new-contact', function (event) {
        saveNewContact();
    })

    // Deleting contact functionality

    $('.phone-book-list').on('click', '.delete-contact', function (event) {
        event.stopPropagation();
        var contactId = $(event.target).data('delete');
        alertify.confirm('Do you really want to delete this contact?', function () {
            $.ajax({
                contentType: 'application/json',
                type: 'DELETE',
                dataType: 'json',
                url: getBaseUrl() + 'contacts',
                data: JSON.stringify(contacts[contactId]),
                success: function (data) {
                    console.log(data);
                    // Removing contact from existing array
                    for (var i = 0; i < contacts.length; i++) {
                        if (contacts[i].id === contactId) {
                            contacts.splice(i, 1);
                        }
                    }
                    var deletedContact = $('#contact-item-' + contactId);
                    deletedContact.remove();
                    alertify.success('Contact was deleted!');
                }, error: function (xhr, str) {
                    console.log(contacts[contactId]);
                    console.log(xhr);
                    var errorResponse = JSON.parse(xhr.responseText).description;
                    alertify.error(errorResponse);
                },
                beforeSend: setHeader1
            });
        }, function () {
            console.log('Cancel');
        });
    })

    // Logout functionality
    // logoutBtn.on('click' , function(event){
    //     e.preventDefault();
    //     localStorage.removeItem('access_token');
    //     localStorage.removeItem('refresh_token');
    //     window.location.href = getBaseUrl() + '/login.html';
    // })

});

var getBaseUrl = function () {
    return 'http://localhost:8010';
}

