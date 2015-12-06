package com.hakaton.tim.ejnabavke.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bojankopanja on 12/6/15.
 */
public class FeedEntity implements Serializable {

    private Integer id;
    private String naziv_dokumenta;
    private String naziv_narucioca;
    private String predmet_nabavke;
    private String opsti_recnik_nabavki;
    private Integer obavestenje_o_zakljucenom_ugovoru;
    private Integer obavestenje_o_obustavi_postupka_javne_nabavke;
    private Integer obavestenje_o_zastiti_prava;
    private Integer pregovaracki_bez_ponuda;
    private String datum_poslednje_izmene;
    private String link;
    private Integer opstina_id;
    private Integer vrsta_postupka_id;
    private Integer kategorija_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv_dokumenta() {
        return naziv_dokumenta;
    }

    public void setNaziv_dokumenta(String naziv_dokumenta) {
        this.naziv_dokumenta = naziv_dokumenta;
    }

    public String getNaziv_narucioca() {
        return naziv_narucioca;
    }

    public void setNaziv_narucioca(String naziv_narucioca) {
        this.naziv_narucioca = naziv_narucioca;
    }

    public String getPredmet_nabavke() {
        return predmet_nabavke;
    }

    public void setPredmet_nabavke(String predmet_nabavke) {
        this.predmet_nabavke = predmet_nabavke;
    }

    public String getOpsti_recnik_nabavki() {
        return opsti_recnik_nabavki;
    }

    public void setOpsti_recnik_nabavki(String opsti_recnik_nabavki) {
        this.opsti_recnik_nabavki = opsti_recnik_nabavki;
    }

    public Integer getObavestenje_o_zakljucenom_ugovoru() {
        return obavestenje_o_zakljucenom_ugovoru;
    }

    public void setObavestenje_o_zakljucenom_ugovoru(Integer obavestenje_o_zakljucenom_ugovoru) {
        this.obavestenje_o_zakljucenom_ugovoru = obavestenje_o_zakljucenom_ugovoru;
    }

    public Integer getObavestenje_o_obustavi_postupka_javne_nabavke() {
        return obavestenje_o_obustavi_postupka_javne_nabavke;
    }

    public void setObavestenje_o_obustavi_postupka_javne_nabavke(Integer obavestenje_o_obustavi_postupka_javne_nabavke) {
        this.obavestenje_o_obustavi_postupka_javne_nabavke = obavestenje_o_obustavi_postupka_javne_nabavke;
    }

    public Integer getObavestenje_o_zastiti_prava() {
        return obavestenje_o_zastiti_prava;
    }

    public void setObavestenje_o_zastiti_prava(Integer obavestenje_o_zastiti_prava) {
        this.obavestenje_o_zastiti_prava = obavestenje_o_zastiti_prava;
    }

    public Integer getPregovaracki_bez_ponuda() {
        return pregovaracki_bez_ponuda;
    }

    public void setPregovaracki_bez_ponuda(Integer pregovaracki_bez_ponuda) {
        this.pregovaracki_bez_ponuda = pregovaracki_bez_ponuda;
    }

    public String getDatum_poslednje_izmene() {
        return datum_poslednje_izmene;
    }

    public void setDatum_poslednje_izmene(String datum_poslednje_izmene) {
        this.datum_poslednje_izmene = datum_poslednje_izmene;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getOpstina_id() {
        return opstina_id;
    }

    public void setOpstina_id(Integer opstina_id) {
        this.opstina_id = opstina_id;
    }

    public Integer getVrsta_postupka_id() {
        return vrsta_postupka_id;
    }

    public void setVrsta_postupka_id(Integer vrsta_postupka_id) {
        this.vrsta_postupka_id = vrsta_postupka_id;
    }

    public Integer getKategorija_id() {
        return kategorija_id;
    }

    public void setKategorija_id(Integer kategorija_id) {
        this.kategorija_id = kategorija_id;
    }

    public String getFormattedDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd. MMMM yy");

            Date date = sdf.parse(getDatum_poslednje_izmene());
            return sdf2.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String toString() {
        return "FeedEntity{" +
                "id=" + id +
                ", naziv_dokumenta='" + naziv_dokumenta + '\'' +
                ", naziv_narucioca='" + naziv_narucioca + '\'' +
                ", predmet_nabavke='" + predmet_nabavke + '\'' +
                ", opsti_recnik_nabavki='" + opsti_recnik_nabavki + '\'' +
                ", obavestenje_o_zakljucenom_ugovoru=" + obavestenje_o_zakljucenom_ugovoru +
                ", obavestenje_o_obustavi_postupka_javne_nabavke=" + obavestenje_o_obustavi_postupka_javne_nabavke +
                ", obavestenje_o_zastiti_prava=" + obavestenje_o_zastiti_prava +
                ", pregovaracki_bez_ponuda=" + pregovaracki_bez_ponuda +
                ", datum_poslednje_izmene=" + datum_poslednje_izmene +
                ", link='" + link + '\'' +
                ", opstina_id=" + opstina_id +
                ", vrsta_postupka_id=" + vrsta_postupka_id +
                ", kategorija_id=" + kategorija_id +
                '}';
    }
}
