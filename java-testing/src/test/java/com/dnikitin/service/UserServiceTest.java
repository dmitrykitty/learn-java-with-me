package com.dnikitin.service;

import com.dnikitin.dto.User;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//tworzy sie tylko jeden klass, wiec nie trzeba Before All oraz After All robic static
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//order of running tests is not defined, but we can set it, using annotaion above class
//default, random, displayname
//methodName -> alfabetically by name
//displayName -> annotation above test method @DisplayName("our name of the test be diplayed") and sorted by this name
//orderAnnotation -> @Order above each test method with the value when it should be run
public class UserServiceTest {
    //another library to use for testing - Hamcrest. Add mvn dependency and use it

    //order of running tests is not defined, but we can set it, using annotaion above class

    private UserService userService;

    private static final User JOHN = new User("John", "1234");
    private static final User MARRY = new User("Marry", "4321");

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

    //we can add nested classes to group tests inside main class
    //it creates better structure and improve readability
    @Nested
    @DisplayName("TEST USERS FUNCTIONALITY")
    @Tag("userlist")
    class UsersListTest {
        @Test
        void usersEmptyIfNoUsersAdded() {
            System.out.println("Test1 " + this);
            var users = userService.getAll();
            assertTrue(users.isEmpty(), "User list should be empty"); //get condition, if true -> test passed
            //assertJ
            assertThat(users.isEmpty()).isTrue();
        }

        @Test
        void userSizeIfUserAdded() {
            System.out.println("Test2 " + this);
            userService.add(JOHN);
            userService.add(MARRY);

            var users = userService.getAll();
            assertAll(
                    () -> assertEquals(2, users.size(), "User list should have 2 users"),
                    //assertJ
                    () -> assertThat(users.size()).isEqualTo(2)
            );
        }

    }


    @Test
    void loginSuccessIfUserExists() {
        userService.add(JOHN);

        Optional<User> maybeJohn = userService.getUserByName("John");
        maybeJohn.ifPresent(user -> {
            assertThat(user.getName()).isEqualTo("John");
        });
    }

    @Test
    @Tag("exception")
        //we can run tests by tag
        //mvn clean test -pl module-name -Dgroups=exception
    void throwsExceptionIfUserIsNull() {
        assertThrows(NullPointerException.class, () -> userService.add(null));
        //or using asserJ
        assertThatThrownBy(() -> userService.add(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("User is NULL")
                .hasNoCause();
    }

    @Test
    void UsersListCorrect() {
        userService.add(JOHN);
        userService.add(MARRY);

        List<User> users = userService.getAll();
        //using assertj to check are array elements correct
        assertThat(users).containsExactlyInAnyOrderElementsOf(List.of(MARRY, JOHN));
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
