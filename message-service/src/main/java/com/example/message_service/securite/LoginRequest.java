package com.example.message_service.securite;


public class LoginRequest {
    private String login;
    private String motpasse;

    // Getters et setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }
}
