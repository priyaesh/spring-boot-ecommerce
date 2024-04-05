package com.luv2code.ecommerce.serviceTest;

import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class userServiceTest {

   @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";

        // Act
        userService.addUser(firstName, lastName);

        // Assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals(firstName, capturedUser.getFirstName());
        assertEquals(lastName, capturedUser.getLastName());
    }
    @Test
    public void testGetUserById() {
        // Prepare mock data
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        // Mock the behavior of userRepository.findById() to return an Optional containing the mock User
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = Optional.ofNullable(userService.getUserById(1L));


        // Invoke the getUserById method
      //userService.getUserById(1L)

        // Verify the result
        assertEquals(Optional.of(user), result);
    }


}
