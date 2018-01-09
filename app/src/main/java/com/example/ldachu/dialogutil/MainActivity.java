package com.example.ldachu.dialogutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ldachu.dialogutil.dialog.CommonDialog;
import com.example.ldachu.dialogutil.dialog.EditDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTvCommon = (TextView) findViewById(R.id.tv_common);

        mTvCommon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_common:
                initCommon();
                break;
        }
    }

    private void initEdit() {
        EditDialog editDialog = new EditDialog();
        editDialog.show(getSupportFragmentManager(),"Edit");
    }

    private void initCommon() {
        CommonDialog commonDialog = new CommonDialog();

        commonDialog.getConfirmDialog("标题","内容 ","你好","测试",new CommonDialog.OnDialogListener(){
            @Override
            public void onPositive() {
                Toast.makeText(MainActivity.this, "米亚", Toast.LENGTH_SHORT).show();
            }

        });
        commonDialog.show(getSupportFragmentManager(),"CommonDialog");

        commonDialog.setPercent(1);
        commonDialog.setGravity(Gravity.BOTTOM);
        commonDialog.setDimAmount(0);


    }
}
