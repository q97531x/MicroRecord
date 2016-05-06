package fragment;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q97531x.myapplication.R;
//import com.example.q97531x.myapplication.RecordActivity;
import com.example.q97531x.myapplication.WriteActivity;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Widget.SlideListView;
import fragment.Adapter.DetailAdapter;
import model.Income;
import model.Outcome;

/**
 * Created by Administrator on 2015/7/28.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {
    //Context ctx;

    int year, month, day, flag = 0;
    LinearLayout outcomeLayout, incomeLayout;
    ImageView detailOutcome, detailIncome;
    private TextView write;
    private static final int UPDATE = 1234;
    private int RequestWrite = 9999;
    SlideListView lv;
    private int topPosition = 0;
    FinalDb db;
    int icontype = 0;
    //    Toolbar toolbar;
    private String type = "outcome";
    private String date;
    private boolean slideUp = false;
    private boolean scrollStop = false;

    public DetailFragment() {
        super();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = View.inflate(ctx,R.layout.fragment_detail,null);
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        db = FinalDb.create(getActivity());
        lv = (SlideListView) view.findViewById(R.id.list);
        outcomeLayout = (LinearLayout) view.findViewById(R.id.outcomeLayout);
        incomeLayout = (LinearLayout) view.findViewById(R.id.incomeLayout);
        detailIncome = (ImageView) view.findViewById(R.id.detailIncomeImg);
        detailOutcome = (ImageView) view.findViewById(R.id.detailOutcomeImg);
        write = (TextView) view.findViewById(R.id.write);
        //设置字体
        write.setTypeface(iconfont);
        /*AssetManager mgr = getActivity().getAssets();//得到AssetManager
        Typeface tf= Typeface.createFromAsset(mgr,"msyh.ttf");//根据路径得到Typeface
        outcomeText.setTypeface(tf);//设置字体
        incomeText.setTypeface(tf);*/
        //listener
        write.setOnClickListener(this);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
//        date = year + "-" + (month + 1) + "-" + day;
        date = year + "-" + (month + 1);
        DataBase(date);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == SCROLL_STATE_FLING){
                    scrollStop = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("position", "firstVisible:" + firstVisibleItem + "top:" + topPosition);
                if (firstVisibleItem > topPosition) {
                    //隐藏按钮
//                    write.setVisibility(View.GONE);
                    //调用动画
                    if(!slideUp) {
                        Animation(write, 0);
                    }
                    scrollStop = false;
                } else if (firstVisibleItem < topPosition) {
//                    write.setVisibility(View.VISIBLE);
                    if(slideUp) {
                        Animation(write, 1);
                    }

                    scrollStop = false;
                }
                topPosition = firstVisibleItem;
            }
        });
        outcomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
//                Toast.makeText(getActivity(),"支出"+flag,Toast.LENGTH_LONG).show();
                detailIncome.setImageResource(R.drawable.icons_income02);
                detailOutcome.setImageResource(R.drawable.icons_outcome1);
                outcomeLayout.setBackgroundResource(R.color.white);
                incomeLayout.setBackgroundResource(R.color.orange);
                type = "outcome";
//                List<Outcome> out = db.findAll(Outcome.class);
                DataBase(date);
            }
        });
        incomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
//                Toast.makeText(getActivity(),"收入"+flag,Toast.LENGTH_LONG).show();
                detailIncome.setImageResource(R.drawable.icons_income1);
                detailOutcome.setImageResource(R.drawable.icons_outcome02);
                outcomeLayout.setBackgroundResource(R.color.orange);
                incomeLayout.setBackgroundResource(R.color.white);
                type = "income";
