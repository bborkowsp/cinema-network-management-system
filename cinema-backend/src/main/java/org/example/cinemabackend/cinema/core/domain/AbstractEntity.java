package org.example.cinemabackend.cinema.core.domain;

import java.io.Serializable;

public class AbstractEntity<ID extends Serializable> implements Serializable {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
