package com.example.nikhil.notebook;

import android.net.Uri;

/**
 * Created by nikhil on 27/7/17.
 */

public class Util {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "cpdemo.db";
    public static final String TAB_NAME = "Users";
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String CREATE_TAB_QUERY = "create table Users(" +
            "_ID integer primary key autoincrement," +
            "NAME text," +
            "DESCRIPTION text" +
            ")";
    public static final Uri USER_URI = Uri.parse("content://com.nikhil.cpdemo.userAuthority/"+TAB_NAME);
    public static final String KEY_USER = "keyUser";


}
