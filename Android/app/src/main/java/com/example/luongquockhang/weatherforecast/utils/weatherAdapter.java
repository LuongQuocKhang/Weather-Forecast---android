package com.example.luongquockhang.weatherforecast.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongquockhang.weatherforecast.Model.DailyWeather;
import com.example.luongquockhang.weatherforecast.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Luong Quoc Khang on 12/7/2017.
 */

public class weatherAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DailyWeather> weatherlist;
    public weatherAdapter(Context context, int layout, List<DailyWeather> weatherlist) {
        this.context = context;
        this.layout = layout;
        this.weatherlist = weatherlist;
    }

    @Override
    public int getCount() {
        return weatherlist.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout,null);

        // ánh xạ view

        ImageView weatherIcon = (ImageView)convertView.findViewById(R.id.imageView);
        TextView datetime = (TextView)convertView.findViewById(R.id.txtDatetime);
        TextView Desciption = (TextView)convertView.findViewById(R.id.txtDesciption);
        TextView MaxTemp = (TextView)convertView.findViewById(R.id.txtMaxTemp);
        TextView MinTemp = (TextView)convertView.findViewById(R.id.txtMinTemp);
        TextView Temp = (TextView)convertView.findViewById(R.id.txtTemp);
        TextView Humidity = (TextView)convertView.findViewById(R.id.txtHumidity);

        DailyWeather item = weatherlist.get(position);
        weatherIcon.setImageBitmap(item.getWeatherIcon());
        Desciption.setText(item.getDesciption());
        MaxTemp.setText(String.valueOf((int)item.getMaxTemp()) + "°C");
        MinTemp.setText(String.valueOf((int)item.getMinTemp()) + "°C");
        Humidity.setText(String.valueOf((int)item.getHumidity()) + "%");
        Temp.setText(String.valueOf((int)item.getTemp()) + "°C");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(item.getDatetime()*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("DD - MM - YYYY");
        datetime.setText(sdf.format(calendar.getTime()));
        
        return convertView;
    }
}
