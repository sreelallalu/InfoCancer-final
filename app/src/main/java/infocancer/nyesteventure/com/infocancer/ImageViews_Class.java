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

import info.Fragment.ImgCancer.ImgBlader;
import info.Fragment.ImgCancer.ImgBreast;
import info.Fragment.ImgCancer.ImgCervical;
import info.Fragment.ImgCancer.ImgColorectal;
import info.Fragment.ImgCancer.ImgMain;
import info.Fragment.ImgCancer.ImgMens;
import info.Fragment.ImgCancer.ImgOverian;
import info.Fragment.ImgCancer.ImgPancreatic;
import info.Fragment.ImgCancer.ImgProstate;
import info.Fragment.ImgCancer.ImgSkin;
import info.Fragment.ImgCancer.ImgSmoke;
import info.Fragment.ImgCancer.ImgWomens;
import info.Fragment.ImgCancer.Imglungs;
import info.Fragment.Prevension.BackFragmentHelper;
import info.NavAdapter.NavDrawerListAdapter;
import info.NavItem.NavDrawerItem;


/**
 * Created by SLR on 8/24/2016.
 */



public class ImageViews_Class extends AppCompatActivity {


    private static final String TAG = "picasso error";
    ImageView user_pic,editpic;
    TextView name_u,email_u;
    //private String share;
    private String[] navMenuTitles;

    ArrayList<NavDrawerItem> navaItem;
    NavDrawerListAdapter navaAdapter;
    private String share;
    ListView listView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbarc();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkk);
        setSupportActionBar(toolbar);

        Intet_getString();
        listView=(ListView)findViewById(R.id.list_main);

        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_cool);
        ActionBarDrawerToggle toggles = new ActionBarDrawerToggle(
                ImageViews_Class.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggles);
        toggles.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_cool);
        navaAdapter=new NavDrawerListAdapter(getApplicationContext(),navaItem);
        listView.setAdapter(navaAdapter);
        listView.setOnItemClickListener(new ListItemClick());

        if(savedInstanceState==null)
        {
            displayimage(0);;
        }
    }


    private void Intet_getString() {


      setImages();



    }





    private ArrayList<NavDrawerItem> setImages() {

        navMenuTitles=getResources().getStringArray(R.array.stringimage);
int nav[]={R.drawable.splash_logo,R.drawable.ic_brest,R.drawable.ic_lung,R.drawable.ic_skin,R.drawable.ic_bladder,
R.drawable.ic_cervical,R.drawable.ic_colon,R.drawable.ic_overian,R.drawable.ic_pancreatic,R.drawable.ic_prostate,
};
        navaItem=ImageAdapterC(navMenuTitles,nav);
        return navaItem;
    }





    private ArrayList<NavDrawerItem> ImageAdapterC(String[] navMenuTitles, int [] navIcon) {
        navaItem = new ArrayList<NavDrawerItem>();
        navaItem.add(new NavDrawerItem(this.navMenuTitles[0], navIcon[0]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[1], navIcon[1]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[2], navIcon[2]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[3], navIcon[3]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[4], navIcon[4]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[5], navIcon[5]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[6], navIcon[6]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[7], navIcon[7]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[8], navIcon[8]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[9], navIcon[9]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[10], navIcon[1]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[11], navIcon[0]));
        navaItem.add(new NavDrawerItem(this.navMenuTitles[12], navIcon[2]));

        return navaItem;
    }











    private class ListItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                displayimage(position);


        }
    }








    private void displayimage(int position)
    {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_cool);
        switch (position)
        {
            case 0:

                ImgMain fragment = new ImgMain();
                fragmentTransaction.replace(R.id.frameik,fragment);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("infoCancer");
                break;
            case 1:
                ImgBreast fragment1 = new ImgBreast();
                fragmentTransaction.replace(R.id.frameik,fragment1);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Breast Cancer");
                break;
            case 2:
                Imglungs fragment2 = new Imglungs();
                fragmentTransaction.replace(R.id.frameik,fragment2);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Lung Cancer");
                break;
            case 3:
                ImgSkin fragment3 = new ImgSkin();
                fragmentTransaction.replace(R.id.frameik,fragment3);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Skin Cancer");
                break;
            case 4:
                ImgBlader fragment4 = new ImgBlader();
                fragmentTransaction.replace(R.id.frameik,fragment4);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Bladder Cancer");
                break;
            case 5:
                ImgCervical fragment5 = new ImgCervical();
                fragmentTransaction.replace(R.id.frameik,fragment5);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Cervical Cancer");
                break;
            case 6:
                ImgColorectal fragment6 = new ImgColorectal();
                fragmentTransaction.replace(R.id.frameik,fragment6);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Colorectal Cancer");
                break;
            case 7:
                ImgOverian fragment7 = new ImgOverian();
                fragmentTransaction.replace(R.id.frameik,fragment7);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Ovarian Cancer");
                break;
            case 8:
                ImgPancreatic fragment8 = new ImgPancreatic();
                fragmentTransaction.replace(R.id.frameik,fragment8);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Pancreatic Cancer");
                break;
            case 9:
                ImgProstate fragment9 = new ImgProstate();
                fragmentTransaction.replace(R.id.frameik,fragment9);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Prostate Cancer");
                break;
            case 10:
                ImgWomens fragment10 = new ImgWomens();
                fragmentTransaction.replace(R.id.frameik,fragment10);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Womens Cancer");
                break;
            case 11:
                ImgMens fragment11 = new ImgMens();
                fragmentTransaction.replace(R.id.frameik,fragment11);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Mens Cancer");
                break;
            case 12:
                ImgSmoke fragment12 = new ImgSmoke();
                fragmentTransaction.replace(R.id.frameik,fragment12);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Smoke Cancer");
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
            Intent i=new Intent(ImageViews_Class.this,UserChoicePage.class);
            startActivity(i);
            finish();
        }

    }
}





