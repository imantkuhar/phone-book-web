package com.cooksdev.service.service;

import com.cooksdev.data.entity.Contact;
import com.cooksdev.data.repository.ContactRepository;
import com.cooksdev.service.dto.ContactDto;
import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.NotFoundException;
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
        Contact savedContact = contactRepository.save(contact);
        if (savedContact == null) {
            throw new NotFoundException(ErrorReason.ENTITY_WAS_NOT_ADDED, "ContactDto", contactDto);
        }
        return transformer.convertToDto(savedContact);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        Contact contact = transformer.convertToEntity(contactDto);
        Contact updatedContact = contactRepository.save(contact);
        if (updatedContact == null) {
            throw new NotFoundException(ErrorReason.ENTITY_WAS_NOT_UPDATED, "ContactDto", contactDto);
        }
        return transformer.convertToDto(updatedContact);
    }

    @Override
    public boolean deleteContact(Integer id) {
        contactRepository.delete(id);
        Contact contact = contactRepository.findOne(id);
        if (contact != null){
            throw new NotFoundException(ErrorReason.ENTITY_WAS_NOT_DELETED, "ContactDto", id);
        }
        return true;
    }

    @Override
    public ContactDto getContact(Integer id) {
        Contact contact = contactRepository.findOne(id);
        if (contact == null) {
            throw new NotFoundException(ErrorReason.ENTITY_WAS_NOT_FOUND, "ContactDto", id);
        }
        return transformer.convertToDto(contact);
    }

    @Override
    public List<ContactDto> getAllContactsByUserId(Integer id) {
        return transformer.convertToDto(contactRepository.getContactsByUserId(id));
    }
}