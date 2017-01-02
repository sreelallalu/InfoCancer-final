package infocancer.nyesteventure.com.infocancer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by SLR on 10/27/2016.
 */
public class PreventionClass extends AppCompatActivity {

    private TextView Ha,Ta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent u=getIntent();
        String head=u.getStringExtra("item");
        String postion=u.getStringExtra("position");

        setContentView(R.layout.prevention_ib);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //PsetTitle(postion);
       // Toast.makeText(this, ""+postion, Toast.LENGTH_SHORT).show();

        Ha=(TextView)findViewById(R.id.pre_id_h);
        Ta=(TextView)findViewById(R.id.pre_id_t);
        Ha.setText(head);
      String path= PsetTitle(postion);
        Log.e("kitti", String.valueOf(path));
       Testing(path);


    }

    private void Testing(String path) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(path)));

            // do reading, usually loop until end of file reading
            String mLine;
            StringBuilder s = new StringBuilder();
            while ((mLine = reader.readLine()) != null) {
                //process line
             s.append(mLine+"\n");
            }
            Ta.setText(s);
           Log.e("kitti", String.valueOf(s));

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

 private String PsetTitle(String postion) {
        int k= Integer.parseInt(postion);

           String str=null;
        switch (k)
        {
            case 0 :
              str="text4/breastPrev.txt";
                break;
            case 1 :
                str="text4/SkinPrev.txt";
                break;
            case 2 :
                str="text4/BowelPrev.txt";
                break;
            case 3 :
                str="text4/LungPrev.txt";
                break;
            case 4 :
                str="text4/childPrev.txt";
                break;
            case 5 :
                str="text4/kidneyPrev.txt";
                break;
            case 6 :
                str="text4/CervixPrev.txt";
                break;
            case 7 :
                str="text4/ColonPrev.txt";
                break;
            case 8 :
                str="text4/OvarianPrev.txt";
                break;
            case 9 :
                str="text4/UterinePrev.txt";
                break;
            case 10 :
                str="text4/VaginalPrev.txt";
                break;
            case 11 :
                str= "text4/LeukemiaPrev.txt";
                break;
            case 12 :
                str="text4/VulvarPrev.txt";
                break;
            case 13 :
                str="text4/LiverPrev.txt";
                break;
            case 14 :
                str="text4/LymphomaPrev.txt";
                break;
            case 15 :
                str="text4/ThyroidPrev.txt";
                break;
            case 16 :
                str="text4/OralPrev.txt";
                break;
            case 17 :
                str="text4/PancreaPrev.txt";
                break;
            case 18 :
                str="text4/UrinaryPrev.txt";
                break;
        }
return str;

    }




    @Override
    public void onBackPressed() {
        // Log.d("BACK_BUTTON_DOESNT_WORK", "I will never execute and you will never see me :(");
        super.onBackPressed();
        this.finish();

    }
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
