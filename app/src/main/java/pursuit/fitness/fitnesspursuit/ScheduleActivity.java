    package pursuit.fitness.fitnesspursuit;

    import android.os.Bundle;
    import android.support.design.widget.FloatingActionButton;
    import android.support.design.widget.Snackbar;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;

    public class ScheduleActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        AdapterView.OnItemClickListener OnItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                // handle click

                    switch (position){
                        case 0: goToActivity(MondayListActivity.class);
                        break;
                        case 1: goToActivity(TuesdayListActivity.class);
                            break;
                        case 2: goToActivity(WednesdayListActivity.class);
                            break;
                        case 3: goToActivity(ThursdayListActivity.class);
                            break;
                        case 4: goToActivity(FridayListActivity.class);
                            break;
                        case 5: goToActivity(SaturdayListActivity.class);
                            break;
                        case 6: goToActivity(SundayListActivity.class);
                            break;
                    }

            }
         };

        ListView listView = findViewById(R.id.day);
        listView.setOnItemClickListener(onItemClickListener);

        private void goToActivity(Class clazz){
            Intent intent = new Intent(ScheduleActivity.this, clazz);
            startActivity(intent);
        }

    }
