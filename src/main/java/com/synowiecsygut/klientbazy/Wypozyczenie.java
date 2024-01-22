package com.synowiecsygut.klientbazy;

import java.time.LocalDate;

public class Wypozyczenie {
    private int idWypozyczenie;
    private int idCzytelnik;
    private String czytelnik;
    private int idEgzemplarz;
    private int idKsiazka;
    private String tytulKsiazki;
    private int idPracownikWyp;
    private String pracownikWyp;
    private LocalDate dataWypozyczenia;
    private int idPracownikZwrot;
    private String pracownikZwrot;
    private LocalDate dataZwrotu;

    public Wypozyczenie(int idWypozyczenie, int idCzytelnik, String czytelnik, int idEgzemplarz, int idKsiazka, String tytulKsiazki,
                        int idPracownikWyp, String pracownikWyp, LocalDate dataWypozyczenia, int idPracownikZwrot,
                        String pracownikZwrot, LocalDate dataZwrotu) {
        this.idWypozyczenie = idWypozyczenie;
        this.idCzytelnik = idCzytelnik;
        this.czytelnik = czytelnik;
        this.idEgzemplarz = idEgzemplarz;
        this.idKsiazka = idKsiazka;
        this.tytulKsiazki = tytulKsiazki;
        this.idPracownikWyp = idPracownikWyp;
        this.pracownikWyp = pracownikWyp;
        this.dataWypozyczenia = dataWypozyczenia;
        this.idPracownikZwrot = idPracownikZwrot;
        this.pracownikZwrot = pracownikZwrot;
        this.dataZwrotu = dataZwrotu;
    }

    public int getIdWypozyczenie() {
        return idWypozyczenie;
    }

    public int getIdCzytelnik() {
        return idCzytelnik;
    }

    public String getCzytelnik() {
        return czytelnik;
    }

    public int getIdEgzemplarz() {
        return idEgzemplarz;
    }

    public int getIdKsiazka() {
        return idKsiazka;
    }

    public String getTytulKsiazki() {
        return tytulKsiazki;
    }

    public int getIdPracownikWyp() {
        return idPracownikWyp;
    }

    public String getPracownikWyp() {
        return pracownikWyp;
    }

    public LocalDate getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public int getIdPracownikZwrot() {
        return idPracownikZwrot;
    }

    public String getPracownikZwrot() {
        return pracownikZwrot;
    }

    public LocalDate getDataZwrotu() {
        return dataZwrotu;
    }

    // Puste gettery zwracające int
    public int getIdWypozyczenia() {
        return idWypozyczenie;
    }

    public int getIdCzytelnika() {
        return idCzytelnik;
    }

    public int getIdEgzemplarza() {
        return idEgzemplarz;
    }

    public int getIdKsiazki() {
        return idKsiazka;
    }

    public int getIdPracownikaWyp() {
        return idPracownikWyp;
    }

    public int getIdPracownikaZwr() {
        return idPracownikZwrot;
    }

    public String getPracownikZwr()
    {
        return pracownikZwrot;
    }
}
