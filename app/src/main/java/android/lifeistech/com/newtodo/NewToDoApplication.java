package android.lifeistech.com.newtodo;

import android.app.Application;
import android.view.View;

import io.realm.Realm;

public class NewToDoApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(getApplicationContext());
    }
}
