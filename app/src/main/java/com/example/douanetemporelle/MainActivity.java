package com.example.douanetemporelle;

import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtNom, edtNaissance, edtDestination;
    RadioGroup rgMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // rabt les elements mta3 l'interface m3a XML
        edtNom = findViewById(R.id.edt_nomvoyageur_ec);
        edtNaissance = findViewById(R.id.edt_datenaissance_ec);
        edtDestination = findViewById(R.id.edt_destination_ec);
        rgMotif = findViewById(R.id.rg_motif_ec);
        btnInspecter = findViewById(R.id.btn_inspecter_ec);

        // ki nenzel 3al bouton inspecter
        btnInspecter.setOnClickListener(v -> {

            // nesta5rjou les informations eli dakhalhom l'utilisateur
            String nom = edtNom.getText().toString().trim();
            String naissance = edtNaissance.getText().toString().trim();
            String destination = edtDestination.getText().toString().trim();

            int motif = rgMotif.getCheckedRadioButtonId();

            // net2akdou eli esm mouch feragh
            if (nom.isEmpty()) {
                edtNom.setError("Saisir le nom");
                edtNom.requestFocus();
                return;
            }

            // net2akdou eli date de naissance mouch fergha
            if (naissance.isEmpty()) {
                edtNaissance.setError("Saisir l'annee de naissance");
                edtNaissance.requestFocus();
                return;
            }

            // net2akdou eli annee de destination mouch fergha
            if (destination.isEmpty()) {
                edtDestination.setError("Saisir l'annee de destination");
                edtDestination.requestFocus();
                return;
            }

            // net2akdou eli l'utilisateur 5tar motif de voyage
            if (motif == -1) {
                Toast.makeText(
                        MainActivity.this,
                        "Choisir un motif",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // nesta5rjou texte mta3 le choix eli 5tarou l'utilisateur
            RadioButton rb = findViewById(motif);
            String texteMotif = rb.getText().toString();

            // na3mlou Intent bech nemchiw lel Activity 2
            Intent intent = new Intent(
                    MainActivity.this,
                    VerificationActivity.class
            );

            // nab3thou les donnees lel Activity 2 bel cles dynamiques
            intent.putExtra(
                    CleExtra.cle(CleExtra.MATRICULE, "NOM"),
                    nom
            );

            intent.putExtra(
                    CleExtra.cle(CleExtra.MATRICULE, "NAISSANCE"),
                    naissance
            );

            intent.putExtra(
                    CleExtra.cle(CleExtra.MATRICULE, "DESTINATION"),
                    destination
            );

            intent.putExtra(
                    CleExtra.cle(CleExtra.MATRICULE, "MOTIF"),
                    texteMotif
            );

            // n7ellou l'Activity 2
            startActivity(intent);
        });

        // na3mlou gestion lel espace mta3 les barres systeme
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }
}
