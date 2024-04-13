package com.example.mmm;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    TextView tex;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tex = findViewById(R.id.textView1);
        but = findViewById(R.id.button1);

        OnClickListener obrabotka = new OnClickListener() {
            @Override
            public void onClick(View v) {
                tex.setText("Button was pressed!");
            }

        };
        but.setOnClickListener(obrabotka);
    }
    }
