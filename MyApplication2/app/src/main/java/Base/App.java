package Base;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;


/**
 * Created by 渡渡鸟 on 2015/12/29.
 */
public class App extends Application{
    public static Context context;
    private static int user = 0;
    public static int getUser(){
        return user;
    }
    @Override
    public void onCreate() {
        super.onCreate();


    }
    public static Context getContext(){
        return context;
    }


}
