package com.delaporte.projet;

import java.util.Date;

public class Musique {
    public boolean est_bonne_reponse;
    public int mu_id;
    public String mu_titre;
    public String mu_date_sortie;
    public String mu_nom_fichier;
    public int fk_ar;
    public int fk_ca;
    public int ar_id;
    public String ar_nom;
    public int ca_id;

    public Musique(int mu_id, String mu_titre, String mu_date_sortie, String mu_nom_fichier, int fk_ar, int fk_ca) {
        this.mu_id = mu_id;
        this.mu_titre = mu_titre;
        this.mu_date_sortie = mu_date_sortie;
        this.mu_nom_fichier = mu_nom_fichier;
        this.fk_ar = fk_ar;
        this.fk_ca = fk_ca;
    }

    @Override
    public String toString() {
        return "Musique{" +
                "est_bonne_reponse=" + est_bonne_reponse +
                ", mu_id=" + mu_id +
                ", mu_titre='" + mu_titre + '\'' +
                ", mu_date_sortie='" + mu_date_sortie + '\'' +
                ", mu_nom_fichier='" + mu_nom_fichier + '\'' +
                ", fk_ar=" + fk_ar +
                ", fk_ca=" + fk_ca +
                ", ar_id=" + ar_id +
                ", ar_nom='" + ar_nom + '\'' +
                ", ca_id=" + ca_id +
                ", ca_libelle='" + ca_libelle + '\'' +
                '}';
    }

    public Musique(boolean est_bonne_reponse, int mu_id, String mu_titre, String mu_date_sortie, String mu_nom_fichier, int fk_ar, int fk_ca, int ar_id, String ar_nom, int ca_id, String ca_libelle) {
        this.est_bonne_reponse = est_bonne_reponse;
        this.mu_id = mu_id;
        this.mu_titre = mu_titre;
        this.mu_date_sortie = mu_date_sortie;
        this.mu_nom_fichier = mu_nom_fichier;
        this.fk_ar = fk_ar;
        this.fk_ca = fk_ca;
        this.ar_id = ar_id;
        this.ar_nom = ar_nom;
        this.ca_id = ca_id;
        this.ca_libelle = ca_libelle;
    }

    public String ca_libelle;

    public boolean isEst_bonne_reponse() {
        return est_bonne_reponse;
    }

    public void setEst_bonne_reponse(boolean est_bonne_reponse) {
        this.est_bonne_reponse = est_bonne_reponse;
    }

    public int getMu_id() {
        return mu_id;
    }

    public void setMu_id(int mu_id) {
        this.mu_id = mu_id;
    }

    public String getMu_titre() {
        return mu_titre;
    }

    public void setMu_titre(String mu_titre) {
        this.mu_titre = mu_titre;
    }

    public String getMu_date_sortie() {
        return mu_date_sortie;
    }

    public void setMu_date_sortie(String mu_date_sortie) {
        this.mu_date_sortie = mu_date_sortie;
    }

    public String getMu_nom_fichier() {
        return mu_nom_fichier;
    }

    public void setMu_nom_fichier(String mu_nom_fichier) {
        this.mu_nom_fichier = mu_nom_fichier;
    }

    public int getFk_ar() {
        return fk_ar;
    }

    public void setFk_ar(int fk_ar) {
        this.fk_ar = fk_ar;
    }

    public int getFk_ca() {
        return fk_ca;
    }

    public void setFk_ca(int fk_ca) {
        this.fk_ca = fk_ca;
    }

    public int getAr_id() {
        return ar_id;
    }

    public void setAr_id(int ar_id) {
        this.ar_id = ar_id;
    }

    public String getAr_nom() {
        return ar_nom;
    }

    public void setAr_nom(String ar_nom) {
        this.ar_nom = ar_nom;
    }

    public int getCa_id() {
        return ca_id;
    }

    public void setCa_id(int ca_id) {
        this.ca_id = ca_id;
    }

    public String getCa_libelle() {
        return ca_libelle;
    }

    public void setCa_libelle(String ca_libelle) {
        this.ca_libelle = ca_libelle;
    }
}
