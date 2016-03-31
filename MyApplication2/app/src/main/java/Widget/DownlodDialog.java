package Widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.zip.Inflater;

/**
 * Created by q97531x on 2016/3/27.
 */
public class DownlodDialog extends Dialog{
    private Context context;
    private LayoutInflater inflater;
    public DownlodDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(context);
//        initView();
    }


}
