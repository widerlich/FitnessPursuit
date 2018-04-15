package pursuit.fitness.fitnesspursuit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class QuestionnaireActivity2 extends BaseActivity {

    NumberPicker pickerAge;
    NumberPicker pickerFrequency;
    Spinner spinnerGoal;
    int age;
    int frequency;
    String [] goals = getResources().getStringArray(R.array.questionnaire_goals);
    String [] tGoals = getResources().getStringArray(R.array.questionnaire_toasts_goals);
    String selectedGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuestionnaireActivity2.this, HomeActivity.class);
                startActivity(i);

            }
        });*/

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
                for (int i = 0; i<goals.length; i++)
                    if(selectedGoal==goals[0])
                        Toast.makeText(getApplicationContext(), tGoals[i], Toast.LENGTH_LONG)
                                .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}