package ru.practicum.explorewithme.ewm.storage.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.exception.UnknownDataException;
import ru.practicum.explorewithme.ewm.model.user.User;
import ru.practicum.explorewithme.ewm.repository.user.UserRepository;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;

import java.util.List;

@Component("userDbStorageImpl")
@RequiredArgsConstructor
public class UserDbStorageImpl implements UserStorage {
    private final UserRepository userRepository;
    @Override
    public User put(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll(Integer from, Integer size) {
        return userRepository.findAll(PageRequest.of(from, size)).getContent();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public void delete(Integer userId) {
        checkUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public void checkUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UnknownDataException(String.format(USER_NOT_FOUND, id));
        }
    }
}
