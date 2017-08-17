package com.cooksdev.web.service;

import com.cooksdev.data.entity.Contact;
import com.cooksdev.service.service.ContactService;
import com.cooksdev.web.dto.ContactDto;
import com.cooksdev.web.util.transformer.ContactTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.cooksdev.web.util.ValidationUtils.validateContactDto;

@Service
public class ContactRestService {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactTransformer contactTransf;

    public ContactDto addContact(ContactDto contactDto) {
        validateContactDto(contactDto);
        Contact newContact = contactTransf.convertToEntity(contactDto);
        Contact addedContact = contactService.addContact(newContact);
        return contactTransf.convertToDto(addedContact);
    }

    public ContactDto updateContact(ContactDto contactDto) {
        validateContactDto(contactDto);
        Contact contact = contactTransf.convertToEntity(contactDto);
        Contact updatedContact  = contactService.updateContact(contact);
        return contactTransf.convertToDto(updatedContact);
    }

    public void deleteContact(ContactDto contactDto) {
        Contact contact = contactTransf.convertToEntity(contactDto);
        contactService.deleteContact(contact);
    }

    public List<ContactDto> getContacts() {
        List<Contact> contacts = contactService.getContacts();
        return contactTransf.convertToDto(contacts);
    }
}

