package com.example.ldachu.dialogutil.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
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
    private String mTitle;
    private String mMessage;
    private String mPositive;
    private String mNegative;
    private AlertDialog mDialog;
    private int mButtonColor;
    private int mTitleColor;
    private int mMesaggeColor;
    private int mDimAmount;
    private int mPercent;


    private int mGravity;

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
        //设置按钮的颜色
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
        if(mDimAmount!=0){
            lp.dimAmount =mDimAmount;
        }
        //显示的位置，中间，底部
        if(mGravity!=0){
            lp.gravity = mGravity;
        }

        /*Display display = window.getDecorView().getDisplay();
        int currentWidth = 0;
        if(mPercent!=0){
            currentWidth = (int)(display.getWidth()*mPercent);
        }

        lp.width = currentWidth;
*/
        window.setAttributes(lp);

        return  mDialog;
    }


    /**
     *
     * @param title 标题
     * @param message 内容
     * @param listener 监听事件
     */
    public void getConfirmDialog(String title,String message,OnDialogListener listener){
        getConfirmDialog(title,message,"确定","取消",listener);
    }

    /**
     * 基本信息
     * @param title 标题
     * @param message 内容
     * @param positive 确定文本
     * @param negative 取消文本
     * @param listener 监听事件
     */
    public void getConfirmDialog(String title,String message,String positive,String negative,OnDialogListener listener){
        mTitle = title;
        mMessage = message;
        mPositive = positive;
        mNegative = negative;
        mListener = listener;
    }


    /**
     * 设置按钮颜色
     * @param buttonColor
     */
    public void setButtonColor(int buttonColor){

        mButtonColor = buttonColor;
    }

    /**
     * 设置标题颜色
     * @param titleColor
     */
    public void setTitleColor(int titleColor){

        mTitleColor = titleColor;
    }


    /**
     * 设置内容文本颜色
     * @param mesaggeColor
     */
    public void setMesaggeColor(int mesaggeColor){

        mMesaggeColor = mesaggeColor;
    }

    /**
     * 设置背景透明度0-1
     * @param dimAmount
     */
    public void setDimAmount(int dimAmount) {
        mDimAmount = dimAmount;
    }


    /**
     * 设置位置
     * @param gravity
     */
    public void setGravity(int gravity) {
        mGravity = gravity;
    }

    /**
     * 设置屏幕宽度的比例
     * @param percent
     */
    public void setPercent(int percent) {
        mPercent = percent;
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
