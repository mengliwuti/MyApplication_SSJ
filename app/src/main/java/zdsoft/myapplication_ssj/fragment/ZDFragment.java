package zdsoft.myapplication_ssj.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.activity.ZDActivity;
import zdsoft.myapplication_ssj.adapter.ZDSZAdapter;
import zdsoft.myapplication_ssj.bean.ZDSZBean;
import zdsoft.myapplication_ssj.databases.OPDatabase;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ZDFragment extends Fragment {
    ImageView zd_img_one;
    ListView sz_list;
    ZDSZAdapter zdszAdapter;
    List<ZDSZBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.zd_layout,null,false);
        zd_img_one= (ImageView) view.findViewById(R.id.zd_img_one);
        sz_list= (ListView) view.findViewById(R.id.sz_list);
        list=new OPDatabase(getActivity()).ZDSZgetAllDate();
        zdszAdapter=new ZDSZAdapter(getActivity(),list);

        zd_img_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ZDActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        list=new OPDatabase(getActivity()).ZDSZgetAllDate();
        zdszAdapter=new ZDSZAdapter(getActivity(),list);
        zdszAdapter.notifyDataSetChanged();
        sz_list.setAdapter(zdszAdapter);
    }
}
