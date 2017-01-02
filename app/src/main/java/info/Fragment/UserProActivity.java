package info.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import infocancer.nyesteventure.com.infocancer.R;


/**
 * Created by SLR on 11/28/2016.
 */

public class UserProActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofileview);
    }
}
