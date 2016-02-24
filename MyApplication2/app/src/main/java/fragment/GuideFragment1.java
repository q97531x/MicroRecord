package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.q97531x.myapplication.R;


/**
 * Created by Administrator on 2015/7/27.
 */
public class GuideFragment1 extends Fragment{
    private Context ctx;
    /*public GuideFragment1(){
        super();
    }*/
    public GuideFragment1(Context ctx){
        super();
        this.ctx = ctx;
    }
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view =  View.inflate(ctx,R.layout.fragment_guide1,null);
        return view;
    }
}
