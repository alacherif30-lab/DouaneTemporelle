package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    TextView txtNom, txtAge, txtMotif;
    Button btnApprouver, btnRefuser;
    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // rabt les elements mta3 l'interface m3a XML
        txtNom = findViewById(R.id.txt_nom_ec);
        txtAge = findViewById(R.id.txt_age_ec);
        txtMotif = findViewById(R.id.txt_motif_ec);
        btnApprouver = findViewById(R.id.btn_approuver_ec);
        btnRefuser = findViewById(R.id.btn_refuser_ec);

        // nesta9blou les informations mel Activity 1
        Intent recu = getIntent();

        nom = recu.getStringExtra(
                CleExtra.cle(CleExtra.MATRICULE, "NOM")
        );

        String naissance = recu.getStringExtra(
                CleExtra.cle(CleExtra.MATRICULE, "NAISSANCE")
        );

        String destination = recu.getStringExtra(
                CleExtra.cle(CleExtra.MATRICULE, "DESTINATION")
        );

        String motif = recu.getStringExtra(
                CleExtra.cle(CleExtra.MATRICULE, "MOTIF")
        );

        // net2akdou eli les informations mawjoudin
        if (nom == null || naissance == null ||
                destination == null || motif == null) {

            finish();
            return;
        }

        // na7sbou l'age temporel
        int age;

        try {
            age = Integer.parseInt(destination)
                    - Integer.parseInt(naissance);

        } catch (NumberFormatException e) {

            finish();
            return;
        }

        // na3rdhou les informations fel interface
        txtNom.setText("Nom du voyageur : " + nom);
        txtAge.setText("Age temporel : " + age);
        txtMotif.setText("Motif du voyage : " + motif);

        // net2akdou ken el motif Modification du passé
        // w ken l'age temporel nombre premier
        if (motif.equals("Modification du passé")
                && estPremier(age)) {

            // na3mlou desactivation lel bouton Approuver
            btnApprouver.setEnabled(false);
        }

        // ki nenzel 3al bouton Approuver
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true));

        // ki nenzel 3al bouton Refuser
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false));
    }

    // na3mlou methode bech nab3thou el verdict lel Activity 3
    void envoyerVerdict(boolean verdict) {

        Intent intent = new Intent(
                VerificationActivity.this,
                ResultActivity.class
        );

        // nab3thou esm el voyageur
        intent.putExtra(
                CleExtra.cle(CleExtra.MATRICULE, "NOM"),
                nom
        );

        // nab3thou el verdict (true wala false)
        intent.putExtra(
                CleExtra.cle(CleExtra.MATRICULE, "VERDICT"),
                verdict
        );

        // n7ellou Activity 3
        startActivity(intent);
    }

    // methode bech net2akdou ken el nombre premier
    boolean estPremier(int n) {

        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {

            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
