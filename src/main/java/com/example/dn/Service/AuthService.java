package com.example.dn.Service;

import com.example.dn.Model.User;
import com.example.dn.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Регистрация пользователя
     *
     * @param username уникальное имя
     * @param email уникальный email
     * @param password пароль
     * @return зарегистрированный User
     * @throws IllegalArgumentException если пользователь с таким username/email уже существует
     */
    public User register(String username, String email, String password) {
        // Проверим не занят ли username
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Пользователь с таким username уже существует!");
        }
        // Можно также проверить email
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует!");
        }

        // Создаём нового пользователя
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    /**
     * Логин
     *
     * @param username имя пользователя
     * @param password пароль
     * @return найденный User, если успех (иначе null или выкинуть исключение)
     */
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null; // нет пользователя с таким именем
        }

        // Сравниваем пароль (без шифрования, так как "безопасность не важна" в рамках ДЗ)
        if (!user.getPassword().equals(password)) {
            return null; // неверный пароль
        }
        return user; // успех
    }

}
