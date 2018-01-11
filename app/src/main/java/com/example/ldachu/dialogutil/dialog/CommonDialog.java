package com.example.ldachu.dialogutil.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * @author zxKueen on 2018-01-08 11:33
 *         Email: 4994766@qq.com
 *         普通对话框
 */

public class CommonDialog extends AppCompatDialogFragment {


    private OnDialogListener mListener;
    private String      mTitle;
    private String      mMessage;
    private String      mPositive="确定";
    private String      mNegative="取消";
    private AlertDialog mDialog;
    private int         mButtonColor;
    private int         mTitleColor;
    private int         mMesaggeColor;
    private float mDimAmount = -1;
    private float mPercent = 0.9f;
    private int mGravity;
    private boolean isOutCancel=true;
    private int resId;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



        if(!TextUtils.isEmpty(mTitle)){
            builder.setTitle(mTitle);
        }
        builder
                .setMessage(mMessage)
                .setPositiveButton(mPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositive();
                    }
                })
                .setNegativeButton(mNegative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNegative();
                    }
                });

        mDialog = builder.create();
        mDialog.show();
        //设置按钮的颜色,后期设置按钮背景颜色
        if(mButtonColor!=0&&mDialog!=null){
            mDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mButtonColor);
            mDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(mButtonColor);
        }

        //通过反射设置标题和内容字体颜色
        Field mAlert = null;
        try {
            mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(mDialog);

            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            Field mTitle = mAlertController.getClass().getDeclaredField("mTitleView");

            mMessage.setAccessible(true);
            mTitle.setAccessible(true);
            //通过反射获取标题和内容的TextView
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            TextView mTitleView = (TextView) mTitle.get(mAlertController);
            //设置标题和内容字体颜色
            if(mTitleColor!=0){
                mTitleView.setTextColor(mTitleColor);
            }
           if(mMesaggeColor!=0){
               mMessageView.setTextColor(mMesaggeColor);
           }

            //可以设置文本相关的，字体大小


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }




        Window window = mDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        //透明度
        if(mDimAmount!=-1){
            lp.dimAmount =mDimAmount;
        }
        //显示的位置，中间，底部
        if(mGravity!=0){
            lp.gravity = mGravity;
        }

        Display display = window.getWindowManager().getDefaultDisplay();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int currentWidth = 0;
        //设置屏幕宽度,默认是0.9
        if(mPercent!=0){
            currentWidth = (int) (display.getWidth()*mPercent);
            lp.width = currentWidth;
        }
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        getActivity().getWindow().getDecorView().setPadding(0,0,0,0);
        window.setBackgroundDrawableResource(android.R.color.white);
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        window.setAttributes(lp);

        //设置动画
        if(resId!=0){
            window.setWindowAnimations(resId);
        }
        //设置对话框点击外部不取消,默认是可取消
        mDialog.setCanceledOnTouchOutside(isOutCancel);
        return  mDialog;
    }




    /**
     * 设置按钮颜色
     * @param buttonColor
     */
    public CommonDialog setButtonColor(int buttonColor){
        mButtonColor = buttonColor;
        return this;
    }

    /**
     * 设置标题颜色
     * @param titleColor
     */
    public CommonDialog setTitleColor(int titleColor){

        mTitleColor = titleColor;
        return this;
    }


    /**
     * 设置内容文本颜色
     * @param mesaggeColor
     */
    public CommonDialog setMesaggeColor(int mesaggeColor){
        mMesaggeColor = mesaggeColor;
        return this;

    }

    /**
     * 设置背景透明度0-1
     * @param dimAmount
     */
    public CommonDialog setDimAmount(float dimAmount) {
        mDimAmount = dimAmount;
        return this;
    }


    /**
     * 设置位置
     * @param gravity
     */
    public CommonDialog setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    /**
     * 设置屏幕宽度的比例
     * @param percent
     */
    public CommonDialog setPercent(float percent) {
        mPercent = percent;
        return this;
    }

    /**
     * 设置外部是否可点击取消
     * @param isOutCancel
     */
    public CommonDialog setOutCancel(boolean isOutCancel) {
        this.isOutCancel = isOutCancel;
        return this;
    }


    /**
     * 设置标题
     * @param title
     */
    public CommonDialog setTitle(String title) {
        mTitle = title;
        return this;
    }

    /**
     * 设置内容
     * @param message
     * @return
     */
    public CommonDialog setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * 设置确定按钮文本
     * @param positive
     * @return
     */
    public CommonDialog setPositive(String positive) {
        mPositive = positive;
        return this;
    }

    /**
     * 设置取消按钮文本
     * @param negative
     * @return
     */
    public CommonDialog setNegative(String negative) {
        mNegative = negative;
        return this;
    }
    /**
     * 设置动画
     * @param resId
     */
    public CommonDialog setResId(int resId) {
        this.resId = resId;
        return this;
    }

    /**
     * 设置监听事件
     * @param listener
     */
    public CommonDialog addDialogListener(OnDialogListener listener) {
        mListener = listener;
        return this;
    }


    /**
     * 显示对话框
     * @param manager
     * @return
     */
    public CommonDialog show(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
    }

    public abstract static class OnDialogListener{
        /**
         * 确定按钮
         */
        public abstract void onPositive();

        /**
         * 取消按钮
         */
        public void onNegative(){};
    }

}
