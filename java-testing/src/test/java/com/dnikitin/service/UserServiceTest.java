package com.dnikitin.service;

import com.dnikitin.dto.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//tworzay sie tylko jeden klass, wiec nie trzeba Before All oraz After All robic static
public class UserServiceTest {

    private UserService userService;

    @BeforeAll
    void init() {
        System.out.println("Before all " + this + "\n");
    }

    @BeforeEach
        //robimy cos co robi sie w kazdym tescie
    void prepare() {
        System.out.println("Before each " + this);
        userService = new UserService();
    }

    @Test
    void usersEmptyIfNoUsersAdded() {
        System.out.println("Test1 " + this);
        var users = userService.getAll();
        assertTrue(users.isEmpty(), () -> "User list should be empty"); //get condition, if true -> test passed
    }

    @Test
    void userSizeIfUserAdded() {
        System.out.println("Test2 " + this);
        userService.add(new User());
        userService.add(new User());

        var users = userService.getAll();
        assertEquals(2, users.size(), "User list should have 2 users");
        assertThat(users.size()).isEqualTo(2);
    }

    @AfterEach
    void deleteDataFromDatabase() {
        System.out.println("After each " + this + "\n");
    }

    @AfterAll
    void finish() {
        System.out.println("After all " + this + "\n");
    }
}
