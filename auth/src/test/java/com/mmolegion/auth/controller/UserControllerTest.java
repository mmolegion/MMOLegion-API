package com.mmolegion.auth.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmolegion.core.model.User;
import com.mmolegion.core.service.UserServiceImpl;
import com.mmolegion.core.util.Crypto;
import com.mmolegion.core.util.Request;
import com.mmolegion.core.util.Token;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@PrepareForTest({
        Request.class,
        Token.class
})
class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private User user;

    @BeforeEach
    void setup() throws InvalidKeySpecException, NoSuchAlgorithmException {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();

        System.setProperty("jwt.secret", "shhhItsASecret");

        Map<String, String> hashed = Crypto.generateHashedPassword("pass");
        user = new User(1, "user", "email@email.com", hashed.get("salt"), hashed.get("hash"), false, 0, 0);
    }

    @Test
    void testCreateUserSuccessful() throws Exception {
        when(userService.userExists(anyString())).thenReturn(false);
        when(userService.emailExists(anyString())).thenReturn(false);
        when(userService.createUser(anyString(), anyString(), anyString())).thenReturn(user);

        mockMvc.perform(
                post("/api/v1/user")
                        .contentType("application/x-www-form-urlencoded")
                        .param("username", "user")
                        .param("email", "email@email.com")
                        .param("password", "pass")
        )
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUserMissingUsername() throws Exception {
        mockMvc.perform(
                post("/api/v1/user")
                        .contentType("application/x-www-form-urlencoded")
                        .param("email", "email@email.com")
                        .param("password", "pass")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("A required parameter is missing from the request, please try again.")));
    }

    @Test
    void testCreateUserMissingEmail() throws Exception {
        mockMvc.perform(
                post("/api/v1/user")
                        .contentType("application/x-www-form-urlencoded")
                        .param("username", "user")
                        .param("password", "pass")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("A required parameter is missing from the request, please try again.")));
    }

    @Test
    void testCreateUserMissingPassword() throws Exception {
        mockMvc.perform(
                post("/api/v1/user")
                        .contentType("application/x-www-form-urlencoded")
                        .param("username", "user")
                        .param("email", "email@email.com")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("A required parameter is missing from the request, please try again.")));
    }

    @Test
    void testCreateUserIfUserExists() throws Exception {
        when(userService.userExists(anyString())).thenReturn(true);

        mockMvc.perform(
                post("/api/v1/user")
                        .contentType("application/x-www-form-urlencoded")
                        .param("username", "user")
                        .param("email", "email@email.com")
                        .param("password", "pass")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The username and/or email address provided already exist. Please try again.")));
    }

    @Test
    void testCreateUserIfEmailExists() throws Exception {
        when(userService.userExists(anyString())).thenReturn(false);
        when(userService.userExists(anyString())).thenReturn(true);

        mockMvc.perform(
                post("/api/v1/user")
                        .contentType("application/x-www-form-urlencoded")
                        .param("username", "user")
                        .param("email", "email@email.com")
                        .param("password", "pass")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The username and/or email address provided already exist. Please try again.")));
    }

    @Test
    void testCreateUserUnsuccessfulCreation() throws Exception {
        when(userService.userExists(anyString())).thenReturn(false);
        when(userService.emailExists(anyString())).thenReturn(false);
        when(userService.createUser(anyString(), anyString(), anyString())).thenReturn(null);

        mockMvc.perform(
                post("/api/v1/user")
                        .contentType("application/x-www-form-urlencoded")
                        .param("username", "user")
                        .param("email", "email@email.com")
                        .param("password", "pass")
        )
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("There was an error in creating your account, please try again later.")));
    }

    @Test
    void testReadUserSuccess() throws Exception {
        when(userService.findUser(anyString())).thenReturn(user);

        mockMvc.perform(
                get("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.username", Matchers.equalTo("user")))
                .andExpect(jsonPath("$.response.email", Matchers.equalTo("email@email.com")));
    }

    @Test
    void testReadUserMissingToken() throws Exception {
        mockMvc.perform(
                get("/api/v1/user")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The token provided is not valid.")));
    }

    @Test
    void testReadUserUserNotExists() throws Exception {
        when(userService.findUser(anyString())).thenReturn(null);

        mockMvc.perform(
                get("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
        )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The username provided does not exist.")));
    }

    @Test
    void testUpdateUserSuccess() throws Exception {
        when(userService.findUser(anyString())).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(1);

        mockMvc.perform(
                put("/api/v1/user")
                    .header("Authorization", Token.createToken(user))
                    .contentType("application/json")
                        .content("{}")
        )
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUserUpdateFailed() throws Exception {
        when(userService.findUser(anyString())).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(0);

        mockMvc.perform(
                put("/api/v1/user")
                    .header("Authorization", Token.createToken(user))
                    .contentType("application/json")
                        .content("{}")
        )
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The update query was unsuccessful.")));
    }

    @Test
    void testUpdateUserMissingToken() throws Exception {
        mockMvc.perform(
                put("/api/v1/user")
                        .contentType("application/json")
                        .content("{}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The token provided is not valid.")));
    }

    @Test
    void testUpdateUserMissingBody() throws Exception {
        mockMvc.perform(
                put("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
                        .contentType("application/json")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateUserUserNotExist() throws Exception {
        when(userService.findUser(anyString())).thenReturn(null);

        mockMvc.perform(
                get("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
                        .contentType("application/json")
                        .content("{}")
        )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The username provided does not exist.")));
    }

    @Test
    void testUpdateUserUpdateUsername() throws Exception {
        when(userService.findUser(anyString())).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(1);

        MvcResult result = mockMvc.perform(
                put("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
                        .contentType("application/json")
                        .content("{\"username\":\"newuser\"}")
        )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode node = new ObjectMapper().readTree(result.getResponse().getContentAsString());
        assertNotEquals("newuser", Token.getSubject(node.get("response").get("token").asText()));
        assertEquals("user", Token.getSubject(node.get("response").get("token").asText()));
    }

    @Test
    void testUpdateUserUpdateEmail() throws Exception {
        when(userService.findUser(anyString())).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(1);

        MvcResult result = mockMvc.perform(
                put("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
                        .contentType("application/json")
                        .content("{\"email\":\"new@email.com\"}")
        )
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("new@email.com", user.getEmail());
    }

    @Test
    void testDeleteUserSuccess() throws Exception {
        when(userService.findUser(anyString())).thenReturn(user);
        when(userService.deleteUser(any(User.class))).thenReturn(1);

        mockMvc.perform(
                delete("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.task", Matchers.equalTo("successful")));
    }

    @Test
    void testDeleteDeleteFailed() throws Exception {
        when(userService.findUser(anyString())).thenReturn(user);
        when(userService.deleteUser(any(User.class))).thenReturn(0);

        mockMvc.perform(
                delete("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
        )
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The delete query was unsuccessful.")));
    }

    @Test
    void testDeleteUserNotExists() throws Exception {
        when(userService.findUser(anyString())).thenReturn(null);

        mockMvc.perform(
                delete("/api/v1/user")
                        .header("Authorization", Token.createToken(user))
        )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The username provided does not exist.")));
    }

    @Test
    void testDeleteMissingToken() throws Exception {
        mockMvc.perform(
                delete("/api/v1/user")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response.valid", Matchers.equalTo("false")))
                .andExpect(jsonPath("$.response.message", Matchers.equalTo("The token provided is not valid.")));
    }

}
