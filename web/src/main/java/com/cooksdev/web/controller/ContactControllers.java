package com.cooksdev.web.controller;

import com.cooksdev.service.dto.ContactDto;
import com.cooksdev.web.security.AuthService;
import com.cooksdev.web.service.ContactRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ContactDto add(@RequestBody ContactDto contactDto) {
        contactDto.setUserId(authService.getUser().getId());
        return contactRestService.add(contactDto);
    }
}
