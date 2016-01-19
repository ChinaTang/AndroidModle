package modle.di.tang.android.pageView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/18.
 */
public class PageViewFragment extends Fragment{

    private int[] imageRes;

    public static final String IMAGE = "image";

    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        imageRes = new int[]{R.drawable.bg0_fine_day, R.drawable.bg0_fine_night,
        R.drawable.bg18_fog_night};

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.page_fragment, container, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.image01);
        imageView.setImageResource(imageRes[getArguments().getInt(IMAGE)]);
        return v;
    }

    public static PageViewFragment getInstance(Bundle arg){
        PageViewFragment pageViewFragment = new PageViewFragment();
        pageViewFragment.setArguments(arg);
        return pageViewFragment;
    }

}
