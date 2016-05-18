package com.appsriv.holbe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class CominUpWithListview extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private CustomListAdapter adapter;
    private ExpandListAdapterForComingUpListview comingUpListview;
    String items[];
    int flag[];
    int background[];
    String lineColour[];
    String[] time;
    String[] typeExc;
    int childPostion, groupPostion;
    Tracker mTracker;
    DrawerLayout drawerLayout;
    View drawerView;
    // TextView textPrompt, textPrompt2;
    ListView drawerList;
    // TextView textSelection;
    CustomListDrawer customListDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.comingup_drawer);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            groupPostion = bundle.getInt("groupPosition");
            childPostion = bundle.getInt("childPosition");
        }

        GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getApplicationContext();
        mTracker = application.getDefaultTracker();


        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Comingup screen")
                .setAction(" Comingup screen")
                .setLabel("Comingup screen")
                .build());

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        //ListView listView =(ListView)findViewById(R.id.listview);
        ExpandableListView listview =(ExpandableListView) findViewById(R.id.exp_list);
        items=new String[]{"Supplement","Workout","Lifestyle","Food & Drink","Others"};
        time=new String[]{"1:30 PM","3:45 PM","5:15 PM","6:30 PM","8:00 PM"};

        lineColour = new String[]{"#ABD14B","#3CC3AF","#1AA2DF","#AA68B4","#BD345E"};

        flag = new int[] { R.drawable.supplements, R.drawable.workouts, R.drawable.lifestyles, R.drawable.foodadndrink,
                R.drawable.others};
        background = new int[] { R.drawable.circle_suppliments, R.drawable.circle_workout, R.drawable.circle_heart, R.drawable.circlefoodanddrink,
                R.drawable.circle_others};
        typeExc= new String[]{"SITUPS","AVACADO SHAKE","VITAMIN C","VITAMIN A","VITAMIN D"};
      //  adapter = new CustomListAdapter(CominUpWithListview.this, items,time,flag,typeExc,background,lineColour,SupplementFragment.list,groupPostion,childPostion);
        comingUpListview = new ExpandListAdapterForComingUpListview(CominUpWithListview.this, items,time,flag,typeExc,background,lineColour,SupplementFragment.list,groupPostion,childPostion,listview);
        listview.setAdapter(comingUpListview);

        ImageView buttonOpenDrawer = (ImageView)findViewById(R.id.id);


        buttonOpenDrawer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                drawerLayout.openDrawer(drawerView);
            }});
        drawerLayout.setDrawerListener(myDrawerListener);

        drawerView.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return true;
            }
        });
        drawerList = (ListView)findViewById(R.id.drawerlist);
        String names[]={"Overview","My Treatment","Profile","Coming Up","","","Settings","Logout"};
        Integer[] img={R.drawable.overview,R.drawable.treatment,R.drawable.profile,R.drawable.comingupwhite,0,0,R.drawable.settting,R.drawable.logout};
        customListDrawer = new CustomListDrawer(CominUpWithListview.this,names,img,"#ABD14B");

        TextView prof_name = (TextView)findViewById(R.id.name);
        TextView city =(TextView)findViewById(R.id.city);
        ImageView prof_pic = (ImageView)findViewById(R.id.prof_pic);
        if (Login.details.size()!=0)
        {
            prof_name.setText(Login.details.get("userFirstName"));
            city.setText(Login.details.get("userCity"));
            UrlImageViewHelper.setUrlDrawable(prof_pic,Login.details.get("user_profile_picture"));
        }


        //  arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dayOfWeek);
        drawerList.setAdapter(customListDrawer);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView selected_item = (TextView) view.findViewById(R.id.selected_item);
                ImageView icon = (ImageView) view.findViewById(R.id.icon);

                if (position==0)
                {
                    selected_item.setBackgroundColor(Color.parseColor("#ABD14B"));
                    icon.setBackgroundResource(R.drawable.overview);
                    startActivity(new Intent(CominUpWithListview.this,Main2Activity.class));


                } else if (position==1)
                {
                    selected_item.setBackgroundColor(Color.parseColor("#ABD14B"));
                    icon.setBackgroundResource(R.drawable.calendarblue);
                    startActivity(new Intent(CominUpWithListview.this,DrawerActivity.class));


                } else if (position==2)
                {
                    icon.setBackgroundResource(R.drawable.userblue);
                    selected_item.setBackgroundColor(Color.parseColor("#ABD14B"));
                    startActivity(new Intent(CominUpWithListview.this,ProfileActivity.class));


                }
                else if (position==3)
                {
                    selected_item.setBackgroundColor(Color.parseColor("#ABD14B"));
                    icon.setBackgroundResource(R.drawable.comingupblue);
                    startActivity(new Intent(CominUpWithListview.this,CominUpWithListview.class));

                }

                else if (position==6)
                {
                    selected_item.setBackgroundColor(Color.parseColor("#ABD14B"));
                    icon.setBackgroundResource(R.drawable.settingsblue);
                    startActivity(new Intent(CominUpWithListview.this,SettingActivity.class));


                } else if (position==7)
                {
                    selected_item.setBackgroundColor(Color.parseColor("#ABD14B"));
                    icon.setBackgroundResource(R.drawable.logoff);
                    startActivity(new Intent(CominUpWithListview.this,Splash.class));

                }
            }});

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // toolbar.setBackgroundColor(Color.TRANSPARENT);


       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       // drawer.setDrawerListener(toggle);
        //toggle.syncState();

        //NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
       // View header=navigationView.getHeaderView(0);

