package android.lifeistech.com.newtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class AddNewTodo extends AppCompatActivity {

    public Realm realm;

    EditText editTitleText, editContentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);

        realm = Realm.getDefaultInstance();

        editTitleText = findViewById(R.id.editTitleText);
        editContentText = findViewById(R.id.editContentText);

    }

    public void save(final String title, final String content){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
               RealmToDoObject realmToDoObject = realm.createObject(RealmToDoObject.class);
               realmToDoObject.title = title;
               realmToDoObject.content = content;
               realmToDoObject.checkBoxisChecked = false;
            }
        });
    }

    public void create(View v){
        String title = editTitleText.getText().toString();
        String content = editContentText.getText().toString();
        save(title, content);
        Log.d("wwwwwwwwww","" + title);
        finish();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        realm.close();
    }



}
