package com.example.fatnassi_chaima_mesure_glycemie.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatnassi_chaima_mesure_glycemie.Controller.Controller;
import com.example.fatnassi_chaima_mesure_glycemie.Model.Patient;
import com.example.fatnassi_chaima_mesure_glycemie.R;

//activity = context(main.java)+content(activity.xml) ---> mainActivity => AppCompatActivity => Activity => context (héritage)
public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 1;
    private TextView tvAgeLabel, tvRes;
    private SeekBar sbAge;
    private RadioButton rbtOui, rbtNon;
    private EditText edtGlycemia;
    private Button btnConsulter;

    private Controller controller =Controller.getInstance();
    private Patient patient = new Patient();

    @Override
    protected void onCreate(Bundle savedInstanceState) { // méthode main : context+content
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // Définir l'action du bouton "Consulter" lorsqu'il est cliqué
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int age;
                float valeurMesure;
                boolean verifAge = false;
                boolean verifValeur = false;
                if (sbAge.getProgress() != 0) {
                    verifAge = true;
                } else {
                    //length_show le temps de message
                    Toast.makeText(MainActivity.this, "Veuillez Verifier votre Age", Toast.LENGTH_SHORT).show();
                }
                if (!edtGlycemia.getText().toString().isEmpty()) {
                    verifValeur = true;
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez Verifier le valeur Mesure ", Toast.LENGTH_LONG).show();
                }
                if (verifAge && verifValeur) {
                    age = sbAge.getProgress();
                    valeurMesure = Float.valueOf(edtGlycemia.getText().toString());
                    boolean IsFasting = rbtOui.isChecked();
                    //useraction:view to controller
                    controller.create_patient(age,valeurMesure,IsFasting);
                    //update cntroller to view
                    Intent intent = new Intent (MainActivity.this, ConsultActivity.class);
                    intent.putExtra("reponse",controller.getResult());
                    startActivityForResult(intent, REQUEST_CODE);
                }
                patient.calculer();
            }
        });
        //Action de changement sur SeekBar
        sbAge.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Log.i("Information","onProgressChange"+progress);
                        tvAgeLabel.setText("Your age : "+progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        // Initialize radio buttons
        rbtOui.setChecked(true);  // Set "Yes" as the default choice

        // Action de changement sur les boutons radio
        rbtOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtOui.setChecked(true);
                rbtNon.setChecked(false);
            }
        });

        rbtNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtOui.setChecked(false);
                rbtNon.setChecked(true);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "ERROR : RESULT_CANCELED", Toast.LENGTH_SHORT).show();
            }
    }
    private void init(){
        tvAgeLabel= (TextView) findViewById(R.id.AgeLabel);
        tvRes= (TextView)findViewById(R.id.res);
        sbAge= (SeekBar) findViewById(R.id.sbAge);
        rbtOui= (RadioButton) findViewById(R.id.rbtOui);
        rbtNon= (RadioButton) findViewById(R.id.rbtNon);
        edtGlycemia= (EditText) findViewById(R.id.edtGlycemia);
        btnConsulter= (Button) findViewById(R.id.btnConsulter);
    }

}
