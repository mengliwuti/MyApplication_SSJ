package zdsoft.myapplication_ssj;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import zdsoft.myapplication_ssj.databases.MyDataBases;

/**
 * Created by Administrator on 2016/11/18.
 */
public class NotesProvider extends ContentProvider{
    static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //操作所有
        matcher.addURI("zdsoft.myapplication_ssj.notesprovide","notes",1);
        //根据某一属性操作
        matcher.addURI("zdsoft.myapplication_ssj.notesprovide","notes/#",2);
    }
    MyDataBases dataBases;
    SQLiteDatabase db;
    Cursor cursor=null;

    @Override
    public boolean onCreate() {
        dataBases=new MyDataBases(this.getContext());
        db=dataBases.getReadableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (matcher.match(uri)){
            // TODO: 2016/11/19 ??
            case 1:
                //查询所有
                cursor=db.query("notes",null,null,null,null,null,null);
                return cursor;
            //标题查询
            case 2:
                cursor=db.query("notes",null,selection,selectionArgs,null,null,null);
                return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)){
            case 1:
                long rowid=db.insert("notes",null,values);
                return ContentUris.withAppendedId(uri,rowid);
            case 2:
                return null;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (matcher.match(uri)){
            case 1:
                int it=db.delete("notes",null,null);
                return it;
            case 2:
                int bb=db.delete("notes",selection,selectionArgs);
                return bb;
            default:
                return 0;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (matcher.match(uri)){
            case 1:
                return 0;
            case 2:
                int aa=db.update("notes",values,selection,selectionArgs);
                // TODO: 2016/11/19 sql修改 
                //db.execSQL();
                return aa;
        }
        return 0;
    }
}
