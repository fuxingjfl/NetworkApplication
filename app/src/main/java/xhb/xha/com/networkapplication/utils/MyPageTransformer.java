package xhb.xha.com.networkapplication.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 *
 * Viewpager的滑动样式的自定义实现
 *
 */
public class MyPageTransformer implements ViewPager.PageTransformer {

    final float SCALE_MAX = 0.8f;


    @Override
    public void transformPage(View page, float position) {

        float scale = (position < 0)
                ? ((1 - SCALE_MAX) * position + 1)
                : ((SCALE_MAX - 1) * position + 1);

        if (position>-1&&position<0){//左滑

            page.setAlpha((float) (1.5+position));

//            page.setRotationY(1+position);

//            page.setScaleY(scale);


        }else if(position>0&&position<=1){//右滑

            page.setAlpha((float) (1.5-position));

//            page.setRotationY(1-position);


//            page.setScaleY(scale);

        }else{//停止
            page.setAlpha(1);
//            page.setRotationY(0);
//            page.setScaleY(scale);
        }

        page.setRotationY(-position*20);

    }

}
