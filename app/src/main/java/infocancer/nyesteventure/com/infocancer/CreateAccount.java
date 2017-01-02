package infocancer.nyesteventure.com.infocancer;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


import info.CameraClick.CameraPhoto;
import info.CameraClick.GalleryPhoto;
import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.Database.UserDbItem;
import info.Holder.DataCheckHolder;
import info.Holder.DataUserAge;
import info.Holder.DataUserEmail;
import info.Holder.DataUserName;
import info.Holder.DataUserPhone;
import info.Holder.DataUserUrl;
import info.Holder.DataUserUserId;
import info.NavItem.AlertBox_;
import info.NavItem.AlertBox_Inner;
import info.NavItem.AlertBox_outer;

import static info.NetWorkCheck.NetworkChecker.isConnected;
import static info.NetWorkCheck.NetworkChecker.isConnectedMobile;
import static info.NetWorkCheck.NetworkChecker.isConnectedWifi;


/**
 * Created by SLR on 10/5/2016.
 */
public class CreateAccount extends AppCompatActivity {
    private static final String TAG ="errorlogin" ;

    private static final String WEBURL ="http://infocancer.nyesteventuretech.com/Service/register.php";
    private static final String TAGURL ="tag" ;

    private static final String DOCTOR_NAME ="doctorname" ;
    private static final String DOCTOR_EMAIL ="doctoremail";
    private static final String DOCTOR_AGE ="doctorage" ;
    private static final String DOCTOR_MOBILE = "doctormobile";
    private static final String DOCTOR_PASS ="doctorpass" ;
    private static final String DOCTOR_PER ="doctoredu";
    private static final String DOCTOR_HOSP ="doctorhospital";

    private static final String DOCTOR_GENDER = "doctorgender";
    private static final String DOCTOR_IMAGE ="doctorimage" ;
    private static final String DOCTORREGISTER ="doctorregister" ;


    private static final String USER_NAME ="username" ;
    private static final String USER_EMAIL = "useremail";
    private static final String USER_AGE ="userage" ;
    private static final String USER_MOBILE = "usermobile";
    private static final String USER_PASS ="userpass" ;
    private static final String USER_GENDER = "usergender";

    private static final String USERREGISTER ="userregister" ;
    private static final int GALLERY_DATAD =11 ;
    private  Uri imageUri;

    private static final String USER_IMAGE ="userimage" ;
    private static final int CROP_PICe =158 ;
    private static final int CROPE = 712;


