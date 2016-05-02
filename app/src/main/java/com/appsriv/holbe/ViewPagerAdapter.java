package com.appsriv.holbe;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    int[] flag;
    LayoutInflater inflater;

    String[] name;
    public ViewPagerAdapter(Context context, int[] flag,String[] name) {
        this.context = context;
        this.name=name;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return flag.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        ImageView imgflag;
        TextView typeName;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);


        imgflag = (ImageView) itemView.findViewById(R.id.flag);
        typeName = (TextView) itemView.findViewById(R.id.typename);
        // Capture position and set to the ImageView
        imgflag.setImageResource(flag[position]);
        typeName.setText(name[position]);
        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}