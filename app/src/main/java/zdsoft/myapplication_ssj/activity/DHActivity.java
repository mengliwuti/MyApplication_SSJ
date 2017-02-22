package zdsoft.myapplication_ssj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.adapter.DHAdapter;

/**
 * Created by Administrator on 2016/11/15.
 */
public class DHActivity extends Activity{
    Intent intent;
    ListView zh_listview;
    DHAdapter dhAdapter;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donghua_zdlayout);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        into();
        addlist();
        dhAdapter=new DHAdapter(DHActivity.this,list);
        zh_listview.setAdapter(dhAdapter);
        intent=new Intent(DHActivity.this,ZHzhuanActivity.class);
    }
    /**
     * 实例化控件*/
    private void into(){
        zh_listview= (ListView) findViewById(R.id.zh_listview);
    }
    public void addlist(){
        list=new ArrayList<String>();
        list.add("现金");
        list.add("储蓄卡");
        list.add("信用卡");
        list.add("支付宝");
    }
    @Override
    protected void onResume() {
        super.onResume();
        intent=getIntent();
        String str=intent.getStringExtra("aa");
        zh_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=list.get(position);
                Intent intent=new Intent();
                intent.putExtra("name",name);
                setResult(0,intent);
                finish();
            }
        });
    }
}
