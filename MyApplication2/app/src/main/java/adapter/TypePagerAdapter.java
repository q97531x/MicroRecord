/*
package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Switch;

import com.example.q97531x.myapplication.R;

import java.lang.reflect.Type;

import fragment.OutcomeTypeFragment;
import util.TypeData;

*/
/**
 * Created by q97531x on 2015/8/12.
 *//*

public class TypePagerAdapter extends FragmentPagerAdapter {
    int count = 0;
    public TypePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        OutcomeTypeFragment outcomeFragment = null;
        TypeData td = new TypeData();
        switch(position){
            case 0:
                outcomeFragment = new OutcomeTypeFragment();
                */
/*for (int i = 0;i<5;i++){
                    Log.e("type","image"+td.getImage(i+5*position)+"text"+td.getText(i+5*position));
                    outcomeFragment.setItem(i,td.getImage(i+5*position),td.getText(i+5*position));
                }*//*

                //outcomeFragment.setItem();
                //outcomeFragment.setItem();
                break;
            case 1:
                outcomeFragment = new OutcomeTypeFragment();
                */
/*for(int i = 0;i<5;i++){
                    Log.e("type","image"+td.getImage(i+5*position)+"text"+td.getText(i+5*position));
                    outcomeFragment.setItem(i,td.getImage(i+5*position),td.getText(i + 5 * position));
                }*//*

                break;

        }
        count = getCount();
        return outcomeFragment;
    }

    @Override
    public int getCount() {
        int page=0;
        TypeData typeData = new TypeData();
        if (typeData.getImageSize()%5==0){
            page = typeData.getImageSize()/5;
        }else if(typeData.getImageSize()%5!=0){
            page = typeData.getImageSize()/5+1;
        }
        return page;
    }
}
*/
