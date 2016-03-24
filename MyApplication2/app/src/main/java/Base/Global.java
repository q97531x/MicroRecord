package Base;

/**
 * Created by XmacZone on 16/3/24.
 */
public class Global {
    public static int sWidthPix;
    public static int sHeightPix;

    public Global() {
        sWidthPix = App.getContext().getResources().getDisplayMetrics().widthPixels;
        sHeightPix = App.getContext().getResources().getDisplayMetrics().heightPixels;
    }
}
