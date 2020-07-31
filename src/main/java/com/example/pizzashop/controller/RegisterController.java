package com.example.pizzashop.controller;

import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.UserCredentials;
import com.example.pizzashop.exception.ValidationException;
import com.example.pizzashop.service.UserService;
import com.example.pizzashop.util.BindingResultUtils;
import com.example.pizzashop.validator.RegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final RegisterValidator registerValidator;

    @InitBinder("credentials")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(registerValidator);
    }

    @PostMapping("/api/register")
    public User register(@Valid @RequestBody UserCredentials credentials,
                         BindingResult bindingResult, HttpServletRequest request) throws ValidationException{

        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }

        if (!userService.isEmailVacant(credentials.getEmail())) {
            throw new ValidationException("Email is already in use");
        }

        userService.register(credentials);

        return userService.login(credentials, request.getSession());
    }
}