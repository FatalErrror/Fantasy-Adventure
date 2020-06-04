package com.GoodGame2020.FantasyAdventure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelAdapter extends ArrayAdapter<Level> {

    public LevelAdapter(Context context, Level[] arr) {
        super(context, R.layout.listitem, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Level level = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.Name)).setText(level.Name);
// Выбираем картинку для месяца
        Bitmap src = BitmapFactory.decodeFile(getContext().getFilesDir() + "/"+level.Name+"image.png");
        if (src!=null) ((ImageView) convertView.findViewById(R.id.PreImage)).setImageBitmap(src);
        else ((ImageView) convertView.findViewById(R.id.PreImage)).setImageResource(R.drawable.image);

        return convertView;
    }

}

