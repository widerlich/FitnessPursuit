package pursuit.fitness.fitnesspursuit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ScheduleActivity extends AppCompatActivity {

    private ListView listView;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }

    private void setupUIViews() {//setup id for each components

        listView = (ListView) findViewById(R.id.lvSchedule);
    }

    private void setupListView() {

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title, String[] description) {//constructor

            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);//allows a different layout into a particular view

        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {//convertview get the right layout
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.activity_layout_single_item, null);
            }

            title = (TextView) convertView.findViewById(R.id.tvSchedule);
            description = (TextView) convertView.findViewById(R.id.tvDescription);
            imageView = (ImageView) convertView.findViewById(R.id.ivSchedule);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            if (titleArray[position].equalsIgnoreCase("Monday")) {
                imageView.setImageResource(R.drawable.book);
            }else if(titleArray[position].equalsIgnoreCase("Tuesday")){
                imageView.setImageResource(R.drawable.book);
            }else if(titleArray[position].equalsIgnoreCase("Wednesday")) {
                imageView.setImageResource(R.drawable.contact);
            }else if(titleArray[position].equalsIgnoreCase("Thursday")) {
                imageView.setImageResource(R.drawable.contact);
            }else if(titleArray[position].equalsIgnoreCase("Friday")) {
                imageView.setImageResource(R.drawable.contact);
            }else if(titleArray[position].equalsIgnoreCase("Saturday")) {
                imageView.setImageResource(R.drawable.contact);
            }else{
                imageView.setImageResource(R.drawable.settings);
            }

            return convertView;
        }
    }

}