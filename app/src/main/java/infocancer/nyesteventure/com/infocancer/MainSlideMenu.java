package infocancer.nyesteventure.com.infocancer;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import info.Fragment.Infocancer.InfoDiagnosed;
import info.Fragment.Infocancer.InfoHome;
import info.Fragment.Infocancer.InfoPrevention;
import info.Fragment.Infocancer.InfoReference;
import info.Fragment.Infocancer.InfoStaging;
import info.Fragment.Infocancer.InfoSymtoms;
import info.Fragment.Infocancer.InfoTypes;
import info.Fragment.Prevension.BackFragmentHelper;
import info.NavAdapter.NavDrawerListAdapter;
import info.NavItem.NavDrawerItem;


/**
 * Created by SLR on 8/24/2016.
 */



public class MainSlideMenu extends AppCompatActivity {


    private static final String TAG = "picasso error";
    ImageView user_pic,editpic;
    TextView name_u,email_u;
    //private String share;
    private String[] navMenuTitles;

    ArrayList<NavDrawerItem> navaItem;
    NavDrawerListAdapter navaAdapter;
    private String share;
    ListView listView;
    //private int navi[]=new int[7];
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbarc();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkk);
        setSupportActionBar(toolbar);
       // toolbar.setTitle("cancerinfomation");
        Intet_getString();
     listView=(ListView)findViewById(R.id.list_main);
      DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_cool);
        ActionBarDrawerToggle toggles = new ActionBarDrawerToggle(
               MainSlideMenu.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggles);
        toggles.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_cool);
        navaAdapter=new NavDrawerListAdapter(getApplicationContext(),navaItem);
        listView.setAdapter(navaAdapter);

        listView.setOnItemClickListener(new ListItemClick());






   if(savedInstanceState==null)
   {

           displaycancer(0);



   }
           }



    private void Intet_getString() {



       setCancer();
            //Toast.makeText(MainSlideMenu.this, "check", Toast.LENGTH_SHORT).show();



    }




    private ArrayList<NavDrawerItem> setCancer() {

       navMenuTitles=getResources().getStringArray(R.array.stringcancer);
        int  navi[]={R.drawable.ic_cancerc,
                R.drawable.ic_types,R.drawable.ic_syptoms,R.drawable.ic_digonsed
        ,R.drawable.ic_staging,R.drawable.ic_prevention,R.drawable.ic_reference};



        navaItem= InfoAdapter(navMenuTitles,navi);

        return navaItem;

    }





        private ArrayList<NavDrawerItem> InfoAdapter(String[] navMenuTitles, int [] navMenuIcons) {
            navaItem = new ArrayList<NavDrawerItem>();
            navaItem.add(new NavDrawerItem(this.navMenuTitles[0], navMenuIcons[0]));
            navaItem.add(new NavDrawerItem(this.navMenuTitles[1], navMenuIcons[1]));
            navaItem.add(new NavDrawerItem(this.navMenuTitles[2], navMenuIcons[2]));
            navaItem.add(new NavDrawerItem(this.navMenuTitles[3], navMenuIcons[3]));
            navaItem.add(new NavDrawerItem(this.navMenuTitles[4], navMenuIcons[4]));
            navaItem.add(new NavDrawerItem(this.navMenuTitles[5], navMenuIcons[5]));
            navaItem.add(new NavDrawerItem(this.navMenuTitles[6], navMenuIcons[6]));


            return navaItem;
        }

        private ArrayList<NavDrawerItem> QuestionAdapter(String[] navMenuTitles, TypedArray navIcon) {




          return navaItem;
        }












    private class ListItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


              displaycancer(position);
               //Toast.makeText(MainSlideMenu.this,"iimage",Toast.LENGTH_SHORT).show();



        }
    }




    private void displaycancer(int pos) {
        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_cool);
        switch(pos)
        {
            case 0:
                InfoHome fragment = new InfoHome();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameik,fragment);
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("infoCancer");
                drawer.closeDrawer(GravityCompat.START);

                break;
            case 1:
                InfoTypes fragment1 = new InfoTypes();
                android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frameik,fragment1);
                fragmentTransaction1.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Type of Cancer");

                break;
            case 2:
                InfoSymtoms fragment2 = new InfoSymtoms();
                android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.frameik,fragment2);
                fragmentTransaction2.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Symtoms and Signs");

                break;
            case 3:
                InfoDiagnosed fragment3 = new InfoDiagnosed();
                android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.frameik,fragment3);
                fragmentTransaction3.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("");
                getSupportActionBar().setTitle("Diagnosed");
                break;
            case 4:
                InfoStaging fragment4 = new InfoStaging();
                android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction4.replace(R.id.frameik,fragment4);
                fragmentTransaction4.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Diagnosed");
                break;
            case 5:
                InfoPrevention fragment5 = new InfoPrevention();
                android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction5.replace(R.id.frameik,fragment5);
                fragmentTransaction5.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Staging");
                break;
            case 6:
                InfoReference fragment6 = new InfoReference();
                android.support.v4.app.FragmentTransaction fragmentTransaction6 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction6.replace(R.id.frameik,fragment6);
                fragmentTransaction6.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Reference");
                break;

        }

    }






    @Override
    public void onBackPressed() {
        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_cool);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        if(!BackFragmentHelper.fireOnBackPressedEvent(this)) {
            // lets do the default back action if fragments don't consume it
            // Toast.makeText(this,"hu",Toast.LENGTH_SHORT).show();

        }



        else {

            super.onBackPressed();
            Intent i=new Intent(MainSlideMenu.this,UserChoicePage.class);
            startActivity(i);
            finish();
        }

    }
    }





