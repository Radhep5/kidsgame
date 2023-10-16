package com.example.kidsgamenew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    Button send_button;
    EditText send_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_button = findViewById(R.id.send_button_id);
        send_text = findViewById(R.id.send_text_id);

        send_button.setOnClickListener(v -> {
            String str = send_text.getText().toString();
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("message_key", str); //
            startActivity(intent);
        });
    }


}