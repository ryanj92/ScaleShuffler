package io.github.ryanj92.scaleshuffler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.app.AppCompatActivity;

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
    private TextView mCurrentElementNumber;

    public ExamModeViewModel currentElementViewModel;

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
        mCurrentElementNumber = findViewById(R.id.tv_EMG1_current_element_number);

        // Create or retrieve ViewModel
        currentElementViewModel = ViewModelProviders.of(this).get(ExamModeViewModel.class);

        // Initialize ViewModel if null, otherwise fetch and display current element
        if (currentElementViewModel.getElementName() == null) {
            updateElement(elementNumberDefault);
        } else {
            mCurrentElementName.setText(currentElementViewModel.getElementName());
        }

        if (!(currentElementViewModel.getElementKey() == null)) {
            mCurrentElementKey.setText(currentElementViewModel.getElementKey());
        }
        if (!(currentElementViewModel.getElementHands() == null)) {
            mCurrentElementHands.setText(currentElementViewModel.getElementHands());
        }
        if (!(currentElementViewModel.getElementTempo() == null)) {
            mCurrentElementTempo.setText(currentElementViewModel.getElementTempo());
        }

        elementNumber = currentElementViewModel.getElementNumber();

        if (elementNumber == 0) {
            elementNumber = elementNumberDefault;
            currentElementViewModel.putElementNumber(elementNumber);
        }

        if (elementNumber == 1) {
            mPrevElement.setEnabled(false);
        } else if (elementNumber == totalElements) {
            mNextElement.setEnabled(false);
        }

        mCurrentElementNumber.setText(String.format(getString(R.string.EMG1_element_number),elementNumber,totalElements));

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
                    currentElementViewModel.putElementNumber(elementNumber);
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
                    currentElementViewModel.putElementNumber(elementNumber);
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
                currentElementViewModel.putElementNumber(elementNumber);
                updateElement(elementNumber);
                mPrevElement.setEnabled(false);
                mNextElement.setEnabled(true);
            }

        });

    }

    private void updateElement(int elementNumber) {

        // Populate ViewModel with the correct data
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
        mCurrentElementName.setText(currentElementViewModel.getElementName());
        mCurrentElementKey.setText(currentElementViewModel.getElementKey());
        mCurrentElementHands.setText(currentElementViewModel.getElementHands());
        mCurrentElementTempo.setText(currentElementViewModel.getElementTempo());

        mCurrentElementNumber.setText(String.format(getString(R.string.EMG1_element_number),elementNumber,totalElements));

    }

    private void makeGrade1MajorScale() {

        // add element name
        String elementName = getString(R.string.EMG1_element_major_scale);
        currentElementViewModel.putElementName(elementName);

        String elementKey;
        String elementHands;
        String elementTempo;

        // choose element key
        switch (randIntGen.nextInt(4))  {
            case 0:
                elementKey = getString(R.string.EMG1_c_major);
                break;
            case 1:
                elementKey = getString(R.string.EMG1_g_major);
                break;
            case 2:
                elementKey = getString(R.string.EMG1_d_major);
                break;
            case 3:
                elementKey = getString(R.string.EMG1_f_major);
                break;
            default:
                elementKey = getString(R.string.EMG1_element_key_default);
                break;
        }

        currentElementViewModel.currentElementKey = elementKey;

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                elementHands = getString(R.string.EMG1_right_hand);
                break;
            case 1:
                elementHands = getString(R.string.EMG1_left_hand);
                break;
            default:
                elementHands = getString(R.string.EMG1_element_hands_default);
                break;
        }

        currentElementViewModel.currentElementHands = elementHands;

        // add element tempo
        elementTempo = getString(R.string.EMG1_scale_tempo);
        currentElementViewModel.currentElementTempo = elementTempo;

    }

    private void makeGrade1MinorScale() {

        // add element name
        String elementName = getString(R.string.EMG1_element_minor_scale);
        currentElementViewModel.putElementName(elementName);

        String elementKey;
        String elementHands;
        String elementTempo;

        // choose element key
        switch (randIntGen.nextInt(2))  {
            case 0:
                elementKey = getString(R.string.EMG1_a_minor);
                break;
            case 1:
                elementKey = getString(R.string.EMG1_d_minor);
                break;
            default:
                elementKey = getString(R.string.EMG1_element_key_default);
                break;
        }

        currentElementViewModel.currentElementKey = elementKey;

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                elementHands = getString(R.string.EMG1_right_hand);
                break;
            case 1:
                elementHands = getString(R.string.EMG1_left_hand);
                break;
            default:
                elementHands = getString(R.string.EMG1_element_hands_default);
                break;
        }

        currentElementViewModel.currentElementHands = elementHands;

        // add element tempo
        elementTempo = getString(R.string.EMG1_scale_tempo);
        currentElementViewModel.currentElementTempo = elementTempo;

    }

    private void makeGrade1ContMotionScale() {

        currentElementViewModel.putElementName(getString(R.string.EMG1_element_cont_motion_scale));
        currentElementViewModel.putElementKey(getString(R.string.EMG1_starting_on_C));
        currentElementViewModel.putElementHands(getString(R.string.EMG1_both_hands));
        currentElementViewModel.putElementTempo(getString(R.string.EMG1_scale_tempo));

    }

    private void makeGrade1MajorBrokenChord() {

        // add element name
        currentElementViewModel.putElementName(getString(R.string.EMG1_element_major_bc));

        // choose element key
        switch (randIntGen.nextInt(3))  {
            case 0:
                currentElementViewModel.putElementKey(getString(R.string.EMG1_c_major));
                break;
            case 1:
                currentElementViewModel.putElementKey(getString(R.string.EMG1_g_major));
                break;
            case 2:
                currentElementViewModel.putElementKey(getString(R.string.EMG1_f_major));
                break;
        }

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                currentElementViewModel.putElementHands(getString(R.string.EMG1_right_hand));
                break;
            case 1:
                currentElementViewModel.putElementHands(getString(R.string.EMG1_left_hand));
                break;
        }

        // add element tempo
        currentElementViewModel.putElementTempo(getString(R.string.EMG1_bc_tempo));

    }

    private void makeGrade1MinorBrokenChord() {

        // add element name
        currentElementViewModel.putElementName(getString(R.string.EMG1_element_minor_bc));

        // choose element key
        switch (randIntGen.nextInt(3))  {
            case 0:
                currentElementViewModel.putElementKey(getString(R.string.EMG1_a_minor));
                break;
            case 1:
                currentElementViewModel.putElementKey(getString(R.string.EMG1_d_minor));
                break;
        }

        // choose element hands
        switch (randIntGen.nextInt(2)) {
            case 0:
                currentElementViewModel.putElementHands(getString(R.string.EMG1_right_hand));
                break;
            case 1:
                currentElementViewModel.putElementHands(getString(R.string.EMG1_left_hand));
                break;
        }

        // add element tempo
        currentElementViewModel.putElementTempo(getString(R.string.EMG1_bc_tempo));

    }

}
