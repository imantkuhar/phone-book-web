package com.cooksdev.web.service;

import com.cooksdev.service.ContactService;
import com.cooksdev.service.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactRestService {

    @Autowired
    private ContactService contactService;

    public ContactDto add(ContactDto contactDto) {
        return contactService.addContact(contactDto);
    }

    public void update(ContactDto contactDto) {
    }

    public void delete(ContactDto contactDto) {
    }

    public void getContact(ContactDto contactDto) {
    }

    public List<ContactDto> getAllContactsByUserId(Integer id) {
        return contactService.getAllContactsByUserId(id);
    }
}
