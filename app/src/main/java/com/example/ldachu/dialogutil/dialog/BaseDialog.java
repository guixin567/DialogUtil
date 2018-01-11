package com.example.ldachu.dialogutil.dialog;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author zxKueen on 2018-01-11 9:16
 *         Email: 4994766@qq.com
 *         对话框的基类,用于一些常见设置
 */

public class BaseDialog extends AppCompatDialogFragment {

    //按钮的监听事件
    public OnDialogListener mListener;
    //标题
    public String      mTitle;
    //内容
    public String      mMessage;
    //右边按钮文本
    public String      mPositive="确定";
    //左边按钮文本
    public String      mNegative="取消";
    //右边按钮的文本颜色
    public int         mRightButtonColor;
    //右边按钮的背景颜色
    public int         mRightButtonBgColor;
    //左边按钮的文本颜色
    public int         mLeftButtonColor;
    //标题文本颜色
    public int         mTitleColor;
    //内容文本颜色
    public int         mMesaggeColor;
    //背景的透明度0-1，默认是50%
    public float mDimAmount = -1;
    //对话框的宽度，默认是屏幕宽度的90%
    public float mPercent = 0.9f;
    //对话框的位置，默认是居中
    public int mGravity;
    //点击对话框外是否可取消对话框
    public boolean isOutCancel=true;
    //设置对话框进入退出动画
    public int resId;
    //圆角,默认是10
    public int radius = 10;
    private int mBgColor;
    private Drawable mIcon;


    @Override
    public void onStart() {

        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        //透明度
        if (mDimAmount != -1) {
            lp.dimAmount = mDimAmount;
        }
        //显示的位置，中间，底部
        if (mGravity != 0) {
            lp.gravity = mGravity;
        }

        Display display = window.getWindowManager().getDefaultDisplay();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int currentWidth = 0;
        //设置屏幕宽度,默认是0.9
        if (mPercent != 0) {
            currentWidth = (int) (display.getWidth() * mPercent);
            lp.width = currentWidth;
        }


        window.setAttributes(lp);

        //设置动画
        if (resId != 0) {
            window.setWindowAnimations(resId);
        }

        //设置对话框点击外部不取消,默认是可取消
        getDialog().setCanceledOnTouchOutside(isOutCancel);
    }

    /**
     * 设置左边按钮颜色
     * @param mLeftButtonColor
     */
    public BaseDialog setLeftButtonColor(int mLeftButtonColor){
        this.mLeftButtonColor = mLeftButtonColor;
        return this;
    }

    /**
     * 设置右边按钮颜色
     * @param mRightButtonColor
     */
    public BaseDialog setRightButtonColor(int mRightButtonColor){
        this.mRightButtonColor = mRightButtonColor;
        return this;
    }

    /**
     * 设置右边按钮背景颜色
     * @param mRightButtonBgColor
     */
    public BaseDialog setRightButtonBgColor(int mRightButtonBgColor){
        this.mRightButtonBgColor = mRightButtonBgColor;
        return this;
    }


    /**
     * 设置标题颜色
     * @param titleColor
     */
    public BaseDialog setTitleColor(int titleColor){

        mTitleColor = titleColor;
        return this;
    }


    /**
     * 设置内容文本颜色
     * @param mesaggeColor
     */
    public BaseDialog setMesaggeColor(int mesaggeColor){
        mMesaggeColor = mesaggeColor;
        return this;

    }

    /**
     * 设置背景透明度0-1
     * @param dimAmount
     */
    public BaseDialog setDimAmount(float dimAmount) {
        mDimAmount = dimAmount;
        return this;
    }


    /**
     * 设置位置
     * @param gravity
     */
    public BaseDialog setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    /**
     * 设置屏幕宽度的比例
     * @param percent
     */
    public BaseDialog setPercent(float percent) {
        mPercent = percent;
        return this;
    }

    /**
     * 设置外部是否可点击取消
     * @param isOutCancel
     */
    public BaseDialog setOutCancel(boolean isOutCancel) {
        this.isOutCancel = isOutCancel;
        return this;
    }


    /**
     * 设置标题
     * @param title
     */
    public BaseDialog setTitle(String title) {
        mTitle = title;
        return this;
    }

    /**
     * 设置内容
     * @param message
     * @return
     */
    public BaseDialog setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * 设置确定按钮文本
     * @param positive
     * @return
     */
    public BaseDialog setPositive(String positive) {
        mPositive = positive;
        return this;
    }

    /**
     * 设置取消按钮文本
     * @param negative
     * @return
     */
    public BaseDialog setNegative(String negative) {
        mNegative = negative;
        return this;
    }
    /**
     * 设置动画
     * @param resId
     */
    public BaseDialog setResId(int resId) {
        this.resId = resId;
        return this;
    }


    public BaseDialog setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    /**
     * 设置监听事件
     * @param listener
     */
    public BaseDialog addDialogListener(OnDialogListener listener) {
        mListener = listener;
        return this;
    }
    /**
     * 显示对话框
     * @param manager
     * @return
     */
    public BaseDialog show(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
    }

    /**
     * 设置整个Dialog的背景颜色，主要用于展示对话框默认是#555
     * @param bgColor
     * @return
     */
    public BaseDialog setBackground(int bgColor){
        mBgColor = bgColor;
        return this;
    }


    /**
     * 设置展示对话框的ICON，默认是对号
     * @param Icon
     * @return
     */
    public BaseDialog setIcon(Drawable Icon){
        mIcon = Icon;
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
