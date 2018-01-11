package com.example.ldachu.dialogutil.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ldachu.dialogutil.R;

/**
 * @author zxKueen on 2018-01-10 12:12
 *         Email: 4994766@qq.com
 */

public class CustomeDialog extends BaseDialog implements View.OnClickListener {
    private TextView  mTvMessage;
    private Guideline mGlV;
    private View      mVLeft;
    private View      mVRight;
    private TextView  mTvCancel;
    private View      mVCenter;
    private TextView  mTvConfirm;
    private TextView  mTvTitle;
    private ConstraintLayout mContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_custome, container);
        initView(view);
        initData();
        return view;
    }



    /**
     * 视图初始化
     */
    private void initView(View view) {

        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
        mTvMessage.setOnClickListener(this);
        mGlV = (Guideline) view.findViewById(R.id.gl_v);
        mGlV.setOnClickListener(this);
        mVLeft = (View) view.findViewById(R.id.v_left);
        mVLeft.setOnClickListener(this);
        mVRight = (View) view.findViewById(R.id.v_right);
        mVRight.setOnClickListener(this);
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(this);
        mVCenter = (View) view.findViewById(R.id.v_center);
        mVCenter.setOnClickListener(this);
        mTvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        mTvConfirm.setOnClickListener(this);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(this);
        mContainer = (ConstraintLayout) view.findViewById(R.id.container);
    }



    /**
     * 初始化数据
     */
    private void initData() {
        //设置标题
        mTvTitle.setText(mTitle);
        if(mTitleColor!=0){
            mTvTitle.setTextColor(mTitleColor);
        }

        //内容
        mTvMessage.setText(mMessage);
        if(mMesaggeColor!=0){
            mTvMessage.setTextColor(mMesaggeColor);
        }
        //按钮
        mTvCancel.setText(mNegative);
        mTvConfirm.setText(mPositive);

        //右侧按钮背景颜色
        if(mRightButtonBgColor!=0){
            GradientDrawable drawable = new GradientDrawable();
            float[] radii ={0,0,0,0,radius,radius,0,0};
            drawable.setCornerRadii(radii);
            drawable.setColor(mRightButtonBgColor);
            mTvConfirm.setBackgroundDrawable(drawable);
            mVCenter.setVisibility(View.GONE);
            mVRight.setBackgroundColor(mRightButtonBgColor);
        }else{
            //右边按钮的右下角
            GradientDrawable drawable_right = new GradientDrawable();
            float[] radii_right ={0,0,0,0,radius,radius,0,0};
            drawable_right.setCornerRadii(radii_right);
            mTvConfirm.setBackgroundDrawable(drawable_right);
        }



        //右侧按钮文字颜色
        if(mRightButtonColor!=0){
            mTvConfirm.setTextColor(mRightButtonColor);
        }

        //背景的四个圆角
        GradientDrawable drawable_container = new GradientDrawable();
        float[] radii_container ={radius,radius,radius,radius,radius,radius,radius,radius};
        drawable_container.setCornerRadii(radii_container);
        drawable_container.setColor(Color.parseColor("#ffffff"));
        mContainer.setBackgroundDrawable(drawable_container);
        //左边按钮的左下角
        GradientDrawable drawable_left = new GradientDrawable();
        float[] radii_left ={0,0,0,0,0,0,radius,radius};
        drawable_left.setCornerRadii(radii_left);
        mTvCancel.setBackgroundDrawable(drawable_left);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                if(mListener!=null){
                    mListener.onNegative();
                }
                dismiss();
                break;
            case R.id.tv_confirm:
                if(mListener!=null){
                    mListener.onPositive();
                }
                dismiss();
                break;
            default:
                break;
        }
    }








}
