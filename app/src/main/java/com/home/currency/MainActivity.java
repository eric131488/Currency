package com.home.currency;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ntd;
    private Button go;
    private static float exchangeRate = 30.9f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        ntd = findViewById(R.id.ntd);
        go = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = ntd.getText().toString();

                if (n.length() == 0) {
                    showAlert("Problem", "Please enter your NTD amount", "OK", null);
                } else {
                    float usd = calculate(Float.parseFloat(n));
                    DialogInterface.OnClickListener listner = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ntd.setText("");
                        }
                    };

                    showAlert("Result", "USD is " + usd, "OK", listner);
                }
            }
        });
    }

    private float calculate(float ntd) {
        return  ntd / exchangeRate;
    }

    private void showAlert(String title, String message, String button, DialogInterface.OnClickListener listner) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(button, listner)
                .show();
    }
}
