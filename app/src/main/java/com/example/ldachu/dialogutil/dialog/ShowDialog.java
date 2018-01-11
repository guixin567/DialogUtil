package com.example.ldachu.dialogutil.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ldachu.dialogutil.R;

/**
 * @author zxKueen on 2018-01-11 15:26
 *         Email: 4994766@qq.com
 *         展示对话框：加载中，收货成功，加入购物车成功
 */

public class ShowDialog extends BaseDialog {
    private int mBgColor;
    private Drawable mIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.item_show, container);
        setPercent(0.3f);
        setDimAmount(0);
        initData(view);
        return view;
    }

    private void initData(View view) {
        ConstraintLayout container = (ConstraintLayout) view.findViewById(R.id.container);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);

        if(mIcon!=null){
            ivIcon.setImageDrawable(mIcon);
        }

        tvContent.setText(mTitle);
        if(mTitleColor!=0){
            tvContent.setTextColor(mTitleColor);
        }



        //背景的四个圆角
        GradientDrawable drawable_container = new GradientDrawable();
        float[] radii_container ={radius,radius,radius,radius,radius,radius,radius,radius};
        drawable_container.setCornerRadii(radii_container);
        if(mBgColor!=0){
            drawable_container.setColor(mBgColor);
        }else{
            drawable_container.setColor(Color.parseColor("#555555"));
        }
        container.setBackgroundDrawable(drawable_container);
    }




}
