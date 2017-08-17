$(document).ready(function () {

    $('.collapsible').collapsible();
    var contacts = [];

    function setHeader(xhr) {
        xhr.setRequestHeader('Authorization', `Bearer ${localStorage.getItem('token')}`);
    }

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8010/contacts',
        success: function (data) {
            contacts = data;
            contacts.forEach(function (contact) {
                generatePhoneBookItem(contact);
            })
        },
        error: function (xhr, str) {
            console.log(xhr)
            window.location.href = 'http://localhost:8010/login.html';
        },
        beforeSend: setHeader
    });


    contacts.forEach(function (contact) {
        generatePhoneBookItem(contact);
    })

    var contactFilter = document.querySelector('#contact_filter');

    contactFilter.addEventListener('keyup', function (event) {
        filterContacts(event.target.value);
    })

    function filterContacts(value) {
        document.querySelector('.phone-book-list').innerHTML = '';
        var filteredContacts = contacts.filter(function (contact) {
            var fullName = `${contact.name} ${contact.surname}`;
            return contact.mobile_phone.indexOf(value) !== -1 || contact.name.indexOf(value) !== -1 || contact.surname.indexOf(value) !== -1 || fullName.indexOf(value) !== -1;
        })
        filteredContacts.forEach(function (contact) {
            generatePhoneBookItem(contact);
        })
    }

    function editContactInfo(target) {
        var contactID = $(target).data('edit')
        var inputFields = $(`[data-info=${contactID}] input`);
        inputFields.each(function () {
            $(this).prop('disabled', false);
        });
    }

    function saveContactInfo(target) {
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
            url: 'http://localhost:8010/contacts',
            data: JSON.stringify(contactData),
            success: function (data) {
                var inputFields = $(`[data-info=${contactId}] input`);
                inputFields.each(function () {
                    $(this).prop('disabled', true);
                });
                alertify.success('Contact was updated!')
            }, error: function (xhr, str) {
                console.log(xhr)
                var errorResponse = JSON.parse(xhr.responseText).description;
                alertify.error(errorResponse);
            },
            beforeSend: setHeader
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
            url: 'http://localhost:8010/contacts',
            data: JSON.stringify(contactData),
            success: function (data) {
                console.log(data, 'data');
                contacts.push(data);
                generatePhoneBookItem(data);
            }, error: function (xhr, str) {
                console.log(xhr)
                var errorResponse = JSON.parse(xhr.responseText).description;
                alertify.error(errorResponse);
            },
            beforeSend: setHeader
        });

    }

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


    function generatePhoneBookItem(contact) {
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
                        <button class="btn save-contact-info" data-save="${contact.id}">Save
                            <i class="material-icons right">save</i>
                        </button>
                    </div>
            </div>`;
        phoneBookList.appendChild(contactListItem);
        Materialize.updateTextFields();
    }

    $('.phone-book-list').on('click', '.edit-contact-info', function (event) {
        editContactInfo(event.target);
    });

    $('.phone-book-list').on('click', '.save-contact-info', function (event) {
        saveContactInfo(event.target);
    });

    $('.phone-book-activity').on('click', '.add-contact-button', function () {
        $('.added-contact-info').removeClass('hidden');
        addNewContact();
    });

    $('.added-contact-info').on('click', '.cancel-adding', function () {
        $('.added-contact-info').addClass('hidden');
        $('.added-contact-info').html('');
    })


    $('.added-contact-info').on('click', '.save-new-contact', function (event) {
        saveNewContact();
        $('.added-contact-info').addClass('hidden');
        $('.added-contact-info').html('');
    })

    $('.phone-book-list').on('click', '.delete-contact', function (event) {
        event.stopPropagation();
        var contactId = $(event.target).data('delete');
        alertify.confirm('Do you really want to delete this contact?', function () {
            $.ajax({
                contentType: 'application/json',
                type: 'DELETE',
                dataType: 'json',
                url: getBaseUrl() +'/contacts',
                success: function (data) {
                    alertify.success('Contact was deleted!');
                    var deletedContact = $('#contact-item-' + contactId);
                    deletedContact.remove();
                }, error: function (xhr, str) {
                    console.log(xhr)
                    var errorResponse = JSON.parse(xhr.responseText).description;
                    alertify.error(errorResponse);
                },
                beforeSend: setHeader
            });
        }, function () {
            console.log('Cancel');
        });
    })

});

var getBaseUrl = function () {
    return 'http://localhost:8010';
}