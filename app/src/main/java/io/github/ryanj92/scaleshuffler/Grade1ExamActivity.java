package io.github.ryanj92.scaleshuffler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Grade1ExamActivity extends AppCompatActivity {

    private int elementNumber;
    private final int totalElements = 5;
    private final int elementNumberDefault = 1;

    private Button mNextElement;
    private Button mPrevElement;
    private Button mResetExam;
    private Button mGradeSelect;

    private TextView mCurrentElementName;
    private TextView mCurrentElementKey;
    private TextView mCurrentElementHands;
    private TextView mCurrentElementTempo;

    private ArrayList<String> currentElement = new ArrayList<>();
    private Random randIntGen = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade1_exam);

        // Find navigation buttons
        mNextElement = findViewById(R.id.bt_EMG1_next_element);
        mPrevElement = findViewById(R.id.bt_EMG1_prev_element);
        mResetExam = findViewById(R.id.bt_EMG1_reset_exam);
        mGradeSelect = findViewById(R.id.bt_EMG1_grade_select);

        // Find current element text views
        mCurrentElementName = findViewById(R.id.tv_EMG1_current_element_name);
        mCurrentElementKey = findViewById(R.id.tv_EMG1_current_element_key);
        mCurrentElementHands = findViewById(R.id.tv_EMG1_current_element_hands);
        mCurrentElementTempo = findViewById(R.id.tv_EMG1_current_element_tempo);

        // Set elementNumber if not already assigned, ensure buttons are disabled if necessary
        if (elementNumber == 0) {
            elementNumber = elementNumberDefault;
            mPrevElement.setEnabled(false);
        } else if (elementNumber == 1) {
            mPrevElement.setEnabled(false);
        } else if (elementNumber == totalElements) {
            mNextElement.setEnabled(false);
        }

        // Update element if none is currently being displayed
        if (currentElement.isEmpty()) {
            updateElement(elementNumber);
        }

        // Set onClickListeners for all navigation buttons -----------------------------------------

        mGradeSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Grade1ExamActivity.this, ExamModeActivity.class);
                startActivity(intent); // Return to Grade Select
            }
        });

        mNextElement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Move to next element in list, update element
                if (elementNumber < totalElements) {
                    elementNumber++;
                    updateElement(elementNumber);
                }

                // disable and re-enable buttons
                if (elementNumber == 2) {
                    mPrevElement.setEnabled(true);
                } else if (elementNumber == totalElements) {
                    mNextElement.setEnabled(false);
                }

            }
        });

        mPrevElement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Move to previous element in list, update element
                if (elementNumber > 1) {
                    elementNumber--;
                    updateElement(elementNumber);
                }

                // disable and re-enable buttons
                if (elementNumber == 1) {
                    mPrevElement.setEnabled(false);
                } else if (elementNumber < totalElements) {
                    mNextElement.setEnabled(true);
                }
            }
        });

        mResetExam.setOnClickListener(new View.OnClickListener() {

            // Return to first element, update element and buttons
            public void onClick(View v) {
                elementNumber = elementNumberDefault;
                updateElement(elementNumber);
                mPrevElement.setEnabled(false);
                mNextElement.setEnabled(true);
            }

        });

    }

    private void updateElement(int elementNumber) {

        // Remove any previously stored element data
        if (!currentElement.isEmpty()) {
            currentElement.clear();
        }

        // Populate currentElement with the correct data
        switch (elementNumber) {
            case 1:
                makeGrade1MajorScale();
                break;
            case 2:
                makeGrade1MinorScale();
                break;
            case 3:
                makeGrade1ContMotionScale();
                break;
            case 4:
                makeGrade1MajorBrokenChord();
                break;
            case 5:
                makeGrade1MinorBrokenChord();
                break;
        }

        // Update TextViews
        mCurrentElementName.setText(currentElement.get(0));
        mCurrentElementKey.setText(currentElement.get(1));
        mCurrentElementHands.setText(currentElement.get(2));
        mCurrentElementTempo.setText(currentElement.get(3));

    }

    private void makeGrade1MajorScale() {

        // add element name
        currentElement.add(getString(R.string.EMG1_element_major_scale));

        // choose element key
        switch (randIntGen.nextInt(4))  {
            case 0:
                currentElement.add(getString(R.string.EMG1_c_major));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_g_major));
                break;
            case 2:
                currentElement.add(getString(R.string.EMG1_d_major));
                break;
            case 3:
                currentElement.add(getString(R.string.EMG1_f_major));
                break;
        }

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                currentElement.add(getString(R.string.EMG1_right_hand));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_left_hand));
                break;
        }

        // add element tempo
        currentElement.add(getString(R.string.EMG1_scale_tempo));

    }

    private void makeGrade1MinorScale() {

        // add element name
        currentElement.add(getString(R.string.EMG1_element_minor_scale));

        // choose element key
        switch (randIntGen.nextInt(2))  {
            case 0:
                currentElement.add(getString(R.string.EMG1_a_minor));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_d_minor));
                break;
        }

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                currentElement.add(getString(R.string.EMG1_right_hand));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_left_hand));
                break;
        }

        // add element tempo
        currentElement.add(getString(R.string.EMG1_scale_tempo));

    }

    private void makeGrade1ContMotionScale() {

        currentElement.add(getString(R.string.EMG1_element_cont_motion_scale));
        currentElement.add(getString(R.string.EMG1_starting_on_C));
        currentElement.add(getString(R.string.EMG1_both_hands));
        currentElement.add(getString(R.string.EMG1_scale_tempo));

    }

    private void makeGrade1MajorBrokenChord() {

        // add element name
        currentElement.add(getString(R.string.EMG1_element_major_bc));

        // choose element key
        switch (randIntGen.nextInt(3))  {
            case 0:
                currentElement.add(getString(R.string.EMG1_c_major));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_g_major));
                break;
            case 2:
                currentElement.add(getString(R.string.EMG1_f_major));
                break;
        }

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                currentElement.add(getString(R.string.EMG1_right_hand));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_left_hand));
                break;
        }

        // add element tempo
        currentElement.add(getString(R.string.EMG1_bc_tempo));

    }

    private void makeGrade1MinorBrokenChord() {

        // add element name
        currentElement.add(getString(R.string.EMG1_element_minor_bc));

        // choose element key
        switch (randIntGen.nextInt(3))  {
            case 0:
                currentElement.add(getString(R.string.EMG1_a_minor));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_d_minor));
                break;
        }

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                currentElement.add(getString(R.string.EMG1_right_hand));
                break;
            case 1:
                currentElement.add(getString(R.string.EMG1_left_hand));
                break;
        }

        // add element tempo
        currentElement.add(getString(R.string.EMG1_bc_tempo));

    }

}
