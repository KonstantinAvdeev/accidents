package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserService.class.getName());
    private final UserRepository userRepository;

    @Override
    public boolean save(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            LOG.error("Что-то пошло не так! Не получается зарегистрировать пользователя!", e);
        }
        return false;
    }

}