package com.example.abhie.marvelherovolley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by abhie on 3/1/18.
 */

public class ListViewAdapter extends ArrayAdapter<Hero> {

    private ArrayList<Hero> arrayList;
    private Context context;
    private ImageLoader imageLoader;
    ShimmerFrameLayout layout;

    public ListViewAdapter(ArrayList<Hero> arrayList, Context context) {
        super(context, R.layout.onerow, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    // this method will return list item
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listViewItem = layoutInflater.inflate(R.layout.onerow, null, true);

        TextView txtName = listViewItem.findViewById(R.id.textViewName);
        TextView txtImageUrl = listViewItem.findViewById(R.id.textViewUrl);
        layout= (ShimmerFrameLayout) listViewItem.findViewById(R.id.shimmerlayout);
        NetworkImageView imageView=listViewItem.findViewById(R.id.imageView);
//        ImageView imageView=listViewItem.findViewById(R.id.imageView);
//        container.setDuration(5000);
        layout.startShimmerAnimation();
        Hero hero = arrayList.get(position);
//        int len= arrayList.size();

        imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(hero.getImageUrl(),ImageLoader.getImageListener(imageView,R.drawable.grey,R.drawable.grey));

        imageView.setImageUrl(hero.getImageUrl(),imageLoader);
        txtName.setText(hero.getName());
        txtImageUrl.setText(hero.getImageUrl());

//        if(position==len) {
//        layout.stopShimmerAnimation();
//        }

        return listViewItem;

        //return super.getView(position, convertView, parent);
    }
}
