package modle.di.tang.androidmodle;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * Created by tangdi on 2016/1/12.
 */
public class MyAdapter extends ArrayAdapter<String>{

    private Context context;

    private ArrayList<String> appList;

    public MyAdapter(Context context, ArrayList<String> appList){
        super(context, 0, appList);
        this.context = context;
        this.appList = appList;
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup parent){
        if(ConvertView == null){
            ConvertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }
        TextView text = (TextView)ConvertView.findViewById(R.id.item_list);
        text.setText(appList.get(position));
        return ConvertView;
    }
}
