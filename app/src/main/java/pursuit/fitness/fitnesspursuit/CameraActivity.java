package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 10;

    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        setTitle(getString(R.string.progress_menubar));
        Button takepic = findViewById(R.id.camera_button);
        takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoClick();
            }

        });
    }

    public void photoClick(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String picname = getPictureName();
        File image = new File(directory, picname);
        Uri pictureUri = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(intent, CAMERA_REQUEST);

    }

    public String getPictureName(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD_HHmmss");
        String timestamp = sdf.format(new Date());
        return "FitnessPursuit_" + timestamp;
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date day = new Date();
            String date = dateFormat.format(day);

            //add both the date and the image to the listview

        }
    }

}

