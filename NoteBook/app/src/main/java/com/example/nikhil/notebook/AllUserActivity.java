package com.example.nikhil.notebook;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AllUserActivity extends AppCompatActivity {
ListView listView ;
    ContentResolver resolver ;
    ArrayList<User> userList ;
    UserAdapter adapter ;
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
        resolver.query(Util.USER_URI , projection , null,null,null);
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

        }


    }
}
