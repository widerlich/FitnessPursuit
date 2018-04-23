package pursuit.fitness.fitnesspursuit;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;



public class DayActivity extends ListActivity {

    public static final String EXTRA_DAY_ID = "fitnesspursuit.Drink";
    private String TAG = "DayActivity";


    FirebaseUser iduser = FirebaseAuth.getInstance().getCurrentUser();

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    //TODO: change populate ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        mListView = (ListView) findViewById(R.id.listExercices);

        mDatabaseHelper = new DatabaseHelper(this);

        int dayId = (Integer) getIntent().getExtras().get(EXTRA_DAY_ID);
        Log.d(TAG, "dayId=" + dayId);

        populateListView();

        setUpUI();


 /*       openAndQueryDatabase();
                                    DOESSNNNNNNNNNNNN'T WORK
        displayResultList();*/


    }

    private void populateListView() {


        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getItemExerciceList();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));

        }
    }

    private void setUpUI() {

        ListView lvExercices = findViewById(R.id.listExercices);

      /*  AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                // handle click

                Intent intent = new Intent(DayActivity.this, Exercices.class);
                startActivity(intent);

            }
        };
        lvExercices.setOnItemClickListener(onItemClickListener);
        */
    }















 /*   private void displayResultList() {

        TextView tView = new TextView(this);
        tView.setText("This data is retrieved from the database " +
                "of the results are displayed");
        getListView().addHeaderView(tView);

       // ListView list = (ListView)findViewById(android.R.id.list);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }
    private void openAndQueryDatabase() {
        try {
            DatabaseHelper dbHelper = new DatabaseHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();

            Cursor a = newDB.rawQuery("SELECT goal FROM " + user + "WHERE userid = " + iduser , null);

            Cursor c = newDB.rawQuery("SELECT ex_name, weight, time FROM " +
                    exercices +
                    " WHERE goal = " + a, null);

            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        String name = c.getString(c.getColumnIndex("name"));
                        int weight = c.getInt(c.getColumnIndex("weight"));
                        int time = c.getInt(c.getColumnIndex("time"));
                        results.add("Name of the exercice: " + name + ", use the following weight: " + weight + "kg, or do this exercice for :" + time + " minutes.");
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + exercices);
            newDB.close();
        }

    }
*/


}
