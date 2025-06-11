package ru.bmstu.WebAppDB.dto;

import jakarta.validation.constraints.NotEmpty;

public class PlayerDTO {
    private Long id;

    @NotEmpty
    private String username;

    private String email;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
