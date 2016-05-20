package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.q97531x.myapplication.R;
import com.example.q97531x.myapplication.RemindNewActivity;

import net.tsz.afinal.FinalDb;

import java.util.List;

import Widget.ContactsDialog;
import model.Contact;

/**
 * Created by q97531x on 2016/5/15.
 */
public class RemindAdapter extends BaseAdapter{
    private Context context;
    private List<Contact> contactList;
    RemindNewActivity activity;
    private FinalDb db;
    public RemindAdapter(Context context,FinalDb db,RemindNewActivity activity) {
        this.context = context;
        this.db = db;
        this.activity = activity;
        this.contactList = db.findAll(Contact.class);
        Log.e("size",contactList.size()+"");
    }

    @Override
    public int getCount() {
        return contactList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.number = (TextView)convertView.findViewById(R.id.number);
            holder.add = (ImageView)convertView.findViewById(R.id.add);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == getCount()-1){
            holder.name.setVisibility(View.GONE);
            holder.add.setVisibility(View.VISIBLE);
            holder.number.setText("添加其他对象");
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //弹框添加联系人
                    final ContactsDialog contactsDialog = new ContactsDialog(context);
                    contactsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    contactsDialog.show();
                    contactsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if(!contactsDialog.isCancel()) {
                                String phoneNum = contactsDialog.getNumber();
                                String phoneName = contactsDialog.getPhone_name();
                                Contact contact = new Contact();
                                contact.setName(phoneName);
                                contact.setPhoneNumber(phoneNum);
                                db.save(contact);
                                activity.refleshAdapter();
                            }
                        }
                    });
                }
            });
        }else {
            holder.name.setVisibility(View.VISIBLE);
            holder.add.setVisibility(View.GONE);
            holder.name.setText(contactList.get(position).getName());
            holder.number.setText(contactList.get(position).getPhoneNumber());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView number;
        ImageView add;
    }
}
