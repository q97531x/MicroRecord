package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

import fragment.GuideFragment1;
import fragment.GuideFragment2;
import fragment.GuideFragment3;

/**
 * Created by Administrator on 2015/7/27.
 */
public class MainPagerAdapter extends FragmentPagerAdapter{
    Context ctx;
    public MainPagerAdapter(FragmentManager fm, Context ctx){
        super(fm);
        // TODO Auto-generated constructor stub
        this.ctx = ctx;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment mFragment = null;
        switch (position){
            case 0:
                mFragment = new GuideFragment1(ctx);
                break;
            case 1:
                mFragment = new GuideFragment2(ctx);
                break;
            case 2:
                mFragment = new GuideFragment3(ctx);
                break;
        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
