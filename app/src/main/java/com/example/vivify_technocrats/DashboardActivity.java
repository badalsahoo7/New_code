package com.example.vivify_technocrats;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vivify_technocrats.ModelClass;
import com.example.vivify_technocrats.R;
import com.example.vivify_technocrats.ResultActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.os.Handler;

public class DashboardActivity extends AppCompatActivity {

    boolean optionsEnabled = true;
    LinearLayout next_button;

    public static ArrayList<ModelClass> list;

    DatabaseReference databaseReference;

    CountDownTimer countDownTimer;
    ProgressBar progressBar;
    int timerValue = 10;

    ModelClass modelClass;
    int index = 0;
    TextView card_question, card_optiona, card_optionb, card_optionc, card_optiond;
    CardView questioncard, cardA, cardB, cardC, cardD;

    int correctCount = 0;
    int wrongCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Hooks();

        list = new ArrayList<>();


        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Question");

        // Retrieve data from Firebase
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear the list before adding new data
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelClass modelClass = snapshot.getValue(ModelClass.class);
                    list.add(modelClass);
                }
                // Once data retrieval is complete, set the first question
                setQuestion(index);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled
            }
        });
        enableOptions();

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(100);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue--;
                int progress = (int) (100 * (millisUntilFinished / 20000.0));
                progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                //showTimeOutDialog();
            }
        }.start();

        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNextQuestion();
                resetColors();
                enableOptions();
            }
        });

        // Set click listeners for options
        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionsEnabled) {
                    checkAnswer(card_optiona.getText().toString());
               enableOptions();
                }
            }
        });

        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionsEnabled) {
                    checkAnswer(card_optionb.getText().toString());
                    enableOptions();
                }
            }
        });

        cardC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionsEnabled) {
                    checkAnswer(card_optionc.getText().toString());
                enableOptions();
                }
            }
        });

        cardD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionsEnabled) {
                    checkAnswer(card_optiond.getText().toString());
                enableOptions();
                }
            }
        });
    }

    private void setQuestion(int i) {
        if (index < list.size()) {
            modelClass = list.get(index);
            card_question.setText(modelClass.getQuestion());
            card_optiona.setText(modelClass.getqA());
            card_optionb.setText(modelClass.getqB());
            card_optionc.setText(modelClass.getqC());
            card_optiond.setText(modelClass.getqD());
        }
    }

    private void showTimeOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.time_out_dialog, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        LinearLayout btnTryAgain = dialogView.findViewById(R.id.btn_tryAgain);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void Hooks() {
        card_question = findViewById(R.id.card_question);
        card_optiona = findViewById(R.id.card_optiona);
        card_optionb = findViewById(R.id.card_optionb);
        card_optionc = findViewById(R.id.card_optionc);
        card_optiond = findViewById(R.id.card_optiond);

        questioncard = findViewById(R.id.questioncard);
        cardA = findViewById(R.id.cardA);
        cardB = findViewById(R.id.cardB);
        cardC = findViewById(R.id.cardC);
        cardD = findViewById(R.id.cardD);
    }

    public void Correct() {
        correctCount++;
        moveToNextQuestion();
    }

    public void Wrong() {
        wrongCount++;
       moveToNextQuestion();
    }

    private void disableOptions() {
        optionsEnabled = false;
    }

    private void enableOptions() {
        optionsEnabled = true;
    }

    private void checkAnswer(String selectedOption) {
        String correctAnswer = modelClass.getAns();

        if (selectedOption.equals(correctAnswer)) {
            Toast.makeText(DashboardActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            Correct();
        } else {
            Toast.makeText(DashboardActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
            Wrong();
        }
        // Disable options after checking the answer
        disableOptions();

    }



    private void resetColors() {
        cardA.setCardBackgroundColor(Color.WHITE);
        cardB.setCardBackgroundColor(Color.WHITE);
        cardC.setCardBackgroundColor(Color.WHITE);
        cardD.setCardBackgroundColor(Color.WHITE);
    }

    private void moveToNextQuestion() {
        if (index < list.size() - 1) {
            index++;
            setQuestion(index); // Set options for the next question
            resetColors();
            enableOptions();
        } else {
            // If it's the last question, start the ResultActivity
            Intent intent = new Intent(DashboardActivity.this, ResultActivity.class);
            intent.putExtra("correctCount", correctCount);
            intent.putExtra("wrongCount", wrongCount);
            startActivity(intent);
            finish();
        }
    }

}

