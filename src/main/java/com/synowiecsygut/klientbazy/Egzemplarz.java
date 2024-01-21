package com.synowiecsygut.klientbazy;

public class Egzemplarz {
    private int id;
    private int idKsiazki;
    private String tytul;
    private int rokWydania;
    private boolean dostepnosc;

    public Egzemplarz(int id, int idKsiazki, String tytul, int rokWydania, boolean dostepnosc) {
        this.id = id;
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.dostepnosc = dostepnosc;
    }

    public int getId() {
        return id;
    }

    public int getIdKsiazki() {
        return idKsiazki;
    }

    public String getTytul() {
        return tytul;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public boolean isDostepnosc() {
        return dostepnosc;
    }
}
