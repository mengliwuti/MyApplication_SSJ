package zdsoft.myapplication_ssj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.bean.ZDBean;
import zdsoft.myapplication_ssj.bean.ZDSZBean;
import zdsoft.myapplication_ssj.databases.OPDatabase;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ZDSZAdapter extends BaseAdapter{
    Context context;
    List<ZDSZBean> list;

    public ZDSZAdapter(Context context, List<ZDSZBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(context).inflate(R.layout.zdsz_item,null);
            viewHodler.shoutime_tv= (TextView) convertView.findViewById(R.id.shoutime_tv);
            viewHodler.shou_tv= (TextView) convertView.findViewById(R.id.shou_tv);
            viewHodler.money_shou= (TextView) convertView.findViewById(R.id.money_shou);
            viewHodler.sz_img= (ImageView) convertView.findViewById(R.id.sz_img);
            viewHodler.zhi_tv= (TextView) convertView.findViewById(R.id.zhi_tv);
            viewHodler.money_zhi= (TextView) convertView.findViewById(R.id.money_zhi);
            viewHodler.zhi_timetv= (TextView) convertView.findViewById(R.id.zhi_timetv);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        if(list.get(position).getState().equals("1")){
            viewHodler.zhi_tv.setText(list.get(position).getNametype());
            viewHodler.zhi_timetv.setText(list.get(position).getTimes());
            viewHodler.money_zhi.setText(list.get(position).getNumber());

            viewHodler.shoutime_tv.setText("");
            viewHodler.shou_tv.setText("");
            viewHodler.money_shou.setText("");

        }else if(list.get(position).getState().equals("2")){
            viewHodler.zhi_tv.setText("");
            viewHodler.zhi_timetv.setText("");
            viewHodler.money_zhi.setText("");

            viewHodler.shoutime_tv.setText(list.get(position).getTimes());
            viewHodler.shou_tv.setText(list.get(position).getNametype());
            viewHodler.money_shou.setText(list.get(position).getNumber());
        }
        viewHodler.sz_img.setImageResource(list.get(position).getImage());
        return convertView;
    }
    class ViewHodler{
        TextView shoutime_tv,shou_tv,zhi_tv,zhi_timetv,money_shou,money_zhi;
        ImageView sz_img;
    }
}
