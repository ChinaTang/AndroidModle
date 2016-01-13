package modle.di.tang.android.Guest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

import android.location.Location;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;


import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/12.
 */
public class GuestModelActivity extends Activity implements GestureDetector.OnGestureListener{

    private GestureDetector mGestureDetector;

    /**
     * 获得屏幕尺寸
     */
    private Screen screen;

    private PopupWindow popupWindow;

    private View contentView;


    private int from = 0;

    private static final String TAG = "GuestModelActivity";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_guest);
       mGestureDetector = new GestureDetector(this, this);
       screen = GestureUtils.getScreenPix(this);
       popupWindowInit();
   }

    private void popupWindowInit(){
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_popup, null);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setBackgroundDrawable(getResources().
                getDrawable(R.color.self_color));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event){

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e){
        /**
         * 按下手势
         */
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e){

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e){
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
        /**
         * 拖动手势
         */
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e){
        /**
         * 长按手势
         */
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        //手指划过屏幕的横向距离
        float x = e2.getX() - e1.getX();
        //手指划过屏幕的纵向距离
        float y = e2.getY() - e1.getY();

        //取得横向距离的绝对值
        float x_abs = Math.abs(x);
        //取得纵向距离的绝对值
        float y_abs = Math.abs(y);

        //屏幕1/5的距离
        float x_limit = screen.widthPixels / 5;
        float y_limit = screen.heightPixels / 5;

        //手指在屏幕上横向滑动
        if(x_abs >= y_abs){
            if(x > x_limit || x < -x_limit){
                if(x > 0){
                    //向右移动
                    from = location.RIGHT.ordinal();
                    initPopuWindow();
                    Log.e(TAG, "Right");
                }else if(x < 0){
                    //向左移动
                    from = location.LEFT.ordinal();
                    initPopuWindow();
                    Log.e(TAG, "Left");
                }
            }
        }
        //横向滑动
        else {
            if(y > y_limit || y < -y_limit){
                if(y > 0){
                    //向下
                    from = location.TOP.ordinal();
                    initPopuWindow();
                    Log.e(TAG, "Down");

                }else if(y < 0 ){
                    //向上
                    from = location.BOTTOM.ordinal();
                    initPopuWindow();
                    Log.e(TAG, "Up");

                }
            }
        }

        return false;
    }


    /**
     * 屏幕尺寸类
     */
    public static class Screen{

        public int widthPixels;
        public int heightPixels;

        public Screen(){}

        public Screen(int widthPixels,int heightPixels){
            this.widthPixels=widthPixels;
            this.heightPixels=heightPixels;
        }

        @Override
        public String toString() {
            return "("+widthPixels+","+heightPixels+")";
        }

    }

    class popupDosmissListener implements PopupWindow.OnDismissListener{
        @Override
        public void onDismiss(){
            //backgroundAlpha(1f);
        }
    }

    private void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    private void initPopuWindow(){
        if(location.LEFT.ordinal() == from || location.RIGHT.ordinal() == from){
            //是否可以不这样定义宽度
            popupWindow.setWidth(800);
            popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        }else if(location.BOTTOM.ordinal() == from || location.TOP.ordinal() == from){
            popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(1200);
        }
        if(location.LEFT.ordinal() == from){
            popupWindow.setAnimationStyle(R.style.AnimationRightFade);
        }
        if(location.RIGHT.ordinal() == from){
            popupWindow.setAnimationStyle(R.style.AnimationLeftFade);
        }
        if(location.BOTTOM.ordinal() == from){
            popupWindow.setAnimationStyle(R.style.AnimationBottomFade);
        }
        if(location.TOP.ordinal() == from){
            popupWindow.setAnimationStyle(R.style.AnimationTopFade);
        }

        if(location.LEFT.ordinal() == from){
            popupWindow.showAtLocation(GuestModelActivity.this.findViewById(R.id.layout_main),
                    Gravity.RIGHT, 0, 0);

            Log.e(TAG, "显示向左的Popuwindow");
        }else if(location.RIGHT.ordinal() == from){
            popupWindow.showAtLocation(GuestModelActivity.this.findViewById(R.id.layout_main),
                    Gravity.LEFT, 0, 0);

            Log.e(TAG, "显示向右的Popuwindow");
        }else if(location.BOTTOM.ordinal() == from){
            popupWindow.showAtLocation(GuestModelActivity.this.findViewById(R.id.layout_main),
                    Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

            Log.e(TAG, "显示向下的Popuwindow");
        }else if(location.TOP.ordinal() == from){
            popupWindow.showAtLocation(GuestModelActivity.this.findViewById(R.id.layout_main),
                    Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        }
       //backgroundAlpha(0.5f);
        //popupWindow.setOnDismissListener(new popupDosmissListener());
    }

    private enum location{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

}
