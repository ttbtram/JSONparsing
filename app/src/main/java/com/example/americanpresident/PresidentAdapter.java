package com.example.americanpresident;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PresidentAdapter extends ArrayAdapter<PresidentModel> {
    public PresidentAdapter(Context context, int textViewResourceId){
        super(context,textViewResourceId);
    }
    public PresidentAdapter(Context context, int resource,List<PresidentModel> items){
        super(context,resource,items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view =convertView;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.activity_customlistview,null);

        }
        PresidentModel p =getItem(position);
        if(p!=null){
            TextView txtName =(TextView) view.findViewById(R.id.textViewtitle);
            txtName.setText(p.name);
            TextView txtDescription=(TextView) view.findViewById(R.id.textViewDescription);
            txtDescription.setText(p.period);
            ImageView imageView=view.findViewById(R.id.imageView2);
            Picasso.get().load(p.img).into(imageView);
        }
        return view;
    }
}


