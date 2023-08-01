package com.isa.jjdzr.service;

import com.isa.jjdzr.dictionary.AdvancementLevelCategory;
import com.isa.jjdzr.dto.UserDto;
import com.isa.jjdzr.mapper.UserMapper;
import com.isa.jjdzr.model.Role;
import com.isa.jjdzr.model.User;
import com.isa.jjdzr.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private static final List<User> mockUsers = List.of(
            new User("andrzej1234", "12345", "andrzej1234@wp.pl", AdvancementLevelCategory.ADVANCE, List.of(new Role("USER"))),
            new User("pawel1234", "qwerty", "pawel1234@gmail.com", AdvancementLevelCategory.BEGINNER, List.of(new Role("USER"))),
            new User("mateusz", "asdf123", "mateusz.pl", AdvancementLevelCategory.PROFESSIONAL, List.of(new Role("USER"))),
            new User("paulina", "asdasd45", "paulina@wp.pl", AdvancementLevelCategory.ADVANCE, List.of(new Role("USER"))));

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private UserMapper userMapperMock;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepositoryMock = mock(UserRepository.class);
        userMapperMock = mock(UserMapper.class);
        userService = new UserService(userRepositoryMock, userMapperMock);
        when(userMapperMock.allUsersToDto(any())).thenCallRealMethod();
    }

    @Test
    void shouldFindAllUsers() {
        //GIVEN
        when(userRepositoryMock.findAll()).thenReturn(mockUsers);
        UserDto expectedUserDto = userMapperMock.userEntityToDto(mockUsers.get(0));
        //WHEN
        List<UserDto> allUsers = userService.findAllUsers();
        UserDto actualUser = allUsers.get(0);
        //THEN
        assertAll(() -> assertThat(allUsers)
                        .isNotNull()
                        .hasSize(4)
                        .isNotEmpty(),
                () -> assertEquals(actualUser, expectedUserDto));
    }

    @Test
    void shouldReturnTrueIfUserExistByEmail() {
        //GIVEN
        String email = mockUsers.get(0).getUserEmail();
        when(userRepositoryMock.existsByUserEmail(email)).thenReturn(true);
        //WHEN
        boolean existsByEmail = userService.existsByEmail(email);
        boolean doesNotExistsByEmail = userService.existsByEmail("joseph@gmail.com");
        //THEN
        assertTrue(existsByEmail);
        assertFalse(doesNotExistsByEmail);
    }

    @Test
    void shouldReturnTrueIfUserExistsByName() {
        //GIVEN
        String userName = mockUsers.get(0).getUsername();
        when(userRepositoryMock.existsByUserName(userName)).thenReturn(true);
        //WHEN
        boolean existsuserName = userService.existsByName(userName);
        boolean doesNotExistUserName = userService.existsByName("joseph123");
        //THEN
        assertTrue(existsuserName);
        assertFalse(doesNotExistUserName);
    }

    @Test
    void shouldReturnUserByUserName() {
        //GIVEN
        User expectedUser = mockUsers.get(2);
        when(userRepositoryMock.findByUserName(expectedUser.getUsername())).thenReturn(Optional.of(expectedUser));
        //WHEN
        Optional<User> actualUser = userService.findByUserName("mateusz");
        //THEN
        assertAll(() -> assertThat(actualUser)
                        .isNotNull()
                        .isNotEmpty(),
                () -> assertEquals(expectedUser, actualUser.get()),
                () -> assertTrue(actualUser.isPresent()));
    }

    @Test
    void shouldReturnUserByUserId() {
        User expectedUser = mockUsers.get(2);
        expectedUser.setUserId(2L);
        when(userRepositoryMock.findByUserId(expectedUser.getUserId())).thenReturn(Optional.of(expectedUser));
        //WHEN
        Optional<User> actualUser = userService.findByUserId(2L);
        //THEN
        assertAll(() -> assertThat(actualUser)
                        .isNotNull(),
                () -> assertEquals(Optional.of(expectedUser), actualUser));
    }
}