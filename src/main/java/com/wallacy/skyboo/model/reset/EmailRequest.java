package com.wallacy.skyboo.model.reset;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class EmailRequest {

    @Email(message = "Deve ser um email válido")
    @NotEmpty(message = "Email não pode ser vazio")
    private String email;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
