package com.cooksdev.web.controller;

import com.cooksdev.web.AbstractWebApiIntegrationTest;
import com.cooksdev.web.TestDataHelper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DatabaseSetup({"/dataset/users_init.xml"})
@DatabaseTearDown(value = "/dataset/clean.xml")
public class OAuthControllerTest extends AbstractWebApiIntegrationTest {

    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTHORIZATION_VALUE = "Basic d2ViY2xpZW50Og==";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
    private static final String GRANT_TYPE  = "grant_type";
    private static final String GRANT_TYPE_VALUE  = "password";
    private static final String USERNAME  = "username";
    private static final String PASSWORD  = "password";

    @Test
    @DatabaseSetup(value = "/dataset/users_init.xml")
    public void authorizationTest() throws Exception{
        mockMvc.perform(post("/oauth/token")
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
                .andExpect(jsonPath("jti", anything()));
    }

    @Test
    @DatabaseSetup(value = "/dataset/users_init.xml")
    public void authorizationTestById() throws Exception {
        mockMvc.perform(post("/oauth/token")
                .header(AUTHORIZATION, AUTHORIZATION_VALUE)
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .param(GRANT_TYPE, GRANT_TYPE_VALUE)
                .param(USERNAME, TestDataHelper.USER_ID)
                .param(PASSWORD, TestDataHelper.USER_PWD))
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token", anything()))
                .andExpect(jsonPath("refresh_token", anything()))
                .andExpect(jsonPath("token_type", is("bearer")))
                .andExpect(jsonPath("expires_in", anything()))
                .andExpect(jsonPath("scope", anything()))
                .andExpect(jsonPath("jti", anything()));
    }

    @Test
    @DatabaseSetup(value = "/dataset/users_init.xml")
    public void authorizationBadCredentialsTest() throws Exception{
        mockMvc.perform(post("/oauth/token")
                .header(AUTHORIZATION, AUTHORIZATION_VALUE)
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .param(GRANT_TYPE, GRANT_TYPE_VALUE)
                .param(USERNAME, "bad credentials")
                .param(PASSWORD, "password"))
                .andExpect(status().is4xxClientError());
    }
}
