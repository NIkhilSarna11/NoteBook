package com.example.nikhil.notebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 29/7/17.
 */

public class UserAdapter extends ArrayAdapter<User>{

    Context context;
    int resource;

    ArrayList<User> userList,tempList;
    public UserAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context ;
        this.resource = resource;
        userList = objects ;
        tempList = new ArrayList<>();
        tempList.addAll(userList);


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView txtName = (TextView)view.findViewById(R.id.textViewName);
        ;

        User user = userList.get(position);
        txtName.setText(user.getId()+" - "+user.getName());


        return view;
    }
    public void filter(String str){

        userList.clear();

        if(str.length()==0){
            userList.addAll(tempList);
        }else{
            for(int i=0;i<tempList.size();i++){
                if(tempList.get(i).getName().toLowerCase().contains(str.toLowerCase())){
                    userList.add(tempList.get(i));
                }
            }
        }


        notifyDataSetChanged();
    }
}
