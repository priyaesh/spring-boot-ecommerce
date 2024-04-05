package com.luv2code.ecommerce.serviceTest;

import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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


}
