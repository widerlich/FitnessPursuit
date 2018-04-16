package pursuit.fitness.fitnesspursuit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class QuestionnaireFabActivity extends AppCompatActivity {
    NumberPicker pickerAge;
    NumberPicker pickerFrequency;
    Spinner spinnerGoal;
    Button b1;
    int age;
    int frequency;
    String selectedGoal;
    Spinner spinnerLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Successful submission", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                }
        });


        pickerAge = (NumberPicker)findViewById(R.id.numberPickerAge);
        pickerAge.setMinValue(10);
        pickerAge.setMaxValue(60);
        pickerAge.setValue(25);
        pickerAge.setWrapSelectorWheel(false);

        pickerAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                age = newVal;
            }
        });

        pickerFrequency = (NumberPicker)findViewById(R.id.numberPickerFrequency);
        pickerFrequency.setMinValue(1);
        pickerFrequency.setMaxValue(7);
        pickerFrequency.setValue(3);
        pickerFrequency.setWrapSelectorWheel(false);

        pickerFrequency.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                frequency = newVal;
            }
        });

        spinnerGoal = (Spinner) findViewById(R.id.spinner_goal);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.questionnaire_goals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoal.setAdapter(adapter);
        spinnerGoal.setSelection(0, false);

        spinnerGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGoal = (String) parent.getItemAtPosition(position);

                // Notify the selected item text
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedGoal, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLang = (Spinner) findViewById(R.id.languagespin);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLang.setAdapter(adapter1);
        spinnerLang.setSelection(0, false);

        spinnerLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = (String) parent.getItemAtPosition(position);
                //Set Language preference in DATABASE!
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
