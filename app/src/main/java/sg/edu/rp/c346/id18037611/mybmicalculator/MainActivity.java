package sg.edu.rp.c346.id18037611.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {


    EditText etHeight, etWeight;
    Button btnCal, btnReset;
    TextView tv1, tv2, status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.height);
        etWeight = findViewById(R.id.weight);
        btnCal = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        tv1 =findViewById(R.id.textView1);
        tv2 =findViewById(R.id.textView2);
        status = findViewById(R.id.status);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);


                String Last_Date = "Last Calculated Date: " + datetime;
                String stat = status.getText().toString();

                float Weight = Float.parseFloat(etWeight.getText().toString());
                float Height = Float.parseFloat(etHeight.getText().toString());

                float BMI = Weight / (Height * Height);

                if (BMI < 18.5) {
                    stat = "You are underweight";
                }else if (BMI >= 18.5 && BMI <= 24.9){
                    stat = "Your BMI is normal";
                }else if (BMI >= 25 && BMI <= 29.9){
                    stat= "You are overweight";
                }else{
                    stat = "You are obese";
                }

                String Last_BMI = "Last Calculated BMI: " + String.valueOf(BMI);


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("Last_Date", Last_Date);
                prefEdit.putString("Last_BMI", Last_BMI);
                prefEdit.putString("Status", stat);
                prefEdit.commit();

                tv1.setText(Last_Date);
                tv2.setText(Last_BMI);
                status.setText(stat);

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etWeight.setText("");
                etHeight.setText("");

                String Last_Date = "Last Calculated Date: ";
                String Last_BMI = "Last Calculated BMI:0.0 ";


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("Last_Date", Last_Date);
                prefEdit.putString("Last_BMI", Last_BMI);
                prefEdit.putString("Status", "");
                prefEdit.commit();

                tv1.setText(Last_Date);
                tv2.setText(Last_BMI);
                status.setText("");


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String Last_Date = prefs.getString("Last_Date", "Last Calculated Date:");
        String Last_BMI = prefs.getString("Last_BMI", "Last Calculated BMI:0.0");
        String Status = prefs.getString("Status", "");

        tv1.setText(Last_Date);
        tv2.setText(Last_BMI);
        status.setText(Status);

    }






}
