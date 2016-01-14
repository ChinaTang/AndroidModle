package modle.di.tang.androidmodle;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import modle.di.tang.android.Guest.GuestModelActivity;


public class MainActivity extends Activity implements View.OnTouchListener {

    private ListView listView;

    private PackageManager packageManager;

    private List<ResolveInfo> activities;

    private static final String ACTION = "modle.di.tang.action";

    private static final String CATEGORY = "modle.di.tang.category";

    private static final String TAG = "MainActivity";

    private ArrayAdapter<ResolveInfo> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAppResolve();



        findViewById(R.id.main).setOnTouchListener(this);


        adapter = new ArrayAdapter<ResolveInfo>(this, 0,
                activities) {
            @Override
            public View getView(int postion, View ContentView, ViewGroup parent) {
                if (ContentView == null) {
                    ContentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
                }
                TextView text = (TextView) ContentView.findViewById(R.id.item_list);
                text.setText(activities.get(postion).loadLabel(packageManager));
                return ContentView;
            }
        };

        initView();

    }


    private void initView() {
        listView = (ListView) findViewById(R.id.main_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo resolveInfo = (ResolveInfo) activities.get(position);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo == null) {
                    return;
                }
                Intent i = new Intent();
                i.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
                startActivity(i);
            }
        });

    }

    private List<ResolveInfo> getAppResolve() {
        Intent intent = new Intent(ACTION);
        intent.addCategory(CATEGORY);
        packageManager = getPackageManager();
        activities = packageManager.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
        Log.e(TAG, activities.toString());
        return activities;
    }

    /**
     * 无法执行
     *
     * @Override public boolean onTouchEvent(MotionEvent event){
     * <p/>
     * if(MotionEvent.ACTION_DOWN == event.getAction()){
     * Intent i = new Intent(this, GuestModelActivity.class);
     * startActivity(i);
     * Log.e(TAG, "onTouchEvent");
     * }
     * Log.e(TAG, "onTouchEvent-return");
     * return super.onTouchEvent(event);
     * }
     **/


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Intent i = new Intent(this, GuestModelActivity.class);
//            startActivity(i);
            Log.e(TAG, "onTouchEvent");
        }
        return false;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.e("tanglaoban", "SHUAIGE MEINV..................");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            Log.e("tanglaoban", "DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("tanglaoban", "MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("tanglaoban", "UP");
                break;

        }

        return false;
    }
}
