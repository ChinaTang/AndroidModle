package modle.di.tang.android.Dialog;
import android.support.v4.app.Fragment;

import modle.di.tang.android.abrstract.*;

/**
 * Created by tangdi on 2016/1/18.
 */
public class DialogActvivty extends AbsFragmentActivity{


    @Override
    public Fragment getFragment() {
        Fragment fm = new DialogFragment();
        return fm;
    }
}
