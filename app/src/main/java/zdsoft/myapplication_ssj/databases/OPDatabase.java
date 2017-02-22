package zdsoft.myapplication_ssj.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import zdsoft.myapplication_ssj.bean.Account;
import zdsoft.myapplication_ssj.bean.Bean;
import zdsoft.myapplication_ssj.bean.ZDSZBean;

/**
 * Created by Administrator on 2016/11/11.
 */
public class OPDatabase {
    SQLiteDatabase db;
    MyDataBases myDataBases;
    ContentValues contentValues;
    public  OPDatabase(Context context){
        myDataBases=new MyDataBases(context);
        contentValues=new ContentValues();
        db=myDataBases.getReadableDatabase();
    }
    //记事簿添加
    public long JSPinsertUser(Bean bean){
        ContentValues contentValues=new ContentValues();
        contentValues.put("times",bean.getTimes());
        contentValues.put("title",bean.getTitle());
        contentValues.put("content",bean.getContent());
        long lg=db.insert("notes",null,contentValues);
        contentValues.clear();
        return lg;
    }
    //记事簿查询所有
    public List<Bean> JSPgetAllData(){
        List<Bean> list=new ArrayList<Bean>();
        Cursor cursor=db.query("notes",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Bean bean=new Bean();
            bean.setTimes(cursor.getString(cursor.getColumnIndex("times")));
            bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            bean.setContent(cursor.getString(cursor.getColumnIndex("content")));
            list.add(bean);
        }
        return list;
    }
    //记事簿数据删除
    public long JSPdelete(String times){
        long lg=db.delete("notes","times=?",new String[]{times});
        return lg;
    }
    //账单数据添加
    public long ZDinsertUser(ZDSZBean zdszBean){
        ContentValues contentValues=new ContentValues();
        contentValues.put("image",zdszBean.getImage());
        contentValues.put("times",zdszBean.getTimes());
        contentValues.put("number",zdszBean.getNumber());
        contentValues.put("nametype",zdszBean.getNametype());
        contentValues.put("state",zdszBean.getState());
        long lg=db.insert("bills",null,contentValues);
        contentValues.clear();
        return lg;
    }

    //账单查询所有
    public List<ZDSZBean> ZDSZgetAllDate(){
        List<ZDSZBean> list=new ArrayList<ZDSZBean>();
        Cursor cursor=db.query("bills",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            ZDSZBean zdszBean=new ZDSZBean();
            zdszBean.setImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("image"))));
            zdszBean.setTimes(cursor.getString(cursor.getColumnIndex("times")));
            zdszBean.setNametype(cursor.getString(cursor.getColumnIndex("nametype")));
            zdszBean.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            zdszBean.setState(cursor.getString(cursor.getColumnIndex("state")));
            list.add(zdszBean);
        }
        return list;
    }
//    /**账单数据修改*/
//    public long zDUpdate(String count,String time){
//        contentValues.put("number",count);
//        long lg=db.update("bills",contentValues,"time=?",new String[]{time});
//        contentValues.clear();
//        return lg;
//    }

    //账户属性添加
    public void zhaddName(){
        contentValues.put("name","all");
        contentValues.put("balance","0");
        db.insert("account_type",null,contentValues);
        contentValues.clear();
        contentValues.put("name","cash");
        contentValues.put("balance","0");
        db.insert("account_type",null,contentValues);
        contentValues.clear();
        contentValues.put("name","deposit");
        contentValues.put("balance","0");
        db.insert("account_type",null,contentValues);
        contentValues.clear();
        contentValues.put("name","credit");
        contentValues.put("balance","0");
        db.insert("account_type",null,contentValues);
        contentValues.clear();
        contentValues.put("name","inter");
        contentValues.put("balance","0");
        db.insert("account_type",null,contentValues);
        contentValues.clear();

    }
    /**
     * 账户类型查询所有*/
    public List<Account> ZHgetAllData(){
        SQLiteDatabase db=myDataBases.getReadableDatabase();
        List<Account> list=new ArrayList<Account>();
        Cursor cursor=db.query("account_type",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            Account account=new Account();
            account.setName(cursor.getString(cursor.getColumnIndex("name")));
            account.setBalance(cursor.getString(cursor.getColumnIndex("balance")));
            list.add(account);
        }
        return list;
    }

    /**账户数据修改*/
    public long zHUpdateXJ(String count){
        contentValues.put("balance",count);
        long lg=db.update("account_type",contentValues,"name=?",new String[]{"cash"});
        contentValues.clear();
        return lg;
    }
    public long zHUpdateZXK(String count){
        contentValues.put("balance",count);
        long lg=db.update("account_type",contentValues,"name=?",new String[]{"deposit"});
        contentValues.clear();
        return lg;
    }
    public long zHUpdateXYK(String count){
        contentValues.put("balance",count);
        long lg=db.update("account_type",contentValues,"name=?",new String[]{"credit"});
        contentValues.clear();
        return lg;
    }
    public long zHUpdateWLZH(String count){
        contentValues.put("balance",count);
        long lg=db.update("account_type",contentValues,"name=?",new String[]{"inter"});
        contentValues.clear();
        return lg;
    }
    public long zHUpdateAll(String count){
        contentValues.put("balance",count);
        long lg=db.update("account_type",contentValues,"name=?",new String[]{"all"});
        contentValues.clear();
        return lg;
    }

}
//查询所有数据
//    public List<ZDSZBean> getAllData(){
//        SQLiteDatabase db=myDataBases.getReadableDatabase();
//        List<ZDSZBean> list=new ArrayList<ZDSZBean>();
//        Cursor cursor=db.query("ADD_BILLS",null,null,null,null,null,null);
//        while (cursor.moveToNext()){
//            ZDSZBean zdszBean=new ZDSZBean();
//            zdszBean.setSname(cursor.getString(cursor.getColumnIndex("name")));
//            list.add(zdszBean);
//        }
//        return list;
//    }
//查询单个
//    public ZDSZBean getUser(String id){
//        SQLiteDatabase db=myDataBases.getReadableDatabase();
//        ZDSZBean zdszBean=new ZDSZBean();
//        Cursor cursor=db.query("user",null,"id=?",new String[]{id},null,null,null);
//        if(cursor.moveToFirst()){
//            zdszBean.setSname(cursor.getString(cursor.getColumnIndex("name")));
//        }
//        return zdszBean;
//    }
