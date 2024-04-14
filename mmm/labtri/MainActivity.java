package com.example.labtri;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Spinner answerSpinner;
    private Button submitButton;
    private ProgressBar progressBar;
    private int correctAnswers = 0;
    private CountDownTimer countDownTimer;


    private String[] questions = {
            "Honda - японський виробник мотоциклів.",
            "Harley-Davidson є найдавнішим виробником мотоциклів у світі.",
            "Kawasaki також виготовляє побутову техніку."
    };

    private boolean[] answers = {
            true,
            true,
            false
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerSpinner = findViewById(R.id.answerSpinner);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.rounded_spinner_item, new String[]{"Вірно", "Не вірно"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        answerSpinner.setAdapter(adapter);

        startGame();
    }
    private void startGame() {
        countDownTimer = new CountDownTimer(180000, 4000) {
            public void onTick(long millisUntilFinished) {

                int progress = (int) (millisUntilFinished / 1000);
                progressBar.setProgress(progress);

                generateRandomQuestion();
            }

            public void onFinish() {

                String resultMessage = "Гра завершена! Правильних відповідей: " + correctAnswers;
                questionTextView.setText(resultMessage);
                submitButton.setEnabled(false);
            }
        }.start();


        submitButton.setOnClickListener(v -> {

            String selectedAnswer = answerSpinner.getSelectedItem().toString();
            boolean isCorrect = selectedAnswer.equals("Вірно") == answers[currentQuestionIndex];
            if (isCorrect) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}


