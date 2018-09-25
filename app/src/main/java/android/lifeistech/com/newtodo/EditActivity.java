package android.lifeistech.com.newtodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class EditActivity extends AppCompatActivity {

    public Realm realm;
    public RealmToDoObject realmToDoObject;

    public EditText editTitleText, editContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);
        realm = Realm.getDefaultInstance();

        editTitleText = (EditText) findViewById(R.id.editTitleText);
        editContentTextView = (EditText) findViewById(R.id.editContentText);

        showDataInEditActivity();
    }

    public void showDataInEditActivity(){
        realmToDoObject = realm.where(RealmToDoObject.class).equalTo("title", getIntent().getStringExtra("title")).findFirst();
        editTitleText.setText(String.valueOf(realmToDoObject.title));
        editContentTextView.setText(String.valueOf(realmToDoObject.content));
    }

    public void create(View v){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmToDoObject.title = (String)editTitleText.getText().toString();
                realmToDoObject.content = (String)editContentTextView.getText().toString();
            }
        });
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //遷移先のActivityが既存の場合、それより上のActivityを削除
        //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); //遷移先のActivityを再構成しない
        intent.putExtra("title", editTitleText.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }

}
