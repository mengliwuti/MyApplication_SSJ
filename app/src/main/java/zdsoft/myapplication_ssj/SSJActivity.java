package zdsoft.myapplication_ssj;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import zdsoft.myapplication_ssj.adapter.MyAdapter;
import zdsoft.myapplication_ssj.fragment.JSPFragment;
import zdsoft.myapplication_ssj.fragment.ZDFragment;
import zdsoft.myapplication_ssj.fragment.ZHFragment;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SSJActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    ViewPager viewPager;
    TextView jsp_button,zd_button,zh_button;
    JSPFragment jspFragment;
    ZDFragment zdFragment;
    ZHFragment zhFragment;
    List<Fragment> list;
    MyAdapter myAdapter;
    Resources resources;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssj_layout);
        fragmentManager=getSupportFragmentManager();
        resources=getResources();
        into();
        intoPager();
    }
    /**
     * 实例化控件*/
    private void into(){
        viewPager= (ViewPager) findViewById(R.id.view_pager);
        jsp_button= (TextView) findViewById(R.id.jsp_button);
        zd_button= (TextView) findViewById(R.id.zd_button);
        zh_button= (TextView) findViewById(R.id.zh_button);
        viewPager.setOnPageChangeListener(this);
        jsp_button.setOnClickListener(this);
        zd_button.setOnClickListener(this);
        zh_button.setOnClickListener(this);
        //首次登陆
        jsp_button.setTextColor(resources.getColor(R.color.blue));
        zd_button.setTextColor(resources.getColor(R.color.gray));
        zh_button.setTextColor(resources.getColor(R.color.gray));
    }
    /**
     * 界面初始化*/
    private void intoPager(){
        list=new ArrayList<Fragment>();
        jspFragment=new JSPFragment();
        zdFragment=new ZDFragment();
        zhFragment=new ZHFragment();
        list.add(jspFragment);
        list.add(zdFragment);
        list.add(zhFragment);
        myAdapter=new MyAdapter(fragmentManager,list);
        viewPager.setAdapter(myAdapter);
    }
    /**
     * 界面切换*/
    @Override
    public void onClick(View v) {
       changeIcon(v.getId());
    }
    public  void changeIcon(int i){
        clearIcon();
        switch (i){
            case R.id.jsp_button: case 0:
                jsp_button.setTextColor(resources.getColor(R.color.blue));
                viewPager.setCurrentItem(0);
                break;
            case R.id.zd_button: case 1:
                zd_button.setTextColor(resources.getColor(R.color.blue));
                viewPager.setCurrentItem(1);
                break;
            case R.id.zh_button: case 2:
                zh_button.setTextColor(resources.getColor(R.color.blue));
                viewPager.setCurrentItem(2);
                break;
        }
    }
    /**
     * 清空字体颜色*/
    public void clearIcon(){
        jsp_button.setTextColor(resources.getColor(R.color.gray));
        zd_button.setTextColor(resources.getColor(R.color.gray));
        zh_button.setTextColor(resources.getColor(R.color.gray));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeIcon(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
