package info.Fragment.Prevension;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import info.Database.MainDataBAse;
import infocancer.nyesteventure.com.infocancer.R;

/**
 * Created by SLR on 9/23/2016.
 */
public class PreventionInfo extends Fragment {


    private static final String DATABASE_TABLE1 ="prevention" ;
    private static final String TAGCANCER ="preventiontag" ;
    private static final String TAG_USER ="user" ;
    private static final String I_URL ="url" ;
    private static final String I_TEXT ="titlestring";
    private static final String WEBURL ="http://rto.venturesoftwares.org/testjson/testimage.php";



    String h[],h1[];
 Fragment fragment;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.home, container, false);
          //String _data = DataHolderClass.getInstance().getDistributor_id();
         // checkthestate(_data);
         // checkdatabase();
          //checkupserver();

      // android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
       //fragmentTransaction.replace( R.id.frameik, new InfoPrevention() ).addToBackStack( "tag" ).commit();

       /* rootView.getView().setFocusableInTouchMode(true);
        rootView.getView().requestFocus();
        rootView.getView().setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    return true;
                }
                return false;
            }
        } );
                       */



       /* getActivity().getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        Toast.makeText(getActivity(),"back",Toast.LENGTH_SHORT).show();
                    }

                });*/
        return rootView;
            }



    private void checkdatabase() {

        MainDataBAse batabase=new MainDataBAse(getActivity());
        batabase.open();

        boolean check=batabase.check(DATABASE_TABLE1);
        batabase.close();

        if(check==true)
        {
           // serverup();
            //AllgetfromDatabase();


        }
        else
        if(check==false)
        {
         //   AllgetfromDatabase();
            //Toast.makeText(getActivity(),""+check, Toast.LENGTH_SHORT).show();


        }


    }
/*
    private void AllgetfromDatabase() {
        h1= URLggetFromdatabase();
        h=StringgetFromdatabase();

    }

    private String[] StringgetFromdatabase() {
        String cv[]=new String[50];
        MainDataBAse newdata=new MainDataBAse(getActivity());
        newdata.open();
        try {
            // newdata.exportDB("urlcancer11db");
            cv=newdata.getTEXTPrevention(DATABASE_TABLE1);


        } catch (Exception e) {
            e.printStackTrace();
        }
        newdata.close();
        return cv;


    }

    private String[] URLggetFromdatabase() {
        String cv[]=new String[50];
        MainDataBAse newdata=new MainDataBAse(getActivity());
        newdata.open();
        try {
            // newdata.exportDB("urlcancer11db");
            cv=newdata.getUrlPrevention(DATABASE_TABLE1);


        } catch (Exception e) {
            e.printStackTrace();
        }
        newdata.close();
        return cv;
    }

    private void serverup() {
        final MainDataBAse dataBAse=new MainDataBAse(getActivity());
        dataBAse.open();
        final String face[]=new String[50];
        final String face1[]=new String[50];
        final String face2[]=new String[50];
        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put("tag",TAGCANCER);



        PostResponseAsyncTask task=new PostResponseAsyncTask(getActivity(), hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

                try{
                    int i;
                    JSONObject main= null;

                    main = new JSONObject(s);

                    JSONArray matchFixturec =main.getJSONArray(TAG_USER);
                    int j=matchFixturec.length();

                    for(i=0;i<j;i++){

                        JSONObject c =matchFixturec.getJSONObject(i);
                        face1[i]=c.getString(I_URL);
                        //face1[i]=c.getString(I_HEAD);
                       face[i]=c.getString(I_TEXT);


                        dataBAse.createEntryPrevn(DATABASE_TABLE1,face[i],face1[i]);

                    }dataBAse.close();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        task.execute(WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {

            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {

            }

            @Override
            public void handleProtocolException(ProtocolException e) {

            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {

            }
        });




    }

    private void checkthestate(String data) {
        switch (data)
        {
            case "prevent0":
                setImageandString(h[0],h1[0]);

                break;
            case "prevent1":
                setImageandString(h[1],h1[1]);
                break;
            case "prevent2":
                setImageandString(h[2],h1[2]);
                break;

            case "prevent3":
                setImageandString(h[3],h1[3]);
                break;
            case "prevent4":
                setImageandString(h[4],h1[4]);
                break;
            case "prevent5":
                setImageandString(h[5],h1[5]);
                break;
            case "prevent6":
                setImageandString(h[6],h1[6]);
                break;
            case "prevent7":
                setImageandString(h[7],h1[7]);
                break;
            case "prevent8":
                setImageandString(h[8],h1[8]);
                break;
            case "prevent9":
                setImageandString(h[9],h1[9]);
                break;
            case "prevent10":
                setImageandString(h[10],h1[10]);
                break;
            case "prevent11":
                setImageandString(h[11],h1[11]);
                break;
            case "prevent12":
                setImageandString(h[12],h1[12]);
                break;
            case "prevent13":
                setImageandString(h[13],h1[13]);
                break;
            case "prevent14":
                setImageandString(h[14],h1[14]);
                break;
            case "prevent15":
                setImageandString(h[15],h1[15]);
                break;
            case "prevent16":
                setImageandString(h[16],h1[16]);
                break;
            case "prevent17":
                setImageandString(h[17],h1[17]);
                break;
            case "prevent18":
                setImageandString(h[18],h1[18]);
                break;
        }

    }

    private void setImageandString(String s, String s1) {


    }


    @Override
    public boolean onBackPressed() {

       try{
          // Toast.makeText(getActivity(),"length",Toast.LENGTH_SHORT).show();
         InfoPrevention fragment = new  InfoPrevention();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameik, fragment).commit();
       }catch(Exception e){e.printStackTrace();
       }
        return false;
    }

    @Override
    public int getBackPriority() {
        return 0;
    }*/
}
