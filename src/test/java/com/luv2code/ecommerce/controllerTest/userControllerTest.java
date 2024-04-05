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
import java.util.Optional;

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
    @Test
    public void testGetUserById() throws Exception {
        // Prepare mock data

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        // Mock the service method to return the mock data
        when(userService.getUserById(1L)).thenReturn(user);

        // Perform the request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Optionally, you can verify the content of the response
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Prepare mock data
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Smith");

        //User user1 = new User(1L, "John", "Doe");
        //User user2 = new User(2L, "Jane", "Smith");
        List<User> userList = Arrays.asList(user1, user2);


        // Mock the service method to return the mock data
        when(userService.getAllUsers()).thenReturn(userList);
        System.out.println(userList);

        // Perform the request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Optionally, you can verify the content of the response
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(userList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Smith"));
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

