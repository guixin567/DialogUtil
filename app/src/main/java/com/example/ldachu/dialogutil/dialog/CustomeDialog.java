package com.example.ldachu.dialogutil.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ldachu.dialogutil.R;

/**
 * @author zxKueen on 2018-01-10 12:12
 *         Email: 4994766@qq.com
 */

public class CustomeDialog extends AppCompatDialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_custome, container);
        return view;

    }
}
