package com.example.labdoi2;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button trueButton;
    private Button falseButton;
    private int correctAnswers = 0;
    private List<Integer> usedIndexes = new ArrayList<>();
    private int roundsCompleted = 0;


    private String[] questions = {
            "Honda - японський виробник \nмотоциклів.",
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
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        startGame();
    }

    private void startGame() {
        new CountDownTimer(180000, 10000) {
            public void onTick(long millisUntilFinished) {

                generateRandomQuestion();
            }

            public void onFinish() {

                String resultMessage = "Гра завершена! Правильних відповідей: " + correctAnswers +
                        "\nПройдено раундів: " + roundsCompleted;
                questionTextView.setText(resultMessage);
                trueButton.setEnabled(false);
                falseButton.setEnabled(false);
            }
        }.start();


        trueButton.setOnClickListener(v -> {
            if (answers[getCurrentQuestionIndex()]) {
                correctAnswers++;
            }
            generateRandomQuestion();
        });

        falseButton.setOnClickListener(v -> {
            if (!answers[getCurrentQuestionIndex()]) {
                correctAnswers++;
            }
            generateRandomQuestion();
        });
    }

    private void generateRandomQuestion() {
        Random random = new Random();
        int index;

        do {
            index = random.nextInt(questions.length);
        } while (usedIndexes.contains(index));
        usedIndexes.add(index);
        if (usedIndexes.size() == questions.length) {

            usedIndexes.clear();
            roundsCompleted++;
        }
        setCurrentQuestionIndex(index);
        questionTextView.setText(questions[index]);
    }

    private int currentQuestionIndex;


    private int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    private void setCurrentQuestionIndex(int index) {
        currentQuestionIndex = index;
    }
}