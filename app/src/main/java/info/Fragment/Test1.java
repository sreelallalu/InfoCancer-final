package info.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import infocancer.nyesteventure.com.infocancer.R;


/**
 * Created by SLR on 8/25/2016.
 */
public class Test1 extends Fragment {
    public Test1(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.test1, container, false);
        return rootView;

    }
}
