package com.cooksdev.service;

import com.cooksdev.data.entity.Contact;
import com.cooksdev.data.entity.User;
import com.cooksdev.data.repository.ContactRepository;
import com.cooksdev.data.repository.UserRepository;
import com.cooksdev.service.dto.ContactDto;
import com.cooksdev.service.util.transformer.ContactTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactTransformer transformer;

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = transformer.convertToEntity(contactDto);
        return transformer.convertToDto(contactRepository.save(contact));
    }

    @Override
    public void updateContact(ContactDto contactDto) {

    }

    @Override
    public void deleteContact(ContactDto contactDto) {

    }

    @Override
    public void getContact(ContactDto contactDto) {

    }

    @Override
    public List<ContactDto> getAllContactsByUserId(Integer id) {
        List<ContactDto> contactDtos = transformer.convertToDto(contactRepository.getContactsByUserId(id));
        return contactDtos;
    }
}