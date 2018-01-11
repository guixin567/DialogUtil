package com.example.ldachu.dialogutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ldachu.dialogutil.dialog.BaseDialog;
import com.example.ldachu.dialogutil.dialog.CommonDialog;
import com.example.ldachu.dialogutil.dialog.CustomeDialog;
import com.example.ldachu.dialogutil.dialog.ShowDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvCommon;
    private BaseDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTvCommon = (TextView) findViewById(R.id.tv_common);

        mTvCommon.setOnClickListener(this);
        findViewById(R.id.tv_second).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_common:
                initShow();
                break;
            case R.id.tv_second:
//                mShowDialog.dismiss();
                Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initShow() {
        mShowDialog = new ShowDialog()
                .setTitle("收货成功")
                .setOutCancel(false)
                .show(getSupportFragmentManager());
        mShowDialog.dismiss();
    }

    private void initEdit() {
        new CustomeDialog()
                .setMessage("测试内容你懂的有的")
                .setRadius(10)
                .setPercent(0.8f)
                .addDialogListener(new BaseDialog.OnDialogListener() {
                    @Override
                    public void onPositive() {
                        Toast.makeText(MainActivity.this, "加油", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(getSupportFragmentManager());
    }

    private void initCommon() {

        CommonDialog show = new CommonDialog()
                .setTitle("标题你懂的")
                .setMessage("设置内容设置内容设置内容")
                .setGravity(Gravity.CENTER)
                .setDimAmount(0.3f)
                .addDialogListener(new CommonDialog.OnDialogListener() {
                    @Override
                    public void onPositive() {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOutCancel(false)
                .setPositive("确定")
                .setNegative("取消")
                .show(getSupportFragmentManager());



    }
}
