package com.nltu.app.diplomaproject.dto;

import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {
    @Email(message = ExceptionMessage.EMAIL_VALIDATION_FAIL,
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z]).{8,}$",
            message = ExceptionMessage.PASSWORD_VALIDATION_FAIL)
    //@Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotBlank(message = ExceptionMessage.FIRSTNAME_VALIDATION_FAIL)
    private String firstName;
    @NotBlank(message = ExceptionMessage.LASTNAME_VALIDATION_FAIL)
    private String lastName;

    public UserRegistrationDto(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
