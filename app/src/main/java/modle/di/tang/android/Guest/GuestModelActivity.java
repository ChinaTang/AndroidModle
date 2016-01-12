package modle.di.tang.android.Guest;

import android.app.Activity;
import android.content.Context;

import android.location.Location;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;

import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/12.
 */
public class GuestModelActivity extends Activity implements GestureDetector.OnGestureListener,
        View.OnTouchListener{

    private GestureDetector mGestureDetector;

    /**
     * 获得屏幕尺寸
     */
    private Screen screen;

    private PopupWindow popupWindow;

    private View contentView;

    private ListView listView;

    private int from = 0;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_guest);
       mGestureDetector = new GestureDetector(this, this);
       screen = GestureUtils.getScreenPix(this);

   }

    private void init(){
        contentView = LayoutInflater.from(this).inflate(R.layout.popup_main, null);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setBackgroundDrawable(getResources().
                getDrawable(R.color.bright_foreground_material_light));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        listView.setFocusable(true);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event){

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

        //屏幕1/3的距离
        float x_limit = screen.widthPixels / 3;
        float y_limit = screen.heightPixels / 3;

        //手指在屏幕上横向滑动
        if(x_abs >= y_abs){
            if(x > x_limit || x < -x_limit){
                if(x > 0){
                    //向右移动

                }else if(x < 0){
                    //向左移动

                }
            }
        }
        //横向滑动
        else {
            if(y > y_limit || y < -y_limit){
                if(y > 0){
                    //向下

                }else if(y < 0 ){
                    //向上

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
            backgroundAlpha(1f);
        }
    }

    private void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    private void initPopuWindow(){
        View popupWindwoView = getLayoutInflater().inflate(R.layout.popup_main, null);
        if(location.LEFT.ordinal() == from || location.RIGHT.ordinal() == from){
            popupWindow.setWidth(screen.widthPixels * (3/4));
            popupWindow.setHeight(screen.heightPixels);
        }else if(location.BOTTOM.ordinal() == from || location.TOP.ordinal() == from){
            popupWindow.setWidth(screen.widthPixels);
            popupWindow.setHeight(screen.heightPixels * (3/4));
        }
        if(location.LEFT.ordinal() == from){
            popupWindow.setAnimationStyle(R.style.AnimationLeftFade);
        }
        if(location.RIGHT.ordinal() == from){
            popupWindow.setAnimationStyle(R.style.AnimationRightFade);
        }
        if(location.BOTTOM.ordinal() == from){
            popupWindow.setAnimationStyle(R.style.AnimationBottomFade);
        }

        if(location.LEFT.ordinal() == from){
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.popup_main, null),
                    Gravity.LEFT, 0, 500);
        }else if(location.RIGHT.ordinal() == from){
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.popup_main, null),
                    Gravity.RIGHT, 0, 500);
        }else if(location.BOTTOM.ordinal() == from){
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.popup_main, null),
                    Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new popupDosmissListener());
    }

    private enum location{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

}
