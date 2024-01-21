package com.synowiecsygut.klientbazy;

public class Ksiazka {
    private int id;
    private String tytul;
    private int idWydawnictwo;
    private String wydawnictwo;
    private String autorzy;

    public Ksiazka(int id, String tytul, int idWydawnictwo, String wydawnictwo, String autorzy) {
        this.id = id;
        this.tytul = tytul;
        this.idWydawnictwo = idWydawnictwo;
        this.wydawnictwo = wydawnictwo;
        this.autorzy = autorzy;
    }

    public int getId() {
        return id;
    }

    public String getTytul() {
        return tytul;
    }

    public int getIdWydawnictwo() {
        return idWydawnictwo;
    }

    public String getWydawnictwo() {
        return wydawnictwo;
    }

    public String getAutorzy() {
        return autorzy;
    }
}
