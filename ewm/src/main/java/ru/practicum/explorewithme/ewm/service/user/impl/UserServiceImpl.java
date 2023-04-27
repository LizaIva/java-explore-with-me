package ru.practicum.explorewithme.ewm.service.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.ewm.dto.user.CreateUserDto;
import ru.practicum.explorewithme.ewm.dto.user.UserDto;
import ru.practicum.explorewithme.ewm.model.user.User;
import ru.practicum.explorewithme.ewm.service.user.UserService;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;
import ru.practicum.explorewithme.ewm.utils.user.UserMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;
    private final UserMapper userMapper;

    @Override
    public UserDto put(CreateUserDto createUserDto) {
        User createUser = userMapper.mapToUser(createUserDto);
        User actualUser = userStorage.put(createUser);
        log.info("Create user with id{}", actualUser.getId());
        return userMapper.mapToUserDto(actualUser);
    }

    @Override
    public List<UserDto> getAll(Integer from, Integer size) {
        return userMapper.mapToUsersDto(userStorage.getAll(from, size));
    }

    @Override
    public void delete(Integer userId) {
        log.info("Delete user with id = {}", userId);
        userStorage.delete(userId);
    }
}
