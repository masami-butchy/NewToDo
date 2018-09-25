package android.lifeistech.com.newtodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import io.realm.Realm;

public class ToDoListAdapter extends ArrayAdapter<RealmToDoObject>{

    List<RealmToDoObject> mListComponent;
    Realm realm;
    public ToDoListAdapter(Context context, int layoutResourceId, List<RealmToDoObject> objects, Realm bgrealm){
        super(context, layoutResourceId, objects);

        mListComponent = objects;
        realm = bgrealm;

    }

    @Override
    public RealmToDoObject getItem(int position){
        return mListComponent.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup paremt){
        final ViewHolder viewHolder;

        if(convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_component, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final RealmToDoObject realmToDoObject = getItem(position);

        if (realmToDoObject != null){
            viewHolder.titleTextView.setText(realmToDoObject.title);
            viewHolder.checkBox.setChecked(realmToDoObject.checkBoxisChecked);
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm bgrealm) {
                            if (realmToDoObject.checkBoxisChecked == true){
                                Log.d("WWWWWWWWWWWWWWWW", "XXXXXXXXXXXXXXXXXXX" + realmToDoObject.checkBoxisChecked);
                                realmToDoObject.checkBoxisChecked = false;
                            }
                            else {
                                realmToDoObject.checkBoxisChecked = true;
                            }
                        }
                    });
                }
            });

            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    //intent.putExtra("checkBoxisChecked", item.checkBoxisChecked);
                    intent.putExtra("title", realmToDoObject.title);
                    //intent.putExtra("content", item.content);
                    v.getContext().startActivity(intent);
                }
            });
        }

        return convertView;
    }

    public static  class ViewHolder{

        TextView titleTextView;
        CheckBox checkBox;
        LinearLayout linearLayout;

        public ViewHolder(View view){
            titleTextView = (TextView)view.findViewById(R.id.titleTextView);
            checkBox = (CheckBox)view.findViewById(R.id.checkBox);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        }
    }

}
