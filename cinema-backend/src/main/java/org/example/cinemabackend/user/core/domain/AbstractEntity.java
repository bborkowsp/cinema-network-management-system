package org.example.cinemabackend.user.core.domain;

import java.io.Serializable;

public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
