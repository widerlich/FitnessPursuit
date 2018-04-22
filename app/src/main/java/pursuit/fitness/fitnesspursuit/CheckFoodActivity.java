package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckFoodActivity extends AppCompatActivity {

    Database db;
    private GestureDetectorCompat gestureObject;
    private CalendarDay todayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.food_log_overview));
        setContentView(R.layout.activity_check_food);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        TextView totalFood = (TextView)findViewById(R.id.totalFood);
        TextView showDate = (TextView)findViewById(R.id.showDate);
        ListView list = (ListView)findViewById(R.id.ListView);

        db = new Database(this);
        Bundle i = getIntent().getExtras();
        todayDate = (CalendarDay)i.getParcelable("selectedDate");

        String selectedDate = todayDate.getDay()+"/"+todayDate.getMonth()+"/"+todayDate.getYear();
        ArrayList<String> FoodDiary = db.checkFoodCalendar(selectedDate);
        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,FoodDiary);
        list.setAdapter(listAdapter);
        ArrayList<Double>FoodTotal = db.getTotal(selectedDate);
        totalFood.setText(
                "\n Total calorie intake (cal):          "+FoodTotal.get(0)+
                "\n Total protein intake (g):             "+ FoodTotal.get(1)+
                "\n Total carbohydrate intake (g):  "+ FoodTotal.get(2)+
                "\n Total fat intake (g):                     "+ FoodTotal.get(3));

       /* //if(language == "Deutsch")
        totalFood.setText("Tagesübersicht"+
                "\n Gesamtkalorien (cal):\t"+FoodTotal.get(0)+
                "\n Gesamtprotein (g):\t"+ FoodTotal.get(1)+
                "\n Gesamtkohlenhydrate (g):\t"+ FoodTotal.get(2)+
                "\n Gesamtfett (g):\t\t"+ FoodTotal.get(3));

        //if(language == "Français")
        totalFood.setText("Vue d'ensemble"+
                "\n Apport calorique (cal):\t"+FoodTotal.get(0)+
                "\n Protèines totales (g):\t"+ FoodTotal.get(1)+
                "\n Carbohydrates totales (g):\t"+ FoodTotal.get(2)+
                "\n Graisse totale (g):\t"+ FoodTotal.get(3));*/


        int newDay= todayDate.getDay(),
                newMonth= todayDate.getMonth(),
                newYear= todayDate.getYear();
        CalendarDay newCalendarDay = new CalendarDay(newYear,newMonth,newDay);
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        showDate.setText(dateFormat.format(newCalendarDay.getDate()));

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int newDay= todayDate.getDay()+1,
                    newMonth= todayDate.getMonth(),
                    newYear= todayDate.getYear();
            if(e2.getX() > e1.getX()){
                Intent i = new Intent(CheckFoodActivity.this,CheckFoodActivity.class);

                CalendarDay b = new CalendarDay();
                //String.valueOf((a.getDay()-1)+"/"+a.getMonth()+"/"+a.getYear());
                newDay = newDay-2;
                CalendarDay newDate = new CalendarDay(newYear,newMonth,newDay);
                i.putExtra("selectedDate",newDate);

                finish();
                startActivity(i);
            }
            else
            if(e2.getX() < e1.getX()){
                Intent i = new Intent(CheckFoodActivity.this,CheckFoodActivity.class);
                newDay = newDay;
                CalendarDay newDate = new CalendarDay(newYear,newMonth,newDay);
                i.putExtra("selectedDate",newDate);

                finish();
                startActivity(i);
            }
            return true;
        }

    }
}