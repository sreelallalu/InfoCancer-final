package info.Fragment.Prevension;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import info.Fragment.UserProfile;
import infocancer.nyesteventure.com.infocancer.R;
import infocancer.nyesteventure.com.infocancer.UserChoicePage;

/**
 * Created by SLR on 12/5/2016.
 */

public class About extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textView=(TextView)findViewById(R.id.text_link_about);

        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.nyesteventuretech.com/'> Terms And Conditions </a>";
        textView.setText(Html.fromHtml(text));

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();

                this.finish();
                return true;

        }

        return super.onOptionsItemSelected(item);




    }

}
