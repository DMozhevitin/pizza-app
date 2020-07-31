package com.example.pizzashop.validator;

import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.UserCredentials;
import com.example.pizzashop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserCredentials.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentials credentials = (UserCredentials) o;
            User user = userService.findByCredentials(credentials);

            if (user == null) {
                errors.rejectValue("email", "invalid email or password", "Invalid email or password");
            }
        }
    }
}
