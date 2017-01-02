package info.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.Database.UserDbItem;
import info.Holder.DataCheckHolder;
import info.NavItem.AlertBox_;
import info.NavItem.AlertBox_Inner;
import info.NavItem.AlertBox_outer;
import infocancer.nyesteventure.com.infocancer.CreateAccount;
import infocancer.nyesteventure.com.infocancer.R;
import infocancer.nyesteventure.com.infocancer.ScrollTabHolderFragment;
import infocancer.nyesteventure.com.infocancer.UserChoicePage;
import infocancer.nyesteventure.com.infocancer.ViewpagerLogin.NotifyingScrollView;

import static info.NetWorkCheck.NetworkChecker.isConnected;
import static info.NetWorkCheck.NetworkChecker.isConnectedMobile;
import static info.NetWorkCheck.NetworkChecker.isConnectedWifi;


/**
 * Created by SLR on 8/17/2016.
 */
public class Doctlogin extends ScrollTabHolderFragment {

    private static final String TAG ="log" ;
    private  int mPosition=0;

    private static final String WEBURL = "http://infocancer.nyesteventuretech.com/Service/logincheck.php";
    private static final String TAGURL ="tag" ;
    private static final String EMAILURL = "email";
    private static final String PASSWORDURL ="password" ;
    private static final String USERLOGIN ="doctorlogin" ;
    private static final int MODE_PRIVATE =0x0000;
    private EditText _name;
    private EditText _passwordText;
    private Button _loginButton;
    private TextView _user_accnt;
    List<String> arrylisList;

    private static final String ARG_POSITION ="positionsq" ;
  private NotifyingScrollView mscrollView;

