package com.yushilei.animatorscale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
    }

    public void jump(View view) {
        startActivity(new Intent(this, RecyActivity.class));
    }
}
