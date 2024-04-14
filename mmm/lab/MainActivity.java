package com.example.lab;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView colorNameTextView;
    private Button yesButton;
    private Button noButton;
    private int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        colorNameTextView = findViewById(R.id.colorNameTextView);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);


        generateRandomColor();

        startGame();
        };
    private void startGame() {
        new CountDownTimer(180000, 1000) {
            public void onTick(long millisUntilFinished) {

                generateRandomColor();
            }

            public void onFinish() {

                String resultMessage = "Гра завершена! Правильних відповідей: " + correctAnswers;
                colorNameTextView.setText(resultMessage);
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
            }
        }.start();


        yesButton.setOnClickListener(v -> {
            if (isTextColorEqualsNameColor()) {
                correctAnswers++;
            }
            generateRandomColor();
        });


        noButton.setOnClickListener(v -> {
            if (!isTextColorEqualsNameColor()) {
                correctAnswers++;
            }
            generateRandomColor();
        });
    }


    private void generateRandomColor() {
        String[] colors = getResources().getStringArray(R.array.colors);
        Random random = new Random();
        int randomIndex = random.nextInt(colors.length);

        String color = colors[randomIndex];
        int colorResource = getResources().getIdentifier(color.toLowerCase(), "color", getPackageName());

        colorNameTextView.setText(color.toUpperCase());
        colorNameTextView.setTextColor(getResources().getColor(colorResource));
    }


    private boolean isTextColorEqualsNameColor() {
        String currentColorName = colorNameTextView.getText().toString().toLowerCase();
        @SuppressLint("ResourceType") String currentTextColor = getResources().getResourceEntryName(colorNameTextView.getCurrentTextColor()).toLowerCase();
        return currentTextColor.equals(currentColorName);
    }
    }
