package xhb.xha.com.networkapplication.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import xhb.xha.com.networkapplication.Base.custom.BasePop;

/**
 * Created by ysq on 2019/5/21.
 */

public class TestPop extends BasePop {

    private int resId;
    private LayoutInflater inflater;
    public View defaultView;

    public TestPop(Context context,int resId) {
        super(context);
        this.resId=resId;
        initPopupWindow();
    }

    @Override
    public void initPopupWindow() {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        defaultView = inflater.inflate(this.resId, null);
        defaultView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        setContentView(defaultView);

        setAttribute();
    }


}
