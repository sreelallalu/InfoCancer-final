package info.Fragment;

import android.app.Activity;
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
import java.util.HashMap;

import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.Database.UserDbItem;
import info.Holder.DataCheckHolder;
import info.NavItem.AlertBox_;
import info.NavItem.AlertBox_Inner;
import info.NavItem.AlertBox_outer;
import infocancer.nyesteventure.com.infocancer.CreateAccount;
import infocancer.nyesteventure.com.infocancer.LoginActivity;
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
public class Userlogin extends ScrollTabHolderFragment {

    private static final String TAG = "tag";
    private static final int PASSONE =1 ;
    private static final String DATABASETABLE4 ="userprofile" ;

    private static final String ARG_POSITION = "positions";

    private static final String WEBURL = "http://infocancer.nyesteventuretech.com/Service/logincheck.php";
    private static final String TAGURL ="tag" ;
    private static final String EMAILURL = "email";
    private static final String PASSWORDURL ="password" ;
    private static final String USERLOGIN ="userlogin" ;
    private static final int MODE_PRIVATE =0x0000;


    private NotifyingScrollView mscrollview;
    int mPosition=0;

    private EditText _name;
    private EditText _passwordText;
    private Button _loginButton;
    private TextView _user_create;

    public static Fragment newInstance(int position) {
        Userlogin f = new Userlogin();
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


    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
         final View rootView = inflater.inflate(R.layout.fragment1,container, false);
        mscrollview = (NotifyingScrollView) rootView.findViewById(R.id.user_scroll);
        _name=(EditText)rootView.findViewById(R.id.user_name_id);
        _passwordText=(EditText)rootView.findViewById(R.id.user_pass_id);
        _loginButton=(Button)rootView.findViewById(R.id.user_button_id);

        _loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(isConnectedWifi(getActivity())||isConnectedMobile(getActivity()))
                {
                    if(isConnected(getActivity())) {

                        login();

                    }
                    else
                    {
                    new AlertBox_Inner(getActivity());

                    }
                }else
                {
                    new AlertBox_outer(getActivity());

                   // new AlertBox("error",getActivity());
                }

              //temporary
                /*Intent intent=new Intent(getActivity(), UserChoicePage.class);
                startActivity(intent);
                getActivity().finish();*/
            }
        });
        _user_create=(TextView)rootView.findViewById(R.id.user_createaccount);
        _user_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getActivity(),"click",Toast.LENGTH_SHORT).show();
              // rootView = inflater.inflate(R.layout.sign_up,container, false);

           _user_create_accnt();
            }
        });

        View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, mscrollview, false);
        placeHolderView.setBackgroundColor(0xFFFFFFFF);

        return rootView;
    }


    private void _user_create_accnt() {
    try {


        Intent ui=new Intent(getActivity(), CreateAccount.class);
        ui.putExtra("messagexc","userlogin");
        ui.putExtra("wpage","splash");
        getActivity().startActivity(ui);
        getActivity().finish();


    }
    catch(Exception e)
    {
        e.printStackTrace();
    }



    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // mListView.getViewTreeObserver().addOnScrollChangedListener((ViewTreeObserver.OnScrollChangedListener) new OnScroll());
        mscrollview.setOnScrollChangedListener(new NotifyingScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                mScrollview.OnScroller(who, l, t, oldl, oldt, mPosition);
                Log.e("scroll", "" + t);

            }
        });

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

       /* final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();*/
             onLoginSuccess(email,password);
       /* new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed

                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);*/
    }


    private void onLoginSuccess(String name, String pass) {

       /* SharedPreferences.Editor editor = getActivity().getPreferences(0).edit();
        editor.putBoolean("userregister", true);

        editor.commit();
     MainDataBAse userprofile=new MainDataBAse(getActivity());
        userprofile.open();

        userprofile.createuserTable(DATABASETABLE4,name,"hj",pass);
          userprofile.close();*/
        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put(TAGURL,USERLOGIN);
        hashmap.put(EMAILURL,name);
        hashmap.put(PASSWORDURL,pass);

        PostResponseAsyncTask task=new PostResponseAsyncTask(getActivity(), hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.e("nourl",s);
                try {
                    JSONObject js=new JSONObject(s);
                    String name=js.getString("name");
                    int ch=js.getInt("succ");
                    String pass=js.getString("password");
                    String phtourl=js.getString("photourl");
                    String email=js.getString("email");
                    String user_id=js.getString("userid");
                    String phone=js.getString("phone");
                    String age=js.getString("age");
                    if(ch==1)
                    {

                      try {
                          nextpage(name, pass, phtourl, email, user_id,age,phone);
                      }catch (Exception e){

                      }
                      finally {
                          DataCheckHolder.getInstance().setDistributor_id(true);
                          Intent intent=new Intent(getActivity(), UserChoicePage.class);
                          startActivity(intent);
                         getActivity().finish();
                      }



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

    private void nextpage(final String name, String pass, String phtourl, String email, String user_id, String age, String phone)throws Exception {
        MainDataBAse data=new MainDataBAse(getActivity());
        data.open();
      boolean check=  data.check(DATABASE_C.TABLE_NAME.T_USER);
        if(check==true)
        {

            data.createuserTable(DATABASE_C.TABLE_NAME.T_USER,new UserDbItem(name,email,pass,user_id,phone,age,phtourl),"userlogin");

        }
        else
        {
            data.updateuserTable(DATABASE_C.TABLE_NAME.T_USER,new UserDbItem(name,email,pass,user_id,phone,age,phtourl),1,"userlogin");

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
