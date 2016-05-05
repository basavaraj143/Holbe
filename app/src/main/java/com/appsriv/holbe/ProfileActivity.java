package com.appsriv.holbe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_prof);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

       navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView prof_name = (TextView)header.findViewById(R.id.name);
        TextView city =(TextView)header.findViewById(R.id.city);
        if (Login.details.size()!=0) {
            prof_name.setText(Login.details.get("userFirstName"));
            city.setText(Login.details.get("userCity"));
        }
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(Login.details.get("userFirstName"));
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            startActivity(new Intent(ProfileActivity.this,DrashBoardActivity.class));

        } else if (id == R.id.nav_gallery)
        {
            startActivity(new Intent(ProfileActivity.this,DrawerActivity.class));

        } else if (id == R.id.nav_slideshow)
        {
            startActivity(new Intent(ProfileActivity.this,ProfileActivity.class));

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(ProfileActivity.this,SettingActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(ProfileActivity.this,SettingActivity.class));
        } else if (id == R.id.nav_send)
        {
            startActivity(new Intent(ProfileActivity.this,Splash.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
