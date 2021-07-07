package com.example.blog.dto;

import org.assertj.core.internal.bytebuddy.implementation.bytecode.StackManipulation;

//import javax.validation.constraints.Size;

public class UserRegistrationDTO {
    private final static Integer ROLE = 2;
    private final static Boolean ACTIVE = true;


   // @Size(min = 2, max = 25)
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstName;


    private String lastName;
//    private Integer role = ROLE;
//    private Boolean isActive = ACTIVE;

    public Integer getROLE() {
        return ROLE;
    }

    public Boolean getACTIVE() {
        return ACTIVE;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

//    public Integer getRole() {
//        return role;
//    }
//
//    public void setRole(Integer role) {
//        this.role = role;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
}
