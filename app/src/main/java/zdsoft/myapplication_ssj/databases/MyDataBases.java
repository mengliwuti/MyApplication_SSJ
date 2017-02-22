package zdsoft.myapplication_ssj.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/9.
 */
public class MyDataBases extends SQLiteOpenHelper{
    //账户类型建表
    private static final String  ADD_ACCOUNT_TYPE= "create table account_type("+ "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "name text,"+"balance text);";
    //记事簿建表
    private static final String ADD_NOTES="create table notes ("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"times varchar(20),"+"title varchar(3999),"+"content varchar(3999));";
    //账单表
    private static final String ADD_BILLS="create table bills ("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"image varchar(20),"+"times varchar(20),"+"number varchar(20),"+"nametype varchar(30),"+"state varchar(30));";
    public MyDataBases(Context context) {
        super(context, "mydbtest.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table user(username varchar(20),password varchar(20))");
        db.execSQL(ADD_ACCOUNT_TYPE);
        db.execSQL(ADD_NOTES);
        db.execSQL(ADD_BILLS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
