package com.appsriv.holbe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by appsriv-02 on 21/4/16.
 */
public class CustomAdapter extends BaseAdapter implements android.widget.SectionIndexer
{

    Context ctx;
    ArrayList<String> array;
    CustomAdapter(Context ctx,ArrayList<String> array)
    {
        this.ctx=ctx;
        this.array=array;

    }

    @Override
    public int getCount()
    {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
}
