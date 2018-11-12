package io.github.ryanj92.scaleshuffler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mScaleDisplay;
    private Button btScaleShuffle;

    static final String CURRENT_SCALE = "currentScale";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // member variables
        mScaleDisplay = (TextView) findViewById(R.id.tv_scale_display);
        btScaleShuffle = (Button) findViewById(R.id.bt_scale_shuffle);

        // load pre-existing text (in case of screen rotation, etc.)
        if (savedInstanceState != null) {
            mScaleDisplay.setText(savedInstanceState.getString(CURRENT_SCALE));
        }

        // generate new scale when button is pressed
        btScaleShuffle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                randomScale(mScaleDisplay);
            }
        });
    }

    // saves current exercise in case of screen rotation, switching apps etc.
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(CURRENT_SCALE,mScaleDisplay.getText().toString());
        super.onSaveInstanceState(outState);

    }

    // function for generating random exercise
    void randomScale(TextView text) {

        StringBuilder newScale = new StringBuilder();

        String[] scaleTypes = {"Similar Motion", "Scale A Third Apart", "Scale A Sixth Apart",
            "Legato Scale In Thirds", "Chromatic Scales A Minor Third Apart",
            "Chromatic Scale In Minor Thirds", "Whole-Tone Scale", "Arpeggios", "Dominant Sevenths",
            "Diminished Sevenths"};

        String[] scaleMajOrMin = {"Major", "Harmonic Minor", "Melodic Minor"};
        String[] scaleLegOrSta = {"Legato", "Staccato"};
        String[] scaleHands = {"Right Hand", "Left Hand", "Hands Together"};
        String[] scaleMajorKeys = {"C", "D", "B", "F#", "F", "Eb", "Ab", "Db"};
        String[] scaleMinorKeys = {"C", "D", "B", "F#", "F", "Eb", "G#", "C#"};
        String[] scaleLegThirds = {"C Major\nRight Hand", "C Major\nLeft Hand",
                "Bb Major\nRight Hand", "Bb Major\nLeft Hand"};
        String[] scaleChromKeys = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        String[] arpegPositions = {"Root Position", "First Inversion", "Second Inversion"};

        int chosenScaleType = (int) (Math.random() * scaleTypes.length);

        newScale.append(scaleTypes[chosenScaleType] + "\n\n");

        int chosenMajOrMin, chosenKey, chosenHands, chosenLegOrSta, chosenLegThird, chosenChromKey,
            chosenPosition;

        switch (chosenScaleType) {

            case 0: // SCALES (SIMILAR MOTION)
                chosenMajOrMin = (int) (Math.random() * 3);
                chosenKey = (int) (Math.random() * 8);
                chosenHands = (int) (Math.random() * 3);
                chosenLegOrSta = (int) (Math.random() * 2);

                if (chosenMajOrMin == 0) {
                    newScale.append(scaleMajorKeys[chosenKey] + " " + scaleMajOrMin[chosenMajOrMin]
                        + "\n" + scaleHands[chosenHands] + "\n" + scaleLegOrSta[chosenLegOrSta]
                        + "\n" + "Tempo: 88");
                }
                else {
                    newScale.append(scaleMinorKeys[chosenKey] + " " + scaleMajOrMin[chosenMajOrMin]
                        + "\n" + scaleHands[chosenHands] + "\n" + scaleLegOrSta[chosenLegOrSta]
                        + "\nTempo: 88");
                }
                break;

            case 1: // SCALES A THIRD APART
                chosenMajOrMin = (int) (Math.random() * 2); // MAJOR AND HARMONIC MINORS ONLY
                chosenKey = (int) (Math.random() * 8);
                chosenLegOrSta = (int) (Math.random() * 2);

                if (chosenMajOrMin == 0) {
                    newScale.append(scaleMajorKeys[chosenKey] + " " + scaleMajOrMin[chosenMajOrMin]
                            + "\n" + scaleLegOrSta[chosenLegOrSta] + "\nTempo: 63");
                }
                else {
                    newScale.append(scaleMinorKeys[chosenKey] + " " + scaleMajOrMin[chosenMajOrMin]
                            + "\n" + scaleLegOrSta[chosenLegOrSta] + "\nTempo: 63");
                }
                break;

            case 2: // SCALES A SIXTH APART
                chosenMajOrMin = (int) (Math.random() * 2); // MAJOR AND HARMONIC MINORS ONLY
                chosenKey = (int) (Math.random() * 8);
                chosenLegOrSta = (int) (Math.random() * 2);

                if (chosenMajOrMin == 0) {
                    newScale.append(scaleMajorKeys[chosenKey] + " " + scaleMajOrMin[chosenMajOrMin]
                            + "\n" + scaleLegOrSta[chosenLegOrSta] + "\nTempo: 63");
                }
                else {
                    newScale.append(scaleMinorKeys[chosenKey] + " " + scaleMajOrMin[chosenMajOrMin]
                            + "\n" + scaleLegOrSta[chosenLegOrSta] + "\nTempo: 63");
                }
                break;

            case 3: // LEGATO SCALES IN THIRDS
                chosenLegThird = (int) (Math.random() * 4);
                newScale.append(scaleLegThirds[chosenLegThird] + "\nTempo: 52");
                break;

            case 4: // CHROMATIC SCALES A MINOR THIRD APART
                chosenChromKey = (int) (Math.random() * 12);
                chosenLegOrSta = (int) (Math.random() * 2);

                newScale.append("Starting on " + scaleChromKeys[chosenChromKey] + " and "
                    + scaleChromKeys[(chosenChromKey + 3) % 12] + "\n"
                    + scaleLegOrSta[chosenLegOrSta] + "\nTempo: 76");
                break;

            case 5: // CHROMATIC SCALE IN MINOR THIRDS
                chosenHands = (int) (Math.random() * 2); // ONE HAND ONLY

                newScale.append("Starting on A# and C#\n" + scaleHands[chosenHands] + "\nTempo: 52");
                break;

            case 6: // WHOLE TONE SCALE
                chosenHands = (int) (Math.random() * 3); // ONE HAND ONLY

                newScale.append("Starting on E\n" + scaleHands[chosenHands] + "\nTempo: 88");
                break;

            case 7: // ARPEGGIOS
                chosenKey = (int) (Math.random() * 8);
                chosenMajOrMin = (int) (Math.random() * 2);
                chosenPosition = (int) (Math.random() * 3);
                chosenHands = (int) (Math.random() * 3);

                if (chosenMajOrMin == 0) {
                    newScale.append(scaleMajorKeys[chosenKey] + " Major\n" + arpegPositions[chosenPosition]
                    + "\n" + scaleHands[chosenHands] + "\nTempo: 66");
                } else {
                    newScale.append(scaleMinorKeys[chosenKey] + " Minor\n" + arpegPositions[chosenPosition]
                            + "\n" + scaleHands[chosenHands] + "\nTempo: 66");
                }
                break;

            case 8: // DOMINANT SEVENTHS
                chosenKey = (int) (Math.random() * 8);
                chosenHands = (int) (Math.random() * 3);

                newScale.append("In the key of " + scaleMajorKeys[chosenKey] + "\n" +
                    scaleHands[chosenHands] + "\nTempo: 66");
                break;

            case 9: // DIMINISHED SEVENTHS
                chosenChromKey = (int) (Math.random() * 12);
                chosenHands = (int) (Math.random() * 3);

                newScale.append("Starting on " + scaleChromKeys[chosenChromKey] + "\n"
                    + scaleHands[chosenHands] + "\nTempo: 66");
                break;

            default:
                newScale.append("ERROR");
                break;
        }

        text.setText(newScale);

    }

}
