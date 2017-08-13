package com.cooksdev.web.controller;

import com.cooksdev.service.dto.ContactDto;
import com.cooksdev.web.security.AuthService;
import com.cooksdev.web.service.ContactRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ContactControllers {

    @Autowired
    private ContactRestService contactRestService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public List<ContactDto> getAllContacts() {
        Integer id = authService.getUser().getId();
        return contactRestService.getAllContactsByUserId(id);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public ContactDto addContact(@RequestBody ContactDto contactDto) {
        contactDto.setUserId(authService.getUser().getId());
        return contactRestService.addContact(contactDto);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
    public boolean deleteContact(@PathVariable("id") String id) {
        return contactRestService.deleteContact(Integer.parseInt(id));
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
    public ContactDto getById(@PathVariable("id") String id) {
        return contactRestService.getContact(Integer.parseInt(id));
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
    public ContactDto update(@RequestBody ContactDto contactDto, @PathVariable("id") String id) {
        contactDto.setId(Integer.parseInt(id));
        return contactRestService.updateContact(contactDto);
    }
}
