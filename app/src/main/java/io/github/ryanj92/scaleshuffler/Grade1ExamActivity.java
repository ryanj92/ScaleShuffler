package io.github.ryanj92.scaleshuffler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Grade1ExamActivity extends AppCompatActivity {

    private int elementNumber = 1;
    private final int totalElements = 5;

    Button mNextElement;
    Button mPrevElement;
    Button mResetExam;
    Button mGradeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade1_exam);

        mNextElement = findViewById(R.id.bt_EMG1_next_element);
        mPrevElement = findViewById(R.id.bt_EMG1_prev_element);
        mResetExam = findViewById(R.id.bt_EMG1_reset_exam);
        mGradeSelect = findViewById(R.id.bt_EMG1_grade_select);

        mGradeSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Grade1ExamActivity.this, ExamModeActivity.class);
                startActivity(intent);
            }
        });

    }



}
