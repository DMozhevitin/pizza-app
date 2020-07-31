package com.example.pizzashop.controller;

import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.UserCredentials;
import com.example.pizzashop.exception.ValidationException;
import com.example.pizzashop.service.UserService;
import com.example.pizzashop.util.BindingResultUtils;
import com.example.pizzashop.validator.LoginValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final LoginValidator loginValidator;

    @InitBinder("credentials")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(loginValidator);
    }

    @PostMapping("api/login")
    public User login(@Valid @RequestBody UserCredentials credentials,
                         BindingResult bindingResult, HttpServletRequest request) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }

        if (userService.findByCredentials(credentials) == null) {
            throw new ValidationException("Invalid login or password");
        }

        return userService.login(credentials, request.getSession());
    }
}
