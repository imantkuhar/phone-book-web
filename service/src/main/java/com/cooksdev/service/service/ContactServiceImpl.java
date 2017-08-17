package com.cooksdev.service.service;

import com.cooksdev.data.entity.Contact;
import com.cooksdev.data.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired private AuthService authService;

    @Override
    public Contact addContact(Contact contact) {
       return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> getContacts() {
        Integer userId = authService.getUserId();
        return contactRepository.findContactByUserId(userId);
    }
}