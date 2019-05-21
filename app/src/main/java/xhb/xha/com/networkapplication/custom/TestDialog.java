package xhb.xha.com.networkapplication.custom;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import xhb.xha.com.networkapplication.Base.custom.BaseDialog;
import xhb.xha.com.networkapplication.R;
import xhb.xha.com.networkapplication.modules.test.contract.TestContract;

/**
 * Created by ysq on 2019/5/20.
 */

public class TestDialog extends BaseDialog{

    private TextView tv_ts,tv_msg,tv_shgx,tv_xzgx;


    public TestDialog(Context context) {
        super(context);
    }


    @Override
    public BaseDialog builder(String type) {
        super.builder(type);
        View contentview  = View.inflate(context, R.layout.layout_testdialog,null);
        tv_ts= (TextView) contentview.findViewById(R.id.tv_ts);
        tv_msg= (TextView) contentview.findViewById(R.id.tv_msg);
        tv_shgx= (TextView) contentview.findViewById(R.id.tv_shgx);
        tv_xzgx= (TextView) contentview.findViewById(R.id.tv_xzgx);
        ll_content.addView(contentview);


        return this;
    }

    @Override
    public BaseDialog setTitle(String title) {
        tv_ts.setText(title);
        return this;
    }

    @Override
    public BaseDialog setMsg(String msg) {
        tv_msg.setText(msg);
        return this;
    }

    @Override
    public BaseDialog setPositiveButton(String text, View.OnClickListener listener) {
        tv_shgx.setText(text);
        tv_shgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    @Override
    public BaseDialog setNegativeButton(String text, View.OnClickListener listener) {
        tv_xzgx.setText(text);
        tv_xzgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }
}
