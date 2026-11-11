package com.example.douanetemporelle;

public class CleExtra {

    // ra9m etudiant: e5er 4 ar9am mta3 carte etudiant
    public static final String MATRICULE = "1234";

    // methode bech na3mlou cle bel matricule w esm l'information
    // exemple: 1234_NOM
    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}
