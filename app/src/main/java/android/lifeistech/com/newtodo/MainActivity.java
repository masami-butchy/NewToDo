package android.lifeistech.com.newtodo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    List<ListComponent> mListComponent;
    ToDoListAdapter mToDoListAdapter;
    public ListView mListView;
    FloatingActionButton floatingActionButton;
    public Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        mListView = (ListView) findViewById(R.id.listViewMain);

        //mListComponent = new ArrayList<ListComponent>();

        /*ここにコンテンツをハウスから突っ込むここ例*/
        //
        //

        setmListComponent();


        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              ToDoListAdapter adapter = (ToDoListAdapter) mListView.getAdapter();
              Object item = (Object) adapter.getItem(position);
              String itemString = item.toString();
              Log.d("A",""+itemString);

              Intent intent = new Intent (parent.getContext(),DetailActivity.class);
              startActivity(intent);
          }
      });*/
        // Realm設定ここから
        //Realm.init(this);
        //RealmConfiguration config = new RealmConfiguration.Builder().build();
        //Realm.setDefaultConfiguration(config);
        // Realm設定ここまで

    }
    @Override
    public void onResume(){
        super.onResume();
        setmListComponent();
    }

    public void setmListComponent(){

        RealmResults<RealmToDoObject> results = null;
        results = realm.where(RealmToDoObject.class).findAll();
        List<RealmToDoObject> item = realm.copyFromRealm(results);
        mToDoListAdapter = new ToDoListAdapter(this, R.layout.activity_list_component, item);
        mListView.setAdapter(mToDoListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void newAdd(View v){
        Intent intent = new Intent(this, AddNewTodo.class);
        startActivity(intent);
    }

}
