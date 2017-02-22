package zdsoft.myapplication_ssj.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zdsoft.myapplication_ssj.R;
import zdsoft.myapplication_ssj.bean.Bean;
import zdsoft.myapplication_ssj.databases.MyDataBases;
import zdsoft.myapplication_ssj.databases.OPDatabase;

/**
 * Created by Administrator on 2016/11/8.
 */
public class JSPAdapter extends BaseAdapter {
    Context context;
    List<Bean> list;

    public JSPAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list==null){
            return 0;
        }else {
            return list.size();
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(context).inflate(R.layout.jsp_item,null);

            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.jsp_biaoti= (TextView) convertView.findViewById(R.id.jsp_biaoti);
        viewHodler.jsp_zhenwen= (TextView) convertView.findViewById(R.id.jsp_zhenwen);
        viewHodler.jsp_time= (TextView) convertView.findViewById(R.id.jsp_time);
        viewHodler.jsp_delect= (TextView) convertView.findViewById(R.id.jsp_delect);
        viewHodler.jsp_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               long lg= new OPDatabase(context).JSPdelete(list.get(position).getTimes());
                if (lg>0){
                    Toast.makeText(context,"删除成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"删除失败",Toast.LENGTH_LONG).show();
                }
            }
        });
        viewHodler.jsp_time.setText(list.get(position).getTimes());
        viewHodler.jsp_biaoti.setText(list.get(position).getTitle());
        viewHodler.jsp_zhenwen.setText(list.get(position).getContent());
        return convertView;
    }
    class ViewHodler{
        TextView jsp_biaoti,jsp_zhenwen,jsp_time,jsp_delect;
    }
}

