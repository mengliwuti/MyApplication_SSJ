package zdsoft.myapplication_ssj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.bean.ZDBean;
import zdsoft.myapplication_ssj.databases.OPDatabase;

/**
 * Created by Administrator on 2016/11/12.
 */
public class ZHzhuanActivity extends Activity implements View.OnClickListener{
    Button zc,zr;
    TextView jiner,je_tv;
    Button one_zh,four_zh,seven_zh,kong_zh,two_zh,five_zh,eight_zh,zero_zh,three_zh,six_zh,nine_zh,dian_zh,shan_zh,jia_zh,deng_zh;
    Intent intent;
    public static final int REQUESTone=1;
    public static final int REQUESTtwo=2;
    String name;
    //计算器金额
    String money;
    //数据库金额
    String xianmoney,zhumoney,xingmoney,wangmoney;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zhuan_layout);
        into();
    }
    public void into(){
        zc= (Button) findViewById(R.id.zc);
        zr= (Button) findViewById(R.id.zr);
        jiner= (TextView) findViewById(R.id.jiner);
        je_tv= (TextView) findViewById(R.id.je_tv);
        one_zh= (Button) findViewById(R.id.one_zh);
        two_zh= (Button) findViewById(R.id.two_zh);
        three_zh= (Button) findViewById(R.id.three_zh);
        four_zh= (Button) findViewById(R.id.four_zh);
        five_zh= (Button) findViewById(R.id.five_zh);
        six_zh= (Button) findViewById(R.id.six_zh);
        seven_zh= (Button) findViewById(R.id.seven_zh);
        eight_zh= (Button) findViewById(R.id.eight_zh);
        nine_zh= (Button) findViewById(R.id.nine_zh);
        zero_zh= (Button) findViewById(R.id.zero_zh);
        dian_zh= (Button) findViewById(R.id.dian_zh);
        kong_zh= (Button) findViewById(R.id.kong_zh);
        shan_zh= (Button) findViewById(R.id.shan_zh);
        jia_zh= (Button) findViewById(R.id.jia_zh);
        deng_zh= (Button) findViewById(R.id.deng_zh);
        one_zh.setOnClickListener(this);
        two_zh.setOnClickListener(this);
        three_zh.setOnClickListener(this);
        four_zh.setOnClickListener(this);
        five_zh.setOnClickListener(this);
        six_zh.setOnClickListener(this);
        seven_zh.setOnClickListener(this);
        eight_zh.setOnClickListener(this);
        nine_zh.setOnClickListener(this);
        zero_zh.setOnClickListener(this);
        dian_zh.setOnClickListener(this);
        kong_zh.setOnClickListener(this);
        shan_zh.setOnClickListener(this);
        jia_zh.setOnClickListener(this);
        deng_zh.setOnClickListener(this);
        zc.setOnClickListener(this);
        zr.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zc:
                intent=new Intent(ZHzhuanActivity.this,DHActivity.class);
                startActivityForResult(intent,REQUESTone);
                break;
            case R.id.zr:
                intent=new Intent(ZHzhuanActivity.this,DHActivity.class);
                startActivityForResult(intent,REQUESTtwo);
                break;
            case R.id.one_zh:
                je_tv.append("1");
                break;
            case R.id.two_zh:
                je_tv.append("2");
                break;
            case R.id.three_zh:
                je_tv.append("3");
                break;
            case R.id.four_zh:
                je_tv.append("4");
                break;
            case R.id.five_zh:
                je_tv.append("5");
                break;
            case R.id.six_zh:
                je_tv.append("6");
                break;
            case R.id.seven_zh:
                je_tv.append("7");
                break;
            case R.id.eight_zh:
                je_tv.append("8");
                break;
            case R.id.nine_zh:
                je_tv.append("9");
                break;
            case R.id.zero_zh:
                je_tv.append("0");
                break;
            case R.id.dian_zh:
                if (!je_tv.getText().toString().contains(".")){
                    je_tv.append(".");
                }
                break;
            case R.id.kong_zh:
                je_tv.setText("");
                break;
            case R.id.shan_zh:
                try{
                    je_tv.setText(je_tv.getText().toString().substring(0,je_tv.length()-1));
                }catch (Exception e){
                }
                break;
            case R.id.jia_zh:
                break;
            case R.id.deng_zh:
                update();
                finish();
                break;
        }
    }


    /**
     * 接受intent对象*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null){
            return;
        }

        name=data.getStringExtra("name");
        if (requestCode==1){
            zc.setText(name);
        }else if(requestCode==2){
            zr.setText(name);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 数据库修改*/
    public void update(){
        money=je_tv.getText().toString();

        String zcname=zc.getText().toString();
        String zrname=zr.getText().toString();
        if (zcname.equals("转出账户")||zrname.equals("转入账户")){
            Toast.makeText(ZHzhuanActivity.this,"请选择账户",Toast.LENGTH_SHORT).show();
        }else{
            OPDatabase opDatabase=new OPDatabase(ZHzhuanActivity.this);
            //数据库金钱
            xianmoney=opDatabase.ZHgetAllData().get(1).getBalance();
            zhumoney=opDatabase.ZHgetAllData().get(2).getBalance();
            xingmoney=opDatabase.ZHgetAllData().get(3).getBalance();
            wangmoney=opDatabase.ZHgetAllData().get(4).getBalance();
            if (zcname.equals("现金")&&zrname.equals("储蓄卡")){
                String xianmon=String.valueOf(Double.parseDouble(xianmoney)-Double.parseDouble(money));
                String zxkmon=String.valueOf(Double.parseDouble(zhumoney)+Double.parseDouble(money));
                opDatabase.zHUpdateXJ(xianmon);
                opDatabase.zHUpdateZXK(zxkmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("现金")&&zrname.equals("信用卡")){
                String xianmon=String.valueOf(Double.parseDouble(xianmoney)-Double.parseDouble(money));
                String xykmon=String.valueOf(Double.parseDouble(xingmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateXJ(xianmon);
                opDatabase.zHUpdateXYK(xykmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("现金")&&zrname.equals("支付宝")){
                String xianmon=String.valueOf(Double.parseDouble(xianmoney)-Double.parseDouble(money));
                String wlmon=String.valueOf(Double.parseDouble(wangmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateXJ(xianmon);
                opDatabase.zHUpdateWLZH(wlmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("储蓄卡")&&zrname.equals("现金")){
                String zxkmon=String.valueOf(Double.parseDouble(zhumoney)-Double.parseDouble(money));
                String xianmon=String.valueOf(Double.parseDouble(xianmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateZXK(zxkmon);
                opDatabase.zHUpdateXJ(xianmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("储蓄卡")&&zrname.equals("信用卡")){
                String zxkmon=String.valueOf(Double.parseDouble(zhumoney)-Double.parseDouble(money));
                String xingmon=String.valueOf(Double.parseDouble(xingmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateZXK(zxkmon);
                opDatabase.zHUpdateXYK(xingmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("储蓄卡")&&zrname.equals("支付宝")){
                String zxknmon=String.valueOf(Double.parseDouble(zhumoney)-Double.parseDouble(money));
                String wlmon=String.valueOf(Double.parseDouble(wangmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateZXK(zxknmon);
                opDatabase.zHUpdateWLZH(wlmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("信用卡")&&zrname.equals("现金")){
                String xykmon=String.valueOf(Double.parseDouble(xingmoney)-Double.parseDouble(money));
                String xjmon=String.valueOf(Double.parseDouble(xianmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateXYK(xykmon);
                opDatabase.zHUpdateXJ(xjmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("信用卡")&&zrname.equals("储蓄卡")){
                String xykmon=String.valueOf(Double.parseDouble(xingmoney)-Double.parseDouble(money));
                String zxkmon=String.valueOf(Double.parseDouble(zhumoney)+Double.parseDouble(money));
                opDatabase.zHUpdateXYK(xykmon);
                opDatabase.zHUpdateZXK(zxkmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("信用卡")&&zrname.equals("支付宝")){
                String xykmon=String.valueOf(Double.parseDouble(xingmoney)-Double.parseDouble(money));
                String wlmon=String.valueOf(Double.parseDouble(wangmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateXYK(xykmon);
                opDatabase.zHUpdateWLZH(wlmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("支付宝")&&zrname.equals("现金")){
                String wlkmon=String.valueOf(Double.parseDouble(wangmoney)-Double.parseDouble(money));
                String xjmon=String.valueOf(Double.parseDouble(xianmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateWLZH(wlkmon);
                opDatabase.zHUpdateXJ(xjmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("支付宝")&&zrname.equals("储蓄卡")){
                String wlkmon=String.valueOf(Double.parseDouble(wangmoney)-Double.parseDouble(money));
                String zxkmon=String.valueOf(Double.parseDouble(zhumoney)+Double.parseDouble(money));
                opDatabase.zHUpdateWLZH(wlkmon);
                opDatabase.zHUpdateXJ(zxkmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }else if(zcname.equals("支付宝")&&zrname.equals("信用卡")){
                String wlkmon=String.valueOf(Double.parseDouble(wangmoney)-Double.parseDouble(money));
                String xykmon=String.valueOf(Double.parseDouble(xingmoney)+Double.parseDouble(money));
                opDatabase.zHUpdateWLZH(wlkmon);
                opDatabase.zHUpdateXJ(xykmon);
                Toast.makeText(ZHzhuanActivity.this,"转账成功",Toast.LENGTH_SHORT).show();
            }



        }
    }
}