/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
       // TextView prof_name = (TextView)header.findViewById(R.id.name);
       /* TextView city =(TextView)header.findViewById(R.id.city);
        if (Login.details.size()!=0)
        {
           // prof_name.setText(Login.details.get("userFirstName"));
           // city.setText(Login.details.get("userCity"));
            TextView name = (TextView)header.findViewById(R.id.name);
            ImageView prof_pic = (ImageView)header.findViewById(R.id.prof_pic);
            //Picasso.with(CominUpWithListview.this).load("http://192.185.26.69/~holbe/api/patient/images/IMG_20160512_160617.jpg").into(prof_pic);
            String  s = Login.details.get("user_profile_picture");
            //UrlImageViewHelper.setUrlDrawable(prof_pic,Login.details.get("user_profile_picture"));
            name.setText(Login.details.get("userFirstName"));
        }*/

    }
    DrawerLayout.DrawerListener myDrawerListener = new DrawerLayout.DrawerListener(){

        @Override
        public void onDrawerClosed(View drawerView) {
            // textPrompt.setText("onDrawerClosed");
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            // textPrompt.setText("onDrawerOpened");
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            // textPrompt.setText("onDrawerSlide: " + String.format("%.2f", slideOffset));
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            String state;
            switch(newState){
                case DrawerLayout.STATE_IDLE:
                    state = "STATE_IDLE";
                    break;
                case DrawerLayout.STATE_DRAGGING:
                    state = "STATE_DRAGGING";
                    break;
                case DrawerLayout.STATE_SETTLING:
                    state = "STATE_SETTLING";
                    break;
                default:
                    state = "unknown!";
            }

            // textPrompt2.setText(state);
        }
    };

    @Override
    protected void onResume()
    {
        super.onResume();
        mTracker.setScreenName("Coming Up Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTracker.setScreenName("Coming Up Screen");
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

        if (id == R.id.overview)
        {
            startActivity(new Intent(CominUpWithListview.this,Main2Activity.class));

        } else if (id == R.id.mytreatment)
        {
            startActivity(new Intent(CominUpWithListview.this,DrawerActivity.class));

        } else if (id == R.id.profile)
        {
            startActivity(new Intent(CominUpWithListview.this,ProfileActivity.class));

        }
        else if (id == R.id.comingup)
        {
            startActivity(new Intent(CominUpWithListview.this,CominUpWithListview.class));
        }

        else if (id == R.id.setting)
        {
            startActivity(new Intent(CominUpWithListview.this,SettingActivity.class));

        } else if (id == R.id.logout)
        {
            startActivity(new Intent(CominUpWithListview.this,Splash.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
