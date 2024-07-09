package com.delaporte.projet;

import java.util.Date;
import java.util.List;

public class Score {
    private int score;
    private int partie_joue;
    private String ca_libelle;
    private String ar_nom;

    @Override
    public String toString() {
        return "Score{" +
                "Score=" + score +
                ", partie_joue=" + partie_joue +
                ", ca_libelle='" + ca_libelle + '\'' +
                ", ar_nom='" + ar_nom + '\'' +
                '}';
    }

    public Score(int score, int partie_joue, String ca_libelle, String type) {
        score = score;
        this.partie_joue = partie_joue;
        if(type=="categorie")
            this.ca_libelle = ca_libelle;
        if(type=="artiste")
            this.ca_libelle = ar_nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        score = score;
    }

    public int getPartie_joue() {
        return partie_joue;
    }

    public void setPartie_joue(int partie_joue) {
        this.partie_joue = partie_joue;
    }

    public String getCa_libelle() {
        return ca_libelle;
    }

    public void setCa_libelle(String ca_libelle) {
        this.ca_libelle = ca_libelle;
    }

    public String getAr_nom() {
        return ar_nom;
    }

    public void setAr_nom(String ar_nom) {
        this.ar_nom = ar_nom;
    }
}
