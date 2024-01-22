package com.synowiecsygut.klientbazy;

public class StatystykiEgzemplarzy {

    private int idKsiazka;
    private String tytul;
    private int iloscWypozyczonych;
    private int iloscWolnych;
    private int ilosc;

    public StatystykiEgzemplarzy(int idKsiazka, String tytul, int iloscWypozyczonych, int iloscWolnych, int ilosctotal) {
        this.idKsiazka = idKsiazka;
        this.tytul = tytul;
        this.iloscWypozyczonych = iloscWypozyczonych;
        this.iloscWolnych = iloscWolnych;
        this.ilosc=ilosctotal;
    }


    public int getIdKsiazka() {
        return idKsiazka;
    }

    public void setIdKsiazka(int idKsiazka) {
        this.idKsiazka = idKsiazka;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getIloscWypozyczonych() {
        return iloscWypozyczonych;
    }

    public void setIloscWypozyczonych(int iloscWypozyczonych) {
        this.iloscWypozyczonych = iloscWypozyczonych;
    }

    public int getIloscWolnych() {
        return iloscWolnych;
    }

    public void setIloscWolnych(int iloscWolnych) {
        this.iloscWolnych = iloscWolnych;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
