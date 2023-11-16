package com.example.fatnassi_chaima_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//activity = context(main.java)+content(activity.xml) ---> mainActivity => AppCompatActivity => Actiity => context (héritage)
public class MainActivity extends AppCompatActivity {

    private TextView tvAgeLabel, tvRes;
    private SeekBar sbAge;
    private RadioButton rbtOui, rbtNon;
    private EditText edtGlycemia;
    private Button btnConsulter;
    @Override
    protected void onCreate(Bundle savedInstanceState) { // méthode main : context+content
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // Définir l'action du bouton "Consulter" lorsqu'il est cliqué
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer();
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
    public void calculer() {
        // Récupérer l'âge à partir de la SeekBar
        int age = sbAge.getProgress();

        // Récupérer le niveau de glycémie entré dans l'EditText
        String glycemiaValueStr = edtGlycemia.getText().toString();

        if (!glycemiaValueStr.isEmpty() && age!=0) {
            Log.i("Info","glycemiaValueStr  = " + glycemiaValueStr + "age = " + age);
            // Convertir la valeur de l'EditText en un nombre à virgule flottante
            float glycemiaValue = Float.parseFloat(glycemiaValueStr);

            boolean isFasting = rbtOui.isChecked();

            String resultText;

            if (isFasting) {
                if ((age < 12 && glycemiaValue < 10.0) || (age >= 12 && glycemiaValue < 10.0)) {
                    resultText = "Result: Your glycemia level is LOW.";
                } else if (glycemiaValue >= 10.0 && glycemiaValue <= 14.0) {
                    resultText = "Result: Your glycemia level is NORMAL.";
                } else {
                    resultText = "Result: Your glycemia level is HIGH.";
                }
            } else {
                if ((age < 12 && glycemiaValue >= 7.0 && glycemiaValue <= 14.0)
                        || (age >= 12 && glycemiaValue >= 7.0 && glycemiaValue <= 14.0)) {
                    resultText = "Result: Your glycemia level is NORMAL.";
                } else if ((age < 12 && glycemiaValue < 7.0) || (age >= 12 && glycemiaValue < 7.0)) {
                    resultText = "Result: Your glycemia level is LOW.";
                } else {
                    resultText = "Result: Your glycemia level is HIGH.";
                }

            }


            // Mettre à jour le TextView tvRes avec le résultat
            tvRes.setText(resultText);
        } else {
            if(age == 0)
            { Toast.makeText(this, "Please verify your age", Toast.LENGTH_SHORT).show();
            Log.i("Info","glycemiaValueStr  = " + glycemiaValueStr + "age = " + age);}
            if(glycemiaValueStr.isEmpty())
                Toast.makeText(this, "Please enter a valid glycemia value", Toast.LENGTH_LONG).show();

        }
    }

}
