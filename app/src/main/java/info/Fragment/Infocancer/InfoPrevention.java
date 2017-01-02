package info.Fragment.Infocancer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.Arrays;

import info.Fragment.Prevension.BackFragment;
import infocancer.nyesteventure.com.infocancer.PreventionClass;
import infocancer.nyesteventure.com.infocancer.R;

/**
 * Created by SLR on 9/19/2016.
 */
public class InfoPrevention extends Fragment implements BackFragment{



      String array[]={"Breast Cancer","Skin Cancer","Bowel Cancer","Lung Cancer",
              "Childhood Cancer","Kidney Cancer"
              ,"Cervical Cancer","Colon Cancer","Ovarian Cancer","Uterine Cervix Cancer",
              "Vaginal Cancer",
                "Leukemia Cancer","Vulval Cancer","Liver Cancer","Lymphoma Cancer",
              "Thyroid Cancer","Oral Cavity and Pharynx Cancer",
            "Pancreas Cancer","Urinary Cancer"};
 private ListView listView;
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.info_prevention, container, false);
            listView=(ListView)rootView.findViewById(R.id.info_prevention_listview);
            ArrayList<String> arrayList=new ArrayList(Arrays.asList(array));
            ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,arrayList);
            listView.setAdapter(adapter);
         listView.setOnItemClickListener(new ListViewClick());

            return rootView;
            }

    @Override
    public boolean onBackPressed() {
        return  true;
    }

    @Override
    public int getBackPriority() {
        return 0;
    }


    private class ListViewClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            display(position);
        }
    }

    private void display(int position) {
       // android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position)
        {

            case 0:
                PreventionMsg(array[0],position);
                break;
            case 1:
                PreventionMsg(array[1],position);
                break;
            case 2:
                PreventionMsg(array[2],position);
                break;
            case 3:
                PreventionMsg(array[3],position);
                break;
            case 4:
                PreventionMsg(array[4],position);
                break;
            case 5:
                PreventionMsg(array[5],position);
                break;
            case 6:
                PreventionMsg(array[6],position);
                break;
            case 7:
                PreventionMsg(array[7],position);
                break;
            case 8:
                PreventionMsg(array[8],position);
                break;
            case 9:
                PreventionMsg(array[9],position);
                break;
            case 10:
                PreventionMsg(array[10],position);
                break;
            case 11:
                PreventionMsg(array[11],position);
                break;
            case 12:
                PreventionMsg(array[12],position);
                break;
            case 13:
                PreventionMsg(array[13],position);
                break;
            case 14:
                PreventionMsg(array[14],position);
                break;
            case 15:
                PreventionMsg(array[15],position);
                break;
            case 16:
                PreventionMsg(array[16],position);
                break;
            case 17:
                PreventionMsg(array[17],position);
                break;
            case 18:
                PreventionMsg(array[18],position);
                break;
        }
    }

    private void PreventionMsg(String s, int pos) {

     Intent intent=new Intent(getActivity(),PreventionClass.class);
        intent.putExtra("item",s);

        intent.putExtra("position",""+pos);
        startActivity(intent);

     /*   PreventionInfo fragment = new PreventionInfo();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameik, fragment).commit();
        DataHolderClass.getInstance().setDistributor_id(s);*/
    }
}
