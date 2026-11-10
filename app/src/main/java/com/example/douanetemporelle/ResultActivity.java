package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtTitreResultat;
    TextView txtMessageResultat;
    Button btnNouveauVoyageur;
    View mainResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //  norbtou anaser maa wejha
        mainResult = findViewById(R.id.main_result_in);
        txtTitreResultat = findViewById(R.id.txtTitreResultat_IN);
        txtMessageResultat = findViewById(R.id.txtMessageResultat_IN);
        btnNouveauVoyageur = findViewById(R.id.btnNouveauVoyageur_IN);

        //  nestakblou maaloumet mel ativity 2
        Intent intent = getIntent();

        String nom = intent.getStringExtra(
                CleExtra.cle(CleExtra.MATRICULE, "NOM")
        );

        boolean verdict = intent.getBooleanExtra(
                CleExtra.cle(CleExtra.MATRICULE, "VERDICT"),
                false
        );

        // netaakdou mel isem mawjoud
        if (nom == null || nom.trim().isEmpty()) {
            nom = "Voyageur";
        }

        // nbadlou fond d'ecran wel nas
        if (verdict) {
            //kif yabda makboul
            txtTitreResultat.setText("VOYAGE AUTORISÉ");
            txtMessageResultat.setText("Bon voyage " + nom + " !");

            // le fond d'ecran vert
            mainResult.setBackgroundColor(Color.parseColor("#23834A"));

        } else {
            // kif yabda refuse
            txtTitreResultat.setText("VOYAGE REFUSÉ");
            txtMessageResultat.setText(nom + " est en état d'arrestation temporelle");

            // le fond d'ecran rouge
            mainResult.setBackgroundColor(Color.parseColor("#B63838"));
        }

        // narj3ou mainactivity
        btnNouveauVoyageur.setOnClickListener(v -> {
            Intent retour = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            retour.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(retour);
        });
    }
}