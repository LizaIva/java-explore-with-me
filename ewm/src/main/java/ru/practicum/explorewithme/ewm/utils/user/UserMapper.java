package ru.practicum.explorewithme.ewm.utils.user;

import lombok.RequiredArgsConstructor;
import org.h2.command.ddl.CreateUser;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.dto.category.CreateCategoryDto;
import ru.practicum.explorewithme.ewm.dto.user.CreateUserDto;
import ru.practicum.explorewithme.ewm.dto.user.UserDto;
import ru.practicum.explorewithme.ewm.model.category.Category;
import ru.practicum.explorewithme.ewm.model.user.User;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public User mapToUser(CreateUserDto createUser) {
        return User.builder()
                .name(createUser.getName())
                .email(createUser.getEmail())
                .build();
    }

    public List<UserDto> mapToUsersDto(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(mapToUserDto(user));
        }
        return usersDto;
    }
}
