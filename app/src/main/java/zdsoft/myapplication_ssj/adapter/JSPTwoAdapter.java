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
 * Created by Administrator on 2016/11/14.
 */
public class JSPTwoAdapter extends BaseAdapter{
    Context context;
    List<String> list;

    public JSPTwoAdapter(Context context, List<String> list) {
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
        ViewHodle viewHodle;
        if(convertView==null){
            viewHodle=new ViewHodle();
            convertView= LayoutInflater.from(context).inflate(R.layout.jsp_twolayout,null);
            convertView.setTag(viewHodle);
        }else{
            viewHodle= (ViewHodle) convertView.getTag();
        }
        viewHodle.tv_twoshow= (TextView) convertView.findViewById(R.id.tv_twoshow);
        viewHodle.tv_twoshow.setText(list.get(position));
        return null;
    }
    class ViewHodle{
        TextView tv_twoshow;

    }
}
