package com.example.kidsgamenew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {

    private TextView previousTimeTxt, txtViewSave;
    private Button returnButton, btnSave;

    private ArrayList<String> arrayTimes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        previousTimeTxt = findViewById(R.id.txtViewTime1);
        returnButton = findViewById(R.id.button);
        txtViewSave = findViewById(R.id.txtViewSave);
        btnSave = findViewById(R.id.btnSave);



        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            String receivedNumber = bundle.getString("NUMBER_KEY");
            txtViewSave.setText(bundle.getString("TIME"));

            previousTimeTxt.setText(receivedNumber);

        }

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openGame();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayTimes.add(previousTimeTxt.getText().toString());
                txtViewSave.setText(previousTimeTxt.getText().toString());
            }
        });

    }

    public void openGame(){
        Intent i = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("TIME_KEY", arrayTimes.get(arrayTimes.size() - 1));
        i.putExtras(bundle);
        startActivity(i);
    }
}