package com.appsriv.holbe.StickyHeader;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.appsriv.holbe.Food;
import com.appsriv.holbe.LifeStyle;
import com.appsriv.holbe.Others;
import com.appsriv.holbe.R;
import com.appsriv.holbe.Supplement;
import com.appsriv.holbe.Workout;

public class DemoActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        HeaderListView list = new HeaderListView(this);

        list.setAdapter(new SectionAdapter()
        {

            @Override
            public int numberOfSections() {
                return 5;
            }

            @Override
            public int numberOfRows(int section) {
                return 5;
            }

            @Override
            public Object getRowItem(int section, int row)
            {
                return null;
            }

            @Override
            public boolean hasSectionHeaderView(int section) {
                return true;
            }

            @Override
            public View getRowView(int section, int row, View convertView, ViewGroup parent)
            {
                Workout workout=null;
                Supplement supplement=null;
                LifeStyle style=null;
                Food food=null;
                Others others=null;
                if (convertView == null)
                {
                 //   convertView = (TextView) getLayoutInflater().inflate(getResources().getLayout(android.R.layout.simple_list_item_1), null);
                    LayoutInflater infalInflater = (LayoutInflater) DemoActivity.this.getSystemService(DemoActivity.this.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.child_item, null);
                }
                //((TextView) convertView).setText("Section " + section + " Row " + row);

                return convertView;
            }

            @Override
            public int getSectionHeaderViewTypeCount() {
                return 2;
            }

            @Override
            public int getSectionHeaderItemViewType(int section) {
                return section % 2;
            }

            @Override
            public View getSectionHeaderView(int section, View convertView, ViewGroup parent)
            {

                if (convertView == null)
                {
                    if (getSectionHeaderItemViewType(section) == 0)
                    {
                        LayoutInflater inf = (LayoutInflater) DemoActivity.this.getSystemService(DemoActivity.this.LAYOUT_INFLATER_SERVICE);
                        convertView = inf.inflate(R.layout.group_item, null);
                    } else
                    {
                        LayoutInflater inf = (LayoutInflater) DemoActivity.this.getSystemService(DemoActivity.this.LAYOUT_INFLATER_SERVICE);
                        convertView = inf.inflate(R.layout.group_item, null);
                    }
                }

                if (getSectionHeaderItemViewType(section) == 0)
                {
                   // ((TextView) convertView).setText("Header for section " + section);
                } else
                {
                   // ((TextView) convertView.findViewById(android.R.id.text1)).setText("Header for section " + section);
                    //((TextView) convertView.findViewById(android.R.id.text1)).setText("Has a detail text field "+section);
                }

                switch (section)
                {
                case 0:
                    convertView.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                    break;
                case 1:
                    convertView.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                    break;
                case 2:
                     convertView.setBackgroundColor(Color.parseColor("#ff99cc00"));
                    break;
                case 3:
                    convertView.setBackgroundColor(Color.parseColor("#ffffbb33"));
                    break;
                }
                return convertView;
            }

            @Override
            public void onRowItemClick(AdapterView<?> parent, View view, int section, int row, long id)
            {
                super.onRowItemClick(parent, view, section, row, id);
                Toast.makeText(DemoActivity.this, "Section " + section + " Row " + row, Toast.LENGTH_SHORT).show();
            }
        });
        setContentView(list);
    }
}
