package pursuit.fitness.fitnesspursuit;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

public class ProgressActivity extends BaseActivity {
    public static final int IMAGE_GALLERY_REQUEST =  20;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
/*

        ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.list_view_row_item, ObjectItemData);
        ListView listViewItems = new ListView(this);
        listViewItems.setAdapter(adapter);
        listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());
        alertDialogStores = new AlertDialog.Builder(ProgressActivity.this)
                .setView(listViewItems)
                .show();

*/


    }
}
