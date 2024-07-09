package com.delaporte.projet;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Partie {
    private int pa_id;
    private int pa_score_global;
    private Date pa_date;
    private int fk_ut;
    private List<Musique> musiques;

    public int getPa_id() {
        return pa_id;
    }

    public void setPa_id(int pa_id) {
        this.pa_id = pa_id;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "pa_id=" + pa_id +
                ", pa_score_global=" + pa_score_global +
                ", pa_date=" + pa_date +
                ", fk_ut=" + fk_ut +
                ", musiques=" + musiques.toString() +
                '}';
    }

    public List<Musique> getMusiques() {
        return musiques;
    }

    public void setMusiques(List<Musique>  musiques) {
        this.musiques = musiques;
    }

    public int getFk_ut() {
        return fk_ut;
    }

    public void setFk_ut(int fk_ut) {
        this.fk_ut = fk_ut;
    }

    public int getPa_score_global() {
        return pa_score_global;
    }

    public void setPa_score_global(int pa_score_global) {
        this.pa_score_global = pa_score_global;
    }

    public Date getPa_date() {
        return pa_date;
    }

    public void setPa_date(Date pa_date) {
        this.pa_date = pa_date;
    }
}
