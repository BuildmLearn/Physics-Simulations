package org.buildmlearn.physicssimulations;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.buildmlearn.physicssimulations.utils.Constants;

import java.util.Date;
import java.util.Random;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class TestActivity extends AppCompatActivity {

    Button answerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        final String simName = intent.getStringExtra(Constants.SIM_NAME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(simName);
        setSupportActionBar(toolbar);

        final int idSim = Constants.getId(simName);
        final int idProblem = (new Random((new Date()).getTime())).nextInt(3);

        TextView problemText = (TextView) findViewById(R.id.problem_text);
        problemText.setText("\t\t" + Constants.PROBLEMS[idSim][idProblem][Constants.PROBLEM]);

        RadioButton radioButtonA = (RadioButton) findViewById(R.id.radio_a);
        radioButtonA.setText(Constants.PROBLEMS[idSim][idProblem][Constants.ANSWER_1]);
        RadioButton radioButtonB = (RadioButton) findViewById(R.id.radio_b);
        radioButtonB.setText(Constants.PROBLEMS[idSim][idProblem][Constants.ANSWER_2]);
        RadioButton radioButtonC = (RadioButton) findViewById(R.id.radio_c);
        radioButtonC.setText(Constants.PROBLEMS[idSim][idProblem][Constants.ANSWER_3]);
        RadioButton radioButtonD = (RadioButton) findViewById(R.id.radio_d);
        radioButtonD.setText(Constants.PROBLEMS[idSim][idProblem][Constants.ANSWER_4]);

        answerButton = (Button) findViewById(R.id.answer_button);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = ((RadioGroup) findViewById(R.id.radio_group))
                        .getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(TestActivity.this, "You have to select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }
                String yourAnswer = ((RadioButton) findViewById(selectedId))
                        .getText().toString();

                if (yourAnswer.equals(Constants.PROBLEMS[idSim][idProblem][Constants.ANSWER_CORRECT])) {
                    new AlertDialog.Builder(TestActivity.this)
                            .setTitle("Correct")
                            .setMessage("Your Answer is Correct\nWell Done!")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setIcon(android.R.drawable.presence_online)
                            .show();
                }
                else {
                    new AlertDialog.Builder(TestActivity.this)
                            .setTitle("Wrong")
                            .setMessage("Your Answer is Wrong\nTry again!")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .setIcon(android.R.drawable.presence_busy)
                            .show();
                }
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean showTutorial = sharedPreferences.getBoolean(Constants.TUT_TEST, true);
        if (showTutorial) {
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(500);
            config.setMaskColor(Constants.COLOR_MASK);
            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
            sequence.setConfig(config);
            sequence.addSequenceItem(problemText,
                    "Read the problem", "GOT IT");
            sequence.addSequenceItem(radioButtonB,
                    "Select your answer", "OK");
            sequence.addSequenceItem(answerButton,
                    "Check your answer", "GOT IT");
            sequence.start();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.TUT_TEST, false);
            editor.apply();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
