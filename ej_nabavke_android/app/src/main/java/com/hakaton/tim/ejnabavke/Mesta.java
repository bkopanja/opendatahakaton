package com.hakaton.tim.ejnabavke;

import com.activeandroid.Model;

/**
 * Created by KDragan on 5.12.2015.
 */
public class Mesta extends Model {

    private int id;
    private String mesto;

    public Mesta() {
        id = -1;
        mesto = null;
    }

    public Mesta(int id, String mesto) {
        this.id = id;
        this.mesto = mesto;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public int getIdTag() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Mesta{" +
                "id=" + id +
                ", mesto='" + mesto + '\'' +
                '}';
    }
}
