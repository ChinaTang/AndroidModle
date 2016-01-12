package modle.di.tang.androidmodle;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;

    private PackageManager packageManager;

    private List<ResolveInfo> activities;

    private static final String ACTION = "modle.di.tang.action";

    private static final String CATEGORY = "modle.di.tang.catage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        listView = (ListView)findViewById(R.id.main_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo resolveInfo = (ResolveInfo)activities.get(position);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if(activityInfo == null){
                    return;
                }
                Intent i = new Intent();
                i.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
                startActivity(i);
            }
        });

    }

    private List<ResolveInfo> getAppResolve(){
        Intent intent = new Intent();
        intent.setAction(ACTION);
        intent.addCategory(CATEGORY);
        packageManager = getPackageManager();
        activities = packageManager.queryIntentActivities(intent, 0);
        return activities;
    }

    ArrayAdapter<ResolveInfo> adapter = new ArrayAdapter<ResolveInfo>(this, 0,
            activities){
        @Override
        public View getView(int postion, View ContentView, ViewGroup parent){
            if(ContentView == null){
                ContentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            }
            TextView text = (TextView)ContentView.findViewById(R.id.item_list);
            text.setText(activities.get(postion).loadLabel(packageManager));
            return ContentView;
        }
    };

}
