package com.example.nikhil.notebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nikhil on 29/7/17.
 */

public class NoteAdapter extends ArrayAdapter<Note>{

    Context context;
    int resource;

    ArrayList<Note> noteList,tempList;
    public NoteAdapter(Context context, int resource, ArrayList<Note> objects) {
        super(context, resource, objects);
        this.context = context ;
        this.resource = resource;
        noteList = objects ;
        tempList = new ArrayList<>();
        tempList.addAll(noteList);


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView txtName = (TextView)view.findViewById(R.id.textViewName);
        ;

        Note note = noteList.get(position);
        txtName.setText(note.getId()+" - "+ note.getName());


        return view;
    }
    public void filter(String str){

        noteList.clear();

        if(str.length()==0){
            noteList.addAll(tempList);
        }else{
            for(int i=0;i<tempList.size();i++){
                if(tempList.get(i).getName().toLowerCase().contains(str.toLowerCase())){
                    noteList.add(tempList.get(i));
                }
            }
        }


        notifyDataSetChanged();
    }
}
