package net.leocadio.joao.medidas;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;


public class MainActivity extends Activity {

    private Medidas selectedEditTxt;
    BarChart barChart;
    private float cent, pol;

    public float getCent() {
        return cent;
    }

    public void setCent(float cent) {
        this.cent = cent;
    }

    public float getPol() {
        return pol;
    }

    public void setPol(float pol) {
        this.pol = pol;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText centimetro = (EditText) findViewById(R.id.cmText);
        final EditText polegada = (EditText) findViewById(R.id.polText);

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

                    setPol(outputPol);

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

                    setCent(outputCent);

                } else if (selectedEditTxt == Medidas.POLEGADAS) {
                    centimetro.setText("");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });

        charts();

    }

    public float stripDecimal(float temp) {

        String valor = String.format("%.2f", temp);
        return Float.parseFloat(valor);
    }

    public void charts() {

        BarChart barChart = (BarChart) findViewById(R.id.barChart);

        //Add single bar
        BarChartModel barChartModel = new BarChartModel();
        barChartModel.setBarValue(50);
        barChartModel.setBarColor(Color.parseColor("#9C27B0"));
        barChartModel.setBarTag(null); //You can set your own tag to bar model
        barChartModel.setBarText("50");

        barChart.addBar(barChartModel);

        //Add mutliple bar at once as list;
        List<BarChartModel> barChartModelList = new ArrayList<>();

        //populate bar array list and add to barchart as a list.
        barChart.addBar(barChartModelList);

    }

}
