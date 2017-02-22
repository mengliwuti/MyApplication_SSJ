package zdsoft.myapplication_ssj.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.adapter.ZDAdapter;
import zdsoft.myapplication_ssj.bean.ZDBean;
import zdsoft.myapplication_ssj.bean.ZDSZBean;
import zdsoft.myapplication_ssj.databases.OPDatabase;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ZDActivity extends Activity implements View.OnClickListener{
    /**
     * state 1是支出  2是收入*/
    TextView zhichu,shouru;
    ImageView zd_img;
    TextView zd_tv;
    GridView gridView;
    Button zd_button;
    Button one,four,seven,kong,two,five,eight,zero,three,six,nine,dian,shan,jia,deng;
    List<ZDBean> listone;
    List<ZDBean> listtwo;
    ZDAdapter zdAdapter;
    TextView jinqian;
    Resources resources;
    List listnow;
    ZDSZBean zdszBean;
    String state;
    int img;
    String money;
    public static final int REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zd_layout_two);
        zdszBean=new ZDSZBean();
        into();
        listAdd();
        firstinto();
        state="1";
        money="";
        resources=getResources();
        //第一次进入支出为蓝色
        zhichu.setTextColor(resources.getColor(R.color.blue));
        shouru.setTextColor(resources.getColor(R.color.gray));
        zdAdapter=new ZDAdapter(listone,this);
        gridView.setAdapter(zdAdapter);
        /**
         * 图片初始值*/
        img=R.drawable.type_big_1;
    }
    private void into(){
        shouru= (TextView) findViewById(R.id.shouru);
        zhichu= (TextView) findViewById(R.id.zhichu);
        zd_img= (ImageView) findViewById(R.id.zd_img);
        zd_tv= (TextView) findViewById(R.id.zd_tv);
        gridView= (GridView) findViewById(R.id.gridview);
        zd_button= (Button) findViewById(R.id.zd_button);
        jinqian= (TextView) findViewById(R.id.jinqian);
        one= (Button) findViewById(R.id.one);
        two= (Button) findViewById(R.id.two);
        three= (Button) findViewById(R.id.three);
        four= (Button) findViewById(R.id.four);
        five= (Button) findViewById(R.id.five);
        six= (Button) findViewById(R.id.six);
        seven= (Button) findViewById(R.id.seven);
        eight= (Button) findViewById(R.id.eight);
        nine= (Button) findViewById(R.id.nine);
        zero= (Button) findViewById(R.id.zero);
        dian= (Button) findViewById(R.id.dian);
        kong= (Button) findViewById(R.id.kong);
        shan= (Button) findViewById(R.id.shan);
        jia= (Button) findViewById(R.id.jia);
        deng= (Button) findViewById(R.id.deng);
        listone=new ArrayList<ZDBean>();
        listtwo=new ArrayList<ZDBean>();
        listnow=new ArrayList();
        shouru.setOnClickListener(this);
        zhichu.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        dian.setOnClickListener(this);
        kong.setOnClickListener(this);
        shan.setOnClickListener(this);
        jia.setOnClickListener(this);
        deng.setOnClickListener(this);
        zd_button.setOnClickListener(this);


    }
    /**
     * 第一次进入图片*/
    private void firstinto(){
        zd_img.setImageResource(R.drawable.type_big_1);
        zd_tv.setText("一般");
    }
    private void listAdd(){
        listone.add(new ZDBean(R.drawable.type_big_1,"一般","1"));
        listone.add(new ZDBean(R.drawable.type_big_2,"用餐","1"));
        listone.add(new ZDBean(R.drawable.type_big_3,"零食","1"));
        listone.add(new ZDBean(R.drawable.type_big_4,"交通","1"));
        listone.add(new ZDBean(R.drawable.type_big_5,"充值","1"));
        listone.add(new ZDBean(R.drawable.type_big_6,"购物","1"));
        listone.add(new ZDBean(R.drawable.type_big_7,"娱乐","1"));
        listone.add(new ZDBean(R.drawable.type_big_8,"住房","1"));
        listone.add(new ZDBean(R.drawable.type_big_9,"约会","1"));
        listone.add(new ZDBean(R.drawable.type_big_10,"网购","1"));
        listone.add(new ZDBean(R.drawable.type_big_11,"日用品","1"));
        listone.add(new ZDBean(R.drawable.type_big_12,"香烟","1"));

        listtwo.add(new ZDBean(R.drawable.type_big_13,"工资","2"));
        listtwo.add(new ZDBean(R.drawable.type_big_14,"外快兼职","2"));
        listtwo.add(new ZDBean(R.drawable.type_big_15,"奖金","2"));
        listtwo.add(new ZDBean(R.drawable.type_big_16,"借入","2"));
        listtwo.add(new ZDBean(R.drawable.type_big_17,"零花钱","2"));
        listtwo.add(new ZDBean(R.drawable.type_big_18,"投资收入","2"));
        listtwo.add(new ZDBean(R.drawable.type_big_19,"礼物红包","2"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //收入，支出按钮
            case R.id.shouru:
                shouRu();
                clearIcon();
                shouru.setTextColor(resources.getColor(R.color.blue));
                zd_img.setImageResource(R.drawable.type_big_13);
                zd_tv.setText("工资");
                zdAdapter.setList(listtwo);
                state="2";
                img=R.drawable.type_big_13;
                listnow=listtwo;
                zdAdapter.notifyDataSetChanged();
                break;
            case R.id.zhichu:
                onResume();
                clearIcon();
                zhichu.setTextColor(resources.getColor(R.color.blue));
                zd_img.setImageResource(R.drawable.type_big_1);
                zd_tv.setText("一般");
                zdAdapter.setList(listone);
                state="1";
                img=R.drawable.type_big_1;
                listnow=listone;

                zdAdapter.notifyDataSetChanged();
                break;
            case R.id.zd_button:
                Intent intent=new Intent(ZDActivity.this,DHActivity.class);
                startActivityForResult(intent,REQUEST);
                break;
            case R.id.one:
                jinqian.append("1");
                break;
            case R.id.two:
                jinqian.append("2");
                break;
            case R.id.three:
                jinqian.append("3");
                break;
            case R.id.four:
                jinqian.append("4");
                break;
            case R.id.five:
                jinqian.append("5");
                break;
            case R.id.six:
                jinqian.append("6");
                break;
            case R.id.seven:
                jinqian.append("7");
                break;
            case R.id.eight:
                jinqian.append("8");
                break;
            case R.id.nine:
                jinqian.append("9");
                break;
            case R.id.zero:
                jinqian.append("0");
                break;
            case R.id.dian:
                if (!jinqian.getText().toString().contains(".")){
                    jinqian.append(".");
                }
                break;
            case R.id.kong:
                jinqian.setText("");
                break;
            case R.id.shan:
                try{
                    jinqian.setText(jinqian.getText().toString().substring(0,jinqian.length()-1));
                }catch (Exception e){
                }
                break;
            case R.id.jia:

                break;
            case R.id.deng:
                bCSJ();
                addmoney();
                jinqian.setText("");
                finish();
                break;
        }
    }
    /**
     * 字体颜色清空*/
    public void clearIcon(){
        zhichu.setTextColor(resources.getColor(R.color.gray));
        shouru.setTextColor(resources.getColor(R.color.gray));
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Integer integer[]=new Integer[]{R.drawable.type_big_1,R.drawable.type_big_2,R.drawable.type_big_3,R.drawable.type_big_4,
                R.drawable.type_big_5,R.drawable.type_big_6,R.drawable.type_big_7,R.drawable.type_big_8,
                R.drawable.type_big_9,R.drawable.type_big_10,R.drawable.type_big_11,R.drawable.type_big_12};
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (integer[position]){
                    case R.drawable.type_big_1:
                        zd_img.setImageResource(R.drawable.type_big_1);
                        img=R.drawable.type_big_1;
                        zd_tv.setText("一般");
                        break;
                    case R.drawable.type_big_2:
                        zd_img.setImageResource(R.drawable.type_big_2);
                        zd_tv.setText("用餐");
                        img=R.drawable.type_big_2;
                        break;
                    case R.drawable.type_big_3:
                        zd_img.setImageResource(R.drawable.type_big_3);
                        zd_tv.setText("零食");
                        img=R.drawable.type_big_3;
                        break;
                    case R.drawable.type_big_4:
                        zd_img.setImageResource(R.drawable.type_big_4);
                        zd_tv.setText("交通");
                        img=R.drawable.type_big_4;
                        break;
                    case R.drawable.type_big_5:
                        zd_img.setImageResource(R.drawable.type_big_5);
                        zd_tv.setText("充值");
                        img=R.drawable.type_big_5;
                        break;
                    case R.drawable.type_big_6:
                        zd_img.setImageResource(R.drawable.type_big_6);
                        zd_tv.setText("购物");
                        img=R.drawable.type_big_6;
                        break;
                    case R.drawable.type_big_7:
                        zd_img.setImageResource(R.drawable.type_big_7);
                        zd_tv.setText("娱乐");
                        img=R.drawable.type_big_7;
                        break;
                    case R.drawable.type_big_8:
                        zd_img.setImageResource(R.drawable.type_big_8);
                        zd_tv.setText("住房");
                        img=R.drawable.type_big_8;
                        break;
                    case R.drawable.type_big_9:
                        zd_img.setImageResource(R.drawable.type_big_9);
                        zd_tv.setText("约会");
                        img=R.drawable.type_big_9;
                        break;
                    case R.drawable.type_big_10:
                        zd_img.setImageResource(R.drawable.type_big_10);
                        zd_tv.setText("网购");
                        img=R.drawable.type_big_10;
                        break;
                    case R.drawable.type_big_11:
                        zd_img.setImageResource(R.drawable.type_big_11);
                        zd_tv.setText("日用品");
                        img=R.drawable.type_big_11;
                        break;
                    case R.drawable.type_big_12:
                        zd_img.setImageResource(R.drawable.type_big_12);
                        zd_tv.setText("香烟");
                        img=R.drawable.type_big_12;
                        break;
                }
            }
        });
    }
    /**gradview监听*/
    public void shouRu(){
        final Integer integer[]=new Integer[]{R.drawable.type_big_13,R.drawable.type_big_14,R.drawable.type_big_15,R.drawable.type_big_16,
                R.drawable.type_big_17,R.drawable.type_big_18,R.drawable.type_big_19};
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (integer[position]){
                    case R.drawable.type_big_13:
                        zd_img.setImageResource(R.drawable.type_big_13);
                        zd_tv.setText("工资");
                        img=R.drawable.type_big_13;
                        break;
                    case R.drawable.type_big_14:
                        zd_img.setImageResource(R.drawable.type_big_14);
                        zd_tv.setText("外快兼职");
                        img=R.drawable.type_big_14;
                        break;
                    case R.drawable.type_big_15:
                        zd_img.setImageResource(R.drawable.type_big_15);
                        zd_tv.setText("奖金");
                        img=R.drawable.type_big_15;
                        break;
                    case R.drawable.type_big_16:
                        zd_img.setImageResource(R.drawable.type_big_16);
                        zd_tv.setText("借入");
                        img=R.drawable.type_big_16;
                        break;
                    case R.drawable.type_big_17:
                        zd_img.setImageResource(R.drawable.type_big_17);
                        zd_tv.setText("零花钱");
                        img=R.drawable.type_big_17;
                        break;
                    case R.drawable.type_big_18:
                        zd_img.setImageResource(R.drawable.type_big_18);

                        img=R.drawable.type_big_18;
                        break;
                    case R.drawable.type_big_19:
                        zd_img.setImageResource(R.drawable.type_big_19);
                        zd_tv.setText("礼物红包");
                        img=R.drawable.type_big_19;
                        break;
                }
            }
        });
    }
    /**
     *ok键保存数据*/
    public void bCSJ(){
        Calendar now = Calendar.getInstance();
        int year= now.get(Calendar.YEAR);
        int month=now.get(Calendar.MONTH) + 1;
        int day=now.get(Calendar.DAY_OF_MONTH);
        int hour=now.get(Calendar.HOUR);
        int minute=now.get(Calendar.MINUTE);
        int second=now.get(Calendar.SECOND);
        if (jinqian.getText().equals("")){
            money="0";
        }else{
            money=jinqian.getText().toString();
        }
        String name=zd_tv.getText().toString();
//        int img=zd_img.getId();
        zdszBean.setTimes(year+"-"+month+"-"+day+"-"+hour+"-"+minute+"-"+second);
        zdszBean.setImage(img);
        zdszBean.setNumber(money);
        zdszBean.setNametype(name);
        zdszBean.setState(state);
        long lg=new OPDatabase(ZDActivity.this).ZDinsertUser(zdszBean);
        if(lg>0){
            Toast.makeText(ZDActivity.this,"数据保存成功",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ZDActivity.this,"数据保存失败",Toast.LENGTH_LONG).show();
        }
    }
    //第二页ok键
    /**
     * 账户金额数据库*/
    public void addmoney(){
        OPDatabase opDatabase=new OPDatabase(ZDActivity.this);
        String name= (String) zd_button.getText();
        if (name.equals("现金")){
            String mone=opDatabase.ZHgetAllData().get(1).getBalance();
            if(state.equals("1")){
                money=String.valueOf(Double.parseDouble(mone)-Double.parseDouble(money));
                opDatabase.zHUpdateXJ(money);
            }else if (state.equals("2")){
                money=String.valueOf(Double.parseDouble(mone)+Double.parseDouble(money));
                opDatabase.zHUpdateXJ(money);

            }
        }else if(name.equals("储蓄卡")){
            String mone=opDatabase.ZHgetAllData().get(2).getBalance();
            if (state.equals("1")){
                money=String.valueOf(Double.parseDouble(mone)-Double.parseDouble(money));
                opDatabase.zHUpdateZXK(money);
            }else if(state.equals("2")){
                money=String.valueOf(Double.parseDouble(mone)+Double.parseDouble(money));
                opDatabase.zHUpdateZXK(money);
            }

        }else if(name.equals("信用卡")){
            String mone=opDatabase.ZHgetAllData().get(3).getBalance();
            if (state.equals("1")){
                money=String.valueOf(Double.parseDouble(mone)-Double.parseDouble(money));
                opDatabase.zHUpdateXYK(money);
            }else if (state.equals("2")){
                money=String.valueOf(Double.parseDouble(mone)+Double.parseDouble(money));
                opDatabase.zHUpdateXYK(money);
            }
        }else if(name.equals("支付宝")){
            String mone=opDatabase.ZHgetAllData().get(4).getBalance();
            if (state.equals("1")){
                money=String.valueOf(Double.parseDouble(mone)-Double.parseDouble(money));
                opDatabase.zHUpdateWLZH(money);
            }else if(state.equals("2")){
                money=String.valueOf(Double.parseDouble(mone)+Double.parseDouble(money));
                opDatabase.zHUpdateWLZH(money);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null){
            return;
        }
        String name=data.getStringExtra("name");
        zd_button.setText(name);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
