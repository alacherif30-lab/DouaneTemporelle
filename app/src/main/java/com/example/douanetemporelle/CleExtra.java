package com.example.douanetemporelle;

public class CleExtra {

    // matricule = a5er 4 arkam mta3 carte étudiant
    public static final String MATRICULE = "1234";

    // méthode static: tjma3 el matricule m3a el esm (ex: "1234_NOM")
    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}