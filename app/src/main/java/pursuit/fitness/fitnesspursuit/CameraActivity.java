package pursuit.fitness.fitnesspursuit;

import android.support.v7.app.AppCompatActivity;


public class CameraActivity extends AppCompatActivity {

    /*static final int REQUEST_IMAGE_CAPTURE = 1;
    private ObjectDBController dbc;
    private List<ProgPicture> list;
    private ArrayList<ProgPicture> aList;
    private ProgPicture pic;
    private ListView listView;
    private ProgPicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_camera);

        //gets all existing saved photos from database
        Thread thread = new Thread() {
            @Override
            public void run() {
                dbc = new ObjectDBController(Camera.this);
                list = dbc.getAllPictures();
            }
        };
        thread.start();
        try {
            thread.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }

        //custom adapter set to the list view to display the necessary info
        listView = getListView();
        adapter = new ProgPicAdapter(this,
                R.layout.photolayout, list);
        listView.setAdapter(adapter);

        Button camera = (Button)findViewById(R.id.camera_btn);
        camera.setOnClickListener(launch_Camera);
    }
    private View.OnClickListener launch_Camera = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dispatchTakePictureIntent();
        }
    };
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

    }

    //allows users to tp and remove photos
    public void onListClick(View v){
        ListView lv = getListView();
        final int position = lv.getPositionForView(v);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to remove this photo?");
        final ProgPicture pp = list.get(position);

// Set up the buttons
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new DeleteTask().execute(pp);
                adapter.notifyDataSetChanged();
                Toast.makeText(Camera.this, "Photo deleted.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    private class DeleteTask extends AsyncTask<ProgPicture, Void, Void> {

        @Override
        protected Void doInBackground(ProgPicture... params) {

            ProgPicture pp = params[0];
            dbc = new ObjectDBController(Camera.this);
            dbc.deletePhoto(pp);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Bitmap imageBit = getResizedBitmap(imageBitmap, 400);
            pic = new ProgPicture(imageBit);

            Thread thread = new Thread() {
                @Override
                public void run() {
                    dbc = new ObjectDBController(Camera.this);
                    dbc.storePicture(pic);
                }
            };
            thread.start();
            try {
                thread.join();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
            adapter.notifyDataSetChanged();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    public void onBackPressed(){
        dbc.close();
        super.onBackPressed();
    }*/

}

