package com.hakaton.tim.ejnabavke.model;

import com.activeandroid.Model;

/**
 * Created by KDragan on 5.12.2015.
 */
public class CityEntity extends Model {

    private Integer id = null;
    private String mesto = null;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CityEntity that = (CityEntity) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
