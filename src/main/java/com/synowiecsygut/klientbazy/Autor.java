package com.synowiecsygut.klientbazy;

public class Autor {
    private int id;
    private String imie;
    private String nazwisko;

    public Autor(int id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
}
