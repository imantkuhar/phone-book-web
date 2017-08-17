package com.cooksdev.service.service;

import com.cooksdev.data.entity.Contact;

import java.util.List;

public interface ContactService {

    Contact addContact (Contact contact);
    Contact updateContact (Contact contact);
    void deleteContact(Contact contact);
    List<Contact> getContacts();
}
