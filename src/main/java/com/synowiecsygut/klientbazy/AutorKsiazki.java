package com.synowiecsygut.klientbazy;

public class AutorKsiazki {
    private int idKsiazki;
    private String tytul;
    private String imieNazwisko;
    private int idAutora;
    private int waznosc;

    public AutorKsiazki(int idKsiazki, String tytul, String imieNazwisko, int idAutora, int waznosc) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.imieNazwisko = imieNazwisko;
        this.idAutora = idAutora;
        this.waznosc = waznosc;
    }

    public int getIdKsiazki() {
        return idKsiazki;
    }

    public void setIdKsiazki(int idKsiazki) {
        this.idKsiazki = idKsiazki;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public int getIdAutora() {
        return idAutora;
    }

    public void setIdAutora(int idAutora) {
        this.idAutora = idAutora;
    }

    public int getWaznosc() {
        return waznosc;
    }

    public void setWaznosc(int waznosc) {
        this.waznosc = waznosc;
    }
}
