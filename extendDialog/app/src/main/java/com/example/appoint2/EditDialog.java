package com.example.appoint2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/31.
 */

public class EditDialog extends Dialog {
    private Button yes, no;//确定按钮
    private EditText et_phone;//输入电话
    public EditText editText;
    public void getfather(EditText editText){
        this.editText = editText;
    }

//    /**
//     * 设置确定按钮和取消被点击的接口
//     */
//    public interface onYesOnclickListener {
//        void onYesClick(String phone);
//    }
//
//    public interface onNoOnclickListener {
//        void onNoClick();
//    }
//
//    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
//    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public EditDialog(Context context) {
        super(context, R.style.Dialog_Msg);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        yes =findViewById(R.id.yes);
        no =findViewById(R.id.no);
        et_phone = findViewById(R.id.et_phone);
        //初始化界面控件的事件

        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    editText.setText(et_phone.getText().toString());
                    dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   dismiss();
            }
        });
        count();
    }

//    public void setYesOnclickListener( onYesOnclickListener onYesOnclickListener) {
//        this.yesOnclickListener = onYesOnclickListener;
//    }
//
//    public void setNoOnclickListener( onNoOnclickListener onNoOnclickListener) {
//        this.noOnclickListener = onNoOnclickListener;
//    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height= ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }


    public void count(){
        TextView tv_show = (TextView) findViewById(R.id.textView3);

        EditText ed_content = (EditText) findViewById(R.id.et_phone);


        ed_content.addTextChangedListener(new TextWatcher() {

            private CharSequence temp;

            private int editStart;

            private int editEnd;

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                temp = s;

            }

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // tv_show.setText(s);// 将输入的内容实时显示

            }

            @Override

            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                editStart = ed_content.getSelectionStart();

                editEnd = ed_content.getSelectionEnd();

                tv_show.setText(String.valueOf(temp.length()));//此处需要进行强制类型转换

                if (temp.length() > 200) {//条件判断可以实现其他功能

                    s.delete(editStart - 1, editEnd);

                    int tempSelection = editStart;

                    ed_content.setText(s);

                    ed_content.setSelection(tempSelection);
                    Toast.makeText(getContext(), "请输入不超过200字的备注", Toast.LENGTH_SHORT).show();



                }

            }

        });

    }
}
