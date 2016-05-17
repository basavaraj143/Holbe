package com.appsriv.holbe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ExpandListAdapterForComingUpListview extends BaseExpandableListAdapter implements SectionIndexer, AbsListView.OnScrollListener {
    private boolean manualScroll;
    private Context context;
    private ArrayList<Group> groups= new ArrayList<>();
    ExpandableListView expandableListView ;
    int[] progressBarRes;
    int pos=0;
    private Activity activity;
    private LayoutInflater inflater;
    private String[] Items;
    private String[] time;
    private int[] icons;
    private int[] background;
    private String[] excName;
    private String[] lineColour;
    private ArrayList<Group> list;
    private ImageView[] complete = new ImageView[1000];
    private ImageView[] partial = new ImageView[1000];
    //int childPostion, groupPostion;


    public ExpandListAdapterForComingUpListview(Activity activity, String[] Items,String[] time,int [] icons,String[] excName,int [] background,String [] lineColour,ArrayList<Group> list,
                                                int groupPostion, int childPostion,ExpandableListView expandableListView) {
        this.expandableListView = expandableListView;
        this.context = activity;
        this.Items = Items;
        this.time=time;
        this.icons=icons;
        this.excName=excName;
        this.background=background;
        this.lineColour = lineColour;
        this.groups = list;
        /*this.groupPostion = groupPostion;
        this.childPostion = childPostion;*/

    }


    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.manualScroll = scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;
    }

    @Override
    public void onScroll(AbsListView view,
                         int firstVisibleItem,
                         int visibleItemCount,
                         int totalItemCount) {
    }

    @Override
    public int getPositionForSection(int section) {
        if (manualScroll) {
            return section;
        } else {
            return expandableListView.getFlatListPosition(
                    ExpandableListView.getPackedPositionForGroup(section));
        }
    }

    // Gets called when scrolling the list manually
    @Override
    public int getSectionForPosition(int position) {
        return ExpandableListView.getPackedPositionGroup(
                expandableListView.getExpandableListPosition(position));
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = 0;


        if (groupPosition == 0)
        {
            ArrayList<Supplement> chList = groups.get(groupPosition).getSup_Items();
            size = chList.size();

        } else if (groupPosition == 1)
        {
            ArrayList<Workout> chList = groups.get(groupPosition).getItems();
            size = chList.size();


        } else if (groupPosition == 2) {
            ArrayList<LifeStyle> chList = groups.get(groupPosition).getLife_Items();
            size = chList.size();
        } else if (groupPosition == 3) {
            ArrayList<Food> chList = groups.get(groupPosition).getFood_Items();
            size = chList.size();
        } else if (groupPosition == 4) {
            ArrayList<Others> chList = groups.get(groupPosition).getOther_Items();
            size = chList.size();
        }
        return size;
    }



    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }


    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        ArrayList<Workout> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    public Object getChild1(int groupPosition, int childPosition)
    {

        ArrayList<Supplement> chList = groups.get(groupPosition).getSup_Items();
        return chList.get(childPosition);
    }

    public Object getChild5(int groupPosition, int childPosition)
    {

        ArrayList<Treatment> chList = groups.get(groupPosition).getTreatments();
        return chList.get(childPosition);
    }
    public Object getChild2(int groupPosition, int childPosition)
    {

        ArrayList<LifeStyle> chList = groups.get(groupPosition).getLife_Items();
        return chList.get(childPosition);
    }
    public Object getChild3(int groupPosition, int childPosition)
    {
        ArrayList<Food> chList = groups.get(groupPosition).getFood_Items();
        return chList.get(childPosition);
    }
    public Object getChild4(int groupPosition, int childPosition)
    {
        ArrayList<Others> chList = groups.get(groupPosition).getOther_Items();
        return chList.get(childPosition);
    }



    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent)
    {
        Workout workout=null;
        Supplement supplement=null;
        LifeStyle style=null;
        Food food=null;
        Others others=null;
        Treatment treatment = null;

         if (groupPosition==0)
        {
            supplement = (Supplement) getChild1(groupPosition,childPosition);
        }
        else if (groupPosition==1)
        {
            workout = (Workout) getChild(groupPosition, childPosition);
        }
        else if (groupPosition==2)
        {
            style = (LifeStyle) getChild2(groupPosition,childPosition);
        }
        else if (groupPosition==3)
        {
            food = (Food)getChild3(groupPosition,childPosition);
        }
        else if (groupPosition==4)
        {
            others = (Others) getChild4(groupPosition,childPosition);
        }


        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.mylist_items, null);
        }

        TextView type =(TextView) convertView.findViewById(R.id.type);
        TextView repsandsitups = (TextView)convertView.findViewById(R.id.repsandsitups);
        TextView time1 =(TextView) convertView.findViewById(R.id.time);
        TextView time2 =(TextView) convertView.findViewById(R.id.time1);
        TextView name =(TextView) convertView.findViewById(R.id.name);
        complete[childPosition]=(ImageView)convertView.findViewById(R.id.complete);
        partial[childPosition]=(ImageView)convertView.findViewById(R.id.partial);



        ImageView icon1 =(ImageView)convertView.findViewById(R.id.icon);
        View line=(View)convertView.findViewById(R.id.line);
        line.setBackgroundColor(Color.parseColor(lineColour[groupPosition]));
        time1.setText(time[groupPosition]);
        icon1.setBackgroundResource(background[groupPosition]);
        //name.setText(excName[position]);
        type.setText(Items[groupPosition]);

        if (groupPosition==0)
        {

            name.setText(supplement.getSupplement_name());
            repsandsitups.setText(supplement.getRepitition());
            time2.setText(supplement.getWhen_time());
            if (groups.get(groupPosition).getSup_Items().get(childPosition).getInt_compliance()==50)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
            }
            else if (groups.get(groupPosition).getSup_Items().get(childPosition).getInt_compliance()==100)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                partial[childPosition].setBackgroundResource(R.drawable.partialgray);
            }

        }
        else if (groupPosition==1)
        {
            name.setText(workout.getWorkout_name());
            repsandsitups.setText(workout.getSets()+" of" + workout.getReps());
            time2.setText(workout.getWeight());

            if (groups.get(groupPosition).getItems().get(childPosition).getInt_compliance()==50)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
            }
            else if (groups.get(groupPosition).getItems().get(childPosition).getInt_compliance()==100)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                partial[childPosition].setBackgroundResource(R.drawable.partialgray);
            }


        }
        else if (groupPosition==2)
        {
            name.setText(style.getLifestyle_name());
            repsandsitups.setText(style.getRepitition());
            time2.setText(style.getWhen());

            if (groups.get(groupPosition).getLife_Items().get(childPosition).getInt_compliance()==50)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
            }
            else if (groups.get(groupPosition).getLife_Items().get(childPosition).getInt_compliance()==100)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                partial[childPosition].setBackgroundResource(R.drawable.partialgray);
            }


        }
        else if (groupPosition==3)
        {
            name.setText(food.getFood_name());
            repsandsitups.setText(food.getWhen());
            time2.setText(food.getWhen());

            if (groups.get(groupPosition).getFood_Items().get(childPosition).getInt_compliance()==50)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
            }
            else if (groups.get(groupPosition).getFood_Items().get(childPosition).getInt_compliance()==100)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                partial[childPosition].setBackgroundResource(R.drawable.partialgray);
            }


        }
        else if (groupPosition==4)
        {
            name.setText(others.getOthers_name());
            repsandsitups.setText(others.getDuration());
            time2.setText(others.getDuration());


            if (groups.get(groupPosition).getOther_Items().get(childPosition).getInt_compliance()==50)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
            }
            else if (groups.get(groupPosition).getOther_Items().get(childPosition).getInt_compliance()==100)
            {
                complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                partial[childPosition].setBackgroundResource(R.drawable.partialgray);
            }

        }

        complete[childPosition].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                if (groupPosition==0)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgray);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=1&id="+groups.get(groupPosition).getSup_Items().get(childPosition).getSupplement_mapping_id()+"&treatment=supplement";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==1)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgray);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=1&id="+groups.get(groupPosition).getItems().get(childPosition).getWorkout_mapping_id()+"&treatment=workout";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==2)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgray);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=1&id="+groups.get(groupPosition).getLife_Items().get(childPosition).getLifestyle_mapping_id()+"&treatment=lifestyle";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==3)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgray);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=1&id="+groups.get(groupPosition).getFood_Items().get(childPosition).getFood_mapping_id()+"&treatment=food";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==4)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedbtn);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgray);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=1&id="+groups.get(groupPosition).getOther_Items().get(childPosition).getOthers_mapping_id()+"&treatment=others";
                    new AsyncHttpTask().execute(url);

                }

            }
        });

        partial[childPosition].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (groupPosition==0)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=0.5&id="+groups.get(groupPosition).getSup_Items().get(childPosition).getSupplement_mapping_id()+"&treatment=supplement";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==1)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=0.5&id="+groups.get(groupPosition).getItems().get(childPosition).getWorkout_mapping_id()+"&treatment=workout";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==2)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=0.5&id="+groups.get(groupPosition).getLife_Items().get(childPosition).getLifestyle_mapping_id()+"&treatment=lifestyle";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==3)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
                    // http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=0.1&id=1&treatment=workout
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=0.5&id="+groups.get(groupPosition).getFood_Items().get(childPosition).getFood_mapping_id()+"&treatment=food";
                    new AsyncHttpTask().execute(url);

                }
                else if (groupPosition==4)
                {
                    complete[childPosition].setBackgroundResource(R.drawable.completedgrey);
                    partial[childPosition].setBackgroundResource(R.drawable.partialgreen);
                    final String url =  "http://192.185.26.69/~holbe/api/patient/updatecompliance.php?completion=0.5&id="+groups.get(groupPosition).getOther_Items().get(childPosition).getOthers_mapping_id()+"&treatment=others";
                    new AsyncHttpTask().execute(url);

                }

            }
        });


        return convertView;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);

    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }


    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public int getChildTypeCount() {
        return super.getChildTypeCount();
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.dummy, null);
        }
        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        mExpandableListView.expandGroup(groupPosition);
        if (isExpanded)
        {

        }
        /*ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
        icon.setBackgroundResource(group.getIcon());
        TextView type = (TextView)convertView.findViewById(R.id.type);
        type.setText(group.getName());
        type.setTextColor(Color.parseColor(groups.get(groupPosition).getItems().get(groupPosition).getColour()));
        TextView compliance_percentage = (TextView)convertView.findViewById(R.id.compliance_percentage);
        if (groupPosition==0)
        {
            compliance_percentage.setText(""+group.getWorkout_compliance());
        }
        else if (groupPosition==1)
        {
            compliance_percentage.setText(""+group.getSupplement_compliance());
        }
        else if (groupPosition==2)
        {
            compliance_percentage.setText(""+group.getLifestyle_compliance());
        }
        else if (groupPosition==3)
        {
            compliance_percentage.setText(""+group.getFood_compliance());
        }
        else if (groupPosition==4)
        {
            compliance_percentage.setText(""+group.getOthers_compliance());
        }
*/
        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public class AsyncHttpTask extends AsyncTask<String, Void, Integer>
    {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(context,"Please wait","Loading...");
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


            if (result == 1)
            {
                if (progressDialog!=null&&progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
            } else {

                // Log.e(TAG, "Failed to fetch data!");
            }
        }
    }
    private void parseResult(String result)
    {

        //{"status":200,"message":"Update successful"}
        try {
            JSONObject object = new JSONObject(result);
            String statu= object.getString("status");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

}