    public static Fragment newInstance(int position) {
        Doctlogin f = new Doctlogin();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);

    }





    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.doctor_login,
                container, false);
        mscrollView = (NotifyingScrollView) rootView.findViewById(R.id.doctor_scroll);
        arrylisList=new ArrayList<>();
        _loginButton=(Button)rootView.findViewById(R.id.doctor_button_id);
        _name=(EditText)rootView.findViewById(R.id.doctor_name_id);
        _passwordText=(EditText)rootView.findViewById(R.id.doctor_pass_id);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isConnectedWifi(getActivity()) || isConnectedMobile(getActivity())) {
                    if (isConnected(getActivity())) {
                        login();
                    } else {
                        new AlertBox_Inner(getActivity());
                    }
                } else {
                    new AlertBox_outer(getActivity());
                }
            }
        });

        _user_accnt=(TextView)rootView.findViewById(R.id.doctor_createaccount) ;
        _user_accnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });


        View placeHolderView = inflater.inflate(R.layout.view_header_placeholder,mscrollView, false);
        placeHolderView.setBackgroundColor(0xFFFFFFFF);

        findid(rootView);

                return rootView;
       }

    private void login() {


        Log.d(TAG, "Login");

        if (!validate()) {

            onLoginFailed();
            return;
        }
        // _loginButton.setEnabled(false);
        final String email = _name.getText().toString();

        final String password = _passwordText.getText().toString();


        onLoginSuccess(email,password);

    }

    private void onLoginSuccess(String name, String pass) {

        HashMap<String,String> hashmap=new HashMap<>();
    hashmap.put(TAGURL,USERLOGIN);
    hashmap.put(EMAILURL,name);
    hashmap.put(PASSWORDURL,pass);

    PostResponseAsyncTask task=new PostResponseAsyncTask(getActivity(), hashmap, new AsyncResponse() {
        @Override
        public void processFinish(String s) {
            try {
                JSONObject js=new JSONObject(s);





                String name=js.getString("name");
                int ch=js.getInt("succ");
                String pass=js.getString("password");
                String phtourl=js.getString("photourl");
                String email=js.getString("email");
                String doctor_id=js.getString("doctorid");
                Log.e("doctorid_photourl",phtourl);
                String agex=js.getString("age");
                String phonex=js.getString("phone");
                if(ch==1)
                {
                    nextpage(name,pass,phtourl,email,doctor_id,agex,phonex);
                    DataCheckHolder.getInstance().setDistributor_id(true);

                    /*SharedPreferences.Editor editor = getActivity().getPreferences(
                            MODE_PRIVATE).edit();
                    editor.putBoolean("registerapp", true);
                    editor.commit();*/




                    Intent intent=new Intent(getActivity(), UserChoicePage.class);

                    startActivity(intent);
                    getActivity().finish();

                }
                else
                if(ch==303)
                {

new AlertBox_("error login",getActivity());
                    //dialog box creation//error
                }





            } catch (JSONException e) {
                e.printStackTrace();
                new AlertBox_("error login",getActivity());
            } catch (Exception e) {
                e.printStackTrace();
                new AlertBox_("error login",getActivity());
            }



            //Toast.makeText(getActivity(),s.toString(),Toast.LENGTH_SHORT).show();

        }
    });

    task.execute(WEBURL);
    task.setEachExceptionsHandler(new EachExceptionsHandler() {
        @Override
        public void handleIOException(IOException e) {
            new AlertBox_("error login",getActivity());
        }

        @Override
        public void handleMalformedURLException(MalformedURLException e) {
            new AlertBox_("error login",getActivity());
        }

        @Override
        public void handleProtocolException(ProtocolException e) {
            new AlertBox_("error login",getActivity());
        }

        @Override
        public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
            new AlertBox_("error login",getActivity());
        }
    });


    //nextActivity();

}

    private void nextpage(String name, String pass, String phtourl, String email, String userid, String age, String phone)throws Exception {

        MainDataBAse data=new MainDataBAse(getActivity());
        data.open();
        boolean check=  data.check(DATABASE_C.TABLE_NAME.T_USER);
        if(check==true)
        {

            data.createuserTable(DATABASE_C.TABLE_NAME.T_USER,new UserDbItem(name,email,pass,userid,phone,age,phtourl),"doctorlogin");

        }
        else
        {
            data.updateuserTable(DATABASE_C.TABLE_NAME.T_USER,new UserDbItem(name,email,pass,userid,phone,age,phtourl),1,"doctorlogin");

        }
        data.close();


    }

    private void onLoginFailed() {

        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);



    }

    private boolean validate() {

        boolean valid = true;


        String password = _passwordText.getText().toString();
        String email = _name.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _name.setError("enter a valid email address");
            valid = false;
        } else {
            _name.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }



        return valid;



    }

    private void click() {
        Intent ui=new Intent(getActivity(), CreateAccount.class);
        ui.putExtra("messagexc","doctorlogin");
        ui.putExtra("wpage","splash");
        getActivity().startActivity(ui);
        getActivity().finish();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // mListView.getViewTreeObserver().addOnScrollChangedListener((ViewTreeObserver.OnScrollChangedListener) new OnScroll());
        mscrollView.setOnScrollChangedListener(new NotifyingScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                mScrollview.OnScroller1(who, l, t, oldl, oldt, mPosition);
                Log.e("scroll", "" + t);

            }
        });

    }




        private void findid(View view) {




        }

            @Override
            public void adjustScroll(int scrollHeight) {

            }


    @Override
          public void OnScroller(ScrollView who, int l, int t, int oldl, int oldt, int position) {


          }

    @Override
    public void OnScroller1(ScrollView who, int l, int t, int oldl, int oldt, int position) {

    }
}
/*   SharedPreferences prefs =getActivity().getPreferences(0);
        Boolean status = prefs.getBoolean("userregister", false);
        if (status) {

        }
                 findid(view);
                  _click.setOnClickListener(this);
                 _save.setOnClickListener(this);
                 imageView.setOnClickListener(this);


        cameraPhoto=new CameraPhoto(getActivity());
        galleryPhoto=new GalleryPhoto(getActivity());*/