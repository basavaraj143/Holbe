package com.appsriv.holbe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ExpandListAdapter ExpAdapter;
    private ArrayList<Group> ExpListItems;
    private ExpandableListView ExpandList;
    ArrayList<Group> list;
    private int flag[];
    public static TextView prof_name;
    String name[];
    String lineColour[];
    ArrayList<Workout> ch_list = null;
    private ViewPager viewPager;
    public static TextView top,top1,top2,top3,top4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        prof_name = (TextView)header.findViewById(R.id.name);
        TextView city =(TextView)header.findViewById(R.id.city);
        if (Login.details.size()!=0) {
            prof_name.setText(Login.details.get("userFirstName"));
            city.setText(Login.details.get("userCity"));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
           startActivity(new Intent(DrawerActivity.this,DrashBoardActivity.class));

        } else if (id == R.id.nav_gallery)
        {
            startActivity(new Intent(DrawerActivity.this,DrawerActivity.class));

        } else if (id == R.id.nav_slideshow)
        {
            startActivity(new Intent(DrawerActivity.this,ProfileActivity.class));

        } else if (id == R.id.nav_manage)
        {
            startActivity(new Intent(DrawerActivity.this,SettingActivity.class));

        } else if (id == R.id.nav_share)
        {
            startActivity(new Intent(DrawerActivity.this,SettingActivity.class));

        } else if (id == R.id.nav_send)
        {
            startActivity(new Intent(DrawerActivity.this,Splash.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setupViewPager(ViewPager viewPager)
    {
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

    private void setupTabIcons() {

        View layoutInflater = LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        top = (TextView)layoutInflater.findViewById(R.id.top);
        TextView bottom = (TextView)layoutInflater.findViewById(R.id.bottom);
        ImageView icon =(ImageView)layoutInflater.findViewById(R.id.icon);
        icon.setBackgroundResource(R.drawable.supplements);
        bottom.setText("Supplements");
        top.setText("3");
        top.setBackgroundResource(R.drawable.supplement_circle);
        layoutInflater.setBackgroundColor(Color.parseColor("#ABD14B"));
        tabLayout.getTabAt(0).setCustomView(layoutInflater);

        View layoutInflater1 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        top1 = (TextView)layoutInflater1.findViewById(R.id.top);
        TextView bottom1 = (TextView)layoutInflater1.findViewById(R.id.bottom);
        ImageView icon1 =(ImageView)layoutInflater1.findViewById(R.id.icon);
        icon1.setBackgroundResource(R.drawable.workouts);
        bottom1.setText("Workouts");
        bottom1.setTextSize(11);
        top1.setText("6");
        top1.setBackgroundResource(R.drawable.workout_circle);
        layoutInflater1.setBackgroundColor(Color.parseColor("#3CC3AF"));
        tabLayout.getTabAt(1).setCustomView(layoutInflater1);

        View layoutInflater2 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        top2 = (TextView)layoutInflater2.findViewById(R.id.top);
        TextView bottom2 = (TextView)layoutInflater2.findViewById(R.id.bottom);
        ImageView icon2 =(ImageView)layoutInflater2.findViewById(R.id.icon);
        icon2.setBackgroundResource(R.drawable.lifestyles);
        bottom2.setText("Lifestyles");
        top2.setText("4");
        bottom2.setTextSize(11);
        top2.setBackgroundResource(R.drawable.lifestyle_circle);
        layoutInflater2.setBackgroundColor(Color.parseColor("#1AA2DF"));
        tabLayout.getTabAt(2).setCustomView(layoutInflater2);

        View layoutInflater3 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        top3 = (TextView)layoutInflater3.findViewById(R.id.top);
        TextView bottom3 = (TextView)layoutInflater3.findViewById(R.id.bottom);
        ImageView icon3 =(ImageView)layoutInflater3.findViewById(R.id.icon);
        icon3.setBackgroundResource(R.drawable.foodadndrink);
        bottom3.setText("Food & Drinks");
        bottom3.setTextSize(11);
        top3.setText("3");
        top3.setBackgroundResource(R.drawable.foodanddrinks_circle);
        layoutInflater3.setBackgroundColor(Color.parseColor("#AA68B4"));
        tabLayout.getTabAt(3).setCustomView(layoutInflater3);

        View layoutInflater4 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        top4 = (TextView)layoutInflater4.findViewById(R.id.top);
        TextView bottom4 = (TextView)layoutInflater4.findViewById(R.id.bottom);
        ImageView icon4 =(ImageView)layoutInflater4.findViewById(R.id.icon);
        icon4.setBackgroundResource(R.drawable.foodadndrink);
        bottom4.setText("Others");
        bottom4.setTextSize(11);
        top4.setText("1");
        top4.setBackgroundResource(R.drawable.circle);
        layoutInflater4.setBackgroundColor(Color.parseColor("#BD345E"));
        tabLayout.getTabAt(4).setCustomView(layoutInflater4);


    }

/*    private void setupTabIcons() {

        View layoutInflater = LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        TextView top = (TextView)layoutInflater.findViewById(R.id.top);
        TextView bottom = (TextView)layoutInflater.findViewById(R.id.bottom);
        ImageView icon =(ImageView)layoutInflater.findViewById(R.id.icon);
        icon.setBackgroundResource(R.drawable.supplements);
        bottom.setText("Supplements");
        top.setText("3");
        top.setBackgroundResource(R.drawable.supplement_circle);
        layoutInflater.setBackgroundColor(Color.parseColor("#ABD14B"));
        tabLayout.getTabAt(0).setCustomView(layoutInflater);

        View layoutInflater1 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        TextView top1 = (TextView)layoutInflater1.findViewById(R.id.top);
        TextView bottom1 = (TextView)layoutInflater1.findViewById(R.id.bottom);
        ImageView icon1 =(ImageView)layoutInflater1.findViewById(R.id.icon);
        icon1.setBackgroundResource(R.drawable.workouts);
        bottom1.setText("Workouts");
        top1.setText("6");
        top1.setBackgroundResource(R.drawable.workout_circle);
        layoutInflater1.setBackgroundColor(Color.parseColor("#3CC3AF"));
        tabLayout.getTabAt(1).setCustomView(layoutInflater1);

        View layoutInflater2 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        TextView top2 = (TextView)layoutInflater2.findViewById(R.id.top);
        TextView bottom2 = (TextView)layoutInflater2.findViewById(R.id.bottom);
        ImageView icon2 =(ImageView)layoutInflater2.findViewById(R.id.icon);
        icon2.setBackgroundResource(R.drawable.lifestyles);
        bottom2.setText("Lifestyles");
        top2.setText("4");
        top2.setBackgroundResource(R.drawable.lifestyle_circle);
        layoutInflater2.setBackgroundColor(Color.parseColor("#1AA2DF"));
        tabLayout.getTabAt(2).setCustomView(layoutInflater2);

        View layoutInflater3 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        TextView top3 = (TextView)layoutInflater3.findViewById(R.id.top);
        TextView bottom3 = (TextView)layoutInflater3.findViewById(R.id.bottom);
        ImageView icon3 =(ImageView)layoutInflater3.findViewById(R.id.icon);
        icon3.setBackgroundResource(R.drawable.foodadndrink);
        bottom3.setText("Food & Drinks");
        top3.setText("3");
        top3.setBackgroundResource(R.drawable.foodanddrinks_circle);
        layoutInflater3.setBackgroundColor(Color.parseColor("#AA68B4"));
        tabLayout.getTabAt(3).setCustomView(layoutInflater3);

        View layoutInflater4 =LayoutInflater.from(DrawerActivity.this).inflate(R.layout.custom_tab_layout,null);
        TextView top4 = (TextView)layoutInflater4.findViewById(R.id.top);
        TextView bottom4 = (TextView)layoutInflater4.findViewById(R.id.bottom);
        ImageView icon4 =(ImageView)layoutInflater4.findViewById(R.id.icon);
        icon4.setBackgroundResource(R.drawable.foodadndrink);
        bottom4.setText("Others");
        top4.setText("1");
        top4.setBackgroundResource(R.drawable.circle);
        layoutInflater4.setBackgroundColor(Color.parseColor("#BD345E"));
        tabLayout.getTabAt(4).setCustomView(layoutInflater4);


    }*/


    public class AsyncHttpTask6 extends AsyncTask<String, Void, Integer>
    {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(DrawerActivity.this,"Please wait","Loading...");
        }

        @Override
        protected Integer doInBackground(String... params)
        {
            InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection urlConnection = null;

            try {
                /* forming th java.net.URL object */
                URL url6 = new URL(params[0]);

                urlConnection = (HttpURLConnection) url6.openConnection();

                /* for Get request */
                urlConnection.setRequestMethod("GET");

                int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode ==  200) {
                    System.out.println("Status code is:" + statusCode);
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    System.out.println("this is response" + response.toString());

                    parseResult(response.toString());



                    result = 1; // Successful

                }else{

                    result = 0; //"Failed to fetch data!"// ;
                    System.out.print("unable to fetch data");
                }

            } catch (Exception e) {


                //  Log.d(TAG, e.getLocalizedMessage());
            }
            finally {
                if (urlConnection!=null)
                {

                }
            }

            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result)
        {

            //mProgressDialog.dismiss();

            /* Download complete. Lets update UI */
            if (result == 1)
            {
                //Log.e(TAG, "fetch data!");

               /* adapter6 = new MyRecyclerAdapter6(SearchActivity.this,feedItemList6);
                HRecyclerView.setAdapter(adapter6);*/
                ExpAdapter= new ExpandListAdapter(DrawerActivity.this,list,ExpandList,flag);
                ExpandList.setAdapter(ExpAdapter);
                progressDialog.dismiss();
                ExpAdapter.notifyDataSetChanged();
                // Toast.makeText(getApplicationContext(), "your search results", Toast.LENGTH_LONG).show();
            } else {

                // Log.e(TAG, "Failed to fetch data!");
            }
        }
    }
    private void parseResult(String result)
    {
        list = new ArrayList<>();
        flag = new int[] { R.drawable.circle_suppliments, R.drawable.circle_workout, R.drawable.circle_heart, R.drawable.circlefoodanddrink,
                R.drawable.circle_others};
        name=new String[]{"Supplements","Workout","Lifestyle","Food & Drinks","Others"};

        lineColour = new String[]{"#ABD14B","#3CC3AF","#1AA2DF","#AA68B4","#BD345E"};

        try
        {

            JSONArray nearByBar = new JSONArray(result);

            if (nearByBar.length()!=0)
            {

                for (int j = 0; j <5; j++)
                {
                    Group gru = new Group();
                    gru.setName(name[j]);
                    gru.setIcon(flag[j]);
                    gru.setPercentage("91");

                    Workout ch = null;
                    ch_list = new ArrayList<Workout>();
                    //  JSONArray jArray = nearByBar.getJSONObject(j).getJSONArray("resLiqInfo");

                    for (int i = 2; i < nearByBar.length(); i++)
                    {
                        ch = new Workout();
                        ch.setWorkout_mapping_id(nearByBar.getJSONObject(i).getString("workout_mapping_id"));
                        ch.setReps(nearByBar.getJSONObject(i).getString("reps"));
                        ch.setSets(nearByBar.getJSONObject(i).getString("sets"));
                        ch.setWeight(nearByBar.getJSONObject(i).getString("weight"));
                        ch.setWorkout_name(nearByBar.getJSONObject(i).getString("workout_name"));
                        ch.setCompliance(nearByBar.getJSONObject(i).getString("compliance"));
                        ch.setColour(lineColour[j]);
                        ch_list.add(ch);
                    }
                    gru.setItems(ch_list);
                    list.add(gru);
                }

            }
            else
            {
                //showAlertDialog(BeerScreen.this, "Alert", "No Data Found Please Try other place", false);

            }

        } catch (JSONException j)
        {
            j.printStackTrace();
        }

    }
}
