package android.lifeistech.com.newtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

public class ListComponent{

    public String title;
    public boolean checkBoxisChecked;

    public ListComponent(boolean checkBoxisChecked, String title){
        this.title = title;
        this.checkBoxisChecked = checkBoxisChecked;
    }
}
