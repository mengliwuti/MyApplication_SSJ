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

/**
 * Created by Administrator on 2016/11/9.
 */
public class ZDAdapter extends BaseAdapter{
    List<ZDBean> list;
    Context context;

    public ZDAdapter(List<ZDBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public List<ZDBean> getList() {
        return list;
    }

    public void setList(List<ZDBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list!=null)
            return list.size();
        return 0;
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(context).inflate(R.layout.grid_item,null);
            viewHodler.img_grid= (ImageView) convertView.findViewById(R.id.img_grid);
            viewHodler.tv_grid= (TextView) convertView.findViewById(R.id.tv_grid);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.img_grid.setImageResource(list.get(position).getId());
        viewHodler.tv_grid.setText(list.get(position).getName());
        return convertView;
    }
    class ViewHodler{
        ImageView img_grid;
        TextView tv_grid;
    }
}
