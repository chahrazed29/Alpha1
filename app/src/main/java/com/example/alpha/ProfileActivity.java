package com.example.alpha;

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity  implements OnMapReadyCallback {
    private static final String TAG = "ProfileActivity";
    View view;
    Bitmap yourSelectedImage;
    GoogleMap map;
    MapView mapview;
    ImageView imageView;
    ImageView photobtn;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    public static final int GALLERY_REQUEST_CODE = 1;
    public static int getid;
  public static final int   CAMERA_REQUEST_CODE=0;
    //vars
    private Boolean mLocationPermissionsGranted = false;
    ImageView retoure;
    EditText etnomutl, etemail, etphone, etdesc;
    DatabaseHelper mydb;
    SQLiteDatabase db;
    double  lg;
    double  lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLocationPermission();
        mydb = new DatabaseHelper(this);
        etnomutl = (EditText) findViewById(R.id.nom_modf);
        etemail = (EditText) findViewById(R.id.email_modf);
        etphone = (EditText) findViewById(R.id.tlph_modf);
        etdesc = (EditText) findViewById(R.id.descr_modf);
        photobtn=findViewById(R.id.photo_modf);
        photobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureFromCameraandgallery();
            }
        });
        imageView=findViewById(R.id.profil_photo);
        retoure.findViewById(R.id.backbtn3);
        retoure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modfIndividue();
                finish();
            }
        });

        Intent intent = new Intent(ProfileActivity.this, PrincipaleActivity.class);
        startActivity(intent);


    }
    private String cameraFilePath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for using again
        cameraFilePath = "file://" + image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image

        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                 yourSelectedImage  = BitmapFactory.decodeStream(imageStream);
                                          modfimg();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    imageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;
                case CAMERA_REQUEST_CODE:

                    imageView.setImageURI(Uri.parse(cameraFilePath));

                    break;

            }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private void captureFromCameraandgallery() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Create an Intent with action as ACTION_PICK
            Intent intent2 = new Intent(Intent.ACTION_PICK);
            // Sets the type as image/*. This ensures only components of type image are selected
            intent2.setType("image/*");
            //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
            String[] mimeTypes = {"image/jpeg", "image/png"};
            intent2.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);


            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()));

// Launching the Intent
startActivityForResult(intent, CAMERA_REQUEST_CODE);
            startActivityForResult(intent2, GALLERY_REQUEST_CODE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     //   private void pickFromGallery () {
            //Create an Intent with action as ACTION_PICK
       //     Intent intent = new Intent(Intent.ACTION_PICK);
            // Sets the type as image/*. This ensures only components of type image are selected
        //    intent.setType("image/*");
            //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
          //  String[] mimeTypes = {"image/jpeg", "image/png"};
         //   intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            // Launching the Intent
         //   startActivityForResult(intent, GALLERY_REQUEST_CODE);
       // }
     public void modfmap () {
         String latit =String.valueOf(lat);
         String longt =String.valueOf(lg);
         String id = String.valueOf(getid);
String z=String.valueOf(DEFAULT_ZOOM);
         mydb = new DatabaseHelper(this);
         db = mydb.getWritableDatabase();
         SQLiteDatabase db = mydb.getWritableDatabase();
         boolean isUpdate = mydb.insertloc2(id, longt, latit,z);
         if (isUpdate == true) {
             Toast.makeText(ProfileActivity.this, "Informations Modifiés", Toast.LENGTH_LONG).show();
         } else {
             Toast.makeText(ProfileActivity.this, "Informations non Modifiés", Toast.LENGTH_LONG).show();
         }
     }

        public void modfIndividue () {
            String n = etnomutl.getText().toString();
            String e = etemail.getText().toString();
            String t = etphone.getText().toString();
            String d = etdesc.getText().toString();
            String id = String.valueOf(getid);

            mydb = new DatabaseHelper(this);
            db = mydb.getWritableDatabase();
            SQLiteDatabase db = mydb.getWritableDatabase();
            boolean isUpdate = mydb.updateData(id, n, t, e, d);
            if (isUpdate == true) {
                Toast.makeText(ProfileActivity.this, "Informations Modifiés", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ProfileActivity.this, "Informations non Modifiés", Toast.LENGTH_LONG).show();
            }
        }
        public void modfimg (){
            String id = String.valueOf(getid);

            mydb = new DatabaseHelper(this);
            db = mydb.getWritableDatabase();
            byte[] B=getBitmapAsByteArray(yourSelectedImage);
         boolean isUpdate = mydb.addimage(id, B);
            if (isUpdate == true) {
                Toast.makeText(ProfileActivity.this, "Photo Modifiés", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ProfileActivity.this, "Photo non Modifiés", Toast.LENGTH_LONG).show();
            }
        }


        private void initMap () {
            Log.d(TAG, "initMap: initialisation du map");
            mapview = findViewById(R.id.mapview);
            if (mapview != null) {
                mapview.onCreate(null);
                mapview.onResume();
                mapview.getMapAsync(ProfileActivity.this);
            }
        }

        private void getDeviceLocation () {
            Log.d(TAG, "getDeviceLocation: getting the devices current location");


            FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            try {
                if (mLocationPermissionsGranted) {

                    final Task location = mFusedLocationProviderClient.getLastLocation();
                    location.addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: found location!");
                                Location currentLocation = (Location) task.getResult();
                                lg=currentLocation.getLongitude();
                                 lat=currentLocation.getLatitude();
                                moveCamera(new LatLng(lat, lg),DEFAULT_ZOOM);
                                   modfmap();
                            } else {
                                Log.d(TAG, "onComplete: current location is null");
                                Toast.makeText(ProfileActivity.this, "impossible d'avoir la localisation  ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            } catch (SecurityException e) {
                Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
            }
        }

        private void moveCamera (LatLng latLng,float zoom){
            Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        }

        private void getLocationPermission () {
            Log.d(TAG, "getLocationPermission: getting location permissions");
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionsGranted = true;
                    initMap();
                } else {
                    ActivityCompat.requestPermissions(this,
                            permissions,
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            Log.d(TAG, "onRequestPermissionsResult: called.");
            mLocationPermissionsGranted = false;

            switch (requestCode) {
                case LOCATION_PERMISSION_REQUEST_CODE: {
                    if (grantResults.length > 0) {
                        for (int i = 0; i < grantResults.length; i++) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                mLocationPermissionsGranted = false;
                                Log.d(TAG, "onRequestPermissionsResult: permission failed");
                                return;
                            }
                        }
                        Log.d(TAG, "onRequestPermissionsResult: permission granted");
                        mLocationPermissionsGranted = true;
                        //initialize our map
                        initMap();
                    }
                }
            }
        }

        @Override
        public void onMapReady (GoogleMap googleMap){
            MapsInitializer.initialize(getBaseContext());
            map = googleMap;
            if (mLocationPermissionsGranted) {
                getDeviceLocation();

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                map.setMyLocationEnabled(true);
                // map.getUiSettings().setMyLocationButtonEnabled(false);

            }
        }

    }