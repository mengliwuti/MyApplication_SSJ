package zdsoft.myapplication_ssj.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.activity.ZHzhuanActivity;
import zdsoft.myapplication_ssj.bean.Account;
import zdsoft.myapplication_ssj.bean.ZDSZBean;
import zdsoft.myapplication_ssj.databases.OPDatabase;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ZHFragment extends Fragment {
    TextView zhuanzhan,zhong_name,zhong_money,xian_name,xian_money,zhu_name,zhu_money,xin_name,xin_money,
            wang_name,wang_money;
    Account account;
    List<Account> accountList;
    OPDatabase opDatabase;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.zh_layout,null,false);
        zhuanzhan= (TextView) view.findViewById(R.id.zhuanzhan);
        zhong_name= (TextView) view.findViewById(R.id.zhong_name);
        zhong_money= (TextView) view.findViewById(R.id.zhong_money);
        xian_name= (TextView) view.findViewById(R.id.xian_name);
        xian_money= (TextView) view.findViewById(R.id.xian_money);
        zhu_name= (TextView) view.findViewById(R.id.zhu_name);
        zhu_money= (TextView) view.findViewById(R.id.zhu_money);
        xin_name= (TextView) view.findViewById(R.id.xin_name);
        xin_money= (TextView) view.findViewById(R.id.xin_money);
        wang_name= (TextView) view.findViewById(R.id.wang_name);
        wang_money= (TextView) view.findViewById(R.id.wang_money);
        account=new Account();
        opDatabase=new OPDatabase(getActivity());
        zhuanzhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ZHzhuanActivity.class);
                startActivity(intent);
            }
        });
        accountList=opDatabase.ZHgetAllData();
        if (accountList==null||accountList.size()==0){
            opDatabase.zhaddName();
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        accountList=opDatabase.ZHgetAllData();
        zhong_money.setText(
                (Double.parseDouble(accountList.get(1).getBalance())+
                        Double.parseDouble(accountList.get(2).getBalance())+
                        Double.parseDouble(accountList.get(3).getBalance())+
                        Double.parseDouble(accountList.get(4).getBalance())
                )
                        +"" );
        xian_money.setText(accountList.get(1).getBalance());
        zhu_money.setText(accountList.get(2).getBalance());
        xin_money.setText(accountList.get(3).getBalance());
        wang_money.setText(accountList.get(4).getBalance());
    }
}
