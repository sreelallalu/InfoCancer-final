package infocancer.nyesteventure.com.infocancer;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import info.Fragment.Doctlogin;
import info.Fragment.Userlogin;
import info.Holder.DataCheckHolder;
import infocancer.nyesteventure.com.infocancer.ViewpagerLogin.PagerSlidingTabStrip;


public class LoginActivity extends FragmentActivity implements ScrollTabHolder, ViewPager.OnPageChangeListener {




  public static final boolean NEEDS_PROXY = Integer.valueOf(Build.VERSION.SDK_INT).intValue() < 11;

    private View mHeader;

    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPage;
    private PagerAdapter mPagerAdapter;

    private int mMinHeaderHeight;

    private int mHeaderHeight;
    private int mMinHeaderTranslation;

    private ImageView user_infopic,doctor_infopic;
    private int mLastY;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
boolean b=false;
        b= DataCheckHolder.getInstance().getDistributor_id();
        if(b!=false)
        {

            Intent i = new Intent(getApplicationContext(),UserChoicePage.class);
            startActivity(i);
            finish();
        }
    /* SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        Boolean status = prefs.getBoolean("registerapp",false);
        if (status) {

            Intent i = new Intent(getApplicationContext(),UserChoicePage.class);
            startActivity(i);
            finish();

        }
*/



        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mMinHeaderHeight;

        setContentView(R.layout.login_login);



        mHeader = findViewById(R.id.header_userlogin);


       user_infopic = (ImageView) findViewById(R.id.user_png);
        doctor_infopic = (ImageView) findViewById(R.id.doctor_png);


        title=(TextView)findViewById(R.id.id_title_login);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf");
       title.setTypeface(face);





        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id._tabs_user);
        mViewPage = (ViewPager) findViewById(R.id._page_container);
        mViewPage.setOffscreenPageLimit(2);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setTabHolderScrollingContent(this);

        mViewPage.setAdapter(mPagerAdapter);

        mPagerSlidingTabStrip.setViewPager(mViewPage);
        mPagerSlidingTabStrip.setOnPageChangeListener(this);
        mLastY=0;




    }

    public class PagerAdapter extends FragmentPagerAdapter {

        private SparseArrayCompat<ScrollTabHolder> mScrollTabHolders;
        private final String[] TITLES = { "USER","DOCTOR"};
        private ScrollTabHolder mListener;
        ScrollTabHolderFragment fragment;


        public PagerAdapter(FragmentManager fm) {
            super(fm);
            mScrollTabHolders = new SparseArrayCompat<ScrollTabHolder>();
        }

        public void setTabHolderScrollingContent(ScrollTabHolder listener) {
            mListener = listener;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0) {
                fragment = (ScrollTabHolderFragment) Userlogin.newInstance(position);
            }
            else if(position==1)
            {
                fragment = (ScrollTabHolderFragment) Doctlogin.newInstance(position);
            }
            mScrollTabHolders.put(position, fragment);
            if (mListener != null) {

                fragment.setScollTB(mListener);
            }

            return fragment;
        }

        public SparseArrayCompat<ScrollTabHolder> getScrollTabHolders() {
            return mScrollTabHolders;
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (positionOffsetPixels > 0) {
            int currentItem = mViewPage.getCurrentItem();
            if(currentItem==0)
            {
                user_infopic.setImageResource(R.drawable.save_user1);
                doctor_infopic.setImageResource(0);
               // doctor_infopic.setImageBitmap(android.R.color.transparent);
            }
            else
            if (currentItem==1)
            {
                doctor_infopic.setImageResource(R.drawable.save_doctor1);
                user_infopic.setImageResource(0);
            }

            SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mPagerAdapter.getScrollTabHolders();
            ScrollTabHolder currentHolder;

            if (position < currentItem) {
                currentHolder = scrollTabHolders.valueAt(position);
            } else {
                currentHolder = scrollTabHolders.valueAt(position + 1);
            }

            if (NEEDS_PROXY) {
                // TODO is not good
                currentHolder.adjustScroll(mHeader.getHeight() - mLastY);
                mHeader.postInvalidate();

            } else {
                currentHolder.adjustScroll((int) (mHeader.getHeight() + mHeader.getTranslationY()));
            }
        }

    }

    @Override
    public void onPageSelected(int position) {

        SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mPagerAdapter.getScrollTabHolders();
        ScrollTabHolder currentHolder = scrollTabHolders.valueAt(position);
        if(NEEDS_PROXY){
            //TODO is not good
            currentHolder.adjustScroll(mHeader.getHeight()-mLastY);
            mHeader.postInvalidate();
        }else{
            currentHolder.adjustScroll((int) (mHeader.getHeight() +mHeader.getTranslationY()));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void adjustScroll(int scrollHeight) {

    }


    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, min), max);
    }


    @Override
    public void OnScroller(ScrollView who, int l, int t, int oldl, int oldt, int position) {
        Log.e("mainactivityAbsalute", String.valueOf(t));
        if (mViewPage.getCurrentItem() == position) {
            int scrollY =who.getScrollY();

            if (NEEDS_PROXY) {
                //TODO is not good
                float translationY = Math.max(-scrollY, mMinHeaderTranslation);
                mHeader.setTranslationY(translationY);
                //mTopImage.setTranslationY(-translationY / 3);

                mLastY = -Math.max(-scrollY, mMinHeaderTranslation);
                //info.setText(String.valueOf(scrollY));
                mHeader.scrollTo(0, mLastY);
                mHeader.postInvalidate();
            } else {
                mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
            }
        }
    }

    @Override
    public void OnScroller1(ScrollView who, int l, int t, int oldl, int oldt, int position) {
        Log.e("mainactivityAbsalute", String.valueOf(t));
        if (mViewPage.getCurrentItem() == position) {
            int scrollY =who.getScrollY();

            if (NEEDS_PROXY) {
                //TODO is not good
                float translationY = Math.max(-scrollY, mMinHeaderTranslation);
                mHeader.setTranslationY(translationY);
                //mTopImage.setTranslationY(-translationY / 3);

                mLastY = -Math.max(-scrollY, mMinHeaderTranslation);
                //info.setText(String.valueOf(scrollY));
                mHeader.scrollTo(0, mLastY);
                mHeader.postInvalidate();
            } else {
                mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
            }
        }
    }
}
