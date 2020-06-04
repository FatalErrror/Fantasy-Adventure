package com.GoodGame2020.FantasyAdventure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, News[] arr) {
        super(context, R.layout.listitem, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final News news = getItem(position);

        if (news.ImageURL.equals(""))
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, null);
        else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_image_item, null);
            ImageView imageView = (ImageView) (convertView.findViewById(R.id.Image));
            Picasso.get().load(news.ImageURL).into(imageView);
        }
        ((TextView) convertView.findViewById(R.id.Name)).setText(news.name);
        ((TextView) convertView.findViewById(R.id.Body)).setText(news.body);
        return convertView;
    }

}
