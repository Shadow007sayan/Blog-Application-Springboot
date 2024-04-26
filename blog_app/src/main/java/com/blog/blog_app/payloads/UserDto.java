package com.blog.blog_app.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class UserDto {


    private int id;

    @NotEmpty
    @Size(min=3 , message = "username must be of minimum 3 characters")
    private String name;
    @Email(message = "email is not valid!!")
    private String email;
    @NotEmpty(message = "password cant be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;
    private String about;

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public UserDto(int id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
