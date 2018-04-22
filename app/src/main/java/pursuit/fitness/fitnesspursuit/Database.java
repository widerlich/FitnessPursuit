package pursuit.fitness.fitnesspursuit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public class Database {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    Context context;
    private final static String USERS_TABLE="users";
    private final static String USER_NAME="username";
    private final static String AGE="age";
    private final static String FREQUENCY = "frequency";
    private final static String GOAL = "goal";
    private final static String LANGUAGE = "language";

    private String userid;
    private int age;
    private String goal;
    private int frequency;

    Database(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    protected void checkUser() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user!=null) {
            userid = user.getUid();
        }
        else{

        }
    }

    public void execSQL(String s) {
        database.execSQL(s);
    }

    public long insertQuery(String userid){
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userid);
        return database.insert(USERS_TABLE, null, values);
    }

    public long insertQuestionnaire(int age, String goal, int frequency){
        ContentValues values = new ContentValues();
        values.put(AGE, age);
        values.put(FREQUENCY, frequency);
        values.put(GOAL, goal);
        return database.insert(USERS_TABLE, null, values);
    }

    public Cursor runQuery(String query, String[] args) {
        Cursor mCursor = database.rawQuery(query, args);
        if (mCursor.moveToFirst()) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    protected ArrayList<String> checkFoodCalendar(String date) {
        Cursor c = database.rawQuery("Select * from foodDiary where meal_date = '" + date + "' ;", null);
        ArrayList<String>theArray = new ArrayList<>();
        if (c.getCount() == 0) {
        }

        while(c.moveToNext()){
            //if(language == "English")
            String result =
                    "\nMeal Name:  "        + c.getString(1)
                    +"\nCalories:        "        + c.getString(2)
                    +"\nProtein:          "         + c.getString(3)
                    +"\nCarbs:            "           + c.getString(4)
                    +"\nFats:               "            + c.getString(5)
                    +"\n";

            //if(language == "Deutsch")
       /*     String result = "Gericht:\t"    + c.getString(1)
                    +"\nKalorien:\t"        + c.getString(2)
                    +"\nProtein:\t"         + c.getString(3)
                    +"\nKohlenhydrate:\t"   + c.getString(4)
                    +"\nFette:\t"           + c.getString(5)
                    +"\n";

             //if(language == "Français")
            String result = "Repas:\t"  + c.getString(1)
                    +"\nCalories:\t"    + c.getString(2)
                    +"\nProtèines:\t"     + c.getString(3)
                    +"\nCarbohydrates:\t"       + c.getString(4)
                    +"\nGraisse:\t"        + c.getString(5)
                    +"\n";

                    */

            theArray.add(result);

        }
        return theArray;
    }
    protected ArrayList<Double> getTotal(String date){
        Cursor c = database.rawQuery("Select calorie_count,protein_count,carb_count,fat_count from foodDiary where meal_date = '" + date + "' ;", null);
        ArrayList<Double>theArray = new ArrayList<>();
        double calorieTotal=0
                ,proteinTotal=0
                ,carbTotal=0
                ,fatTotal=0;
        if (c.getCount() == 0)
        {
            Toast t = Toast.makeText(context, R.string.food_no_input,Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER_VERTICAL, 0,0);
            t.show();

        }
        while(c.moveToNext()){
            calorieTotal += c.getDouble(0);
            proteinTotal += c.getDouble(1);
            carbTotal += c.getDouble(2);
            fatTotal += c.getDouble(3);

        }
        theArray.add(calorieTotal);
        theArray.add(proteinTotal);
        theArray.add(carbTotal);
        theArray.add(fatTotal);
        return theArray;
        //collectTotal(theArray);
    }

    protected ArrayList<Double> getNutritionGraph(CalendarDay today, String selection){
        int day = today.getDay();
        int month = today.getMonth();
        int year = today.getYear();
        String date1 = today.getDay()+"/"+today.getMonth()+"/"+today.getYear();
        String date2 = today.getDay()-1+"/"+today.getMonth()+"/"+today.getYear();
        String date3 = today.getDay()-2+"/"+today.getMonth()+"/"+today.getYear();
        String date4 = today.getDay()-3+"/"+today.getMonth()+"/"+today.getYear();
        String date5 = today.getDay()-4+"/"+today.getMonth()+"/"+today.getYear();
        String date6 = today.getDay()-5+"/"+today.getMonth()+"/"+today.getYear();
        String date7 = today.getDay()-6+"/"+today.getMonth()+"/"+today.getYear();

        Cursor newDay1 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date1 + "'; ",null);
        Cursor newDay2 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date2 + "'; ",null);
        Cursor newDay3 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date3 + "'; ",null);
        Cursor newDay4 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date4 + "'; ",null);
        Cursor newDay5 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date4 + "'; ",null);
        Cursor newDay6 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date4 + "'; ",null);
        Cursor newDay7 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date4 + "'; ",null);
        ArrayList<Double> theArray = new ArrayList<>();
        double getTotalDay1 =0;
        double getTotalDay2 =0;
        double getTotalDay3 =0;
        double getTotalDay4 =0;
        double getTotalDay5 =0;
        double getTotalDay6 =0;
        double getTotalDay7 =0;
        while ((newDay1.moveToNext())){
            getTotalDay1 += newDay1.getDouble(0);
        }
        while (newDay2.moveToNext()){
            getTotalDay2 += newDay2.getDouble(0);
        }
        while (newDay3.moveToNext()){
            getTotalDay3 += newDay3.getDouble(0);
        }
        while (newDay4.moveToNext()){
            getTotalDay4 += newDay4.getDouble(0);
        }
        while (newDay5.moveToNext()){
            getTotalDay5 += newDay5.getDouble(0);
        }
        while (newDay6.moveToNext()){
            getTotalDay6 += newDay6.getDouble(0);
        }
        while (newDay7.moveToNext()){
            getTotalDay7 += newDay7.getDouble(0);
        }


        theArray.add(getTotalDay7);
        theArray.add(getTotalDay6);
        theArray.add(getTotalDay5);
        theArray.add(getTotalDay4);
        theArray.add(getTotalDay3);
        theArray.add(getTotalDay2);
        theArray.add(getTotalDay1);
        return theArray;
    }


    protected ArrayList<Double> dateValidation(CalendarDay today,String selection) {
        //0-jan, 1-feb 2-mar 3-apr 4-may 5-jun 6- jul 7-aug 8-sep 9-oct 10-nov 11-dec
        String date1="", date2="", date3="", date4="", date5="", date6="", date7="";
        ArrayList<Double> theArray = new ArrayList<>();
        if (today.getDay() == 1) {
            date1 = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
            if (today.getDay() - 1 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);
                date2 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date3 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date4 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 4 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 5 + "/" + Newday.getMonth() + "/" + Newday.getYear();

            } else if (today.getDay() - 1 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);
                date2 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date3 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date4 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 4 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 5 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            } else if (today.getDay() - 1 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);
                date2 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date3 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date4 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 4 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 5 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            }

            theArray= graphPoints(selection,date1,date2,date3,date4,date5,date6,date7);
        }
        else if (today.getDay() == 2) {
            date1 = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
            date2 = today.getDay() - 1 + "/" + today.getMonth() + "/" + today.getYear();
            if (today.getDay() - 2 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);

                date3 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date4 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 4 + "/" + Newday.getMonth() + "/" + Newday.getYear();

            } else if (today.getDay() - 2 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);

                date3 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date4 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 4 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            } else if (today.getDay() - 2 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);

                date3 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date4 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 4 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            }

            theArray= graphPoints(selection,date1,date2,date3,date4,date5,date6,date7);
        }
        else if (today.getDay() == 3) {
            date1 = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
            date2 = today.getDay() - 1 + "/" + today.getMonth() + "/" + today.getYear();
            date3 = today.getDay() - 2 + "/" + today.getMonth() + "/" + today.getYear();
            if (today.getDay() - 3 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);


                date4 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();

            } else if (today.getDay() - 3 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);


                date4 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            } else if (today.getDay() - 3 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);


                date4 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date5 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 3 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            }
            theArray= graphPoints(selection,date1,date2,date3,date4,date5,date6,date7);
        }
        else if (today.getDay() == 4) {
            date1 = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
            date2 = today.getDay() - 1 + "/" + today.getMonth() + "/" + today.getYear();
            date3 = today.getDay() - 2 + "/" + today.getMonth() + "/" + today.getYear();
            date4 = today.getDay() - 3 + "/" + today.getMonth() + "/" + today.getYear();
            if (today.getDay() - 4 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);


                date5 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();

            } else if (today.getDay() - 4 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1,30);


                date5 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            } else if (today.getDay() - 4 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);


                date5 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date6 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 2 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            }
            theArray= graphPoints(selection,date1,date2,date3,date4,date5,date6,date7);
        }
        else if (today.getDay() == 5) {
            date1 = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
            date2 = today.getDay() - 1 + "/" + today.getMonth() + "/" + today.getYear();
            date3 = today.getDay() - 2 + "/" + today.getMonth() + "/" + today.getYear();
            date4 = today.getDay() - 3 + "/" + today.getMonth() + "/" + today.getYear();
            date5 = today.getDay() - 4 + "/" + today.getMonth() + "/" + today.getYear();
            if (today.getDay() - 5 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);


                date6 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();

            } else if (today.getDay() - 5 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1,30);


                date6 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            } else if (today.getDay() - 5 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1,28);


                date6 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
                date7 = Newday.getDay() - 1 + "/" + Newday.getMonth() + "/" + Newday.getYear();
            }
            theArray= graphPoints(selection,date1,date2,date3,date4,date5,date6,date7);
        }
        else if (today.getDay() == 6) {
            date1 = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
            date2 = today.getDay() - 1 + "/" + today.getMonth() + "/" + today.getYear();
            date3 = today.getDay() - 2 + "/" + today.getMonth() + "/" + today.getYear();
            date4 = today.getDay() - 3 + "/" + today.getMonth() + "/" + today.getYear();
            date5 = today.getDay() - 4 + "/" + today.getMonth() + "/" + today.getYear();
            date6 = today.getDay() - 5 + "/" + today.getMonth() + "/" + today.getYear();
            if (today.getDay() - 6 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1,31);
                date7 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();

            } else if (today.getDay() - 6 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1,30);
                date7 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
            } else if (today.getDay() - 6 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1,28);
                date7 = Newday.getDay() + "/" + Newday.getMonth() + "/" + Newday.getYear();
            } else if (today.getDay() == 6) {
                date1 = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
                date2 = today.getDay() - 1 + "/" + today.getMonth() + "/" + today.getYear();
                date3 = today.getDay() - 2 + "/" + today.getMonth() + "/" + today.getYear();
                date4 = today.getDay() - 3 + "/" + today.getMonth() + "/" + today.getYear();
                date5 = today.getDay() - 4 + "/" + today.getMonth() + "/" + today.getYear();
                date6 = today.getDay() - 5 + "/" + today.getMonth() + "/" + today.getYear();
                date7 = today.getDay() - 6 + "/" + today.getMonth() + "/" + today.getYear();
            }
            theArray= graphPoints(selection,date1,date2,date3,date4,date5,date6,date7);

        }
        return theArray;
    }



    private ArrayList<Double> graphPoints(String selection,String date1,String date2,String date3,String date4,String date5,String date6,String date7)
    {
        Cursor newDay1 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date1 + "'; ",null);
        Cursor newDay2 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date2 + "'; ",null);
        Cursor newDay3 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date3 + "'; ",null);
        Cursor newDay4 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date4 + "'; ",null);
        Cursor newDay5 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date5 + "'; ",null);
        Cursor newDay6 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date6 + "'; ",null);
        Cursor newDay7 = database.rawQuery("Select "+ selection + " from foodDiary where meal_date = '" + date7 + "'; ",null);


        ArrayList<Double> theArray = new ArrayList<>();
        double getTotalDay1 =0;
        double getTotalDay2 =0;
        double getTotalDay3 =0;
        double getTotalDay4 =0;
        double getTotalDay5 =0;
        double getTotalDay6 =0;
        double getTotalDay7 =0;
        while ((newDay1.moveToNext())){
            getTotalDay1 += newDay1.getDouble(0);
        }
        while (newDay2.moveToNext()){
            getTotalDay2 += newDay2.getDouble(0);
        }
        while (newDay3.moveToNext()){
            getTotalDay3 += newDay3.getDouble(0);
        }
        while (newDay4.moveToNext()){
            getTotalDay4 += newDay4.getDouble(0);
        }
        while (newDay5.moveToNext()){
            getTotalDay5 += newDay5.getDouble(0);
        }
        while (newDay6.moveToNext()){
            getTotalDay6 += newDay6.getDouble(0);
        }
        while (newDay7.moveToNext()){
            getTotalDay7 += newDay7.getDouble(0);
        }


        theArray.add(getTotalDay7);
        theArray.add(getTotalDay6);
        theArray.add(getTotalDay5);
        theArray.add(getTotalDay4);
        theArray.add(getTotalDay3);
        theArray.add(getTotalDay2);
        theArray.add(getTotalDay1);
        return theArray;

    }

}
