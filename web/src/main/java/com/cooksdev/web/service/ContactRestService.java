package com.cooksdev.web.service;

import com.cooksdev.service.service.ContactService;
import com.cooksdev.service.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cooksdev.service.util.ValidationUtils.rejectIfIdIsNotValid;
import static com.cooksdev.service.util.ValidationUtils.validateContactDto;

@Service
public class ContactRestService {

    @Autowired
    private ContactService contactService;

    public ContactDto addContact(ContactDto contactDto) {
        validateContactDto(contactDto);
        return contactService.addContact(contactDto);
    }

    public ContactDto updateContact(ContactDto contactDto) {
        validateContactDto(contactDto);
        return contactService.updateContact(contactDto);
    }

    public boolean deleteContact(Integer id) {
        rejectIfIdIsNotValid(id);
        return contactService.deleteContact(id);
    }

    public ContactDto getContact(Integer id) {
        rejectIfIdIsNotValid(id);
        return contactService.getContact(id);
    }

    public List<ContactDto> getAllContactsByUserId(Integer id) {
        rejectIfIdIsNotValid(id);
        return contactService.getAllContactsByUserId(id);
    }
}

