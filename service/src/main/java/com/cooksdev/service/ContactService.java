package com.cooksdev.service;

import com.cooksdev.data.entity.Contact;
import com.cooksdev.service.dto.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto addContact (ContactDto contactDto);
    void updateContact (ContactDto contactDto);
    void deleteContact(ContactDto contactDto);
    void getContact(ContactDto contactDto);
    List<ContactDto> getAllContactsByUserId(Integer userId);
}