//                List<Income> in = db.findAll(Income.class);
                DataBase(date);
            }
        });

        return view;
    }

    /**
     * 上下滑动画
     * @param view  控件
     * @param type  0是向下滑,1是向上滑
     */
    public void Animation(View view,int type){
        if(type == 0) {
            ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), view.getTranslationY() + 250).setDuration(400).start();
            slideUp = true;
        }else {
            ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), view.getTranslationY() - 250).setDuration(400).start();
            slideUp = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write:
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("aim", "create");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DataBase(date);
//        DataBase(toolbar);
    }

    public void setTitle(Toolbar toolbar) {
        toolbar.setTitle("账单");
    }

    public void DataBase(final String date) {
        if (flag == 0) {
            final List<Outcome> outcomeList = db.findAllByWhere(Outcome.class, " outcomeMonth=\"" + date + "\"");
            final ArrayList<Float> listAmount = new ArrayList<>();
            final ArrayList<String> listType = new ArrayList<>();
            final ArrayList<String> listTime = new ArrayList<>();

            for (int i = 0; i < outcomeList.size(); i++) {
                listAmount.add(outcomeList.get(i).getOutcomeAmount());
                listType.add(outcomeList.get(i).getOutcomeType());
                listTime.add(outcomeList.get(i).getOutcomeTime());
            }
            final DetailAdapter adapter = new DetailAdapter(getActivity(), listAmount, listTime, listType);
            /*Log.e("outcome",outcomeList.toString());
            List<Map<String ,Object>> listItems = new ArrayList<Map<String,Object>>();
            for(int i = 0;i<outcomeList.size();i++){
                Map<String,Object> listItem = new HashMap<String,Object>();
                listItem.put("Amount", outcomeList.get(i).getOutcomeAmount());
                listItem.put("Type", outcomeList.get(i).getOutcomeType());
                listItem.put("Time", outcomeList.get(i).getOutcomeHour());
                listItem.put("id",outcomeList.get(i).getOutcomeId());
                listItems.add(listItem);
            }
            final SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.simple_item, new String[]{"Amount","Type","Time","id"}, new int[]{R.id.detailAmount,R.id.detailType,R.id.detailTime});
            lv.setAdapter(simpleAdapter);*/
            lv.setAdapter(adapter);
            /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    // TODO Auto-generated method stub
                    HashMap<String, Object> clickItem = (HashMap<String, Object>) lv.getItemAtPosition(arg2);
                    int id = (Integer) clickItem.get("id");
                    Log.v("select", "" + id);
                    Intent intent = new Intent(getActivity(), WriteActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    intent.putExtra("bd", bundle);
                    startActivity(intent);
                }
            });*/
            lv.SetRemoveListener(new SlideListView.RemoveListener() {
                @Override
                public void removeItem(SlideListView.RemoveDirection direction,final int position) {
                    if (direction == SlideListView.RemoveDirection.RIGHT) {
                        Log.e("id", outcomeList.get(position).getOutcomeId() + "");
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("是否确认删除该条记录?");
                        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteById(Outcome.class, outcomeList.get(position).getOutcomeId());
                                DataBase(date);
                            }
                        });
                        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataBase(date);
                                dialog.dismiss();
                            }
                        });
                        builder.show();

                    } else {
                        Intent intent = new Intent(getActivity(), WriteActivity.class);
                        intent.putExtra("id", outcomeList.get(position).getOutcomeId() + "");
                        intent.putExtra("aim", "update");
                        intent.putExtra("type", type);
                        startActivityForResult(intent, UPDATE);
                    }
                }
            });
        } else if (flag == 1) {
            final List<Income> incomeList = db.findAllByWhere(Income.class, " incomeMonth=\"" + date + "\"");
            final ArrayList<Float> listAmount = new ArrayList<>();
            final ArrayList<String> listType = new ArrayList<>();
            final ArrayList<String> listTime = new ArrayList<>();

            for (int i = 0; i < incomeList.size(); i++) {
                listAmount.add(incomeList.get(i).getIncomeAmount());
                listType.add(incomeList.get(i).getIncomeType());
                listTime.add(incomeList.get(i).getIncomeTime());
            }
            final DetailAdapter adapter = new DetailAdapter(getActivity(), listAmount, listTime, listType);
            lv.setAdapter(adapter);
            lv.SetRemoveListener(new SlideListView.RemoveListener() {
                @Override
                public void removeItem(SlideListView.RemoveDirection direction, final int position) {
                    if (direction == SlideListView.RemoveDirection.RIGHT) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("是否确认删除该条记录?");
                        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteById(Income.class, incomeList.get(position).getIncomeId());
                                DataBase(date);
                            }
                        });
                        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataBase(date);
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                    } else {
                        Intent intent = new Intent(getActivity(), WriteActivity.class);
                        intent.putExtra("id", incomeList.get(position).getIncomeId() + "");
                        intent.putExtra("aim", "update");
                        intent.putExtra("type", type);
                        startActivityForResult(intent, UPDATE);
                    }
                }
            });
            /*Log.e("income", incomeList.toString()+"ss");
            List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < incomeList.size(); i++) {
                Map<String, Object> listItem = new HashMap<String, Object>();
                listItem.put("Amount", incomeList.get(i).getIncomeAmount());
                listItem.put("Type", incomeList.get(i).getIncomeType());
                listItem.put("Time", incomeList.get(i).getIncomeHour());
                listItem.put("id", incomeList.get(i).getUserId());
                listItems.add(listItem);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.simple_item, new String[]{"Amount", "Type", "Time", "id"}, new int[]{R.id.detailAmount, R.id.detailType, R.id.detailTime});
            lv.setAdapter(simpleAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    // TODO Auto-generated method stub
                    HashMap<String, Object> clickItem = (HashMap<String, Object>) lv.getItemAtPosition(arg2);
                    int id = (Integer) clickItem.get("id");
                    Intent intent = new Intent(getActivity(), WriteActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    bundle.putInt("flag", flag);
                    intent.putExtra("bd", bundle);
                    startActivityForResult(intent, RequestWrite);
                }
            });*/
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_date:
                Log.e("click", "menu");
                if (icontype == 0) {
                    Log.e("click", "menu1");
                    item.setIcon(R.mipmap.iconfont_zhou);
                    icontype = 1;
                } else {
                    item.setIcon(R.mipmap.iconfont_yue);
                    icontype = 0;
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
