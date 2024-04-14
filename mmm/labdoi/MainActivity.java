package com.example.labdoi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private Button trueButton;
    private Button falseButton;
    private int correctAnswers = 0;
    private String[] questions = {
            "Honda - японський \nвиробник мотоциклів.",
            "Harley-Davidson є найдавнішим \nвиробником мотоциклів у світі.",
            "Kawasaki також виготовляє \nпобутову техніку."
    };

    private boolean[] answers = {
            true,
            true,
            false
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.questionTextView);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        startGame();
    }

    private void startGame() {
        new CountDownTimer(180000, 3000) {
            public void onTick(long millisUntilFinished) {

                generateRandomQuestion();
            }

            public void onFinish() {

                String resultMessage = "Гра завершена! Правильних відповідей: \n\n                       " + correctAnswers;
                questionTextView.setText(resultMessage);
                trueButton.setEnabled(false);
                falseButton.setEnabled(false);
            }
        }.start();


        trueButton.setOnClickListener(v -> {
            if (answers[currentQuestionIndex]) {
                correctAnswers++;
            }
            generateRandomQuestion();
        });

        falseButton.setOnClickListener(v -> {
            if (!answers[currentQuestionIndex]) {
                correctAnswers++;
            }
            generateRandomQuestion();
        });
    }

    private int currentQuestionIndex;


    private void generateRandomQuestion() {
        Random random = new Random();
        currentQuestionIndex = random.nextInt(questions.length);
        questionTextView.setText(questions[currentQuestionIndex]);
    }
}