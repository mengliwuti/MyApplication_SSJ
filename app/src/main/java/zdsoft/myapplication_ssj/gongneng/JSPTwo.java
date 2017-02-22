package zdsoft.myapplication_ssj.gongneng;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.SSJActivity;
import zdsoft.myapplication_ssj.adapter.JSPAdapter;
import zdsoft.myapplication_ssj.bean.Bean;
import zdsoft.myapplication_ssj.databases.MyDataBases;
import zdsoft.myapplication_ssj.databases.OPDatabase;
import zdsoft.myapplication_ssj.fragment.JSPFragment;

/**
 * Created by Administrator on 2016/11/8.
 */
public class JSPTwo extends Activity implements View.OnClickListener{
    ImageView queding_jsp,shanchu_jsp,shijian_jsp;
    EditText biaoti,zhenwen;
    Bean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsp_layout_two);
        into();
    }
    /**
     * 实例化控件*/
    public void into(){
        bean=new Bean();
        queding_jsp= (ImageView) findViewById(R.id.queding_jsp);
        shanchu_jsp= (ImageView) findViewById(R.id.shanchu_jsp);
        shijian_jsp= (ImageView) findViewById(R.id.shijian_jsp);
        biaoti= (EditText) findViewById(R.id.biaoti);
        zhenwen= (EditText) findViewById(R.id.zhenwen);
        queding_jsp.setOnClickListener(this);
        shanchu_jsp.setOnClickListener(this);
        shijian_jsp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.queding_jsp:
                add();
                tiao();
                break;
            case R.id.shanchu_jsp:
                shanchu();
                break;
            case R.id.shijian_jsp:
                break;
        }
    }
    /**
     * 删除*/
    public void shanchu(){
        zhenwen.setText("");
    }
    /**
     * 获得时间*/
    public void add(){
        Calendar now = Calendar.getInstance();
        int year= now.get(Calendar.YEAR);
        int month=now.get(Calendar.MONTH) + 1;
        int day=now.get(Calendar.DAY_OF_MONTH);
        int hour=now.get(Calendar.HOUR);
        int minute=now.get(Calendar.MINUTE);
        int second=now.get(Calendar.SECOND);
        String title=biaoti.getText().toString();
        String content=zhenwen.getText().toString();
        bean=new Bean();
        bean.setTimes(year+"年"+month+"月"+day+"日"+hour+"时"+minute+"分"+second+"秒");
        bean.setTitle(title);
        bean.setContent(content);
        new OPDatabase(JSPTwo.this).JSPinsertUser(bean);
    }
    private void tiao(){
        Intent intent=new Intent(JSPTwo.this, SSJActivity.class);
        startActivity(intent);
        finish();
    }
}
