package com.hakaton.tim.ejnabavke.model;

/**
 * Created by Bojan on 5.12.2015.
 */
public class TownEntity {

    private Integer id = null;
    private String naziv = null;
    private Integer broj_nabavki = null;
    private Boolean selected = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBroj_nabavki() {
        return broj_nabavki;
    }

    public void setBroj_nabavki(Integer broj_nabavki) {
        this.broj_nabavki = broj_nabavki;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "TownEntity{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", broj_nabavki=" + broj_nabavki +
                ", selected=" + selected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TownEntity that = (TownEntity) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
