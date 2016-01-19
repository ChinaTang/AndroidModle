package modle.di.tang.android.pageView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/18.
 */
public class PageViewActivity extends FragmentActivity{

    private ViewPager viewPager;

    private FragmentStatePagerAdapter fragmentStatePagerAdapter;


    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        Log.e("PageViewActivity", "进入onCreate");
        //以代码形式创建ViewPage保证代码的低耦合性
        setContentView(R.layout.activity_pageview);
        //viewPager = new ViewPager(this);
        //viewPager.setId(R.id.viewPager);
        viewPager = (ViewPager)findViewById(R.id.activity_viewpage);
        FragmentManager fm = getSupportFragmentManager();
        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Bundle arg = new Bundle();
                arg.putInt(PageViewFragment.IMAGE, position);
                Log.e("PageViewActivity", "Fragment");
                return PageViewFragment.getInstance(arg);
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
        viewPager.setAdapter(fragmentStatePagerAdapter);
    }



}
