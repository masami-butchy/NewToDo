package android.lifeistech.com.newtodo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.TimerTask;

public class DetailActivity extends AppCompatActivity {

    CheckBox checkBox;
    TextView detailTitleTextView, detailContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void edit(View v){
        Intent intent = new Intent(this, AddNewTodo.class);
        startActivity(intent);
    }
}
