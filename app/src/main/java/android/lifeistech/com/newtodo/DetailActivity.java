package android.lifeistech.com.newtodo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.TestLooperManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.TimerTask;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    public CheckBox detailCheckBox;
    public TextView detailTitleTextView, detailContentTextView;
    public Realm realm;
    public RealmToDoObject realmToDoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//戻るボタン
        getSupportActionBar().setHomeButtonEnabled(true);//android.R.id.home というIDでonOptionsItemSelectedで使えるようにする

        realm = Realm.getDefaultInstance();

        detailCheckBox = (CheckBox) findViewById(R.id.detailCheckBox);
        detailTitleTextView = (TextView) findViewById(R.id.detailTitleTextView);
        detailContentTextView = (TextView) findViewById(R.id.detailContentTextView);
        showData();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menuDel:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realmToDoObject.deleteFromRealm();
                    }
                });
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    //クラス内ファンクション関数
    public void showData(){
        realmToDoObject = realm.where(RealmToDoObject.class).equalTo("title", getIntent().getStringExtra("title")).findFirst();
        detailCheckBox.setChecked(realmToDoObject.checkBoxisChecked);
        detailTitleTextView.setText(String.valueOf(realmToDoObject.title));
        detailContentTextView.setText(String.valueOf(realmToDoObject.content));
    }

    //以降ボタン
    public void edit(View v){
        Intent intent = new Intent(this, EditActivity.class);
        //intent.putExtra("checkBoxisChecked", item.checkBoxisChecked);
        intent.putExtra("title", detailTitleTextView.getText());
        //intent.putExtra("content", item.content);
        v.getContext().startActivity(intent);
        startActivity(intent);
    }

    public void checkBoxTouch(final View v){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                if (realmToDoObject.checkBoxisChecked == true){
                    realmToDoObject.checkBoxisChecked = false;
                }
                else {
                    realmToDoObject.checkBoxisChecked = true;
                }
            }
        });

    }

}
