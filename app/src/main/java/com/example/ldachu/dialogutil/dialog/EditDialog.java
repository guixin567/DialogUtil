package com.example.ldachu.dialogutil.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.ldachu.dialogutil.R;

/**
 * @author zxKueen on 2018-01-09 10:42
 *         Email: 4994766@qq.com
 */

public class EditDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("标题")
                .setView(R.layout.dialog_edit)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                ;
//        View view = getLayoutInflater().inflate(R.layout.dialog_edit, null);

        builder.show();
        return builder.create();
    }
}
