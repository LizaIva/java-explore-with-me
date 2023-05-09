package ru.practicum.explorewithme.ewm.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.user.CreateUserDto;
import ru.practicum.explorewithme.ewm.dto.user.UserDto;
import ru.practicum.explorewithme.ewm.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/users")
public class UserForAdminController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid CreateUserDto createUserDto) {
        log.info("Create user");
        return userService.put(createUserDto);
    }

    @GetMapping
    public List<UserDto> getAll(
            @RequestParam(name = "ids", required = false) List<Integer> ids,
            @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Request all users from {} size {}", from, size);
        return userService.getAll(ids, from, size);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer userId) {
        log.info("Delete user with id = {}", userId);
        userService.delete(userId);
    }
}
