package net.leocadio.joao.medidas;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    private Medidas selectedEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText centimetro = (EditText) findViewById(R.id.cmText);
        final EditText polegada = (EditText) findViewById(R.id.polText);
//        final BarChart barChart = (BarChart) findViewById(R.id.barChart);


        // Centimentro
        centimetro.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = Medidas.CENTIMENTROS;
            }
        });

        // Centimentro
        centimetro.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String tmpValue = centimetro.getText().toString();

                if (!tmpValue.equals("") && !tmpValue.equals(".") && selectedEditTxt == Medidas.CENTIMENTROS) {
                    float temp = Float.parseFloat(tmpValue);
                    // polegadas
                    float outputPol = (float) (temp * 0.39370);

                    polegada.setText(String.valueOf(stripDecimal(outputPol)));

                } else if (selectedEditTxt == Medidas.CENTIMENTROS){
                    polegada.setText("");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        // Polegadas
        polegada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = Medidas.POLEGADAS;
            }
        });

        // Polegadas
        polegada.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String tmpValue = polegada.getText().toString();

                if (!tmpValue.equals("") && !tmpValue.equals(".") && selectedEditTxt == Medidas.POLEGADAS) {
                    float temp = Float.parseFloat(tmpValue);
                    //centimetros
                    float outputCent = (float) (temp / 0.39370);

                    centimetro.setText(String.valueOf(stripDecimal(outputCent)));

                } else if (selectedEditTxt == Medidas.POLEGADAS) {
                    centimetro.setText("");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });

    }

    public float stripDecimal(float temp) {

        String valor = String.format("%.2f", temp);
        return Float.parseFloat(valor);
    }

}

