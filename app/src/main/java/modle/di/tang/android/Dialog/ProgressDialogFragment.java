package modle.di.tang.android.Dialog;

import android.app.*;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.*;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/19.
 */
public class ProgressDialogFragment  extends android.support.v4.app.DialogFragment{

    //圆形进度框
    public static final int CRICLE_DIALOG = 1;

    //水平进度框
    public static final int HORI_DIALOG = 2;

    //自定义对话框
    public static final int SELF_DIALOG = 3;

    //选择对话框形式标志
    public static final String STYLE = "style";

    private ProgressDialog progressDialog;

    public int progerssStatus = 0;

    private int hasData = 0;

    //进度条进度改变Handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0x123){
                progressDialog.setProgress(progerssStatus);
                Log.d("handler", "接受消息" + progerssStatus);
            }
        }
    };
    @Override
    public void onCreate(Bundle SaveInstanceBundle){
        super.onCreate(SaveInstanceBundle);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        progressDialog = new ProgressDialog(getActivity());
        int choose = getArguments().getInt(STYLE);
        if(choose == HORI_DIALOG) {
            //设置是否可以通过back键取消
            progressDialog.setCancelable(true);
            progressDialog.setTitle("123");
            progressDialog.setMessage("456");
            //设置仍无进度最大值
            progressDialog.setMax(100);
            //设置对话框风格
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //设置对话框是否显示进度
            progressDialog.setIndeterminate(true);
            //设置对话框进度条图片
            //progressDialog.setIndeterminateDrawable();
            //改变进度条值

        }else if(choose == CRICLE_DIALOG){
            //环形对话框
            progressDialog.setCancelable(true);
            progressDialog.setTitle("123");
            progressDialog.setMessage("456");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        }else if(choose == SELF_DIALOG){
            //自定义对话框
            Dialog dialog = new CustomerDialog(getActivity());
            return dialog;
        }


        return progressDialog;

    }

    /**
     * 设置进度框风格
     * @param args
     * @return
     */
    public static ProgressDialogFragment getInstance(Bundle args){
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.setArguments(args);
        return progressDialogFragment;
    }

    //模拟进度条改变线程
    class ProgressChange implements Runnable{

        @Override
        public void run() {
            while(progerssStatus < 100){
                ++progerssStatus;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage(0x123);
                handler.sendEmptyMessage(0x123);
                Log.d("ProgressChange", "发送消息");
            }
        }
    }

    public void runThread(){
        new Thread(new ProgressChange()).start();
    }

    class CustomerDialog extends Dialog{
        private TextView mMessage;

        public CustomerDialog(Context context, int msgId) {
            super(context, R.style.load_dialog);
            setContentView(R.layout.dialog_loding);
            ImageView loding = (ImageView)findViewById(R.id.dial_loding);
            loding.setImageResource(R.drawable.loading);
            AnimationDrawable drawable = (AnimationDrawable) loding.getDrawable();
            drawable.start();
            setCancelable(true);
            setCanceledOnTouchOutside(false);
        }

        public CustomerDialog(Context context) {
            super(context, R.style.load_dialog);
            setContentView(R.layout.dialog_loding);
            ImageView loding = (ImageView)findViewById(R.id.dial_loding);
            loding.setImageResource(R.drawable.loading);
            AnimationDrawable drawable = (AnimationDrawable) loding.getDrawable();
            drawable.start();
            setCancelable(true);
            setCanceledOnTouchOutside(false);
        }
    }

}
