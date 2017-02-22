package zdsoft.myapplication_ssj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.activity.JSPActivity;
import zdsoft.myapplication_ssj.adapter.JSPAdapter;
import zdsoft.myapplication_ssj.bean.Bean;
import zdsoft.myapplication_ssj.databases.OPDatabase;
import zdsoft.myapplication_ssj.gongneng.JSPTwo;

/**
 * Created by Administrator on 2016/11/8.
 */
public class JSPFragment extends Fragment {
    ImageView jsp_img_one;
    ListView listView;
    JSPAdapter jspAdapter;
    List<Bean> list;
    String title,content;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.jsp_layout,null,false);
        jsp_img_one= (ImageView) view.findViewById(R.id.jsp_img_one);
        listView= (ListView) view.findViewById(R.id.jsp_listshow);
        list=new OPDatabase(getActivity()).JSPgetAllData();
        jspAdapter=new JSPAdapter(getActivity(),list);
        return view;
    }
    public void onResume(){
        listView.setAdapter(jspAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title=list.get(position).getTitle();
                content=list.get(position).getContent();
                Intent intent=new Intent(getActivity(),JSPActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("content",content);
                startActivity(intent);
            }
        });
        super.onResume();
        jsp_img_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), JSPTwo.class);
                startActivity(intent);
            }
        });
        // TODO: 2016/11/16 刷新
//        list=new OPDatabase(getActivity()).JSPgetAllData();
        jspAdapter=new JSPAdapter(getActivity(),list);

        jspAdapter.notifyDataSetChanged();
        listView.setAdapter(jspAdapter);

    }
}
