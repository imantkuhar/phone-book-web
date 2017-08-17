package com.cooksdev.web.controller;

import com.cooksdev.web.AbstractWebApiIntegrationTest;
import com.cooksdev.web.TestWebHelper;
import com.cooksdev.web.dto.UserDto;
import com.cooksdev.web.util.JsonUtils;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DatabaseSetup({"/dataset/users_init.xml"})
@DatabaseTearDown(value = "/dataset/clean.xml")
public class UserControllerTest extends AbstractWebApiIntegrationTest {

    private static final String LOGIN = "testlogin";
    private static final String PWD = "testpwd";
    private static final String FULL_NAME = "testFullName";

    private UserDto userDto;

    @Before
    public void createUserDto(){
        userDto = UserDto.builder()
                .login(LOGIN)
                .password(PWD)
                .fullName(FULL_NAME)
                .build();
    }

    @Test
    public void registerUser() throws Exception {
        MvcResult mvcResult  = mockMvc.perform(post("/users")
                .contentType(TestWebHelper.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJson(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("login", is(LOGIN)))
                .andExpect(jsonPath("full_name", is(FULL_NAME)))
                .andReturn();

        String userJson = mvcResult.getResponse().getContentAsString();
        UserDto userDto = JsonUtils.fromJson(userJson, UserDto.class);
    }

    @Test
    public void registerUserWithEmptyLogin() throws Exception {
        userDto.setLogin("");
        mockMvc.perform(post("/users")
                .contentType(TestWebHelper.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJson(userDto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void registerUserWithEmptyPwd() throws Exception {
        userDto.setPassword("");
        mockMvc.perform(post("/users")
                .contentType(TestWebHelper.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJson(userDto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void registerUserWithEmptyFullName() throws Exception {
        userDto.setFullName("");
        mockMvc.perform(post("/users")
                .contentType(TestWebHelper.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJson(userDto)))
                .andExpect(status().is4xxClientError());
    }
}