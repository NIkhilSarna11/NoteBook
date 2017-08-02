package com.example.nikhil.notebook;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllUserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView listView ;
    ContentResolver resolver ;
    ArrayList<User> userList ;
    UserAdapter adapter ;
    User user;
    int pos;
    void initViews()
    {

        listView = (ListView)findViewById(R.id.activityListView);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);
        initViews();
        resolver = getContentResolver();
        retrievUser();

    }
    void retrievUser()
    {
        String[] projection = { Util.COL_ID , Util.COL_NAME , Util.COL_DESCRIPTION
        };
        Cursor cursor  = resolver.query(Util.USER_URI , projection , null,null,null);
        if(cursor!=null)
        {
            userList = new ArrayList<User>();
            int id = 0 ;
            String n="" , d = "" ;
            while(cursor.moveToNext())
            {
                id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                n = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                d = cursor.getString(cursor.getColumnIndex(Util.COL_DESCRIPTION));
                userList.add(new User(id,n,d));

            }
            adapter = new UserAdapter(this,R.layout.list_item,userList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }
    }
    void showUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(user.getName());
        builder.setMessage(user.toString());
        builder.setPositiveButton("Done",null);
        builder.create().show();
    }
    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete: "+user.getName());
        builder.setMessage("Are you Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteUser();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    void deleteUser(){

        String where = Util.COL_ID+" = "+user.getId();
        //String where = Util.COL_ID+" = '"+user.getName()+"'";

        int i = resolver.delete(Util.USER_URI,where,null);

        if(i>0){
            userList.remove(pos);
            adapter.notifyDataSetChanged();
            Toast.makeText(this,user.getName()+" deleted... ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,user.getName()+" not deleted... ", Toast.LENGTH_LONG).show();
        }

    }
    void showOptions(){

        String[] items = {"View User","Delete User","Update User"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i){
                    case 0:
                        showUser();
                        break;

                    case 1:
                        askForDeletion();
                        break;

                    case 2:
                        Intent intent = new Intent(AllUserActivity.this,NoteDetailsActivity.class);
                        intent.putExtra(Util.KEY_USER,user);
                        startActivity(intent);
                        break;
                }

            }
        });
        builder.create().show();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        pos = i;
        user = userList.get(i);
        showOptions();
    }
}
