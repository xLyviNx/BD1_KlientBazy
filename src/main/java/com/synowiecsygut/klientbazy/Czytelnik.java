package com.synowiecsygut.klientbazy;

public class Czytelnik {
    private int id;
    private String imie;
    private String nazwisko;
    private String ulica;
    private String nrDomu;
    private String nrLokalu;
    private String kodPocztowy;
    private float kara;

    public Czytelnik(int id, String imie, String nazwisko, String ulica, String nrDomu, String nrLokalu, String kodPocztowy, float kara) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.nrLokalu = nrLokalu;
        this.kodPocztowy = kodPocztowy;
        this.kara = kara;
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

    public String getUlica() {
        return ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public float getKara() {
        return kara;
    }
}
