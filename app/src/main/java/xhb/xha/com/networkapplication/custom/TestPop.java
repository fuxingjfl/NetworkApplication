package xhb.xha.com.networkapplication.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.viewbinding.ViewBinding;
import android.widget.LinearLayout;
import android.widget.TextView;

import xhb.xha.com.networkapplication.Base.custom.BasePop;
import xhb.xha.com.networkapplication.R;
import xhb.xha.com.networkapplication.databinding.LayoutTestdialogBinding;

/**
 * Created by ysq on 2019/5/21.
 */

public class TestPop extends BasePop {

    private LayoutTestdialogBinding resId;
    private LayoutInflater inflater;
    public View defaultView;

    private TextView tv_shgx;
    public TestPop(Context context, LayoutTestdialogBinding resId) {
        super(context);
        this.resId=resId;
        initPopupWindow();
    }

    @Override
    public void initPopupWindow() {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        defaultView = resId.getRoot();
        defaultView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        setContentView(defaultView);

        tv_shgx=resId.tvShgx;

        tv_shgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

        setAttribute();
    }


}
