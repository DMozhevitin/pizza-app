package com.example.pizzashop.service;

import com.example.pizzashop.domain.Currency;
import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.UserCredentials;
import com.example.pizzashop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final CurrencyService currencyService;
    private final TokenService tokenService;

    public boolean isEmailVacant(String email) {
        return repository.countByEmail(email) == 0;
    }

    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setEmail(userCredentials.getEmail());
        user.setPasswordSha(DigestUtils.sha256Hex(userCredentials.getPassword()));
        user.setName(userCredentials.getName());
        user.setPhoneNumber(userCredentials.getPhoneNumber());
        user.setCurrency(currencyService.loadUSD());
        user.setToken(tokenService.generateToken());
        user.setGuest(false);
        return repository.save(user);
    }

    public User findByCredentials(UserCredentials userCredentials) {
        String passwordSha = DigestUtils.sha256Hex(userCredentials.getPassword());
        return repository.findByEmailAndPasswordSha(userCredentials.getEmail(), passwordSha);
    }

    public User loadById(Long userId) {
        return repository.findById(userId).orElse(null);
    }

    public User login(UserCredentials credentials, HttpSession httpSession) {
        User user = findByCredentials(credentials);
        httpSession.setAttribute("user", user);
        return user;
    }

    public User toggleCurrency(User user) {
        if ("USD".equals(user.getCurrency().getName())) {
            user.setCurrency(currencyService.loadEUR());
        } else {
            user.setCurrency(currencyService.loadUSD());
        }

        return repository.save(user);
    }


    public User loadByToken(String token) {
        User user = repository.findByToken(token);
        if (user == null) {
            user = repository.save(
                    User.builder()
                    .token(token)
                    .currency(currencyService.loadUSD())
                    .guest(true)
                    .build()
            );
        }

        return user;
    }

    public User loadOrCreate(Long id, HttpSession httpSession) {
        User user = loadById(id);
        if (user == null) {
            String token = (String) httpSession.getAttribute("token");
            if (token == null) {
                token = tokenService.generateToken();
                httpSession.setAttribute("token", token);
            }

            user = loadByToken(token);
        }

        return user;
    }
}
