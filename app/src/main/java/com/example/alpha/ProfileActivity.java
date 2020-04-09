package com.example.alpha;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuItem;
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
//implements OnMapReadyCallback
public class ProfileActivity extends AppCompatActivity  implements OnMapReadyCallback  {
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
    Button btnch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView=findViewById(R.id.profil_photo);
        mydb = new DatabaseHelper(this);
        etnomutl = findViewById(R.id.nom_modf);
        etemail =  findViewById(R.id.email_modf);
        etphone =  findViewById(R.id.tlph_modf);
        etdesc =  findViewById(R.id.descr_modf);
        photobtn=findViewById(R.id.photo_modf);
   mapview=findViewById(R.id.MAPview);
initMap();
btnch=findViewById(R.id.changermap);
 btnch.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         getLocationPermission();
     }
 });
 //mapview.setOnClickListener(new View.OnClickListener() {
  //   @Override
  //  public void onClick(View v) {
       // getLocationPermission();
        // initMap();
       //  getDeviceLocation();
   //  }
 // });

     photobtn.setOnClickListener(new View.OnClickListener() {
         @Override
           public void onClick(View v) {
          // captureFromCameraandgallery();
             selectImage(ProfileActivity.this);
          }
     });

     //  retoure.findViewById(R.id.backbtn3);
     /*  retoure.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View v) {
          //  modfIndividue();
             Intent intent = new Intent(ProfileActivity.this, PrincipaleActivity.class);
            startActivity(intent);     finish();
         }
     });*/




   }
   // private String cameraFilePath;
   /* private File createImageFile() throws IOException {
        // Create an image file name
       @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
       String imageFileName = "JPEG_" + timeStamp + "_";
      //This is the directory in which the file will be created. This is the default location of Camera photos
      File storageDir = new File(Environment.getExternalStoragePublicDirectory(
               Environment.DIRECTORY_DCIM), "Camera");
       File image = File.createTempFile(
               imageFileName,  //* prefix *//*
               ".jpg",         //* suffix *//*
               storageDir      //* directory *//*
      );

       // Save a file: path for using again
       cameraFilePath = "file://" + image.getAbsolutePath();
      return image;
  }*/
/*
 @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
      if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI for the selected Image
                   Uri selectedImage = data.getData();
                  // InputStream imageStream = null;
                  //// try {
                 ///     imageStream = getContentResolver().openInputStream(selectedImage);
             //   } catch (FileNotFoundException e) {
               ////     e.printStackTrace();
            //     }
            //  yourSelectedImage  = BitmapFactory.decodeStream(imageStream);
                                     //  modfimg();
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
                  ///case CAMERA_REQUEST_CODE:

                   //imageView.setImageURI(Uri.parse(cameraFilePath));

                   //// break;

          }
          }
   }*/

//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//    }
//
   /* private void captureFromCameraandgallery() {
      //  try {
         //   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Create an Intent with action as ACTION_PICK
           Intent intent2 = new Intent(Intent.ACTION_PICK);
           // Sets the type as image/*. This ensures only components of type image are selected
           intent2.setType("image/*");
           //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
          intent2.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);


         //  intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()));
//
//// Launching the Intent
//startActivityForResult(intent, CAMERA_REQUEST_CODE);
startActivityForResult(intent2, GALLERY_REQUEST_CODE);
     //   } catch (IOException ex) {
     //      ex.printStackTrace();
   //   }
       }*/
     /*  private void pickFromGallery () {
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
    /* public void modfmap () {
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
            String id = tring.valueOf(getid);

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

*/
        private void initMap () {
            Log.d(TAG, "initMap: initialisation du map");
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
                                //   modfmap();
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
                map.getUiSettings().setMyLocationButtonEnabled(false);

           }
        }
    private void selectImage(Context context) {
        final CharSequence[] options = {"Prendre Une Photo", "Choisir Une Photo", "Annuler"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choisissez votre Photo de Profil");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Prendre Une Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choisir Une Photo")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Annuler")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + requestCode);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_capture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }