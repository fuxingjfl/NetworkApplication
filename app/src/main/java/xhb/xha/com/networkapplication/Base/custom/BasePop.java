package xhb.xha.com.networkapplication.Base.custom;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import xhb.xha.com.networkapplication.R;

/**
 * Created by ysq on 2019/5/21.
 */

public abstract class BasePop extends PopupWindow {

    public Context context;

    public BasePop(Context context){
        this.context=context;

    }




    public abstract void initPopupWindow();


    public void setAttribute(){
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.popwin_anim_style);
//        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
        setFocusable(false);
        setOutsideTouchable(false);
        update();
    }

}
