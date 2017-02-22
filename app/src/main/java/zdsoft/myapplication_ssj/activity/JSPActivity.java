package zdsoft.myapplication_ssj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.adapter.JSPTwoAdapter;

/**
 * Created by Administrator on 2016/11/14.
 */
public class JSPActivity extends Activity{
    TextView title_tv,content_tv;
    JSPTwoAdapter jspTwoAdapter;
    String title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsp_tvlayout);
        title_tv= (TextView) findViewById(R.id.title_tv);
        content_tv= (TextView) findViewById(R.id.content_tv);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        title=intent.getStringExtra("title");
        content=intent.getStringExtra("content");
        Log.e("aa",title);
        Log.e("bb",content);
        title_tv.setText(title);
        content_tv.setText(content);
        setResult(0,intent);
    }
}
