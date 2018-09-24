package android.lifeistech.com.newtodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ToDoListAdapter extends ArrayAdapter<RealmToDoObject>{

    List<RealmToDoObject> mListComponent;

    public ToDoListAdapter(Context context, int layoutResourceId, List<RealmToDoObject> objects){
        super(context, layoutResourceId, objects);

        mListComponent = objects;
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

        final RealmToDoObject item = getItem(position);

        if (item != null){
            viewHolder.titleTextView.setText(item.title);
            viewHolder.checkBox.setChecked(item.checkBoxisChecked);
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.checkBox.isChecked()){
                        viewHolder.checkBox.setChecked(false);
                    }
                    else if (viewHolder.checkBox.isChecked()){
                        viewHolder.checkBox.setChecked(true);
                    }
                }
            });

            viewHolder.titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DetailActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

        return convertView;
    }

    public static  class ViewHolder{

        TextView titleTextView;
        CheckBox checkBox;

        public ViewHolder(View view){
            titleTextView = (TextView)view.findViewById(R.id.titleTextView);
            checkBox = (CheckBox)view.findViewById(R.id.checkBox);
        }
    }

}
