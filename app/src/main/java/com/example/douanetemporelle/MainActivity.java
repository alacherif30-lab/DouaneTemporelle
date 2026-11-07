package com.example.douanetemporelle;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;   // ZIDA: bech na9rew el motif elli e5tarou
import android.widget.Button;
import android.widget.Toast;        // ZIDA: message "choisir un motif"
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    EditText edtNom, edtNaissance, edtDestination;
    RadioGroup rgMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Rbet el vues m3a el XML
        edtNom = findViewById(R.id.edt_nomvoyageur_ec);
        edtNaissance = findViewById(R.id.edt_datenaissance_ec);
        edtDestination = findViewById(R.id.edt_destination_ec);
        rgMotif = findViewById(R.id.rg_motif_ec);
        btnInspecter = findViewById(R.id.btn_inspecter_ec);

        btnInspecter.setOnClickListener(v -> {

            // Récupération des informations
            String nom = edtNom.getText().toString();
            String naissance = edtNaissance.getText().toString();
            String destination = edtDestination.getText().toString();
            int motif = rgMotif.getCheckedRadioButtonId();

            // Vérification des informations
            if (nom.isEmpty()) {
                edtNom.setError("Saisir le nom");
            }
            else if (naissance.isEmpty()) {
                edtNaissance.setError("Saisir l'année de naissance");
            }
            else if (destination.isEmpty()) {
                edtDestination.setError("Saisir l'année de destination");
            }
            else if (motif == -1) {
                // ZIDA: message bech el user ya3ref chnowa na9es
                Toast.makeText(this, "Choisir un motif", Toast.LENGTH_SHORT).show();
            }
            else {
                // ZIDA: na9rew el texte mta3 el RadioButton elli tkhayarneh
                RadioButton rb = findViewById(motif);
                String texteMotif = rb.getText().toString();

                // Les informations sont saisies
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                // ZIDA: nab3thou el données m3a el Intent (clé, valeur)
                intent.putExtra(CleExtra.cle(CleExtra.MATRICULE, "NOM"), nom);
                intent.putExtra(CleExtra.cle(CleExtra.MATRICULE, "NAISSANCE"), naissance);
                intent.putExtra(CleExtra.cle(CleExtra.MATRICULE, "DESTINATION"), destination);
                intent.putExtra(CleExtra.cle(CleExtra.MATRICULE, "MOTIF"), texteMotif);

                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}