package com.example.studentcourse.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

  public class StudentRequestDto {
    
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

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

    

    
    
}
