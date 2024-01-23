--------------------------------------------------------
--  File created - Tuesday-January-23-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence CZYTELNIK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "C##KLIENTBAZA"."CZYTELNIK_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 11 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence EGZEMPLARZ_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "C##KLIENTBAZA"."EGZEMPLARZ_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 48 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence KSIAZKI_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "C##KLIENTBAZA"."KSIAZKI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence PRAC_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "C##KLIENTBAZA"."PRAC_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 43 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_AUTOR
--------------------------------------------------------

   CREATE SEQUENCE  "C##KLIENTBAZA"."SEQ_AUTOR"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 8 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_WYDAWNICTWO
--------------------------------------------------------

   CREATE SEQUENCE  "C##KLIENTBAZA"."SEQ_WYDAWNICTWO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence WYPO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "C##KLIENTBAZA"."WYPO_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 7 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table AUTORZY
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."AUTORZY" 
   (	"ID_AUTOR" NUMBER(6,0), 
	"NAZWISKO" VARCHAR2(20 BYTE), 
	"IMIE" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table AUTORZY_KSIAZEK
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."AUTORZY_KSIAZEK" 
   (	"WAZNOSC" NUMBER(2,0), 
	"ID_KSIAZKA" NUMBER(6,0), 
	"ID_AUTOR" NUMBER(6,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CZYTELNICY
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."CZYTELNICY" 
   (	"ID_CZYTELNIK" NUMBER(10,0), 
	"NAZWISKO" VARCHAR2(30 BYTE), 
	"IMIE" VARCHAR2(30 BYTE), 
	"ULICA" VARCHAR2(30 BYTE), 
	"NR_DOMU" VARCHAR2(8 BYTE), 
	"NR_LOKALU" VARCHAR2(4 BYTE), 
	"KOD_POCZTOWY" VARCHAR2(6 BYTE), 
	"POCZTA" VARCHAR2(20 BYTE), 
	"KARA" NUMBER(6,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EGZEMPLARZE
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."EGZEMPLARZE" 
   (	"ID_EGZEMPLARZ" NUMBER(6,0), 
	"ID_KSIAZKA" NUMBER(6,0), 
	"ROK_WYDANIA" NUMBER(4,0), 
	"STAN" NUMBER(1,0) DEFAULT 1
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KSIAZKI
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."KSIAZKI" 
   (	"ID_KSIAZKA" NUMBER(6,0), 
	"TYTUL" VARCHAR2(30 BYTE), 
	"ID_WYDAWNICTWO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PRACOWNICY
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."PRACOWNICY" 
   (	"ID_PRACOWNIK" NUMBER(6,0), 
	"NAZWISKO" VARCHAR2(20 BYTE), 
	"IMIE" VARCHAR2(20 BYTE), 
	"WYNAGRODZENIE" NUMBER(8,2) DEFAULT (10000), 
	"STANOWISKO" VARCHAR2(20 BYTE) DEFAULT 'Bibliotekarz'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table WYDAWNICTWA
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."WYDAWNICTWA" 
   (	"ID_WYDAWNICTWO" NUMBER(10,0), 
	"NAZWA" VARCHAR2(20 BYTE), 
	"ULICA" VARCHAR2(30 BYTE), 
	"NR_DOMU" VARCHAR2(8 BYTE), 
	"NR_LOKALU" VARCHAR2(4 BYTE), 
	"KOD_POCZTOWY" VARCHAR2(6 BYTE), 
	"POCZTA" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table WYPOZYCZENIA
--------------------------------------------------------

  CREATE TABLE "C##KLIENTBAZA"."WYPOZYCZENIA" 
   (	"ID_WYPOZYCZENIE" NUMBER(10,0), 
	"ID_CZYTELNIK" NUMBER(10,0), 
	"ID_EGZEMPLARZ" NUMBER(6,0), 
	"ID_PRACOWNIK_WYP" NUMBER(6,0), 
	"DATA_WYPOZYCZENIA" DATE DEFAULT (sysdate), 
	"ID_PRACOWNIK_ZWR" NUMBER(6,0), 
	"DATA_ZWROTU" DATE DEFAULT (NULL)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for View WIDOK_AUTORZY_KSIAZEK
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_AUTORZY_KSIAZEK" ("ID_KSIAZKA", "TYTUL", "IMIE_I_NAZWISKO", "ID_AUTOR", "WAZNOSC") AS 
  SELECT ak.id_ksiazka, ks.TYTUL, au.IMIE || ' ' || au.NAZWISKO AS imie_i_nazwisko, ak.id_autor, ak.waznosc
FROM AUTORZY_KSIAZEK ak
LEFT JOIN KSIAZKI ks ON ak.id_ksiazka = ks.id_ksiazka
LEFT JOIN autorzy au ON ak.id_autor = au.id_autor
;
--------------------------------------------------------
--  DDL for View WIDOK_CZYTELNICY
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_CZYTELNICY" ("ID_CZYTELNIK", "NAZWISKO", "IMIE", "ULICA", "NR_DOMU", "NR_LOKALU", "KOD_POCZTOWY", "POCZTA", "KARA", "WYPOZYCZEN") AS 
  SELECT
    c."ID_CZYTELNIK",c."NAZWISKO",c."IMIE",c."ULICA",c."NR_DOMU",c."NR_LOKALU",c."KOD_POCZTOWY",c."POCZTA",c."KARA",
    (
        SELECT COUNT(*)
        FROM WYPOZYCZENIA w
        WHERE c.ID_CZYTELNIK = w.ID_CZYTELNIK AND w.DATA_ZWROTU IS NULL
    ) AS WYPOZYCZEN  -- Use the alias directly without table prefix
FROM
    CZYTELNICY c
;
--------------------------------------------------------
--  DDL for View WIDOK_EGZEMPLARZE_KSIAZKI
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_EGZEMPLARZE_KSIAZKI" ("ID_EGZEMPLARZ", "ID_KSIAZKA", "TYTUL", "ROK_WYDANIA", "STAN") AS 
  SELECT 
    e.id_egzemplarz,
    e.id_ksiazka,
    ks.tytul,
    e.rok_wydania,
    e.stan
FROM egzemplarze e
LEFT JOIN ksiazki ks ON e.id_ksiazka = ks.id_ksiazka
;
--------------------------------------------------------
--  DDL for View WIDOK_KSIAZKI_AUTORZY
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_KSIAZKI_AUTORZY" ("ID_KSIAZKA", "TYTUL", "ID_WYDAWNICTWO", "WYDAWNICTWO", "AUTORZY") AS 
  SELECT 
    ks.id_ksiazka, 
    ks.tytul, 
    wyd.id_wydawnictwo, 
    wyd.nazwa AS wydawnictwo, 
    LISTAGG(a.imie || ' ' || a.nazwisko, ', ') WITHIN GROUP (ORDER BY ak.waznosc DESC) AS autorzy
FROM ksiazki ks
LEFT JOIN wydawnictwa wyd ON ks.id_wydawnictwo = wyd.id_wydawnictwo
LEFT JOIN autorzy_ksiazek ak ON ks.id_ksiazka = ak.id_ksiazka
LEFT JOIN autorzy a ON ak.id_autor = a.id_autor
GROUP BY ks.id_ksiazka, ks.tytul, wyd.id_wydawnictwo, wyd.nazwa
;
--------------------------------------------------------
--  DDL for View WIDOK_STATYSTYKI_EGZEMPLARZY
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_STATYSTYKI_EGZEMPLARZY" ("ID_KSIAZKA", "TYTUL", "ILOSC_WYPOZYCZONYCH", "ILOSC_WOLNYCH") AS 
  SELECT
    K.ID_KSIAZKA,
    K.TYTUL,
    COUNT(W.ID_EGZEMPLARZ) AS ILOSC_WYPOZYCZONYCH,
    (SELECT COUNT(*) FROM EGZEMPLARZE E WHERE E.ID_KSIAZKA = K.ID_KSIAZKA AND E.STAN = 1) AS ILOSC_WOLNYCH
FROM
    KSIAZKI K
LEFT JOIN EGZEMPLARZE E ON K.ID_KSIAZKA = E.ID_KSIAZKA
LEFT JOIN WYPOZYCZENIA W ON E.ID_EGZEMPLARZ = W.ID_EGZEMPLARZ
GROUP BY K.ID_KSIAZKA, K.TYTUL
;
--------------------------------------------------------
--  DDL for View WIDOK_STATYSTYKI_SUMMARY
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_STATYSTYKI_SUMMARY" ("SUM_ILOSC_WYPOZYCZONYCH", "SUM_ILOSC_WOLNYCH", "PROCENT_WOLNYCH") AS 
  SELECT 
    SUM(ILOSC_WYPOZYCZONYCH) AS SUM_ILOSC_WYPOZYCZONYCH,
    SUM(ILOSC_WOLNYCH) AS SUM_ILOSC_WOLNYCH,
    (SUM(ILOSC_WOLNYCH)/(SUM(ILOSC_WOLNYCH)+SUM(ILOSC_WYPOZYCZONYCH))* 100) AS PROCENT_WOLNYCH
FROM WIDOK_STATYSTYKI_EGZEMPLARZY
;
--------------------------------------------------------
--  DDL for View WIDOK_WYPOZYCZENIA
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_WYPOZYCZENIA" ("ID_WYPOZYCZENIE", "ID_CZYTELNIK", "CZYTELNIK", "ID_EGZEMPLARZ", "ID_KSIAZKA", "TYTUL_KSIAZKI", "ID_PRACOWNIK_WYP", "PRACOWNIK_WYP", "DATA_WYPOZYCZENIA", "ID_PRACOWNIK_ZWR", "PRACOWNIK_ZWR", "DATA_ZWROTU") AS 
  SELECT
    W.ID_WYPOZYCZENIE,
    W.ID_CZYTELNIK,
    C.IMIE || ' ' || C.NAZWISKO AS CZYTELNIK,
    W.ID_EGZEMPLARZ,
    E.ID_KSIAZKA,
    K.TYTUL AS TYTUL_KSIAZKI,
    W.ID_PRACOWNIK_WYP,
    PW.IMIE || ' ' || PW.NAZWISKO AS PRACOWNIK_WYP,
    W.DATA_WYPOZYCZENIA,
    W.ID_PRACOWNIK_ZWR,
    PZ.IMIE || ' ' || PZ.NAZWISKO AS PRACOWNIK_ZWR,
    W.DATA_ZWROTU
FROM
    C##KLIENTBAZA.WYPOZYCZENIA W
    JOIN C##KLIENTBAZA.CZYTELNICY C ON W.ID_CZYTELNIK = C.ID_CZYTELNIK
    JOIN C##KLIENTBAZA.EGZEMPLARZE E ON W.ID_EGZEMPLARZ = E.ID_EGZEMPLARZ
    JOIN C##KLIENTBAZA.KSIAZKI K ON E.ID_KSIAZKA = K.ID_KSIAZKA
    JOIN C##KLIENTBAZA.PRACOWNICY PW ON W.ID_PRACOWNIK_WYP = PW.ID_PRACOWNIK
    LEFT JOIN C##KLIENTBAZA.PRACOWNICY PZ ON W.ID_PRACOWNIK_ZWR = PZ.ID_PRACOWNIK
;
--------------------------------------------------------
--  DDL for View WIDOK_WYPOZYCZENIA_BEZZWROTU
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##KLIENTBAZA"."WIDOK_WYPOZYCZENIA_BEZZWROTU" ("ID_WYPOZYCZENIE", "ID_CZYTELNIK", "CZYTELNIK", "ID_EGZEMPLARZ", "ID_KSIAZKA", "TYTUL_KSIAZKI", "ID_PRACOWNIK_WYP", "PRACOWNIK_WYP", "DATA_WYPOZYCZENIA", "ID_PRACOWNIK_ZWR", "PRACOWNIK_ZWR", "DATA_ZWROTU") AS 
  SELECT
    W.ID_WYPOZYCZENIE,
    W.ID_CZYTELNIK,
    C.IMIE || ' ' || C.NAZWISKO AS CZYTELNIK,
    W.ID_EGZEMPLARZ,
    E.ID_KSIAZKA,
    K.TYTUL AS TYTUL_KSIAZKI,
    W.ID_PRACOWNIK_WYP,
    PW.IMIE || ' ' || PW.NAZWISKO AS PRACOWNIK_WYP,
    W.DATA_WYPOZYCZENIA,
    W.ID_PRACOWNIK_ZWR,
    PZ.IMIE || ' ' || PZ.NAZWISKO AS PRACOWNIK_ZWR,
    W.DATA_ZWROTU
FROM
    C##KLIENTBAZA.WYPOZYCZENIA W
    JOIN C##KLIENTBAZA.CZYTELNICY C ON W.ID_CZYTELNIK = C.ID_CZYTELNIK
    JOIN C##KLIENTBAZA.EGZEMPLARZE E ON W.ID_EGZEMPLARZ = E.ID_EGZEMPLARZ
    JOIN C##KLIENTBAZA.KSIAZKI K ON E.ID_KSIAZKA = K.ID_KSIAZKA
    JOIN C##KLIENTBAZA.PRACOWNICY PW ON W.ID_PRACOWNIK_WYP = PW.ID_PRACOWNIK
    LEFT JOIN C##KLIENTBAZA.PRACOWNICY PZ ON W.ID_PRACOWNIK_ZWR = PZ.ID_PRACOWNIK
WHERE
    W.DATA_ZWROTU IS NULL OR PZ.ID_PRACOWNIK IS NULL
;
REM INSERTING into C##KLIENTBAZA.AUTORZY
SET DEFINE OFF;
Insert into C##KLIENTBAZA.AUTORZY (ID_AUTOR,NAZWISKO,IMIE) values ('1','Sienkiewicz','Henryk');
Insert into C##KLIENTBAZA.AUTORZY (ID_AUTOR,NAZWISKO,IMIE) values ('2','Orzeszkowa','Eliza');
Insert into C##KLIENTBAZA.AUTORZY (ID_AUTOR,NAZWISKO,IMIE) values ('3','Prus','Boleslaw');
Insert into C##KLIENTBAZA.AUTORZY (ID_AUTOR,NAZWISKO,IMIE) values ('4','Wyspianski','Stanislaw');
Insert into C##KLIENTBAZA.AUTORZY (ID_AUTOR,NAZWISKO,IMIE) values ('5','Nalkowska','Zofia');
REM INSERTING into C##KLIENTBAZA.AUTORZY_KSIAZEK
SET DEFINE OFF;
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('1','8','3');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('1','1','1');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('1','3','3');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('1','4','4');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('1','5','5');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('1','6','1');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('1','7','1');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('2','7','2');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('3','7','4');
Insert into C##KLIENTBAZA.AUTORZY_KSIAZEK (WAZNOSC,ID_KSIAZKA,ID_AUTOR) values ('4','7','5');
REM INSERTING into C##KLIENTBAZA.CZYTELNICY
SET DEFINE OFF;
Insert into C##KLIENTBAZA.CZYTELNICY (ID_CZYTELNIK,NAZWISKO,IMIE,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA,KARA) values ('10','Pudzianowski','Mariusz',null,null,null,null,null,'0');
Insert into C##KLIENTBAZA.CZYTELNICY (ID_CZYTELNIK,NAZWISKO,IMIE,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA,KARA) values ('1','Kowalski','Adam',null,null,null,null,null,'0');
Insert into C##KLIENTBAZA.CZYTELNICY (ID_CZYTELNIK,NAZWISKO,IMIE,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA,KARA) values ('2','Nowak','Jan',null,null,null,null,null,'10');
Insert into C##KLIENTBAZA.CZYTELNICY (ID_CZYTELNIK,NAZWISKO,IMIE,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA,KARA) values ('3','Malinowski','Henryk',null,null,null,null,null,'20');
Insert into C##KLIENTBAZA.CZYTELNICY (ID_CZYTELNIK,NAZWISKO,IMIE,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA,KARA) values ('4','Kowalczyk','Adam',null,null,null,null,null,'0');
REM INSERTING into C##KLIENTBAZA.EGZEMPLARZE
SET DEFINE OFF;
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('8','1','1997','1');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('9','1','311','1');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('28','8','2021','0');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('1','1','2001','1');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('2','1','2001','0');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('3','1','2001','1');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('4','1','2001','1');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('5','2','2002','0');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('6','2','2005','1');
Insert into C##KLIENTBAZA.EGZEMPLARZE (ID_EGZEMPLARZ,ID_KSIAZKA,ROK_WYDANIA,STAN) values ('7','3','2000','0');
REM INSERTING into C##KLIENTBAZA.KSIAZKI
SET DEFINE OFF;
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('8','TEST','3');
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('9','TEST2',null);
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('1','Potop',null);
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('2','Nad Niemnem',null);
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('3','Lalka','2');
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('4','Wesele',null);
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('5','Granica',null);
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('6','W pustyni i w puszczy',null);
Insert into C##KLIENTBAZA.KSIAZKI (ID_KSIAZKA,TYTUL,ID_WYDAWNICTWO) values ('7','Literatura polska',null);
REM INSERTING into C##KLIENTBAZA.PRACOWNICY
SET DEFINE OFF;
Insert into C##KLIENTBAZA.PRACOWNICY (ID_PRACOWNIK,NAZWISKO,IMIE,WYNAGRODZENIE,STANOWISKO) values ('3','TESTOWY1','ktos','1453','jakies');
Insert into C##KLIENTBAZA.PRACOWNICY (ID_PRACOWNIK,NAZWISKO,IMIE,WYNAGRODZENIE,STANOWISKO) values ('5','PaŸdzioch','Marian','1500','ok');
Insert into C##KLIENTBAZA.PRACOWNICY (ID_PRACOWNIK,NAZWISKO,IMIE,WYNAGRODZENIE,STANOWISKO) values ('25','Qqweqwe','qweqwasfcd','124','dasdqw');
Insert into C##KLIENTBAZA.PRACOWNICY (ID_PRACOWNIK,NAZWISKO,IMIE,WYNAGRODZENIE,STANOWISKO) values ('1','Nowakowski','Marcin','1500','Bibliotekarz');
Insert into C##KLIENTBAZA.PRACOWNICY (ID_PRACOWNIK,NAZWISKO,IMIE,WYNAGRODZENIE,STANOWISKO) values ('2','Polski','Jan','2000','Bibliotekarz');
REM INSERTING into C##KLIENTBAZA.WYDAWNICTWA
SET DEFINE OFF;
Insert into C##KLIENTBAZA.WYDAWNICTWA (ID_WYDAWNICTWO,NAZWA,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA) values ('5','asddas','2','3','4','qw123','eeq');
Insert into C##KLIENTBAZA.WYDAWNICTWA (ID_WYDAWNICTWO,NAZWA,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA) values ('1','PWN','Daleka','1','2','25-900','Kielce');
Insert into C##KLIENTBAZA.WYDAWNICTWA (ID_WYDAWNICTWO,NAZWA,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA) values ('2','WNT','Kopernika','3A','4','25-800','Kielce');
Insert into C##KLIENTBAZA.WYDAWNICTWA (ID_WYDAWNICTWO,NAZWA,ULICA,NR_DOMU,NR_LOKALU,KOD_POCZTOWY,POCZTA) values ('3','PsK','1000-lecia P.P.','7',null,'25-314','Kielce');
REM INSERTING into C##KLIENTBAZA.WYPOZYCZENIA
SET DEFINE OFF;
Insert into C##KLIENTBAZA.WYPOZYCZENIA (ID_WYPOZYCZENIE,ID_CZYTELNIK,ID_EGZEMPLARZ,ID_PRACOWNIK_WYP,DATA_WYPOZYCZENIA,ID_PRACOWNIK_ZWR,DATA_ZWROTU) values ('6','10','28','25',to_date('24/01/23','RR/MM/DD'),null,null);
Insert into C##KLIENTBAZA.WYPOZYCZENIA (ID_WYPOZYCZENIE,ID_CZYTELNIK,ID_EGZEMPLARZ,ID_PRACOWNIK_WYP,DATA_WYPOZYCZENIA,ID_PRACOWNIK_ZWR,DATA_ZWROTU) values ('1','1','2','1',to_date('05/10/01','RR/MM/DD'),null,null);
Insert into C##KLIENTBAZA.WYPOZYCZENIA (ID_WYPOZYCZENIE,ID_CZYTELNIK,ID_EGZEMPLARZ,ID_PRACOWNIK_WYP,DATA_WYPOZYCZENIA,ID_PRACOWNIK_ZWR,DATA_ZWROTU) values ('2','1','5','2',to_date('05/11/13','RR/MM/DD'),'2',to_date('05/12/10','RR/MM/DD'));
Insert into C##KLIENTBAZA.WYPOZYCZENIA (ID_WYPOZYCZENIE,ID_CZYTELNIK,ID_EGZEMPLARZ,ID_PRACOWNIK_WYP,DATA_WYPOZYCZENIA,ID_PRACOWNIK_ZWR,DATA_ZWROTU) values ('3','2','7','2',to_date('06/01/21','RR/MM/DD'),null,null);
--------------------------------------------------------
--  DDL for Index AK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."AK_PK" ON "C##KLIENTBAZA"."AUTORZY_KSIAZEK" ("ID_KSIAZKA", "ID_AUTOR") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index AUTOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."AUTOR_PK" ON "C##KLIENTBAZA"."AUTORZY" ("ID_AUTOR") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index CZYTELNIK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."CZYTELNIK_PK" ON "C##KLIENTBAZA"."CZYTELNICY" ("ID_CZYTELNIK") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index EGZEMPLARZ_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."EGZEMPLARZ_PK" ON "C##KLIENTBAZA"."EGZEMPLARZE" ("ID_EGZEMPLARZ") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index KSIAZKA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."KSIAZKA_PK" ON "C##KLIENTBAZA"."KSIAZKI" ("ID_KSIAZKA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PRACOWNIK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."PRACOWNIK_PK" ON "C##KLIENTBAZA"."PRACOWNICY" ("ID_PRACOWNIK") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index WYDAWNICTWO_NAZWA_U
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."WYDAWNICTWO_NAZWA_U" ON "C##KLIENTBAZA"."WYDAWNICTWA" ("NAZWA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index WYDAWNICTWO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."WYDAWNICTWO_PK" ON "C##KLIENTBAZA"."WYDAWNICTWA" ("ID_WYDAWNICTWO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index WYPOZYCZENIE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##KLIENTBAZA"."WYPOZYCZENIE_PK" ON "C##KLIENTBAZA"."WYPOZYCZENIA" ("ID_WYPOZYCZENIE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger AFTER_DELETE_WYPOZYCZENIE
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##KLIENTBAZA"."AFTER_DELETE_WYPOZYCZENIE" 
AFTER DELETE ON WYPOZYCZENIA
FOR EACH ROW
BEGIN
    UPDATE EGZEMPLARZE SET STAN = 1 WHERE ID_EGZEMPLARZ = :OLD.ID_EGZEMPLARZ;
END AFTER_Delete_Wypozyczenie;

/
ALTER TRIGGER "C##KLIENTBAZA"."AFTER_DELETE_WYPOZYCZENIE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BEFORE_DELETE_CZYTELNIK
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##KLIENTBAZA"."BEFORE_DELETE_CZYTELNIK" 
BEFORE DELETE ON CZYTELNICY
FOR EACH ROW
BEGIN
    EXECUTE IMMEDIATE 'DELETE FROM WYPOZYCZENIA WHERE ID_CZYTELNIK = (:1)' USING :OLD.ID_CZYTELNIK;
END Before_Delete_Czytelnik;

/
ALTER TRIGGER "C##KLIENTBAZA"."BEFORE_DELETE_CZYTELNIK" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BEFORE_DELETE_PRACOWNIK
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##KLIENTBAZA"."BEFORE_DELETE_PRACOWNIK" 
BEFORE DELETE ON PRACOWNICY
FOR EACH ROW
BEGIN
    -- Aktualizacja wypo¿yczeñ, gdzie pracownik by³ odpowiedzialny za wypo¿yczenie
    UPDATE WYPOZYCZENIA
    SET ID_PRACOWNIK_WYP = NULL
    WHERE ID_PRACOWNIK_WYP = :OLD.ID_PRACOWNIK;

    -- Aktualizacja wypo¿yczeñ, gdzie pracownik by³ odpowiedzialny za zwrot
    UPDATE WYPOZYCZENIA
    SET ID_PRACOWNIK_ZWR = NULL
    WHERE ID_PRACOWNIK_ZWR = :OLD.ID_PRACOWNIK;
END Before_Delete_Pracownik_Zwrot;

/
ALTER TRIGGER "C##KLIENTBAZA"."BEFORE_DELETE_PRACOWNIK" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USUNAUTORATRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##KLIENTBAZA"."USUNAUTORATRIGGER" 
AFTER DELETE ON autorzy
FOR EACH ROW
BEGIN
    DELETE FROM autorzy_ksiazek WHERE id_autor = :OLD.id_autor;
END UsunAutoraTrigger;

/
ALTER TRIGGER "C##KLIENTBAZA"."USUNAUTORATRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USUNKSIAZKETRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##KLIENTBAZA"."USUNKSIAZKETRIGGER" 
AFTER DELETE ON ksiazki
FOR EACH ROW
BEGIN
    DELETE FROM autorzy_ksiazek WHERE id_ksiazka = :OLD.id_ksiazka;
END UsunKsiazkeTrigger;

/
ALTER TRIGGER "C##KLIENTBAZA"."USUNKSIAZKETRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Procedure DODAJAUTORA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJAUTORA" (
    p_Nazwisko VARCHAR2,
    p_Imie VARCHAR2,
    p_Success OUT NUMBER
) AS
    v_IdAutor NUMBER;
BEGIN
    SELECT Seq_Autor.NEXTVAL INTO v_IdAutor FROM DUAL;
    INSERT INTO Autorzy (id_autor, Nazwisko, Imie)
    VALUES (v_IdAutor, p_Nazwisko, p_Imie);

    p_Success := 1;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        p_Success := 0;
        RAISE_APPLICATION_ERROR(-20001, 'B³¹d: ' || SQLERRM);
END DodajAutora;

/
--------------------------------------------------------
--  DDL for Procedure DODAJAUTORAKSIAZKI
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJAUTORAKSIAZKI" (
    p_id_ksiazka IN NUMBER,
    p_id_autor IN NUMBER,
    p_waznosc IN NUMBER,
    p_success OUT NUMBER
) AS
    v_autor_exists NUMBER;
    v_ksiazka_exists NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_autor_exists FROM autorzy WHERE id_autor = p_id_autor;
    IF v_autor_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Podany autor nie istnieje.');
    END IF;
    SELECT COUNT(*) INTO v_ksiazka_exists FROM ksiazki WHERE id_ksiazka = p_id_ksiazka;
    IF v_ksiazka_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Podana ksi¹¿ka nie istnieje.');
    END IF;
    FOR c IN (SELECT 1 FROM autorzy_ksiazek WHERE id_ksiazka = p_id_ksiazka AND id_autor = p_id_autor)
    LOOP
        p_success := 0;
        RAISE_APPLICATION_ERROR(-20002, 'Ta ksi¹¿ka ju¿ ma tego autora.');
    END LOOP;
    BEGIN
        INSERT INTO autorzy_ksiazek (id_ksiazka, id_autor, waznosc)
        VALUES (p_id_ksiazka, p_id_autor, p_waznosc);
        p_success := 1;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            p_success := 0;
            RAISE_APPLICATION_ERROR(-20001, 'Nie uda³o siê dodaæ autora ksi¹¿ki. ' || SQLERRM);
    END;
END DodajAutoraKsiazki;

/
--------------------------------------------------------
--  DDL for Procedure DODAJCZYTELNIKA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJCZYTELNIKA" (
    p_nazwisko IN VARCHAR2,
    p_imie IN VARCHAR2,
    p_ulica IN VARCHAR2,
    p_nr_domu IN VARCHAR2,
    p_nr_lokalu IN VARCHAR2,
    p_kod_pocztowy IN VARCHAR2,
    p_kara IN NUMBER,
    p_success OUT NUMBER
) AS
    v_id NUMBER;
BEGIN
    v_id := 0;
    SELECT czytelnik_seq.NEXTVAL INTO v_id FROM dual;
    BEGIN
        INSERT INTO czytelnicy (id_czytelnik, nazwisko, imie, ulica, nr_domu, nr_lokalu, kod_pocztowy, kara)
        VALUES (v_id, p_nazwisko, p_imie, NULLIF(p_ulica, ''), NULLIF(p_nr_domu, ''), NULLIF(p_nr_lokalu, ''), NULLIF(p_kod_pocztowy, ''), p_kara);
        p_success := 1;
        COMMIT;

    EXCEPTION
        WHEN OTHERS THEN
            p_success := 0;
            RAISE_APPLICATION_ERROR(-20001, 'Nie uda³o siê dodaæ czytelnika. ' || SQLERRM);
    END;
END DodajCzytelnika;

/
--------------------------------------------------------
--  DDL for Procedure DODAJEGZEMPLARZ
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJEGZEMPLARZ" (
    p_idksiazki IN NUMBER,
    p_rokwydania IN NUMBER,
    p_stan IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    p_success := 0;

    IF p_idksiazki IS NULL OR p_rokwydania IS NULL OR p_stan IS NULL THEN
        raise_application_error(-20001, 'Wszystkie dane musz¹ byæ wype³nione!');
    END IF;

    IF p_stan NOT IN (0, 1) THEN
        raise_application_error(-20002, 'Stan musi byæ 0 (niedostêpny) lub 1 (dostêpny)!');
    END IF;

    BEGIN
        INSERT INTO Egzemplarze VALUES (egzemplarz_seq.NEXTVAL, p_idksiazki, p_rokwydania, p_stan);
        COMMIT;
        p_success := 1;
    EXCEPTION
        WHEN OTHERS THEN
            p_success := 0;
            raise_application_error(-20003, 'B³¹d podczas wstawiania danych do tabeli Egzemplarze: ' || SQLERRM);
    END;
END DodajEgzemplarz;

/
--------------------------------------------------------
--  DDL for Procedure DODAJKSIAZKE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJKSIAZKE" (
    p_tytul IN VARCHAR2,
    p_id_wydawnictwo IN NUMBER,
    p_success OUT NUMBER
) AS
    v_wydawnictwo_count NUMBER;
    v_actual_id_wydawnictwo NUMBER;
BEGIN
    p_success := 0;
    IF p_tytul IS NULL THEN
        raise_application_error(-20001, 'Tytu³ musi byæ wype³niony!');
    END IF;
    IF p_id_wydawnictwo > 0 THEN
        SELECT COUNT(*) INTO v_wydawnictwo_count FROM Wydawnictwa WHERE id_wydawnictwo = p_id_wydawnictwo;
        IF v_wydawnictwo_count = 0 THEN
            raise_application_error(-20002, 'Wydawnictwo o podanym ID nie istnieje!');
        END IF;
        v_actual_id_wydawnictwo := p_id_wydawnictwo;
    ELSE
        v_actual_id_wydawnictwo := NULL;
    END IF;
    BEGIN
        INSERT INTO Ksiazki (id_ksiazka, tytul, id_wydawnictwo) 
        VALUES (ksiazki_seq.NEXTVAL, p_tytul, v_actual_id_wydawnictwo);
        COMMIT;
        p_success := 1;
    EXCEPTION
        WHEN OTHERS THEN
            p_success := 0;
            raise_application_error(-20003, 'B³¹d podczas wstawiania danych do tabeli Ksiazki: ' || SQLERRM);
    END;
END DodajKsiazke;

/
--------------------------------------------------------
--  DDL for Procedure DODAJPRACOWNIKA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJPRACOWNIKA" (
    p_naz IN VARCHAR2,
    p_imie IN VARCHAR2,
    p_wynagrodzenie IN NUMBER,
    p_stanowisko IN VARCHAR2,
    p_success OUT NUMBER
) AS
BEGIN
    p_success := 0;
    IF p_naz IS NULL OR p_imie IS NULL OR p_stanowisko IS NULL THEN
        raise_application_error(-20001, 'Wszystkie dane musz¹ byæ wype³nione!');
    END IF;

    IF p_wynagrodzenie IS NULL THEN
        raise_application_error(-20002, 'Wynagrodzenie musi byæ liczb¹!');
    END IF;
    BEGIN
        INSERT INTO Pracownicy VALUES (prac_seq.NEXTVAL, p_naz, p_imie, p_wynagrodzenie, p_stanowisko);
        COMMIT;
        p_success := 1;
    EXCEPTION
        WHEN OTHERS THEN
            p_success := 0;
            raise_application_error(-20003, 'B³¹d podczas wstawiania danych do tabeli Pracownicy: ' || SQLERRM);
    END;
END DodajPracownika;

/
--------------------------------------------------------
--  DDL for Procedure DODAJWYDAWNICTWO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJWYDAWNICTWO" (
    p_Nazwa VARCHAR2,
    p_Ulica VARCHAR2,
    p_NrDomu VARCHAR2,
    p_NrLokalu VARCHAR2,
    p_KodPocztowy VARCHAR2,
    p_Poczta VARCHAR2,
    p_Success OUT NUMBER
) AS
    v_IdWydawnictwo NUMBER;
BEGIN
    SELECT Seq_Wydawnictwo.NEXTVAL INTO v_IdWydawnictwo FROM DUAL;
    INSERT INTO Wydawnictwa
    VALUES (v_IdWydawnictwo, p_Nazwa, p_Ulica, p_NrDomu, p_NrLokalu, p_KodPocztowy, p_Poczta);

    p_Success := 1;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        p_Success := 0;
        RAISE_APPLICATION_ERROR(-20001, 'B³¹d: ' || SQLERRM);
END DodajWydawnictwo;

/
--------------------------------------------------------
--  DDL for Procedure DODAJWYPOZYCZENIE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."DODAJWYPOZYCZENIE" (
    p_id_czytelnik IN NUMBER,
    p_id_egzemplarz IN NUMBER,
    p_id_pracownik_wyp IN NUMBER,
    p_data_wypozyczenia IN DATE,
    p_success OUT NUMBER
) AS
    v_egzemplarz_stan NUMBER;
BEGIN
    p_success := 0;

    IF p_id_czytelnik IS NULL OR p_id_egzemplarz IS NULL OR p_id_pracownik_wyp IS NULL OR p_data_wypozyczenia IS NULL THEN
        raise_application_error(-20001, 'Wszystkie dane musz¹ byæ wype³nione!');
    END IF;
    SELECT STAN INTO v_egzemplarz_stan FROM EGZEMPLARZE WHERE ID_EGZEMPLARZ = p_id_egzemplarz;

    IF v_egzemplarz_stan <> 1 THEN
        raise_application_error(-20002, 'Egzemplarz o ID ' || p_id_egzemplarz || ' jest ju¿ wypo¿yczony!');
    END IF;

    BEGIN
        INSERT INTO WYPOZYCZENIA (ID_WYPOZYCZENIE, ID_CZYTELNIK, ID_EGZEMPLARZ, ID_PRACOWNIK_WYP, DATA_WYPOZYCZENIA)
        VALUES (wypo_seq.NEXTVAL, p_id_czytelnik, p_id_egzemplarz, p_id_pracownik_wyp, p_data_wypozyczenia);
        UPDATE EGZEMPLARZE SET STAN = 0 WHERE ID_EGZEMPLARZ = p_id_egzemplarz;
        COMMIT;
        p_success := 1;
    EXCEPTION
        WHEN OTHERS THEN
            p_success := 0;
            raise_application_error(-20003, 'B³¹d podczas wstawiania danych do tabeli WYPOZYCZENIA: ' || SQLERRM);
    END;
END DodajWypozyczenie;

/
--------------------------------------------------------
--  DDL for Procedure EDYTUJAUTORA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."EDYTUJAUTORA" (
    p_IdAutor NUMBER,
    p_Nazwisko VARCHAR2,
    p_Imie VARCHAR2,
    p_Success OUT NUMBER
) AS
BEGIN
    UPDATE Autorzy
    SET 
        Nazwisko = COALESCE(p_Nazwisko, Nazwisko),
        Imie = COALESCE(p_Imie, Imie)
    WHERE id_autor = p_IdAutor;

    IF SQL%FOUND THEN
        p_Success := 1;
        COMMIT;
    ELSE
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'B³¹d: Nie znaleziono autora o podanym ID.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'B³¹d: ' || SQLERRM);
END EdytujAutora;

/
--------------------------------------------------------
--  DDL for Procedure EDYTUJCZYTELNIKA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."EDYTUJCZYTELNIKA" (
    p_id_czytelnik IN NUMBER,
    p_nazwisko IN VARCHAR2,
    p_imie IN VARCHAR2,
    p_ulica IN VARCHAR2,
    p_nr_domu IN VARCHAR2,
    p_nr_lokalu IN VARCHAR2,
    p_kod_pocztowy IN VARCHAR2,
    p_kara IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    p_success := 0;
    BEGIN
        SELECT COUNT(*) INTO p_success FROM czytelnicy WHERE id_czytelnik = p_id_czytelnik;

        IF p_success = 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'Czytelnik o podanym ID nie istnieje.');
        END IF;
        UPDATE czytelnicy
        SET
            nazwisko = COALESCE(p_nazwisko, nazwisko),
            imie = COALESCE(p_imie, imie),
            ulica = COALESCE(p_ulica, ulica),
            nr_domu = COALESCE(p_nr_domu, nr_domu),
            nr_lokalu = COALESCE(p_nr_lokalu, nr_lokalu),
            kod_pocztowy = COALESCE(p_kod_pocztowy, kod_pocztowy),
            kara = CASE WHEN p_kara < 0 THEN kara ELSE p_kara END
        WHERE id_czytelnik = p_id_czytelnik;
        p_success := SQL%ROWCOUNT;
        IF p_success = 1 THEN
            COMMIT;
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20002, 'B³¹d: ' || SQLCODE || ' - ' || SQLERRM);
    END;
END EdytujCzytelnika;

/
--------------------------------------------------------
--  DDL for Procedure EDYTUJEGZEMPLARZ
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."EDYTUJEGZEMPLARZ" (
    p_id IN NUMBER,
    p_idksiazki IN NUMBER,
    p_rokwydania IN NUMBER,
    p_stan IN NUMBER,
    p_success OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    p_success := 0;
    SELECT COUNT(*) INTO v_count FROM Egzemplarze WHERE id_egzemplarz = p_id;

    IF v_count > 0 THEN
        BEGIN
            IF p_stan NOT IN (0, 1) AND p_stan > 0 THEN
                raise_application_error(-20008, 'Nieprawid³owa wartoœæ dla stanu egzemplarza!');
            END IF;

            UPDATE Egzemplarze
            SET
                id_ksiazka = CASE WHEN p_idksiazki >= 1 THEN p_idksiazki ELSE id_ksiazka END,
                rok_wydania = CASE WHEN p_rokwydania >= 0 THEN p_rokwydania ELSE rok_wydania END,
                stan = CASE WHEN p_stan IN (0, 1) THEN p_stan ELSE stan END
            WHERE id_egzemplarz = p_id;

            COMMIT;
            p_success := 1;
        EXCEPTION
            WHEN OTHERS THEN
                p_success := 0;
                raise_application_error(-20007, 'B³¹d podczas aktualizacji egzemplarza: ' || SQLERRM);
        END;
    ELSE
        raise_application_error(-20006, 'Egzemplarz o podanym ID nie istnieje!');
    END IF;
END EdytujEgzemplarz;

/
--------------------------------------------------------
--  DDL for Procedure EDYTUJKSIAZKE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."EDYTUJKSIAZKE" (
    p_id IN NUMBER,
    p_tytul IN VARCHAR2,
    p_id_wydawnictwo IN NUMBER,
    p_success OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    p_success := 0;
    SELECT COUNT(*) INTO v_count FROM Ksiazki WHERE id_ksiazka = p_id;
    IF v_count > 0 THEN
        BEGIN
            UPDATE Ksiazki
            SET
                tytul = COALESCE(p_tytul, tytul),
                id_wydawnictwo = COALESCE(p_id_wydawnictwo, id_wydawnictwo)
            WHERE id_ksiazka = p_id;
            COMMIT;
            p_success := 1;
        EXCEPTION
            WHEN OTHERS THEN
                p_success := 0;
                raise_application_error(-20007, 'B³¹d podczas aktualizacji ksi¹¿ki: ' || SQLERRM);
        END;
    ELSE
        raise_application_error(-20006, 'Ksi¹¿ka o podanym ID nie istnieje!');
    END IF;
END EdytujKsiazke;

/
--------------------------------------------------------
--  DDL for Procedure EDYTUJPRACOWNIKA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."EDYTUJPRACOWNIKA" (
    p_id IN NUMBER,
    p_naz IN VARCHAR2,
    p_imie IN VARCHAR2,
    p_wynagrodzenie IN NUMBER,
    p_stanowisko IN VARCHAR2,
    p_success OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    p_success := 0;
    SELECT COUNT(*) INTO v_count FROM Pracownicy WHERE id_pracownik = p_id;
    IF v_count > 0 THEN
        BEGIN
            UPDATE Pracownicy
            SET
                nazwisko = COALESCE(p_naz, nazwisko),
                imie = COALESCE(p_imie, imie),
                wynagrodzenie = CASE WHEN p_wynagrodzenie >= 0 THEN p_wynagrodzenie ELSE wynagrodzenie END,
                stanowisko = COALESCE(p_stanowisko, stanowisko)
            WHERE id_pracownik = p_id;

            COMMIT;
            p_success := 1;
        EXCEPTION
            WHEN OTHERS THEN
                p_success := 0;
                raise_application_error(-20007, 'B³¹d podczas aktualizacji pracownika: ' || SQLERRM);
        END;
    ELSE
        raise_application_error(-20006, 'Pracownik o podanym ID nie istnieje!');
    END IF;
END EdytujPracownika;

/
--------------------------------------------------------
--  DDL for Procedure EDYTUJWYDAWNICTWO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."EDYTUJWYDAWNICTWO" (
    p_IdWydawnictwo NUMBER,
    p_Nazwa VARCHAR2,
    p_Ulica VARCHAR2,
    p_NrDomu VARCHAR2,
    p_NrLokalu VARCHAR2,
    p_KodPocztowy VARCHAR2,
    p_Poczta VARCHAR2,
    p_Success OUT NUMBER
) AS
BEGIN
    UPDATE Wydawnictwa
    SET 
        Nazwa = COALESCE(p_Nazwa, Nazwa),
        Ulica = COALESCE(p_Ulica, Ulica),
        Nr_Domu = COALESCE(p_NrDomu, Nr_Domu),
        Nr_Lokalu = COALESCE(p_NrLokalu, Nr_Lokalu),
        Kod_Pocztowy = COALESCE(p_KodPocztowy, Kod_Pocztowy),
        Poczta = COALESCE(p_Poczta, Poczta)
    WHERE id_wydawnictwo = p_IdWydawnictwo;

    IF SQL%FOUND THEN
        p_Success := 1;
        COMMIT;
    ELSE
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'B³¹d: Nie znaleziono wydawnictwa o podanym ID.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'B³¹d: ' || SQLERRM);
END EdytujWydawnictwo;

/
--------------------------------------------------------
--  DDL for Procedure EDYTUJWYPOZYCZENIE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."EDYTUJWYPOZYCZENIE" (
    p_id_wypozyczenie IN NUMBER,
    p_id_czytelnik IN NUMBER,
    p_id_egzemplarz IN NUMBER,
    p_id_pracownik_wyp IN NUMBER,
    p_data_wypozyczenia IN DATE,
    p_id_pracownik_zwr IN NUMBER,
    p_data_zwrotu IN DATE,
    p_success OUT NUMBER
) AS
    v_exists NUMBER;
BEGIN
    p_success := 0;

    -- Sprawdzenie, czy wypo¿yczenie o podanym ID istnieje
    SELECT COUNT(1) INTO v_exists FROM WYPOZYCZENIA WHERE ID_WYPOZYCZENIE = p_id_wypozyczenie;
    IF v_exists < 1 THEN
        raise_application_error(-20001, 'Wypo¿yczenie o podanym ID nie istnieje!');
    END IF;

    -- Sprawdzenie i aktualizacja ID_CZYTELNIK
    IF p_id_czytelnik > 0 THEN
        SELECT COUNT(1) INTO v_exists FROM CZYTELNICY WHERE ID_CZYTELNIK = p_id_czytelnik;
        IF v_exists < 1 THEN
            raise_application_error(-20002, 'Czytelnik o podanym ID nie istnieje!');
        END IF;
        UPDATE WYPOZYCZENIA SET ID_CZYTELNIK = p_id_czytelnik;
    END IF;

    -- Sprawdzenie i aktualizacja ID_EGZEMPLARZ
    IF p_id_egzemplarz > 0 THEN
        SELECT COUNT(1) INTO v_exists FROM EGZEMPLARZE WHERE ID_EGZEMPLARZ = p_id_egzemplarz;
        IF v_exists < 1 THEN
            raise_application_error(-20003, 'Egzemplarz o podanym ID nie istnieje!');
        END IF;
        UPDATE WYPOZYCZENIA SET ID_EGZEMPLARZ = p_id_egzemplarz;
    END IF;

    -- Sprawdzenie i aktualizacja ID_PRACOWNIK_WYP
    IF p_id_pracownik_wyp > 0 THEN
        SELECT COUNT(1) INTO v_exists FROM PRACOWNICY WHERE ID_PRACOWNIK = p_id_pracownik_wyp;
        IF v_exists < 1 THEN
            raise_application_error(-20004, 'Pracownik wypo¿yczaj¹cy o podanym ID nie istnieje!');
        END IF;
        UPDATE WYPOZYCZENIA SET ID_PRACOWNIK_WYP = p_id_pracownik_wyp;
    END IF;

    -- Sprawdzenie i aktualizacja ID_PRACOWNIK_ZWR
    IF p_id_pracownik_zwr > 0 THEN
        SELECT COUNT(1) INTO v_exists FROM PRACOWNICY WHERE ID_PRACOWNIK = p_id_pracownik_zwr;
        IF v_exists < 1 THEN
            raise_application_error(-20005, 'Pracownik zwracaj¹cy o podanym ID nie istnieje!');
        END IF;
        UPDATE WYPOZYCZENIA SET ID_PRACOWNIK_ZWR = p_id_pracownik_zwr;
    END IF;

    -- Aktualizacja dat
    UPDATE WYPOZYCZENIA
    SET
        DATA_WYPOZYCZENIA = CASE WHEN p_data_wypozyczenia IS NOT NULL THEN p_data_wypozyczenia ELSE DATA_WYPOZYCZENIA END,
        DATA_ZWROTU = CASE WHEN p_data_zwrotu IS NOT NULL THEN p_data_zwrotu ELSE DATA_ZWROTU END;

    COMMIT;
    p_success := 1;

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
        raise_application_error(-20006, 'B³¹d podczas aktualizacji wypo¿yczenia: ' || SQLERRM);
END EdytujWypozyczenie;

/
--------------------------------------------------------
--  DDL for Procedure USUNAUTORA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNAUTORA" (
    p_IdAutor NUMBER,
    p_Success OUT NUMBER
) AS
BEGIN
    DELETE FROM Autorzy WHERE id_autor = p_IdAutor;

    IF SQL%FOUND THEN
        p_Success := 1;
        COMMIT;
    ELSE
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'B³¹d: Nie znaleziono autora o podanym ID.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'B³¹d: ' || SQLERRM);
END UsunAutora;

/
--------------------------------------------------------
--  DDL for Procedure USUNAUTORAKSIAZKI
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNAUTORAKSIAZKI" (
    p_id_ksiazka IN NUMBER,
    p_id_autor IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    FOR c IN (SELECT 1 FROM autorzy_ksiazek WHERE id_ksiazka = p_id_ksiazka AND id_autor = p_id_autor)
    LOOP
        BEGIN
            DELETE FROM autorzy_ksiazek WHERE id_ksiazka = p_id_ksiazka AND id_autor = p_id_autor;
            p_success := 1;
            COMMIT;

        EXCEPTION
            WHEN OTHERS THEN
                p_success := 0;
                RAISE_APPLICATION_ERROR(-20001, 'Nie uda³o siê usun¹æ autora ksi¹¿ki. ' || SQLERRM);
        END;

        EXIT;
    END LOOP;
    IF p_success = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Ten autor nie jest przypisany do tej ksi¹¿ki.');
    END IF;
END UsunAutoraKsiazki;

/
--------------------------------------------------------
--  DDL for Procedure USUNCZYTELNIKA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNCZYTELNIKA" (
    p_id_czytelnik IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    BEGIN
        DELETE FROM czytelnicy WHERE id_czytelnik = p_id_czytelnik;
        p_success := 1;
        COMMIT;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            p_success := 0;
            RAISE_APPLICATION_ERROR(-20002, 'Nie znaleziono czytelnika o ID ' || p_id_czytelnik);
        WHEN OTHERS THEN
            p_success := 0;
            RAISE_APPLICATION_ERROR(-20001, 'Nie uda³o siê usun¹æ czytelnika. ' || SQLERRM);
    END;
END UsunCzytelnika;

/
--------------------------------------------------------
--  DDL for Procedure USUNEGZEMPLARZ
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNEGZEMPLARZ" (
    p_idegzemplarza IN NUMBER,
    p_success OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    p_success := 0;
    SELECT COUNT(*) INTO v_count FROM Egzemplarze WHERE id_egzemplarz = p_idegzemplarza;

    IF v_count > 0 THEN
        BEGIN
            DELETE FROM Egzemplarze WHERE id_egzemplarz = p_idegzemplarza;
            COMMIT;
            p_success := 1;
        EXCEPTION
            WHEN OTHERS THEN
                p_success := 0;
                raise_application_error(-20005, 'B³¹d podczas usuwania egzemplarza: ' || SQLERRM);
        END;
    ELSE
        raise_application_error(-20004, 'Egzemplarz o podanym ID nie istnieje!');
    END IF;
END UsunEgzemplarz;

/
--------------------------------------------------------
--  DDL for Procedure USUNKSIAZKE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNKSIAZKE" (
    p_id IN NUMBER,
    p_success OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    p_success := 0;
    SELECT COUNT(*) INTO v_count FROM Ksiazki WHERE id_ksiazka = p_id;
    IF v_count > 0 THEN
        BEGIN
            DELETE FROM Ksiazki WHERE id_ksiazka = p_id;
            COMMIT;
            p_success := 1;
        EXCEPTION
            WHEN OTHERS THEN
                p_success := 0;
                raise_application_error(-20005, 'B³¹d podczas usuwania ksi¹¿ki: ' || SQLERRM);
        END;
    ELSE
        raise_application_error(-20004, 'Ksi¹¿ka o podanym ID nie istnieje!');
    END IF;
END UsunKsiazke;

/
--------------------------------------------------------
--  DDL for Procedure USUNPRACOWNIKA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNPRACOWNIKA" (
    p_id IN NUMBER,
    p_success OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    p_success := 0;
    SELECT COUNT(*) INTO v_count FROM Pracownicy WHERE id_pracownik = p_id;

    IF v_count > 0 THEN
        BEGIN
            DELETE FROM Pracownicy WHERE id_pracownik = p_id;
            COMMIT;
            p_success := 1;
        EXCEPTION
            WHEN OTHERS THEN
                p_success := 0;
                raise_application_error(-20005, 'B³¹d podczas usuwania pracownika: ' || SQLERRM);
        END;
    ELSE
        raise_application_error(-20004, 'Pracownik o podanym ID nie istnieje!');
    END IF;
END UsunPracownika;

/
--------------------------------------------------------
--  DDL for Procedure USUNWYDAWNICTWO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNWYDAWNICTWO" (
    p_IdWydawnictwo NUMBER,
    p_Success OUT NUMBER
) AS
BEGIN
    DELETE FROM Wydawnictwa WHERE id_wydawnictwo = p_IdWydawnictwo;

    IF SQL%FOUND THEN
        p_Success := 1;
        COMMIT;
    ELSE
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'B³¹d: Nie znaleziono wydawnictwa o podanym ID.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_Success := 0;
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'B³¹d: ' || SQLERRM);
END UsunWydawnictwo;

/
--------------------------------------------------------
--  DDL for Procedure USUNWYPOZYCZENIE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##KLIENTBAZA"."USUNWYPOZYCZENIE" (
    p_id_wypozyczenie IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    p_success := 0;
    DELETE FROM Wypozyczenia
    WHERE id_wypozyczenie = p_id_wypozyczenie;
    COMMIT;
    p_success := 1;

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
        raise_application_error(-20001, 'B³¹d podczas usuwania wypo¿yczenia: ' || SQLERRM);
END UsunWypozyczenie;

/
--------------------------------------------------------
--  DDL for Function OBLICZ_SUME_KAR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "C##KLIENTBAZA"."OBLICZ_SUME_KAR" RETURN NUMBER IS
  total_kara NUMBER := 0;
  CURSOR kara_cursor IS
    SELECT KARA FROM CZYTELNICY;
  kara_rec kara_cursor%ROWTYPE;
BEGIN
  OPEN kara_cursor;

  FETCH kara_cursor INTO kara_rec;
  WHILE kara_cursor%FOUND LOOP
    total_kara := total_kara + kara_rec.KARA;
    FETCH kara_cursor INTO kara_rec;
  END LOOP;

  CLOSE kara_cursor;

  RETURN total_kara;
END oblicz_sume_kar;

/
--------------------------------------------------------
--  DDL for Function SREDNIA_WYPOZYCZEN_W_TYM_ROKU
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "C##KLIENTBAZA"."SREDNIA_WYPOZYCZEN_W_TYM_ROKU" RETURN VARCHAR2 IS
    v_srednia NUMBER;
    v_wiadomosc VARCHAR2(200);
BEGIN
    SELECT AVG(NVL(WYPOZYCZENIA, 0))
    INTO v_srednia
    FROM (
        SELECT NVL(COUNT(WW.ID_WYPOZYCZENIE), 0) AS WYPOZYCZENIA
        FROM (SELECT DISTINCT ID_CZYTELNIK FROM CZYTELNICY) C
        LEFT JOIN WIDOK_WYPOZYCZENIA WW
        ON C.ID_CZYTELNIK = WW.ID_CZYTELNIK
        AND EXTRACT(YEAR FROM WW.DATA_WYPOZYCZENIA) = EXTRACT(YEAR FROM SYSDATE)
        GROUP BY C.ID_CZYTELNIK
    );

    IF v_srednia IS NOT NULL THEN
        v_wiadomosc := 'W tym roku œrednio czytelnik wypo¿yczy³: ' || TO_CHAR(v_srednia, '0.99') || ' ksi¹¿ek';
    ELSE
        v_wiadomosc := 'W tym roku nikt nie wypo¿yczy³ jeszcze ¿adnej ksi¹¿ki';
    END IF;

    RETURN v_wiadomosc;
END srednia_wypozyczen_w_tym_roku;

/
--------------------------------------------------------
--  Constraints for Table PRACOWNICY
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."PRACOWNICY" MODIFY ("NAZWISKO" CONSTRAINT "PRACOWNIK_NAZWISKO_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."PRACOWNICY" MODIFY ("IMIE" CONSTRAINT "PRACOWNIK_IMIE_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."PRACOWNICY" ADD CONSTRAINT "PRACOWNIK_PK" PRIMARY KEY ("ID_PRACOWNIK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table WYPOZYCZENIA
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."WYPOZYCZENIA" ADD CONSTRAINT "WYPOZYCZENIE_PK" PRIMARY KEY ("ID_WYPOZYCZENIE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table AUTORZY
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."AUTORZY" MODIFY ("NAZWISKO" CONSTRAINT "AUTOR_NAZWISKO_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."AUTORZY" MODIFY ("IMIE" CONSTRAINT "AUTOR_IMIE_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."AUTORZY" ADD CONSTRAINT "AUTOR_PK" PRIMARY KEY ("ID_AUTOR")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table AUTORZY_KSIAZEK
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."AUTORZY_KSIAZEK" ADD CONSTRAINT "AK_PK" PRIMARY KEY ("ID_KSIAZKA", "ID_AUTOR")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table EGZEMPLARZE
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."EGZEMPLARZE" MODIFY ("ROK_WYDANIA" CONSTRAINT "EGZEMPLARZ_ROK_WYDANIA_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."EGZEMPLARZE" ADD CONSTRAINT "EGZEMPLARZ_STAN_CK" CHECK (stan = 0 OR stan = 1) ENABLE;
  ALTER TABLE "C##KLIENTBAZA"."EGZEMPLARZE" ADD CONSTRAINT "EGZEMPLARZ_PK" PRIMARY KEY ("ID_EGZEMPLARZ")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table KSIAZKI
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."KSIAZKI" MODIFY ("TYTUL" CONSTRAINT "KSIAZKA_TYTUL_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."KSIAZKI" ADD CONSTRAINT "KSIAZKA_PK" PRIMARY KEY ("ID_KSIAZKA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table WYDAWNICTWA
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."WYDAWNICTWA" ADD CONSTRAINT "WYDAWNICTWO_PK" PRIMARY KEY ("ID_WYDAWNICTWO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##KLIENTBAZA"."WYDAWNICTWA" ADD CONSTRAINT "WYDAWNICTWO_NAZWA_U" UNIQUE ("NAZWA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CZYTELNICY
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."CZYTELNICY" MODIFY ("NAZWISKO" CONSTRAINT "CZYTELNIK_NAZWISKO_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."CZYTELNICY" MODIFY ("IMIE" CONSTRAINT "CZYTELNIK_IMIE_NN" NOT NULL ENABLE);
  ALTER TABLE "C##KLIENTBAZA"."CZYTELNICY" ADD CONSTRAINT "CZYTELNIK_PK" PRIMARY KEY ("ID_CZYTELNIK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table AUTORZY_KSIAZEK
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."AUTORZY_KSIAZEK" ADD CONSTRAINT "AK_KSIAZKA_FK" FOREIGN KEY ("ID_KSIAZKA")
	  REFERENCES "C##KLIENTBAZA"."KSIAZKI" ("ID_KSIAZKA") ENABLE;
  ALTER TABLE "C##KLIENTBAZA"."AUTORZY_KSIAZEK" ADD CONSTRAINT "AK_AUTOR_FK" FOREIGN KEY ("ID_AUTOR")
	  REFERENCES "C##KLIENTBAZA"."AUTORZY" ("ID_AUTOR") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EGZEMPLARZE
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."EGZEMPLARZE" ADD CONSTRAINT "EGZEMPLARZ_KSIAZKA_FK" FOREIGN KEY ("ID_KSIAZKA")
	  REFERENCES "C##KLIENTBAZA"."KSIAZKI" ("ID_KSIAZKA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table KSIAZKI
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."KSIAZKI" ADD CONSTRAINT "FK_KSIAZKI_WYDAWNICTWA" FOREIGN KEY ("ID_WYDAWNICTWO")
	  REFERENCES "C##KLIENTBAZA"."WYDAWNICTWA" ("ID_WYDAWNICTWO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table WYPOZYCZENIA
--------------------------------------------------------

  ALTER TABLE "C##KLIENTBAZA"."WYPOZYCZENIA" ADD CONSTRAINT "WYPOZYCZENIE_CZYTELNIK_FK" FOREIGN KEY ("ID_CZYTELNIK")
	  REFERENCES "C##KLIENTBAZA"."CZYTELNICY" ("ID_CZYTELNIK") ENABLE;
  ALTER TABLE "C##KLIENTBAZA"."WYPOZYCZENIA" ADD CONSTRAINT "WYPOZYCZENIE_EGZEMPLARZ_FK" FOREIGN KEY ("ID_EGZEMPLARZ")
	  REFERENCES "C##KLIENTBAZA"."EGZEMPLARZE" ("ID_EGZEMPLARZ") ENABLE;
  ALTER TABLE "C##KLIENTBAZA"."WYPOZYCZENIA" ADD CONSTRAINT "WYPOZYCZENIE_PRACOWNIK_WYP_FK" FOREIGN KEY ("ID_PRACOWNIK_WYP")
	  REFERENCES "C##KLIENTBAZA"."PRACOWNICY" ("ID_PRACOWNIK") ENABLE;
  ALTER TABLE "C##KLIENTBAZA"."WYPOZYCZENIA" ADD CONSTRAINT "WYPOZYCZENIE_PRACOWNIK_ZWR_FK" FOREIGN KEY ("ID_PRACOWNIK_ZWR")
	  REFERENCES "C##KLIENTBAZA"."PRACOWNICY" ("ID_PRACOWNIK") ENABLE;
