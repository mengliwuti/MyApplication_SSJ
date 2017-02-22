package zdsoft.myapplication_ssj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zdsoft.myapplication_ssj.R;

/**
 * Created by Administrator on 2016/11/15.
 */
public class DHAdapter extends BaseAdapter{
    Context context;
    List<String> list;
    public DHAdapter(Context context, List list) {
        this.context = context;
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
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.dh_item,null);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.zhtv= (TextView) convertView.findViewById(R.id.zhtv);

        viewHolder.zhtv.setText(list.get(position));

        return convertView;
    }
    class ViewHolder{
        TextView zhtv;
    }
}
