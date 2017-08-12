package com.cooksdev.service.util.transformer;

import com.cooksdev.data.entity.Contact;
import com.cooksdev.data.entity.User;
import com.cooksdev.data.repository.UserRepository;
import com.cooksdev.service.dto.ContactDto;
import com.cooksdev.service.util.transformer.base.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactTransformer extends AbstractTransformer<Contact, ContactDto> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ContactDto convertToDto(Contact contact) {
        if (contact == null){
            return null;
        }

        return ContactDto.builder()
                .id(contact.getId())
                .name(contact.getName())
                .surname(contact.getSurname())
                .mobilePhone(contact.getMobilePhone())
                .homePhone(contact.getHomePhone())
                .address(contact.getAddress())
                .mail(contact.getMail())
                .userId(contact.getUser().getId())
                .build();
    }

    @Override
    public Contact convertToEntity(ContactDto contactDto) {
        if (contactDto == null){
            return null;
        }

        User user = userRepository.findOne(contactDto.getUserId());
        return Contact.builder()
                .name(contactDto.getName())
                .surname(contactDto.getSurname())
                .homePhone(contactDto.getHomePhone())
                .mobilePhone(contactDto.getMobilePhone())
                .address(contactDto.getAddress())
                .mail(contactDto.getMail())
                .user(user)
                .build();
    }
}
