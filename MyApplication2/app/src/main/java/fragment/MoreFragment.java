package fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q97531x.myapplication.AboutActivity;
import com.example.q97531x.myapplication.MainActivity;
import com.example.q97531x.myapplication.PasswordActivity;
import com.example.q97531x.myapplication.R;
import com.example.q97531x.myapplication.SuggestActivity;
import com.example.q97531x.myapplication.remindActivity;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.HttpHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import model.Budget;
import model.Income;
import model.Outcome;
import model.Remind;
import model.User;

/**
 * Created by Administrator on 2015/7/29.
 */
public class MoreFragment extends Fragment {
        LinearLayout morePassword,moreRemind,moreHelp,moreSuggest,moreUpdate,aboutUs,moreCopy,moreDelete;
        FinalDb db;
        TextView textView;
        String urlStr = "https://raw.githubusercontent.com/q97531x/home/master/verson.txt",apkurl = "https://github.com/q97531x/home/raw/master/MicroRecord.apk";
        StringBuffer sb;
        String fileName = "update.apk";
        File tmpFile = new File("/sdcard/update");
        public MoreFragment(){
            super();
        }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_more,container,false);
            morePassword = (LinearLayout)view.findViewById(R.id.morePassword);
            moreRemind = (LinearLayout)view.findViewById(R.id.moreRemind);
            moreHelp = (LinearLayout)view.findViewById(R.id.moreHelp);
            moreSuggest = (LinearLayout)view.findViewById(R.id.moreSuggest);
            moreUpdate = (LinearLayout)view.findViewById(R.id.moreUpdate);
            aboutUs = (LinearLayout)view.findViewById(R.id.aboutUs);
            moreCopy = (LinearLayout)view.findViewById(R.id.moreCopy);
            moreDelete = (LinearLayout)view.findViewById(R.id.moreDelete);
            textView = (TextView)view.findViewById(R.id.verson);
            db = FinalDb.create(getActivity());
            //如果文件不存在则创建
            if (!tmpFile.exists()) {
                tmpFile.mkdir();
            }
            morePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_pass = new Intent(getActivity(), PasswordActivity.class);
                    startActivity(intent_pass);
                }
            });

            moreRemind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(),"提醒功能仍在施工中，如有不便，敬请谅解",Toast.LENGTH_LONG).show();
                    Intent intent_remind = new Intent(getActivity(),remindActivity.class);
                    startActivity(intent_remind);
                }
            });
            moreHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });
            moreSuggest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),SuggestActivity.class);
                    startActivity(intent);
                }
            });
            moreUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new Thread(){
                        public void run()
                        {
                            //把网络访问的代码放在这里
                            try {
                /*
                 * 通过URL取得HttpURLConnection
                 * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
                 * <uses-permission android:name="android.permission.INTERNET" />
                 */
                                URL url=new URL(urlStr);
                                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                                //取得inputStream，并进行读取
                                InputStream input=conn.getInputStream();
                                BufferedReader in=new BufferedReader(new InputStreamReader(input));
                                String line=null;
                                sb=new StringBuffer();
                                while((line=in.readLine())!=null){
                                    sb.append(line);
                                }
                               System.out.print(sb.toString());
                                //获取APP版本号
                                PackageManager manager = getActivity().getPackageManager();
                                PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
                                int version = info.versionCode;
                                int httpversion = Integer.valueOf(sb.toString());
                                //比较服务端版本号与本机版本号，如果本机版本号小则下载apk更新
                                if(httpversion > version){
                                    //Log.e("true","true");

                                    URL versionurl=new URL(apkurl);
                                    HttpURLConnection con=(HttpURLConnection)versionurl.openConnection();
                                    InputStream is = con.getInputStream();
                                    File file = new File("/sdcard/update/" + fileName);

                                    FileOutputStream fos = new FileOutputStream(file);
                                    byte[] buf = new byte[256];
                                    con.connect();
                                    double count = 0;
                                    if (con.getResponseCode() >= 400) {
                                        Looper.prepare();
                                        Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    } else {
                                        while (count <= 100) {
                                            if (is != null) {
                                                int numRead = is.read(buf);
                                                if (numRead <= 0) {
                                                    break;
                                                } else {
                                                    fos.write(buf, 0, numRead);
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                        Looper.prepare();
                                        Toast.makeText(getActivity(),"正在下载",Toast.LENGTH_LONG).show();
                                        Looper.loop();
                                    }
                                    con.disconnect();
                                    fos.close();
                                    is.close();
                                    Intent intent = new Intent();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setAction(android.content.Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(file),
                                            "application/vnd.android.package-archive");
                                    startActivity(intent);
                                }else{
                                    Looper.prepare();
                                    Toast.makeText(getActivity(),"当前已是最新版本",Toast.LENGTH_LONG).show();
                                    Looper.loop();
                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (Exception e){
                                e.printStackTrace();
                                //return getActivity().getString(R.string.can_not_find_version_name);
                            }
                        }
                    }.start();
                    //Log.e("sy", sb.toString());
                    //Toast.makeText(getActivity(),sb.toString(),Toast.LENGTH_SHORT).show();

                }
            });

            aboutUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),AboutActivity.class);
                    startActivity(intent);
                }
            });
            moreCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(),"敬请期待",Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(),sb.toString(),Toast.LENGTH_SHORT).show();
                }
            });
            moreDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deleteAll(Budget.class);
                    db.deleteAll(Income.class);
                    db.deleteAll(Outcome.class);
                    db.deleteAll(Remind.class);
                    db.deleteAll(User.class);
                    Toast.makeText(getActivity(),"数据已清除",Toast.LENGTH_LONG).show();
                }
            });
            return view;
        }
}
