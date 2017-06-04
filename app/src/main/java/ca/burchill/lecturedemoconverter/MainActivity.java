package ca.burchill.lecturedemoconverter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity {

    private RadioButton rbFromMile;
    private RadioButton rbFromKm;

    private RadioButton rbToMile;
    private RadioButton rbToKm;

    private EditText distValue;
    private EditText distResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbFromMile = (RadioButton) findViewById(R.id.rbFromMile);
        rbFromKm = (RadioButton) findViewById(R.id.rbFromKm);
        rbToMile = (RadioButton) findViewById(R.id.rbToMile);
        rbToKm = (RadioButton) findViewById(R.id.rbToKm);
        distValue = (EditText) findViewById(R.id.distValue);
        distResult = (EditText) findViewById(R.id.distResult);

    }

    public void handleClick(View view) {
        String unitFrom = "Mile";
        String unitTo = "Mile";

        switch (view.getId()) {

            case R.id.convert:
                String value = distValue.getText().toString();
                if (value.length() > 0) {
                    if (rbFromKm.isChecked()) {
                        unitFrom="Km";
                    }
                    if (rbToKm.isChecked()) {
                        unitTo = "Km";
                    }
                    if (unitFrom.contentEquals(unitTo)) {
                        distResult.setText(value);
                    }
                    else {
                        if (unitFrom.contentEquals("Mile")) {
                            distResult.setText(milesToKm(value));
                        } else
                        {
                            distResult.setText(kmToMiles(value));
                        }
                    }
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Empty Value!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast  toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;


            case R.id.reset:
                distValue.setText("");
                distResult.setText("");
                rbFromMile.setChecked(true);
                rbToMile.setChecked(true);
                break;

        }

    }

    public String milesToKm(String strMiles) {
        double miles = Double.parseDouble(strMiles);
        double km = miles * 1.60934;
        java.text.DecimalFormat format = new java.text.DecimalFormat("#.##");
        return String.valueOf(format.format(km));
    }

    public String kmToMiles(String strMiles) {
        double km = Double.parseDouble(strMiles);
        double miles = km / 1.60934;
        java.text.DecimalFormat format = new java.text.DecimalFormat("#.##");
        return String.valueOf(format.format(miles));
    }


}
