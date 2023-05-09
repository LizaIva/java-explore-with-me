package ru.practicum.explorewithme.ewm.service.user;

import ru.practicum.explorewithme.ewm.dto.user.CreateUserDto;
import ru.practicum.explorewithme.ewm.dto.user.UserDto;

import java.util.List;

public interface UserService {
    UserDto put(CreateUserDto createUserDto);

    List<UserDto> getAll(List<Integer> ids, Integer from, Integer size);

    void delete(Integer userId);
}
