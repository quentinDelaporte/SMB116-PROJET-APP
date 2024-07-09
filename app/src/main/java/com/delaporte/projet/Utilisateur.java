package com.delaporte.projet;

public class Utilisateur {
    private int ut_id;
    private String ut_nom;
    private String ut_prenom;
    private String ut_nom_utilisateur;
    private String ut_email;
    private String ut_mot_passe;

    public int getUt_id() {
        return ut_id;
    }

    public void setUt_id(int ut_id) {
        this.ut_id = ut_id;
    }

    public String getUt_nom() {
        return ut_nom;
    }

    public void setUt_nom(String ut_nom) {
        this.ut_nom = ut_nom;
    }

    public String getUt_prenom() {
        return ut_prenom;
    }

    public void setUt_prenom(String ut_prenom) {
        this.ut_prenom = ut_prenom;
    }

    public String getUt_nom_utilisateur() {
        return ut_nom_utilisateur;
    }

    public void setUt_nom_utilisateur(String ut_nom_utilisateur) {
        this.ut_nom_utilisateur = ut_nom_utilisateur;
    }

    public String getUt_email() {
        return ut_email;
    }

    public void setUt_email(String ut_email) {
        this.ut_email = ut_email;
    }

    public String getUt_mot_passe() {
        return ut_mot_passe;
    }

    public void setUt_mot_passe(String ut_mot_passe) {
        this.ut_mot_passe = ut_mot_passe;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "ut_id=" + ut_id +
                ", ut_nom='" + ut_nom + '\'' +
                ", ut_prenom='" + ut_prenom + '\'' +
                ", ut_nom_utilisateur='" + ut_nom_utilisateur + '\'' +
                ", ut_email='" + ut_email + '\'' +
                ", ut_mot_passe='" + ut_mot_passe + '\'' +
                '}';
    }
}
