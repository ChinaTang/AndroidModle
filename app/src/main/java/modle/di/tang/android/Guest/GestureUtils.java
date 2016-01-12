package modle.di.tang.android.Guest;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by tangdi on 2016/1/11.
 */
public class GestureUtils {

    //获取屏幕的大小
    public static GuestModelActivity.Screen getScreenPix(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return new GuestModelActivity.Screen(dm.widthPixels,dm.heightPixels);
    }
}
