package infocancer.nyesteventure.com.infocancer;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.Fragment.Prevension.About;
import info.Fragment.UserProfile;
import info.Holder.DataUserAge;
import info.Holder.DataUserEmail;
import info.Holder.DataUserName;
import info.Holder.DataUserPPP;
import info.Holder.DataUserPhone;
import info.Holder.DataUserUserId;
import info.NavItem.AlertBox_Inner;
import info.NavItem.AlertBox_outer;

import static info.NetWorkCheck.NetworkChecker.isConnected;
import static info.NetWorkCheck.NetworkChecker.isConnectedMobile;
import static info.NetWorkCheck.NetworkChecker.isConnectedWifi;


public class UserChoicePage extends AppCompatActivity implements View.OnClickListener
{
    private static final int USERPROFILE =11 ;
    private static final int NEXTCLASS =10 ;
    private static final String TAG ="error_picassa" ;
    private Menu menus;
    private Button cancertext,imagetext,questiontext;
    private LinearLayout view1,view2,view3,view4;
    TextView view_t1,view_t2,view_t3,view_t4;
    TextView Title;
    ImageView user_image;
    private String PPop,PPP;
    private String user_name,user_pic;

    private static final String DATABASETABLE4 ="userprofile" ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);

        view1=(LinearLayout)findViewById(R.id.linear_view1);
        view2=(LinearLayout)findViewById(R.id.linear_view2);
        view3=(LinearLayout)findViewById(R.id.linear_view3);
        view4=(LinearLayout)findViewById(R.id.linear_view4);
     Toolbar toolbar=(Toolbar)findViewById(R.id.choice_toolbar);
        setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setDisplayShowHomeEnabled(true);



      //  username = (TextView) findViewById(R.id.username_id_nxc);

       view_t1 = (TextView) findViewById(R.id.view_text1);
        view_t2 = (TextView) findViewById(R.id.view_text2);
        view_t3 = (TextView) findViewById(R.id.view_textq);
        view_t4 = (TextView) findViewById(R.id.view_text4);



        user_image = (ImageView) findViewById(R.id.user_profile_pic_idxc);

        Title = (TextView) findViewById(R.id.id_title);



        view1.setOnClickListener(this);
       view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);


        user_image.setOnClickListener(this);
     /*List<String> d=DataHolderClass.getInstance().getDistributor_id();
        if (d.size()==0)
        {
         List<String> arrayli= doitPost();
            DataHolderClass.getInstance().setDistributor_id(arrayli);
        }
*/
        Intent page=getIntent();

        String page1=page.getStringExtra("whose");
       // Toast.makeText(this,page1,Toast.LENGTH_SHORT).show();


        MainDataBAse data=new MainDataBAse(UserChoicePage.this);
        data.open();
        PPP=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_PPP,1);
        String userid=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_userid,1);
        String phone=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_phone,1);
        String age=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_age,1);
        String name=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_name,1);
        String url=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_url,1);
        String email=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_email,1);
        data.close();

        DataUserUserId.getInstance().setDistributor_id(userid);
        DataUserEmail.getInstance().setDistributor_id(email);
        DataUserPPP.getInstance().setDistributor_id(PPP);

        DataUserAge.getInstance().setDistributor_id(age);
        DataUserPhone.getInstance().setDistributor_id(phone);
        DataUserName.getInstance().setDistributor_id(name);


        if(PPP.contains("userlogin"))
        {
          //  this.user_name=name;
            this.user_pic=url;
            //this.PPop=PPP;
           // username.setText(name);
           if(user_pic.trim().contains("nourl")) {
               user_image.setImageResource(R.drawable.save_user1);
            }else{
                Post_Picassa(user_pic, PPP);
        }
        }
        else
        if(PPP.contains("doctorlogin"))
        {

           // username.setText(name);
            this.user_pic=url;

            if(user_pic.trim().contains("nourl")) {
                user_image.setImageResource(R.drawable.save_doctor1);
            }else{
                Post_Picassa(user_pic, PPP);
            }
            this.user_name=name;

        }
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf");
        Title.setTypeface(face);
        view_t1.setTypeface(face);
        view_t2.setTypeface(face);
        view_t3.setTypeface(face);
        view_t4.setTypeface(face);




        //String title = "infoCancer";


      /*  final SpannableString spannableString = new SpannableString(title);
        int position = 1;
        for (int i = 2, ei = title.length(); i < ei; i++) {
            char c = title.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                position = i;
                break;
            }
        }
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 4, 0);
        Title.setText(spannableString);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.about, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int k=item.getItemId();
        if(k==R.id.menuabout)
        {
            Intent about=new Intent(UserChoicePage.this,About.class);
            startActivity(about);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Post_Picassa(String url, String ppp) {

        try {

            Picasso picasso=new Picasso.Builder(UserChoicePage.this).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                }
            }).build();



if(ppp.contains("userlogin")) {
    picasso.load(url)
            .placeholder(R.drawable.save_user1)
            .into(user_image, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "sucseess");
                }

                @Override
                public void onError() {
                    Log.d(TAG, "error");
                }
            });
}else
    if(ppp.contains("doctorlogin")){

        picasso.load(url)
                .placeholder(R.drawable.save_doctor1)
                .into(user_image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "sucseess");
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "error");
                    }
                });
    }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_view1:

                if(isConnectedWifi(UserChoicePage.this)||isConnectedMobile(UserChoicePage.this))
                {
                    if(isConnected(UserChoicePage.this)) {

                    Intent i=new Intent(UserChoicePage.this,MainSlideMenu.class);

                    startActivity(i);
                    finish();

                    }
                    else
                    {
                        new AlertBox_Inner(UserChoicePage.this);

                    }
                }else
                {
                    new AlertBox_outer(UserChoicePage.this);

                    // new AlertBox("error",getActivity());
                }



                break;
            case R.id.linear_view2:
                if(isConnectedWifi(UserChoicePage.this)||isConnectedMobile(UserChoicePage.this))
                {
                    if(isConnected(UserChoicePage.this)) {

                        Intent it=new Intent(UserChoicePage.this,ImageViews_Class.class);
                        startActivity(it);
                        finish();
                        break;

                    }
                    else
                    {
                        new AlertBox_Inner(UserChoicePage.this);

                    }
                }else
                {
                    new AlertBox_outer(UserChoicePage.this);

                    // new AlertBox("error",getActivity());
                }





           break;

            case R.id.linear_view3:


                //Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
             if(PPP.contains("userlogin"))
                {
                    if(isConnectedWifi(UserChoicePage.this)||isConnectedMobile(UserChoicePage.this))
                    {
                        if(isConnected(UserChoicePage.this)) {

                       new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(UserChoicePage.this,Questions.class);
                                    startActivity(intent);
                                    finish();
                              }
                           });






                        }
                        else
                        {
                            new AlertBox_Inner(UserChoicePage.this);

                        }
                    }else
                    {
                        new AlertBox_outer(UserChoicePage.this);

                        // new AlertBox("error",getActivity());
                    }
                }
                else
                if(PPP.contains("doctorlogin"))
                {
                    if(isConnectedWifi(UserChoicePage.this)||isConnectedMobile(UserChoicePage.this))
                    {
                        if(isConnected(UserChoicePage.this)) {

                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {

                                    Intent intent=new Intent(UserChoicePage.this,Question_Doctor.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });





                        }
                        else
                        {
                            new AlertBox_Inner(UserChoicePage.this);

                        }
                    }else
                    {
                        new AlertBox_outer(UserChoicePage.this);

                        // new AlertBox("error",getActivity());
                    }






                }




                break;
            case R.id.user_profile_pic_idxc:

              Intent nezs=new Intent(UserChoicePage.this, UserProfile.class);

                startActivity(nezs);
                this.finish();


                break;
            case R.id.linear_view4:

                Toast.makeText(this, "under construction", Toast.LENGTH_SHORT).show();
                break;



        }
    }






    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if(requestCode==USERPROFILE)
        {
            finish();
        }

    }
}
