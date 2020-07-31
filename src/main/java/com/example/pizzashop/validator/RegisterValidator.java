package com.example.pizzashop.validator;

import com.example.pizzashop.dto.UserCredentials;
import com.example.pizzashop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



@Component
@RequiredArgsConstructor
public class RegisterValidator implements Validator {
    private final UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return UserCredentials.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentials credentials = (UserCredentials) o;

            if (!userService.isEmailVacant(credentials.getEmail())) {
                errors.rejectValue("email", "email.is-in-use", "email is already in use");
            }

            if (credentials.getPhoneNumber() == null || credentials.getPhoneNumber().trim().isEmpty()) {
                errors.rejectValue("phoneNumber", "phone-number.is-empty",
                        "Phone number should not be empty");
            }

            if (credentials.getName() == null || credentials.getName().trim().isEmpty()) {
                errors.rejectValue("name", "name.empty-name", "Name should not be empty");
            }

        }
    }
}
