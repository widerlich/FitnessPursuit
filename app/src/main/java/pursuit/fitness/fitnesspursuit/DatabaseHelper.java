package pursuit.fitness.fitnesspursuit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "FitnessPursuit";

    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE_USERS = ("CREATE TABLE IF NOT EXISTS users(username VARCHAR, password VARCHAR);");
    private static final String DATABASE_CREATE_EXERCISES = ("CREATE TABLE IF NOT EXISTS exercises(ex_id INT, ex_name VARCHAR, area VARCHAR);");
    private static final String DATABASE_CREATE_FOODDIARY = ("CREATE TABLE IF NOT EXISTS foodDiary(meal_id INTEGER PRIMARY KEY AUTOINCREMENT,meal_name VARCHAR,calorie_count DOUBLE,protein_count DOUBLE,carb_count DOUBLE,fat_count DOUBLE,meal_date VARCHAR);");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_USERS);
        database.execSQL(DATABASE_CREATE_EXERCISES);
        database.execSQL(DATABASE_CREATE_FOODDIARY);
        database.execSQL("INSERT INTO exercises (ex_id, ex_name, area) VALUES(1, 'Chest Press', 'chest'), (2, 'Squat', 'legs'), (3, 'Deadlift', 'back'), (4, 'Dumbbell Curls', 'arms'), (5, 'Pull Ups', 'back'), (6, 'Dumbbell Shoulder Press', 'shoulders'), (7, 'Cable Cross', 'chest'), (8, 'Cable Rows', 'back'), (9, 'Dumbbell Lunges', 'legs'), (10, 'Russian Twists', 'core'), (11,'Tricep Pull-Downs', 'arms'), (12, 'Sit-Ups', 'core'), (13, 'Cable Curls', 'arms'), (14, 'Military Press', 'shoulders'), (15, 'Shoulder Shrugs', 'shoulders'), (16, 'Dumbbell Flys', 'chest'), (17, 'Leg Press', 'legs'), (18, 'Crunches', 'core')");
        database.execSQL("INSERT INTO users VALUES('admin','admin');");
        database.execSQL("CREATE TABLE IF NOT EXISTS foodDiary(meal_id INTEGER PRIMARY KEY AUTOINCREMENT,meal_name VARCHAR,calorie_count DOUBLE,protein_count DOUBLE,carb_count DOUBLE,fat_count DOUBLE,meal_date VARCHAR);");
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from " + oldVersion +" to "+newVersion);
        database.execSQL("DROP TABLE IF EXISTS users");
        onCreate(database);
    }

}
