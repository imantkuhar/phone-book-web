package com.cooksdev.service.service;

import com.cooksdev.service.dto.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto addContact (ContactDto contactDto);
    ContactDto updateContact (ContactDto contactDto);
    boolean deleteContact(Integer id);
    ContactDto getContact(Integer id);
    List<ContactDto> getAllContactsByUserId(Integer userId);
}
