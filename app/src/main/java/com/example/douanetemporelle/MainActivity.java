package com.example.douanetemporelle;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    EditText edtNom, edtNaissance, edtDestination;
    RadioGroup rgMotif;
    Button btnInspecter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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
                    // Aucun motif sélectionné
                }
                else {
                    // Les informations sont saisies
                }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}