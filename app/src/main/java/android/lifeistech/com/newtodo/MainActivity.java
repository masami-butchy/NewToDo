package android.lifeistech.com.newtodo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    public int filterSignal;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filterSignal = 0;

        //Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        mListView = (ListView) findViewById(R.id.listViewMain);
        textView = (TextView) findViewById(R.id.todolist);
        textView.setText("All ToDoList");

        //mListComponent = new ArrayList<ListComponent>();

        /*ここにコンテンツをハウスから突っ込むここ例*/
        //
        //

        setListComponent();


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
        setListComponent();
    }

    public void setListComponent(){

        RealmResults<RealmToDoObject> results = null;
        if (filterSignal ==1) {
            results = realm.where(RealmToDoObject.class).equalTo("checkBoxisChecked", true).findAll();
        } else if (filterSignal == 2) {
            results = realm.where(RealmToDoObject.class).equalTo("checkBoxisChecked", false).findAll();
        } else {
            results = realm.where(RealmToDoObject.class).findAll();
        }
        List<RealmToDoObject> item = realm.copyFromRealm(results);
        mToDoListAdapter = new ToDoListAdapter(this, R.layout.activity_list_component, item, realm);
        mListView.setAdapter(mToDoListAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//これは onCreateOptionsMenu(Menu menu){}の前じゃないとここは機能しない。onCreateOptionsMenu でonOptionsItemSelectedを含めた設定が適用されると思われる。

        switch(item.getItemId()) {
            case R.id.menuALL:
                filterSignal = 0;
                textView.setText("All ToDoList");
                setListComponent();
                return true;

            case R.id.menuCOMPLETE:
                filterSignal = 1;
                textView.setText("completed ToDoList");
                setListComponent();
                return true;

            case R.id.menuACTIVE:
                filterSignal = 2;
                textView.setText("Active ToDoList");
                setListComponent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//これはonOptionsItemSelected(MenuItem item){}のあとじゃないとonOptionsItemSelectedが機能しない。ここでonOptionsItemSelectedを含めた設定が適用されると思われる。

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void newAdd(View v){
        Intent intent = new Intent(this, AddNewTodo.class);
        startActivity(intent);
    }

}
