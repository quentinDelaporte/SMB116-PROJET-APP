package com.delaporte.projet;

import java.util.Date;

public class Message {
    private int mes_id;
    private int ut_id_emeteur;
    private int ut_id_receveur;
    private Date mes_date;

    public Message(int mes_id, int ut_id_emeteur, int ut_id_receveur, Date mes_date, String mes_message) {
        this.mes_id = mes_id;
        this.ut_id_emeteur = ut_id_emeteur;
        this.ut_id_receveur = ut_id_receveur;
        this.mes_date = mes_date;
        this.mes_message = mes_message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mes_id=" + mes_id +
                ", ut_id_emeteur=" + ut_id_emeteur +
                ", ut_id_receveur=" + ut_id_receveur +
                ", mes_date=" + mes_date +
                ", mes_message='" + mes_message + '\'' +
                '}';
    }

    public int getMes_id() {
        return mes_id;
    }

    public void setMes_id(int mes_id) {
        this.mes_id = mes_id;
    }

    public int getUt_id_emeteur() {
        return ut_id_emeteur;
    }

    public void setUt_id_emeteur(int ut_id_emeteur) {
        this.ut_id_emeteur = ut_id_emeteur;
    }

    public int getUt_id_receveur() {
        return ut_id_receveur;
    }

    public void setUt_id_receveur(int ut_id_receveur) {
        this.ut_id_receveur = ut_id_receveur;
    }

    public Date getMes_date() {
        return mes_date;
    }

    public void setMes_date(Date mes_date) {
        this.mes_date = mes_date;
    }

    public String getMes_message() {
        return mes_message;
    }

    public void setMes_message(String mes_message) {
        this.mes_message = mes_message;
    }

    private String mes_message;


}
