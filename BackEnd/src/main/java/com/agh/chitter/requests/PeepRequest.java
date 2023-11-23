package com.agh.chitter.requests;

import com.agh.chitter.model.Peep;
import com.agh.chitter.model.User;

public class PeepRequest {
    private User user;
    private Peep peep;

    public PeepRequest(){}

    public PeepRequest(User user, Peep peep) {
        this.user = user;
        this.peep = peep;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Peep getPeep() {
        return peep;
    }

    public void setPeep(Peep peep) {
        this.peep = peep;
    }
}
