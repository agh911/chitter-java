package com.agh.chitter.requests;

import com.agh.chitter.model.User;

public class SignUpRequest {
    private User newUser;

    public SignUpRequest(User newUser) {
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }
}
