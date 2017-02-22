package zdsoft.myapplication_ssj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    Button button_dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_dl= (Button) findViewById(R.id.button_dl);
        button_dl.setOnClickListener(this);
    }
/**
 * 界面跳转
 * */
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,SSJActivity.class);
        startActivity(intent);
    }
}
