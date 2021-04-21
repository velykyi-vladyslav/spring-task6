package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.dto.UserDto;

public interface UserService {
    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    void deleteUser(String email);

    UserDto updateUser(String email, UserDto userDto);
}
