package com.luv2code.ecommerce.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.ecommerce.controller.UserController;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@WebMvcTest(UserController.class)
public class userControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @Test
        public void testAddUser() throws Exception {
            User user = new User();
            user.setFirstName("John");
            user.setLastName("Doe");

            when(userService.addUser(any(String.class), any(String.class))).thenReturn(user);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/users/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(user)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
        }



        // Helper method to convert object to JSON string
        private static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

