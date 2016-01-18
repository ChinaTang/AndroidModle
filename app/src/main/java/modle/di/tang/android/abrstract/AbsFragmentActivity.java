package modle.di.tang.android.abrstract;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.zip.Inflater;

import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/18.
 */
public abstract class AbsFragmentActivity extends FragmentActivity{

    public abstract Fragment getFragment();

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.fragment_layout);
        if(fragment == null){
            fragment = getFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_layout, fragment)
                    .commit();
        }
    }

}
