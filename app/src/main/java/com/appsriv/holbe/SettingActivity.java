package com.appsriv.holbe;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SettingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText fname,lname,phone,email,dob,address;
    String message;
    ImageView prof_pic;
    Tracker mTracker;
    private static final int CAMERA_REQUEST = 1888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getApplicationContext();
        mTracker = application.getDefaultTracker();


        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Setting screen")
                .setAction(" Setting screen")
                .setLabel("Setting screen")
                .build());


        TextView save = (TextView)toolbar.findViewById(R.id.save);
        fname =(EditText)findViewById(R.id.fname);
        lname =(EditText)findViewById(R.id.lname);
        phone =(EditText)findViewById(R.id.phone);
        email =(EditText)findViewById(R.id.email);
        dob =(EditText)findViewById(R.id.dob);
        address =(EditText)findViewById(R.id.address);
        prof_pic = (ImageView)findViewById(R.id.prof_picture);
        UrlImageViewHelper.setUrlDrawable(prof_pic,Login.details.get("user_profile_picture"));
        if (Login.details.size()!=0)
        {
            /*details.put("userId",userId);
                details.put("userFirstName",userFirstName);
                details.put("userCity",userCity);
                details.put("userPhoneNo",userPhoneNo);
                details.put("userAddress",userAddress);
                details.put("userDob",userDob);
                details.put("userEmailAddress",userEmailAddress);
                details.put("userLastName",userLastName);*/

            fname.setText(Login.details.get("userFirstName"));
            lname.setText(Login.details.get("userLastName"));
            phone.setText(Login.details.get("userPhoneNo"));
            email.setText(Login.details.get("userEmailAddress"));
            dob.setText(Login.details.get("userDob"));
            address.setText(Login.details.get("userAddress"));
        }

        ImageView camera = (ImageView)findViewById(R.id.camera);
       // prof_pic = (ImageView)findViewById(R.id.prof_pic);
        camera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (fname.getText().toString().isEmpty())
                {
                    fname.setError("Please Enter Name");
                }
                else if (lname.getText().toString().isEmpty())
                {
                    lname.setError("Please Enter Last Name");
                }
                else if (phone.getText().toString().isEmpty())
                {
                    phone.setError("Please Enter Phone Number");
                }
                else if (email.getText().toString().isEmpty())
                {
                    email.setError("Please Enter Email");
                }
                else if (dob.getText().toString().isEmpty())
                {
                    dob.setError("Please Enter DOB");
                }
                else if (address.getText().toString().isEmpty())
                {
                    email.setError("Please Enter Address");
                }

                else
                {
                    String firstName = fname.getText().toString();
                    String lastName = lname.getText().toString();
                    String phoneNum = phone.getText().toString();
                    String emailId = email.getText().toString();
                    String DOB = dob.getText().toString();
                    String add = address.getText().toString();


                    new SettingTask().execute(firstName,lastName,phoneNum,add,Login.details.get("userId"));


                }

            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        TextView prof_name = (TextView)header.findViewById(R.id.name);
        ImageView prof_pic = (ImageView)header.findViewById(R.id.prof_pic);
        TextView city =(TextView)header.findViewById(R.id.city);
        if (Login.details.size()!=0) {
            prof_name.setText(Login.details.get("userFirstName"));
            //Picasso.with(DrawerActivity.this).load("http://192.185.26.69/~holbe/api/patient/images/IMG_20160512_160617.jpg").into(prof_pic);
            UrlImageViewHelper.setUrlDrawable(prof_pic,Login.details.get("user_profile_picture"));
            // city.setText(Login.details.get("userCity"));
        }
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        mTracker.setScreenName("Setting Screen ");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTracker.setScreenName("Setting Screen ");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //
        // noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.overview)
        {
            startActivity(new Intent(SettingActivity.this,DrashBoardActivity.class));

        } else if (id == R.id.mytreatment)
        {
            startActivity(new Intent(SettingActivity.this,DrawerActivity.class));

        } else if (id == R.id.profile)
        {
            startActivity(new Intent(SettingActivity.this,ProfileActivity.class));

        } else if (id == R.id.setting)
        {
            startActivity(new Intent(SettingActivity.this,SettingActivity.class));

        } else if (id == R.id.logout)
        {
            startActivity(new Intent(SettingActivity.this,Splash.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
           // prof_pic = (ImageView)findViewById(R.id.prof_picture);
            prof_pic.setImageBitmap(photo);
        }
    }

    private class SettingTask extends AsyncTask<String, Void, String>
    {
        HttpURLConnection urlConnection;
        StringBuilder sb = new StringBuilder();

           /*
    fname
    lname
    phone
    address
    id
        */

        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                JSONObject object = new JSONObject();
                object.put("fname",params[0]);
                object.put("lname",params[1]);
                object.put("phone",params[2]);
                object.put("address",params[3]);
                object.put("id",params[4]);
                URL url = new URL("http://192.185.26.69/~holbe/api/patient/updateuserdetails.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Content-Type","application/json");
                //  urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
                urlConnection.connect();
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(object.toString());
                out.close();

                int HttpResult =urlConnection.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(),"utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    JSONObject mainObject = new JSONObject(sb.toString());
                    message = mainObject.getString("message");
                    return line;

                }
                else
                {
                    System.out.println(urlConnection.getResponseMessage());
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            catch (IOException e) {

                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally{
                if(urlConnection!=null)
                    urlConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(SettingActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }

}
