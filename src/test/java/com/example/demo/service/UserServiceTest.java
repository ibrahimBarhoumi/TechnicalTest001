package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repositories.UserRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void should_return_user_when_is_found() throws NotFoundException {
        User user = createUser();
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(user));
        Assertions.assertEquals(userService.findById("132644").getId(), "132644");
    }

    @Test
    public void should_return_notFoundException_when_user_is_not_found(){
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userService.findById("1324"));
    }

    private User createUser() {
        return User.builder().id("132644").build();
    }
}
