package xhb.xha.com.networkapplication.Base.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import xhb.xha.com.networkapplication.R;

/**
 * Created by ysq on 2019/5/20.
 */

public abstract class BaseDialog {

    public Context context;
    private Display display;
    public View view;
    public Dialog dialog;
    public FrameLayout ll_content;

    public static final String GUIDEANGLE="Guide_angle";//导角的对话框

    public static final String RECTANGLE="rectangle";//矩形的对话框

    public BaseDialog(Context context){
        this.context=context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public BaseDialog builder(String type){
        view = View.inflate(context, R.layout.dialog_layout,null);

//        LayoutInflater from = LayoutInflater.from(context);
//        view = from.inflate(R.layout.dialog_layout, null);

        ll_content=view.findViewById(R.id.ll_content);

        // 调整dialog背景大小
        view.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT));

        // 定义Dialog布局和参数
        if (GUIDEANGLE.equals(type)){
            dialog = new Dialog(context, R.style.Dialog_FS);
        }else{
            dialog = new Dialog(context);
        }
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);

        return this;
    }


    public abstract BaseDialog setTitle(String title);

    public abstract BaseDialog setMsg(String msg);

    public BaseDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public abstract BaseDialog setPositiveButton(String text,
                                                 final View.OnClickListener listener);

    public abstract BaseDialog setNegativeButton(String text,
                                                 final View.OnClickListener listener);

    public void show() {
        dialog.show();
    }

}
