package modle.di.tang.android.Dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/18.
 */
public class DialogFragment extends Fragment implements View.OnClickListener{

    public static final String DATE_DIALOG = "1";

    public static final int DATE_CODE = 1;

    private static final String TAG = "DialogFragment";

    private Button bn;

    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        initView(v);
        return v;
    }

    public static DialogFragment getInstance(Bundle arg){
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.dateDialog :
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Date date = new Date();
                Bundle arg = new Bundle();
                arg.putSerializable("setData", date);
                Fdialog fdialog = Fdialog.getInstance(arg);
                fdialog.setTargetFragment(DialogFragment.this, DATE_CODE);
                fdialog.show(fm, DATE_DIALOG);
                Log.e(TAG, "onClilck DateDialog");
                break;

        }
    }

    private void initView(View v){
        bn = (Button) v.findViewById(R.id.dateDialog);
        bn.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requetCode, int resultCode, Intent data){
        //if(requetCode != Activity.RESULT_OK)return;
        Log.e(TAG, "onActivityResult");
        if(requetCode == DATE_CODE){
            Date date = (Date)data.getSerializableExtra(Fdialog.SETDATA);
            bn.setText(date.toString());
        }
    }
}
