package pursuit.fitness.fitnesspursuit;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class FoodActivity extends BaseActivity {

    Button submitButton;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        submitButton = (Button)findViewById(R.id.submit_Button);
        submitButton.setOnClickListener(insertFood);
        db =  new Database(this);

    }
    private View.OnClickListener insertFood = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText meal_Name = (EditText)findViewById(R.id.meal_Name);
            EditText Calorie_count = (EditText)findViewById(R.id.Calorie_Count);
            EditText Protein_Count = (EditText)findViewById(R.id.Protein_Count);
            EditText Carb_Count = (EditText)findViewById(R.id.Carb_Count);
            EditText Fat_count = (EditText)findViewById(R.id.Fat_Count);
            if(meal_Name.getText().toString().equals("") || Calorie_count.getText().toString().equals("") || Protein_Count.getText().toString().equals("")|| Carb_Count.getText().toString().equals("")|| Fat_count.getText().toString().equals("")){
                Toast.makeText(FoodActivity.this, R.string.food_enter_information, Toast.LENGTH_SHORT).show();
            }
            else {

                String mealName = meal_Name.getText().toString();
                float calories = Float.parseFloat(Calorie_count.getText().toString());
                float protein = Float.parseFloat(Protein_Count.getText().toString());
                float carbs = Float.parseFloat(Carb_Count.getText().toString());
                float fats = Float.parseFloat(Fat_count.getText().toString());

                CalendarDay todayDate = new CalendarDay();
                String today = todayDate.getDay() + "/" + todayDate.getMonth() + "/" + todayDate.getYear();
                String query = "INSERT into foodDiary(`meal_name`,`calorie_count`,`protein_count`,`carb_count`,`fat_count`,`meal_date`)" +
                        " VALUES ('" + mealName + "','" + String.valueOf(calories) + "','" + String.valueOf(protein) + "','" + String.valueOf(carbs) + "','"
                        + String.valueOf(fats) + "','" + today + "');";
                // String query = "INSERT INTO food (`meal_name`) VALUES('test');";
                db.execSQL(query);
                setResult(RESULT_OK);
                finish();
            }
        }
    };
}
