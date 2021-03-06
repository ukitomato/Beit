package com.google.developer.beit.beit;


import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.developer.beit.beit.Fragment.CarrierAchievementsFragment;
import com.google.developer.beit.beit.Fragment.CarrierSettingFragment;
import com.google.developer.beit.beit.Fragment.FavoriteFragment;
import com.google.developer.beit.beit.Fragment.HelpFragment;
import com.google.developer.beit.beit.Fragment.HomeFragment;
import com.google.developer.beit.beit.Fragment.InquiryFragment;
import com.google.developer.beit.beit.Fragment.OrderCreateFragment;

import java.util.Arrays;
import java.util.Objects;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener, OrderCreateFragment.OnFragmentInteractionListener,
        CarrierAchievementsFragment.OnFragmentInteractionListener, CarrierSettingFragment.OnFragmentInteractionListener,
        FavoriteFragment.OnFragmentInteractionListener, HelpFragment.OnFragmentInteractionListener,
        InquiryFragment.OnFragmentInteractionListener, FragmentTabHost.OnTabChangeListener,
        ViewPager.OnPageChangeListener {
    private static final String[] pageTitle = {"キャリアー設定", "実績"};
    private static final Class[] carrierFragments = {CarrierSettingFragment.class, CarrierAchievementsFragment.class};
    private static final String CARE_UNSELECTED = "android.graphics.drawable.GradientDrawable$GradientState@41268ce";
    private static final String CARE_SELECTED = "android.graphics.drawable.GradientDrawable$GradientState@d0fefc";
    FrameLayout content;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);

        content = findViewById(R.id.content);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                Class fragmentClass;
                fragmentClass = carrierFragments[position];
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return fragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageTitle[position];
            }

            @Override
            public int getCount() {
                return pageTitle.length;
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_settings);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_achievement);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass;
            fragmentClass = HomeFragment.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order_write) {
            Log.d("onMenuItemClick", String.valueOf(id));
            Fragment fragment = null;
            Objects.requireNonNull(getSupportActionBar()).setTitle("オーダー作成");
            Class fragmentClass = OrderCreateFragment.class;
            content.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            try {
                fragment = (Fragment) Objects.requireNonNull(fragmentClass).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        boolean carrierFlag = false;
        switch (id) {
            case R.id.nav_home:
                Objects.requireNonNull(getSupportActionBar()).setTitle("ホーム");
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_order_create:
                Objects.requireNonNull(getSupportActionBar()).setTitle("オーダー作成");
                fragmentClass = OrderCreateFragment.class;
                break;
            case R.id.nav_favorite:
                Objects.requireNonNull(getSupportActionBar()).setTitle("お気に入り");
                fragmentClass = FavoriteFragment.class;
                break;
            case R.id.nav_help:
                Objects.requireNonNull(getSupportActionBar()).setTitle("ヘルプ");
                fragmentClass = InquiryFragment.class;
                break;
            case R.id.nav_inquiry:
                Objects.requireNonNull(getSupportActionBar()).setTitle("問い合わせ");
                fragmentClass = InquiryFragment.class;
                break;
            case R.id.nav_carrier_setting:
                Objects.requireNonNull(getSupportActionBar()).setTitle("キャリアー設定");
                carrierFlag = true;
                break;
            case R.id.nav_achievement:
                Objects.requireNonNull(getSupportActionBar()).setTitle("実績");
                carrierFlag = true;
                break;
        }

        if (carrierFlag) {
            content.setVisibility(View.GONE);
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
        } else {
            content.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            try {
                fragment = (Fragment) Objects.requireNonNull(fragmentClass).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onMethodButtonClick(View view, ImageView[] buttons) {
        for (ImageView button : buttons) {
            button.setBackground(getDrawable(R.drawable.round_frame_gray));
        }
        view.setBackground(getDrawable(R.drawable.round_frame_white));
    }

    @Override
    public void onCareButtonClick(View view, boolean state) {
        if (state) {
            view.setBackground(getDrawable(R.drawable.round_frame_white));
        } else {
            view.setBackground(getDrawable(R.drawable.round_frame_gray));
        }


    }

    @Override
    public void onTabChanged(String tabId) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
