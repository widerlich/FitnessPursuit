package pursuit.fitness.fitnesspursuit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class CalendarActivity extends AppCompatActivity {

    Button calTotal, proTotal, carbTotal, fatTotal,allTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setTitle(getString(R.string.calendar_title));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        calTotal = (Button) findViewById(R.id.calorie_count_button);
        proTotal = (Button) findViewById(R.id.protein_count_button);
        carbTotal = (Button) findViewById(R.id.carb_count_button);
        fatTotal = (Button) findViewById(R.id.fat_count_button);
        fatTotal.setOnClickListener(launch_graphFat);
        carbTotal.setOnClickListener(launch_graphCarb);
        proTotal.setOnClickListener(launch_graphPro);
        calTotal.setOnClickListener(launch_graphCal);
        final MaterialCalendarView calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        calendarView.state().edit()
                .setFirstDayOfWeek(android.icu.util.Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2018, 3, 0))
                .setMaximumDate(CalendarDay.from(2020, 11, 30))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent intent = new Intent(CalendarActivity.this, CheckFoodActivity.class);
                CalendarDay todayDate = (CalendarDay) calendarView.getSelectedDate();
                String selectedDate = todayDate.getDay() + "/" + todayDate.getMonth() + "/" + todayDate.getYear();
                intent.putExtra("selectedDate", todayDate);
                startActivity(intent);
            }
        });
    }
    private View.OnClickListener launch_graphFat = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CalendarActivity.this,MealGraph.class);
            String f = "fat_count";
            intent.putExtra("selection",f);
            startActivity(intent);
        }
    };
    private View.OnClickListener launch_graphCarb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CalendarActivity.this,MealGraph.class);
            String f = "carb_count";
            intent.putExtra("selection",f);
            startActivity(intent);
        }
    };
    private  View.OnClickListener launch_graphPro = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CalendarActivity.this,MealGraph.class);
            String f = "protein_count";
            intent.putExtra("selection",f);
            startActivity(intent);
        }
    };
    private  View.OnClickListener launch_graphCal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CalendarActivity.this,MealGraph.class);
            String f = "calorie_count";
            intent.putExtra("selection",f);
            startActivity(intent);
        }
    };

    public void addMeal(View view){
        startActivityForResult(new Intent(this, FoodActivity.class), 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, getString(R.string.meal_added), Toast.LENGTH_SHORT).show();
            }
        }
    }
}