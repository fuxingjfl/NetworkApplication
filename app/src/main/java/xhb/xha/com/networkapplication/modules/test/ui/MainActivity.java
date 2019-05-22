package xhb.xha.com.networkapplication.modules.test.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.collect.SortedMultiset;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import com.lwkandroid.rtpermission.RTPermission;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import butterknife.Bind;
import butterknife.OnClick;
import okio.Okio;
import xhb.xha.com.networkapplication.Base.activity.BaseActivity;
import xhb.xha.com.networkapplication.Base.custom.BaseDialog;
import xhb.xha.com.networkapplication.R;
import xhb.xha.com.networkapplication.custom.TestDialog;
import xhb.xha.com.networkapplication.custom.TestPop;
import xhb.xha.com.networkapplication.modules.test.bean.News;
import xhb.xha.com.networkapplication.modules.test.contract.TestContract;
import xhb.xha.com.networkapplication.modules.test.presenter.TestPresenter;
import xhb.xha.com.networkapplication.statusbar.Eyes;

public class MainActivity extends BaseActivity<TestPresenter> implements TestContract.View {

    @Bind(R.id.tv_dinaji)
    TextView tv_dinaji;
    @Bind(R.id.tv_pop)
    TextView tv_pop;

    private TestPop testPop;

    @OnClick(R.id.tv_dinaji)
    public void onViewClicked(){
        Log.e("TAG","点击到了tv_dinaji====");
        Eyes.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.colorAccent));
        TestDialog testDialog = (TestDialog) new TestDialog(MainActivity.this).builder(BaseDialog.GUIDEANGLE)
                .setTitle("版本：")
                .setMsg("本次更新：" )
                .setNegativeButton("下载更新", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("TAG","点击下载更新");
                    }
                });
                testDialog.setPositiveButton("稍后更新", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("TAG","点击稍后更新");
                    }
                });
            testDialog.show();
    }


    @OnClick(R.id.tv_pop)
    public void ANpop(){

        //状态栏设置为透明状态栏
        Eyes.translucentStatusBar(MainActivity.this,true);

        if (testPop != null) {
            if (testPop.isShowing()) {
                testPop.dismiss();
            } else {
                setWindowTranslucence(0.3);
                testPop.showAtLocation(tv_dinaji, Gravity.BOTTOM, 0, 0);
            }
        }
    }

    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        testPop = new TestPop(MainActivity.this,R.layout.layout_testdialog);
        testPop.setOnDismissListener(onDismissListener);

//        news_hot
        mPresenter.getTestData("news_regimen");

    }
    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            setWindowTranslucence(1.0f);
        }
    };
    @Override
    public void initData() {
        super.initData();

        ImmutableSet<String> foobar= ImmutableSortedSet.of("a","foot","fa","b","d","t");
        ImmutableSet<String> names = ImmutableSet.of("vivo", "oppo", "HUWEI");
        ImmutableList<String> strings = ImmutableList.copyOf(foobar);
        Log.e("TAG","strings==="+strings.toString());

        ImmutableMultiset<String> aaa = ImmutableMultiset.of("a","a","fafaf","c");
        Log.e("TAG","aaa==="+aaa.toString());
        SortedMultiset<String> bbb = ImmutableSortedMultiset.of("a","a","fafaf","c");
        Log.e("TAG","bbb==="+bbb.toString());
        SortedMultiset<Integer> ccc = ImmutableSortedMultiset.of(3,2,5,7);
        Log.e("TAG","ccc==="+ccc.toString());


        Set<List<String>> lists1 = Sets.cartesianProduct(names, foobar);
        Log.e("TAG","笛卡尔==="+lists1.toString());



        Multiset<Object> multiset = HashMultiset.create();
        multiset.add("3232");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add("优秀");
        multiset.add(3);
        multiset.add(3);
        multiset.add("我的",6);

        Multiset<Object> mu = HashMultiset.create();
        mu.add("我的",3);
        boolean b = multiset.containsAll(mu);
        multiset.removeAll(mu);
        boolean empty = multiset.isEmpty();
        Log.e("TAG", "&&&&===b="+b+",multiset==="+multiset.toString()+",empty==="+empty);

        int 优秀 = multiset.count("优秀");
        Log.e("TAG", "000000Multiset===" + multiset.toString()+"优秀===" + 优秀+",总数==="+multiset.size());
        multiset.remove("优秀",4);
        优秀 = multiset.count("优秀");
        Log.e("TAG", "111111Multiset===" + multiset.toString()+"优秀===" + 优秀+",总数==="+multiset.size());


        Multimap<String,Object> map = HashMultimap.create();
        map.put("key1","不知道");
        map.put("key1","我日");
        map.putAll("key2",multiset);
        Log.e("TAG", "map===" + map.toString());


        Map<String, Collection<Object>> stringCollectionMap = map.asMap();
        Log.e("TAG", "stringCollectionMap===" + stringCollectionMap.values());

        ArrayListMultimap<String,Object> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("key1","不知道");
        arrayListMultimap.removeAll("key1");
        arrayListMultimap.put("key1","我日");
        arrayListMultimap.putAll("key2",multiset);
        Log.e("TAG", "arrayListMultimap===" + arrayListMultimap.toString());


        BiMap<String,Object> biMap1 = HashBiMap.create();
        biMap1.put("key1","不知道");
        biMap1.put("key1","我日");
        biMap1.putIfAbsent("key2",multiset);

        BiMap<Object, String> biMap2 = biMap1.inverse();
        Log.e("TAG", "biMap1===" + biMap1.toString()+",biMap2"+biMap2);

        ArrayList lists = Lists.newArrayListWithCapacity(2);
        lists.add("4343");
        lists.add("hahah");
        lists.add("再次添加");
        Log.e("TAG", "lists===" + lists);

        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(3, 7, 9),
                Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6

        Log.e("TAG", "concatenated===" + concatenated.toString());

        Maps.filterValues(biMap1, new Predicate<Object>() {
            @Override
            public boolean apply(@NullableDecl Object input) {
                Log.e("TAG", "执行到------===apply" );
                return false;
            }
        });
    }

    @Override
    public void testSuccess(List<News> newsList) {

        Log.e("TAG","数据条数==="+newsList.size());

        for (News news : newsList) {

            Log.e("TAG","title==="+news.title);

        }
    }

    @Override
    public void showError() {
        super.showError();

        Log.e("TAG","执行失败了......");

    }

}