    ImageView camarapicp,_edit_pic;
    Button _save,_click;
    EditText _name,_email,_age,_mobile,_pass,_Hospital,_Education;
    Spinner _geneder;
    private String photostring;
    CameraPhoto cameraPhoto;
    private int CAMERACLICk=12;
    GalleryPhoto galleryPhoto;
    Context context;
  private String h_name,h_email,h_url,h_age,h_phone;
boolean boo=false;
    private int CROPEx=60;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String sg=intent.getStringExtra("messagexc");
        String wpage=intent.getStringExtra("wpage");
        if(wpage.trim().equals("splash")){

            boo=true;
        }
        else
        if(wpage.trim().equals("userprofile"))
        {
            boo=false;

        }


        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());
         h_name= DataUserName.getInstance().getDistributor_id();
        h_email= DataUserEmail.getInstance().getDistributor_id();
        h_age= DataUserAge.getInstance().getDistributor_id();
        h_phone= DataUserPhone.getInstance().getDistributor_id();
         h_url= DataUserUrl.getInstance().getDistributor_id();

        if(sg.trim().equals("userlogin"))
        {
            setContentView(R.layout.userprofile);

          usergetId();

            picassaurl(h_url);

        }else
        if(sg.trim().equals("doctorlogin"))
        {
            setContentView(R.layout.sign_up);
         doctorgetId();
            picassaurl(h_url);
        }



    }



    private void doctorgetId() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

      _save=(Button)findViewById(R.id.doctor_profile_save);
        _save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isConnectedWifi(CreateAccount.this) || isConnectedMobile(CreateAccount.this)) {
                    if (isConnected(CreateAccount.this)) {
                       createdoctor();
                    } else {
                        new AlertBox_Inner(CreateAccount.this);
                    }
                } else {
                    new AlertBox_outer(CreateAccount.this);
                }
            }
        });

        camarapicp=(ImageView)findViewById(R.id.doctor_profile_imageview);
        _edit_pic=(ImageView)findViewById(R.id.edit_doctor_pic);
        _edit_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeapic();
            }
        });

        _name=(EditText)findViewById(R.id.doctor_proid_name);
        _email=(EditText)findViewById(R.id.doctor_proid_email);
        _age=(EditText)findViewById(R.id.doctor_profile_age);
        _mobile=(EditText)findViewById(R.id.doctor_profile_mbno);
        _pass=(EditText)findViewById(R.id.doctor_profile_password);
        _geneder=(Spinner)findViewById(R.id.doctor_profile_gender);
        _Education=(EditText)findViewById(R.id.doctor_proid_education);
        _Hospital=(EditText)findViewById(R.id.doctor_proid_hospital);
        String gender[]={"male","female"};
    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,gender);



        _geneder.setAdapter(adapter);

        _name.setText(h_name);
        _email.setText(h_email);
        _age.setText(h_age);
        _mobile.setText(h_phone);

    }

    private void takeapic() {
        ActivityCompat.requestPermissions(CreateAccount.this,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE},
                1);


        String choice[]={"Take a Picture","Select From Gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateAccount.this,android.R.layout.select_dialog_item, choice);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0) {
//cameratestdemo();
             callcameraxc();
                }
                if (which == 1) {
                    try {
                        gallareydf();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        });
        final AlertDialog dialog = builder.create();
        dialog.show();


    }



    private void gallareydf()throws Exception {
            startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_DATAD);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(CreateAccount.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                if(boo==true){

                    Intent intent=new Intent(CreateAccount.this,LoginActivity.class);
                    startActivity(intent);
                    this.finish();
                }
                else if(boo==false)
                {
                    Intent intent=new Intent(CreateAccount.this,UserChoicePage.class);
                    startActivity(intent);
                    this.finish();
                }
                return true;

        }

        return super.onOptionsItemSelected(item);




    }

    private void callcameraxc() {
        try {
            startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERACLICk);
        }
        catch (Exception e)
        {e.printStackTrace();}
    }

    private void createdoctor() {
        Log.d(TAG, "Login");

        if (!doctorvalidate()) {
            useronLoginFailed();
            return;
        }

        final String name = _name.getText().toString();
        final String email = _email.getText().toString();
        final String age = _age.getText().toString();
        final String mobile = _mobile.getText().toString();
        final String education = _Education.getText().toString();
        final String hospital = _Hospital.getText().toString();
        final String pass = _pass.getText().toString();
        final String gender =_geneder.getSelectedItem().toString();
        String idf= DataUserUserId.getInstance().getDistributor_id();
        if (idf!=null) {

            onLoginSuccesDoctorUpdate(name, email, age, mobile, education, hospital, pass, gender,idf);
        }else{
            onLoginSuccesDoctor(name, email, age, mobile, education, hospital, pass, gender);
        }


    }

    private void onLoginSuccesDoctorUpdate(final String name, final String email, final String age, final String mobile, final String education, final String hospital, final String pass, final String gender, final String idf) {
        final MainDataBAse dataBAse=new MainDataBAse(getApplicationContext());
        dataBAse.open();

        String encodeImage="";

        Bitmap bitmap = null;

        if(photostring!=null){
            try {
                bitmap = ImageLoader.init().from(photostring).requestSize(200, 200).getBitmap();
                encodeImage = ImageBase64.encode( bitmap);
               // Log.e("respo",encodeImage.toString());

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put(TAGURL,"doctorupdate");
        hashmap.put(DOCTOR_NAME,name);
        hashmap.put(DOCTOR_EMAIL,email);
        hashmap.put(DOCTOR_AGE,age);
        hashmap.put("doctor_id",idf);
        hashmap.put(DOCTOR_MOBILE,mobile);
        hashmap.put(DOCTOR_PASS,pass);
        hashmap.put(DOCTOR_PER,education);
        hashmap.put(DOCTOR_HOSP,hospital);
        hashmap.put(DOCTOR_GENDER,gender);
        hashmap.put(DOCTOR_IMAGE,encodeImage);
        Log.e("resps",encodeImage);

        PostResponseAsyncTask task=new PostResponseAsyncTask(CreateAccount.this, hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
              Log.e("response",""+s);


                try {
                   String h = null;
                    JSONObject jsonObject=new JSONObject(s);
                    int ch =jsonObject.getInt("success");



                    if(ch==1)
                    {
                        String ulr =jsonObject.getString("url");

                        String doctorid =jsonObject.getString("doctorid");
                        if(ulr.contains(""))
                        {

                            ulr=dataBAse.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_url,1);

                        }

                        dataBAse.updateuserTable(DATABASE_C.TABLE_NAME.T_USER, new UserDbItem(name, email, pass,doctorid,mobile,age, ulr),1,"doctorlogin");


                        dataBAse.close();
                       // DataCheckHolder.getInstance().setDistributor_id(true);
                        Intent intent = new Intent(CreateAccount.this, UserChoicePage.class);
                        startActivity(intent);
                        finish();
                    }else
                    if(ch==303)
                    {
                        String errormsg= jsonObject.getString("errormsg");
                        new AlertBox_(errormsg,CreateAccount.this);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }





            }
        });

        task.execute(WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }
        });

    }


    private boolean doctorvalidate() {
        boolean valid = true;
     String name = _name.getText().toString();
        String email = _email.getText().toString();
         String age = _age.getText().toString();
         String mob = _mobile.getText().toString();
      String education = _Education.getText().toString();
       String hospital = _Hospital.getText().toString();
        String password = _pass.getText().toString();
         String gender =_geneder.getSelectedItem().toString();



        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _email.setError("enter a valid email address");
            valid = false;
        } else {
            _email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _pass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _pass.setError(null);
        }

        if (name.isEmpty() || name.length() < 3 || name.length() > 18) {
            _name.setError("between 3 and 18 alphanumeric characters");
            valid = false;
        } else {
            _name.setError(null);
        }
        if (age.isEmpty() || age.length() < 2 || age.length() >2) {
            _age.setError("between 10 and 99 ");
            valid = false;
        } else {
            _age.setError(null);
        }
        if (mob.isEmpty() || mob.length() < 10 || mob.length() > 14) {
            _mobile.setError("between 10 and 14");
            valid = false;
        } else {
            _mobile.setError(null);
        }

        if (hospital.isEmpty() ||hospital.length() < 5 ) {
            _Hospital.setError("check");
            valid = false;
        } else {
            _Hospital.setError(null);
        }
        if (education.isEmpty() || education.length() < 5 ) {
            _Education.setError("check");
            valid = false;
        } else {
            _Education.setError(null);
        }
        if (gender.isEmpty() ) {
            Toast.makeText(CreateAccount.this,"genderempty", Toast.LENGTH_SHORT).show();
            valid = false;
        }


        return valid;




    }

    private void onLoginSuccesDoctor(final String name, final String email, final String age, final String mobile, String education, String hospital, final String pass, String gender) {
        final MainDataBAse dataBAse=new MainDataBAse(getApplicationContext());
        dataBAse.open();


        String encodeImage="";

        Bitmap bitmap = null;
        if(photostring!=null){
            try {
                bitmap = ImageLoader.init().from(photostring).requestSize(200, 200).getBitmap();
                encodeImage = ImageBase64.encode( bitmap);
                Log.e("respo",encodeImage.toString());

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }

        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put(TAGURL,DOCTORREGISTER);
        hashmap.put(DOCTOR_NAME,name);
        hashmap.put(DOCTOR_EMAIL,email);
        hashmap.put(DOCTOR_AGE,age);
        hashmap.put(DOCTOR_MOBILE,mobile);
        hashmap.put(DOCTOR_PASS,pass);
        hashmap.put(DOCTOR_PER,education);
        hashmap.put(DOCTOR_HOSP,hospital);
        hashmap.put(DOCTOR_GENDER,gender);
        hashmap.put(DOCTOR_IMAGE,encodeImage);

        PostResponseAsyncTask task=new PostResponseAsyncTask(CreateAccount.this, hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
     Log.e("respo",s.toString());

                try {
                    JSONObject jsonObject=new JSONObject(s);
                  int ch =jsonObject.getInt("success");
                    if(ch==1)
                    {
                        String ulr =jsonObject.getString("url");
                        String doctorid =jsonObject.getString("doctorid");

                        dataBAse.createuserTable(DATABASE_C.TABLE_NAME.T_USER, new UserDbItem(name, email, pass,doctorid,mobile,age, ulr), "doctorlogin");
                        dataBAse.close();
                        DataCheckHolder.getInstance().setDistributor_id(true);
                        Intent intent = new Intent(CreateAccount.this, UserChoicePage.class);
                        startActivity(intent);
                        finish();

                    }else
                        if(ch==303)
                        {
                            String errormsg= jsonObject.getString("errormsg");
                            new AlertBox_(errormsg,CreateAccount.this);

                        }




                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }





                 }
        });

        task.execute(WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }
        });


    }




    private void usergetId() {

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    _save=(Button)findViewById(R.id.user_profile_save);
      //  _save.setVisibility(View.INVISIBLE);


        _save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isConnectedWifi(CreateAccount.this) || isConnectedMobile(CreateAccount.this)) {
                    if (isConnected(CreateAccount.this)) {

                    createuser();


                    } else {
                        new AlertBox_Inner(CreateAccount.this);
                    }
                } else {
                    new AlertBox_outer(CreateAccount.this);
                }

            }
        });

        camarapicp=(ImageView)findViewById(R.id.user_profile_imageview);
        _edit_pic=(ImageView)findViewById(R.id.userpr_floting);
        _edit_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeapic();
            }
        });


        _name=(EditText)findViewById(R.id.user_proid_name);
        _email=(EditText)findViewById(R.id.user_proid_email);
        _age=(EditText)findViewById(R.id.user_profile_age);
        _mobile=(EditText)findViewById(R.id.user_profile_mbno);
        _pass=(EditText)findViewById(R.id.user_profile_password);
        _geneder=(Spinner)findViewById(R.id.user_profile_gender);
        String gender[]={"male","female"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,gender);
        _geneder.setAdapter(adapter);
        _name.setText(h_name);
        _email.setText(h_email);
        _age.setText(h_age);
        _mobile.setText(h_phone);



    }



    private void picassaurl(String h) {
        try {
            Picasso picasso=new Picasso.Builder(CreateAccount.this).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                }
            }).build();




            picasso.load(h)

                    .into(camarapicp, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG,"sucseess");
                        }

                        @Override
                        public void onError() {
                            Log.d(TAG,"error");
                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    private void createuser() {

        Log.d(TAG, "Login");

        if (!useronValid()) {
            useronLoginFailed();
            return;
        }

        final String name = _name.getText().toString();
        final String email = _email.getText().toString();
        final String age = _age.getText().toString();
        final String mobile = _mobile.getText().toString();
        final String pass = _pass.getText().toString();
        final String gender =_geneder.getSelectedItem().toString();

        try {
            String idf = DataUserUserId.getInstance().getDistributor_id();

            if (idf != null) {

                onLoginSuccesUserUpdate(name, email, age, mobile, pass, gender, idf);
            } else {
                onLoginSuccesUser(name, email, age, mobile, pass, gender);
            }

        }catch (Exception e)
        {
            Log.e("error",""+e);
        }

    }

    private void onLoginSuccesUserUpdate(final String name, final String email, final String age, final String mobile, final String pass, String gender, final String idf) {

        final MainDataBAse dataBAse=new MainDataBAse(getApplicationContext());
        dataBAse.open();
        String encodeImage="";

        Bitmap bitmap = null;
        if(photostring!=null){
            try {
                bitmap = ImageLoader.init().from(photostring).requestSize(200, 200).getBitmap();
                encodeImage = ImageBase64.encode(bitmap);
                //Log.e("encodeimage",encodeImage);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put(TAGURL,"userupdate");
        hashmap.put(USER_NAME,name);
        hashmap.put(USER_EMAIL,email);
        hashmap.put(USER_AGE,age);
       hashmap.put("user_id",idf);
        hashmap.put(USER_MOBILE,mobile);
        hashmap.put(USER_PASS,pass);
        hashmap.put(USER_GENDER,gender);
        hashmap.put(USER_IMAGE,encodeImage);



        PostResponseAsyncTask task=new PostResponseAsyncTask(CreateAccount.this, hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.e("response_upsdate",""+s);
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(s);
                    int ch = jsonObject.getInt("success");


                    if (ch == 1) {
                        String url = jsonObject.getString("url");

                        String user_id = jsonObject.getString("userid");

                        if(url.contains(""))
                        {

                            url= dataBAse.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_url,1);

                        }
                        dataBAse.updateuserTable(DATABASE_C.TABLE_NAME.T_USER, new UserDbItem(name, email, pass,user_id,mobile,age, url), 1, "userlogin");
                        dataBAse.close();
                        DataCheckHolder.getInstance().setDistributor_id(true);
                        Intent intent = new Intent(CreateAccount.this, UserChoicePage.class);
                        startActivity(intent);
                        finish();

                    } else if (ch == 303) {
                        String errormsg= jsonObject.getString("errormsg");
                        new AlertBox_(errormsg, CreateAccount.this);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

                /*try {
                    JSONObject jsonObject = new JSONObject(s);
                   int ch = jsonObject.getInt("success");
                    String url = jsonObject.getString("url");
                    String user_id = jsonObject.getString("userid");


                    if (ch == 1) {
                        if(url.contains(""))
                        {
                            url=DataUserUrl.getInstance().getDistributor_id();
                        }
                            dataBAse.updateuserTable(DATABASE_C.TABLE_NAME.T_USER, new UserDbItem(name, email, pass,user_id, url), 1, "userlogin");
                            dataBAse.close();
                           DataCheckHolder.getInstance().setDistributor_id(true);
                            Intent intent = new Intent(CreateAccount.this, UserChoicePage.class);
                            startActivity(intent);
                            finish();

                    } else if (succ[0] == 303) {
                        new AlertBox_("error registration", CreateAccount.this);
                    }
                }catch (Exception e){e.printStackTrace();}
*/
                //Toast.makeText(getActivity(),s.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        task.execute(WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }
        });



    }

    private void useronLoginFailed() {
        Toast.makeText(CreateAccount.this, "Login failed", Toast.LENGTH_LONG).show();

        _save.setEnabled(true);

    }

    private boolean useronValid() {

        boolean valid = true;
        String name=_name.getText().toString();
        String email = _email.getText().toString();
        String password = _pass.getText().toString();
        String age = _age.getText().toString();
        String mob=_mobile.getText().toString();
         String gender =_geneder.getSelectedItem().toString();



        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _email.setError("enter a valid email address");
            valid = false;
        } else {
            _email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _pass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _pass.setError(null);
        }

        if (name.isEmpty() || name.length() < 3 || name.length() > 18) {
            _name.setError("between 3 and 18 alphanumeric characters");
            valid = false;
        } else {
            _name.setError(null);
        }
        if (age.isEmpty() || age.length() < 2 || age.length() >2) {
            _age.setError("between 10 and 99 ");
            valid = false;
        } else {
            _age.setError(null);
        }
        if (mob.isEmpty() || mob.length() < 10 || mob.length() > 14) {
            _mobile.setError("between 10 and 14");
            valid = false;
        } else {
            _mobile.setError(null);
        }
        if (gender.isEmpty() ) {
            Toast.makeText(CreateAccount.this,"genderempty", Toast.LENGTH_SHORT).show();
            valid = false;
        }


        return valid;


    }

    private void onLoginSuccesUser(final String name, final String email, final String age, final String mobile, final String pass, String gender) {
        final MainDataBAse dataBAse=new MainDataBAse(getApplicationContext());
        dataBAse.open();
        String encodeImage="";


        Bitmap bitmap = null;
        if(photostring!=null){
            try {
                bitmap = ImageLoader.init().from(photostring).requestSize(200, 200).getBitmap();
                encodeImage = ImageBase64.encode(bitmap);

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put(TAGURL,USERREGISTER);
        hashmap.put(USER_NAME,name);
        hashmap.put(USER_EMAIL,email);
        hashmap.put(USER_AGE,age);
        hashmap.put(USER_MOBILE,mobile);
        hashmap.put(USER_PASS,pass);
        hashmap.put(USER_GENDER,gender);
        hashmap.put(USER_IMAGE,encodeImage);



       PostResponseAsyncTask task=new PostResponseAsyncTask(CreateAccount.this, hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.e("responswe",s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                   int ch = jsonObject.getInt("success");




                    if (ch == 1) {




                        String url = jsonObject.getString("url");
                        String user_id = jsonObject.getString("userid");
                            dataBAse.createuserTable(DATABASE_C.TABLE_NAME.T_USER, new UserDbItem(name, email, pass, user_id,mobile,age, url), "userlogin");
                            dataBAse.close();

                            DataCheckHolder.getInstance().setDistributor_id(true);
                            Intent intent = new Intent(CreateAccount.this, UserChoicePage.class);
                            startActivity(intent);
                            finish();

                    } else if (ch == 303) {

                        String errormsg= jsonObject.getString("errormsg");
                        new AlertBox_(errormsg, CreateAccount.this);
                    }

                }catch (Exception e){e.printStackTrace();}
                //Toast.makeText(getActivity(),s.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        task.execute(WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                new AlertBox_("error connecting",CreateAccount.this);
            }
        });



    }

    private void performCrop(Uri picUri) {

        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 0);
            cropIntent.putExtra("aspectY", 0);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            File photoFile = cameraPhoto.createImageFile();
            cropIntent.putExtra("output",Uri.fromFile(photoFile));
            startActivityForResult(cropIntent,CROPE);
        }

        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void performCropnm(Uri picUri) {

        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 0);
            cropIntent.putExtra("aspectY", 0);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            File photoFile = cameraPhoto.createImageFile();
            cropIntent.putExtra("output",Uri.fromFile(photoFile));

            startActivityForResult(cropIntent,CROPEx);

        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==CreateAccount.RESULT_OK) {

            if (requestCode == CAMERACLICk)
            {
             String photoPaths = cameraPhoto.getPhotoPath();
                File op = new File(photoPaths);
                Uri uricv = Uri.fromFile(op);
                Log.e("crop",photoPaths);
            try {
                    performCrop(uricv);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (requestCode == GALLERY_DATAD)
            {
                try {
                    Uri uri = data.getData();
                    galleryPhoto.setPhotoUri(uri);
                    String   photoPaths = galleryPhoto.getPath();
                    File op = new File(photoPaths);
                    Uri uricv = Uri.fromFile(op);
                    Log.e("crop",photoPaths);
                    try {
                        performCropnm(uricv);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            else
            if (requestCode==CROPE)

            {
                String photoPaths = cameraPhoto.getPhotoPath();
                photostring=photoPaths;
                Log.e("path",photoPaths);
                try{
                File op = new File(photoPaths);

                if(op.exists()){

                    Picasso.with(CreateAccount.this).load(op).into(camarapicp);

                }}catch (Exception e){
                    e.printStackTrace();
                }
        }
            else
            if (requestCode==CROPEx)

            {
                String photoPaths = cameraPhoto.getPhotoPath();
                photostring=photoPaths;
                Log.e("path",photoPaths);
                try{
                    File op = new File(photoPaths);

                    if(op.exists()){

                        Picasso.with(CreateAccount.this).load(op).into(camarapicp);


                    }}catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }









    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(boo==true){

            Intent intent=new Intent(CreateAccount.this,LoginActivity.class);
            startActivity(intent);
            this.finish();
        }
        else if(boo==false)
        {
            Intent intent=new Intent(CreateAccount.this,UserChoicePage.class);
            startActivity(intent);
            this.finish();
        }
    }



}
