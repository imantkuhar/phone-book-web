package com.cooksdev.web.controller;

import com.cooksdev.web.dto.ContactDto;
import com.cooksdev.web.service.ContactRestService;
import com.cooksdev.web.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactControllers {

    @Autowired
    private ContactRestService contactRestService;

    @RequestMapping(method = RequestMethod.POST)
    public ContactDto addContact(@RequestBody ContactDto contactDto) {
        return contactRestService.addContact(contactDto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteContact(@RequestBody ContactDto contactDto) {
        contactRestService.deleteContact(contactDto);
        return JsonUtils.toSimpleJson("status", "DELETED");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ContactDto update(@RequestBody ContactDto contactDto) {
        return contactRestService.updateContact(contactDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ContactDto> getContacts() {
        return contactRestService.getContacts();
    }

}
