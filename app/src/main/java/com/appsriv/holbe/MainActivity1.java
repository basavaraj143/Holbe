package com.appsriv.holbe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity1 extends AppCompatActivity
{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.supplements_green,
            R.drawable.workouts,
            R.drawable.lifestyles,
            R.drawable.foodadndrink, R.drawable.foodadndrink};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {

        View layoutInflater =LayoutInflater.from(MainActivity1.this).inflate(R.layout.custom_tab_layout,null);
        TextView top = (TextView)layoutInflater.findViewById(R.id.top);
        TextView bottom = (TextView)layoutInflater.findViewById(R.id.bottom);
        ImageView icon =(ImageView)layoutInflater.findViewById(R.id.icon);
        icon.setBackgroundResource(R.drawable.supplements);
        bottom.setText("Supplements");
        top.setBackgroundResource(R.drawable.circle);
        top.setText("3");
        layoutInflater.setBackgroundColor(Color.parseColor("#ABD14B"));
        tabLayout.getTabAt(0).setCustomView(layoutInflater);

        View layoutInflater1 =LayoutInflater.from(MainActivity1.this).inflate(R.layout.custom_tab_layout,null);
        TextView top1 = (TextView)layoutInflater1.findViewById(R.id.top);
        TextView bottom1 = (TextView)layoutInflater1.findViewById(R.id.bottom);
        ImageView icon1 =(ImageView)layoutInflater1.findViewById(R.id.icon);
        icon1.setBackgroundResource(R.drawable.workouts);
        bottom1.setText("Workout");
        top1.setText("6");
        layoutInflater1.setBackgroundColor(Color.parseColor("#3CC3AF"));
        tabLayout.getTabAt(1).setCustomView(layoutInflater1);

        View layoutInflater2 =LayoutInflater.from(MainActivity1.this).inflate(R.layout.custom_tab_layout,null);
        TextView top2 = (TextView)layoutInflater2.findViewById(R.id.top);
        TextView bottom2 = (TextView)layoutInflater2.findViewById(R.id.bottom);
        ImageView icon2 =(ImageView)layoutInflater2.findViewById(R.id.icon);
        icon2.setBackgroundResource(R.drawable.lifestyles);
        bottom2.setText("Lifestyles");
        top2.setText("4");
        layoutInflater2.setBackgroundColor(Color.parseColor("#1AA2DF"));
        tabLayout.getTabAt(2).setCustomView(layoutInflater2);

        View layoutInflater3 =LayoutInflater.from(MainActivity1.this).inflate(R.layout.custom_tab_layout,null);
        TextView top3 = (TextView)layoutInflater3.findViewById(R.id.top);
        TextView bottom3 = (TextView)layoutInflater3.findViewById(R.id.bottom);
        ImageView icon3 =(ImageView)layoutInflater3.findViewById(R.id.icon);
        icon3.setBackgroundResource(R.drawable.foodadndrink);
        bottom3.setText("Food & Drinks");
        top3.setText("3");
        layoutInflater3.setBackgroundColor(Color.parseColor("#AA68B4"));
        tabLayout.getTabAt(3).setCustomView(layoutInflater3);

        View layoutInflater4 =LayoutInflater.from(MainActivity1.this).inflate(R.layout.custom_tab_layout,null);
        TextView top4 = (TextView)layoutInflater4.findViewById(R.id.top);
        TextView bottom4 = (TextView)layoutInflater4.findViewById(R.id.bottom);
        ImageView icon4 =(ImageView)layoutInflater4.findViewById(R.id.icon);
        icon4.setBackgroundResource(R.drawable.foodadndrink);
        bottom4.setText("Others");
        top4.setText("1");
        layoutInflater4.setBackgroundColor(Color.parseColor("#BD345E"));
        tabLayout.getTabAt(4).setCustomView(layoutInflater4);

/*
        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Workouts");
        tabTwo.setTextSize(10);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.workouts, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Lifestyles");
        tabThree.setTextSize(10);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lifestyles, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("Food & Drinks");
        tabFour.setTextSize(10);
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.foodadndrink, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);

        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFive.setText("Others");
        tabFive.setTextSize(10);
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.foodadndrink, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabFive);*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new SupplementFragment(), "ONE");
        adapter.addFrag(new SupplementFragment(), "TWO");
        adapter.addFrag(new SupplementFragment(), "THREE");
        adapter.addFrag(new SupplementFragment(), "Four");
        adapter.addFrag(new SupplementFragment(), "Five");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}