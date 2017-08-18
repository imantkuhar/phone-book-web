package com.cooksdev.web.controller;

import com.cooksdev.web.AbstractWebApiIntegrationTest;
import com.cooksdev.web.AuthDto;
import com.cooksdev.web.TestDataHelper;
import com.cooksdev.web.TestWebHelper;
import com.cooksdev.web.dto.ContactDto;
import com.cooksdev.web.util.JsonUtils;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DatabaseSetup({"/dataset/users_init.xml", "/dataset/contacts_init.xml"})
@DatabaseTearDown(value = "/dataset/clean.xml")
public class ContactControllersTest extends AbstractWebApiIntegrationTest {

    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTHORIZATION_VALUE = "Basic d2ViY2xpZW50Og==";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
    private static final String GRANT_TYPE = "grant_type";
    private static final String GRANT_TYPE_VALUE = "password";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final Integer ID = 1;
    private static final String NAME = "contactName";
    private static final String SURNAME = "contactSurname";
    private static final String MOBILE_PHONE = "1234567890";
    private static final String HOME_PHONE = "123456";
    private static final String ADDRESS = "contactAddress";
    private static final String EMAIL = "test@gmail.com";
    private static final Integer USER_ID = 1;
    private static final String UPDATED_NAME = "updatedName";
    private static final String UPDATED_SURNAME = "updatedSurname";

    private AuthDto authDto;
    private ContactDto contactDto;
    private String newAuthorizationValue;

    @Before
    public void createContactDto() {
        contactDto = new ContactDto();
        contactDto.setId(ID);
        contactDto.setName(NAME);
        contactDto.setSurname(SURNAME);
        contactDto.setMobilePhone(MOBILE_PHONE);
        contactDto.setHomePhone(HOME_PHONE);
        contactDto.setAddress(ADDRESS);
        contactDto.setEmail(EMAIL);
        contactDto.setUserId(USER_ID);
    }

    @Before
    public void authorizationTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/oauth/token")
                .header(AUTHORIZATION, AUTHORIZATION_VALUE)
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .param(GRANT_TYPE, GRANT_TYPE_VALUE)
                .param(USERNAME, TestDataHelper.USER_LOGIN)
                .param(PASSWORD, TestDataHelper.USER_PWD))
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token", anything()))
                .andExpect(jsonPath("refresh_token", anything()))
                .andExpect(jsonPath("token_type", is("bearer")))
                .andExpect(jsonPath("expires_in", anything()))
                .andExpect(jsonPath("scope", anything()))
                .andExpect(jsonPath("jti", anything()))
                .andReturn();

        String authJson = mvcResult.getResponse().getContentAsString();
        authDto = JsonUtils.fromJson(authJson, AuthDto.class);
        newAuthorizationValue = "Bearer " + authDto.getAccessToken();
    }

    @Test
    public void addContact() throws Exception {
        mockMvc.perform(post("/contacts")
                .header(AUTHORIZATION, newAuthorizationValue)
                .contentType(TestWebHelper.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJson(contactDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(NAME)))
                .andExpect(jsonPath("surname", is(SURNAME)));
    }

    @Test
    public void deleteContact() throws Exception {
        mockMvc.perform(delete("/contacts")
                .header(AUTHORIZATION, newAuthorizationValue)
                .contentType(TestWebHelper.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJson(contactDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        contactDto.setName(UPDATED_NAME);
        contactDto.setSurname(UPDATED_SURNAME);
        mockMvc.perform(put("/contacts")
                .header(AUTHORIZATION, newAuthorizationValue)
                .contentType(TestWebHelper.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJson(contactDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(contactDto.getId())))
                .andExpect(jsonPath("name", is(UPDATED_NAME)))
                .andExpect(jsonPath("surname", is(UPDATED_SURNAME)));
    }

    @Test
    public void getContacts() throws Exception {
        mockMvc.perform(get("/contacts")
                .header(AUTHORIZATION, newAuthorizationValue)
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE))
                .andExpect(status().isOk());
    }
}