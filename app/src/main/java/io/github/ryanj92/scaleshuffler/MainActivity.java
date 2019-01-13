package io.github.ryanj92.scaleshuffler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mExamMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExamMenuButton = findViewById(R.id.bt_MM_exam_mode);

        /*
        // load pre-existing text (in case of screen rotation, etc.)
        if (savedInstanceState != null) {

        }
        */

        mExamMenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExamModeActivity.class);
                startActivity(intent);
            }
        });

    }

    /*
    // saves current exercise in case of screen rotation, switching apps etc.
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

    }
    */

}
