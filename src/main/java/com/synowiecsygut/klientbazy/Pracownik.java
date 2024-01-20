package com.synowiecsygut.klientbazy;

public class Pracownik {
    private int id;
    private String imie;
    private String nazwisko;
    private float wynagrodzenie;
    private String stanowisko;

    // Konstruktor, gettery i settery

    public Pracownik(int id, String imie, String nazwisko, float wynagrodzenie, String stanowisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wynagrodzenie = wynagrodzenie;
        this.stanowisko = stanowisko;
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

    public float getWynagrodzenie() {
        return wynagrodzenie;
    }

    public String getStanowisko() {
        return stanowisko;
    }
}
