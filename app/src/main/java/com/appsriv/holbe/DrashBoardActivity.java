package com.appsriv.holbe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DrashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    TextView overalll_compliance,treatment_compliance,days_left,sup_compliance,work_compliance,life_compliance, food_compliance,other_compliance,this_week_compliance,last_week_compliance;
    int str_overalll_compliance, str_treatment_compliance, str_days_left, str_work_compliance, str_life_compliance,str_sup_compliance,
    str_food_compliance, str_other_compliance, str_this_week_compliance, str_last_week_compliance;
    ProgressBar supplement1, workout,lifestyle ,foodanddrink ,others, lastweek ,thisweek;
    Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_dash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getApplicationContext();
        mTracker = application.getDefaultTracker();


        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Dashboard screen")
                .setAction(" Dashboard screen")
                .setLabel("Dashboard screen")
                .build());


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        overalll_compliance = (TextView)findViewById(R.id.overalll_compliance);
        treatment_compliance = (TextView)findViewById(R.id.treatment_compliance);
        days_left = (TextView)findViewById(R.id.days_left);
        sup_compliance = (TextView)findViewById(R.id.sup_compliance);
        work_compliance = (TextView)findViewById(R.id.work_compliance);
        life_compliance = (TextView)findViewById(R.id.life_compliance);
        food_compliance = (TextView)findViewById(R.id.food_compliance);
        other_compliance = (TextView)findViewById(R.id.other_compliance);
        this_week_compliance = (TextView)findViewById(R.id.this_week_compliance);
        last_week_compliance = (TextView)findViewById(R.id.last_week_compliance);

         supplement1 = (ProgressBar)findViewById(R.id.supplement1);
         workout = (ProgressBar)findViewById(R.id.workout);
         lifestyle = (ProgressBar)findViewById(R.id.lifestyle);
         foodanddrink = (ProgressBar)findViewById(R.id.foodanddrink);
         others = (ProgressBar)findViewById(R.id.others);
        lastweek = (ProgressBar)findViewById(R.id.lastweek);
        thisweek = (ProgressBar)findViewById(R.id.thisweek);

        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
       TextView prof_name = (TextView)header.findViewById(R.id.name);

        TextView city =(TextView)header.findViewById(R.id.city);
        ImageView prof_pic = (ImageView)header.findViewById(R.id.prof_pic);
        if (Login.details.size()!=0) {
            prof_name.setText(Login.details.get("userFirstName"));
            city.setText(Login.details.get("userCity"));
           // Picasso.with(DrashBoardActivity.this).load("http://192.185.26.69/~holbe/api/patient/images/IMG_20160512_160617.jpg").into(prof_pic);
            UrlImageViewHelper.setUrlDrawable(prof_pic,Login.details.get("user_profile_picture"));
        }

        String url = "http://192.185.26.69/~holbe/api/patient/get_dashboard.php?id=1";
        new AsyncHttpTask6().execute(url);

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
    protected void onResume()
    {
        super.onResume();
        mTracker.setScreenName("Dashboard Screen ");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTracker.setScreenName("Dashboard Screen ");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.overview)
        {
            startActivity(new Intent(DrashBoardActivity.this,DrashBoardActivity.class));

        } else if (id == R.id.mytreatment)
        {
            startActivity(new Intent(DrashBoardActivity.this,DrawerActivity.class));

        } else if (id == R.id.profile)
        {
            startActivity(new Intent(DrashBoardActivity.this,ProfileActivity.class));

        } else if (id == R.id.setting)
        {
            startActivity(new Intent(DrashBoardActivity.this,SettingActivity.class));

        } else if (id == R.id.logout)
        {
            startActivity(new Intent(DrashBoardActivity.this,Splash.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class AsyncHttpTask6 extends AsyncTask<String, Void, Integer>
    {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(DrashBoardActivity.this,"Please wait","Loading...");
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

                if (progressDialog!=null&&progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                overalll_compliance.setText(""+str_overalll_compliance+"%");
                treatment_compliance.setText(""+str_treatment_compliance+"");
                days_left.setText(""+str_days_left +" Days left");
                sup_compliance.setText(""+str_sup_compliance+"%");
                work_compliance.setText(""+str_work_compliance+"%");
                life_compliance.setText(""+str_life_compliance+"%");
                food_compliance.setText(""+str_food_compliance+"%");
                other_compliance.setText(""+str_other_compliance+"%");
                this_week_compliance.setText(""+str_this_week_compliance+"%");
                last_week_compliance.setText(""+str_last_week_compliance+"%");

                supplement1.setProgress(str_sup_compliance);
                workout.setProgress(str_work_compliance);
                lifestyle.setProgress(str_life_compliance);
                foodanddrink.setProgress(str_food_compliance);
                others.setProgress(str_other_compliance);
                lastweek.setProgress(str_last_week_compliance);
                thisweek.setProgress(str_this_week_compliance);

            } else
            {

                // Log.e(TAG, "Failed to fetch data!");
            }
        }
    }
    private void parseResult(String result)
    {

        try
        {

            JSONArray nearByBar = new JSONArray(result);

            if (nearByBar.length()!=0)
            {

                str_work_compliance=nearByBar.getJSONObject(0).getInt("workout_compliance");
                str_sup_compliance=nearByBar.getJSONObject(0).getInt("supplement_compliance");
                str_life_compliance= nearByBar.getJSONObject(0).getInt("lifestyle_compliance");
                str_food_compliance = nearByBar.getJSONObject(0).getInt("food_compliance");
                str_other_compliance = nearByBar.getJSONObject(0).getInt("others_compliance");
                str_overalll_compliance = nearByBar.getJSONObject(0).getInt("overall_compliance");
                str_this_week_compliance = nearByBar.getJSONObject(0).getInt("current_week_compliance");
                str_days_left =  nearByBar.getJSONObject(0).getInt("days_left");
                str_last_week_compliance = nearByBar.getJSONObject(0).getInt("last_week_compliance");
                str_treatment_compliance = nearByBar.getJSONObject(0).getInt("treatment_count");
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
