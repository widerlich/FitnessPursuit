package pursuit.fitness.fitnesspursuit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FitnessPursuit";

    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_EXERCICE = "exercice_table";
    private static final String TABLE_USER = "user_table";
    private static final String TABLE_FREQUENCY = "frequency_table";


    private static final String COL_USER_0 = "userid";
    private static final String COL_USER_1 = "age";
    private static final String COL_USER_2 = "goal";
    private static final String COL_USER_3 = "language";
    private static final String COL_USER_4 = "nameUser";
    private static final String COL_USER_5 = "frequency";

    private static final String COL_EXERICE_0 = "id";
    private static final String COL_EXERICE_1 = "name";
    private static final String COL_EXERICE_2 = "weight";
    private static final String COL_EXERICE_3 = "time";
    private static final String COL_EXERICE_4 = "goal";

    private static final String COL_FREQUENCY_0 = "frequency";
    private static final String COL_FREQUENCY_1 = "day";


    // Database creation sql statement
    private static final String DATABASE_CREATE_FOODDIARY = ("CREATE TABLE IF NOT EXISTS foodDiary(meal_id INTEGER PRIMARY KEY AUTOINCREMENT,meal_name VARCHAR,calorie_count DOUBLE,protein_count DOUBLE,carb_count DOUBLE,fat_count DOUBLE,meal_date VARCHAR);");
    // private static final String DATABASE_CREATE_FREQUENCY = ("CREATE TABLE IF NOT EXISTS frequency(frequency INT, day VARCHAR);");

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + COL_USER_0 + " TEXT PRIMARY KEY," + COL_USER_1
            + " INTEGER," + COL_USER_2 + " TEXT," + COL_USER_3 + " TEXT," + COL_USER_4
            + " TEXT," + COL_USER_5 + " INTEGER)";

    private static final String CREATE_TABLE_EXERCICE = "CREATE TABLE "
            + TABLE_EXERCICE + "(" + COL_EXERICE_0 + " INTEGER PRIMARY KEY," + COL_EXERICE_1
            + " TEXY," + COL_EXERICE_2 + " INTEGER," + COL_USER_3 + " INTEGER," + COL_EXERICE_3
            + " INTEGER," + COL_EXERICE_4 + " TEXT)";

    private static final String CREATE_TABLE_FREQUENCY = "CREATE TABLE "
            + TABLE_FREQUENCY + "(" + COL_FREQUENCY_0 + " INTEGER PRIMARY KEY," + COL_FREQUENCY_1 + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_USER);
        database.execSQL(CREATE_TABLE_EXERCICE);
        database.execSQL(DATABASE_CREATE_FOODDIARY);
        database.execSQL(CREATE_TABLE_FREQUENCY);

        database.execSQL("INSERT INTO exercice_table (ex_name, weight, time, goal) VALUES ('Full body strength workout', 10, NULL, 'Gain strength - Beginner'), ('Full body strength workout', 20, NULL, 'Gain strength - Advanced')," +
                " ('Full body strength workout', 30, NULL, 'Gain strength - pro'), (' Split workout chest and shoulders', 10, NULL, 'Gain strength - beginner')," +
                " (' Split workout chest and shoulders', 20, NULL, 'Gain strength - intermediate'), (' Split workout chest and shoulders', 30, NULL, 'Gain strength - pro'), ('Split workout back', 10, NULL, 'Gain strength - beginner')," +
                " ('Split workout back', 20, NULL, 'Gain strength - advanced'), ('Split workout back', 30, NULL, 'Gain strength - pro'), ('Split workout legs', 10, NULL, 'Gain strength - beginner')," +
                " ('Split workout legs', 20, NULL, 'Gain strength - advanced'), ('HIIT', NULL, 20, 'Gain strength - beginner'), ('Split workout legs', 30, NULL, 'Gain strength - pro'), ('HIIT', NULL, 25, 'Gain strength - advanced')," +
                " ('HIIT', NULL, 30, 'Gain strength - pro'), ('HIIT', NULL, 25, 'Lose weight'), ('Full body strength workout', 25, NULL, 'Lose weight'), ('Full body strength workout', 25, NULL, 'Increase health')," +
                " ('HIIT ', NULL, 25, 'Increase health'), ('High intensity cardio session', NULL, 40, 'Increase health');");

        database.execSQL("INSERT INTO frequency_table (frequency, day) VALUES (1, 'Monday'), (2, 'Monday'), (2, 'Thursday'), (3, 'Monday'), (3, 'Wednesday'), (3, 'Friday')," +
                " (4, 'Monday'), (4, 'Wednesday'), (4, 'Friday'), (4, 'Sunday'), (5, 'Monday'), (5, 'Wednesday'), (5, 'Thursday'), (5, 'Saturday'), (5, 'Sunday')," +
                " (6, 'Monday'), (6, 'Wednesday'), (6, 'Thursday'), (6, 'Friday'), (6, 'Saturday'), (6, 'Sunday');");

        database.execSQL("CREATE TABLE IF NOT EXISTS foodDiary(meal_id INTEGER PRIMARY KEY AUTOINCREMENT,meal_name VARCHAR,calorie_count DOUBLE,protein_count DOUBLE,carb_count DOUBLE,fat_count DOUBLE,meal_date VARCHAR);");

    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from " + oldVersion + " to " + newVersion);

        database.execSQL("DROP TABLE IF EXISTS users");

        onCreate(database);
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + CREATE_TABLE_EXERCICE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getName() {

        SQLiteDatabase db = this.getWritableDatabase();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        String userid = user.getUid();

        String query = "SELECT " + COL_USER_4 + " FROM " + CREATE_TABLE_USER + " WHERE " + COL_USER_0 + " =' " + userid + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getItemExerciceList() {

        SQLiteDatabase db = this.getWritableDatabase();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        String userid = user.getUid();

        String query0 = "SELECT" + COL_USER_2 + " FROM" + CREATE_TABLE_USER +
                " WHERE " + COL_USER_0 + " ='" + userid + "'";
        Cursor goal = db.rawQuery(query0, null);


        String query = "SELECT " + COL_EXERICE_1 + " FROM " + CREATE_TABLE_EXERCICE +
                " WHERE " + COL_EXERICE_4 + " = '" + goal + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getDay() {


        SQLiteDatabase db = this.getWritableDatabase();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        String userid = user.getUid();

        String query0 = "SELECT" + COL_USER_5 + " FROM" + CREATE_TABLE_USER +
                " WHERE " + COL_USER_0 + " ='" + userid + "'";
        Cursor frequency = db.rawQuery(query0, null);

        String query = "SELECT " + COL_FREQUENCY_1 + " FROM " + CREATE_TABLE_FREQUENCY +
                " WHERE " + COL_FREQUENCY_1 + " = '" + frequency + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }


    public void deleteCurrentUser () {

        SQLiteDatabase db = this.getWritableDatabase();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        String userid = user.getUid();

        db.execSQL("DELETE\n" + "FROM" + CREATE_TABLE_USER +" WHERE " + COL_USER_0 + " = '" + userid + "'");

        db.close();
    }

    public void addUser (FirebaseUser user_id, int age, String goal, String language, EditText nameUser, int frequency) {


        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + CREATE_TABLE_USER + "(" + COL_USER_0 + "," + COL_USER_1 + "," + COL_USER_2 + "," + COL_USER_3 + ","
                + COL_USER_4 + "," + COL_USER_5 + ")  VALUES(" + user_id +
                "," + age + "," + goal + "," + language + "," + nameUser + ","  + frequency + ")");

    }

}
