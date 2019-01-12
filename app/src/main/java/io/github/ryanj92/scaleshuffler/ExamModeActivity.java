package io.github.ryanj92.scaleshuffler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamModeActivity extends AppCompatActivity {

    Button mMainMenuButton;
    Button mGrade1SelectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_mode);

        mMainMenuButton = findViewById(R.id.bt_EM_main_menu);
        mGrade1SelectButton = findViewById(R.id.bt_EM_grade1select);

        mMainMenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExamModeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mGrade1SelectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExamModeActivity.this, Grade1ExamActivity.class);
                startActivity(intent);
            }
        });

    }


}
