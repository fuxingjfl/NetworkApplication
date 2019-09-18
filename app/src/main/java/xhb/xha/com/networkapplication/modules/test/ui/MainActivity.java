package xhb.xha.com.networkapplication.modules.test.ui;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import butterknife.Bind;
import butterknife.OnClick;
import xhb.xha.com.networkapplication.Base.activity.BaseActivity;
import xhb.xha.com.networkapplication.Base.custom.BaseDialog;
import xhb.xha.com.networkapplication.R;
import xhb.xha.com.networkapplication.custom.TestDialog;
import xhb.xha.com.networkapplication.custom.TestPop;
import xhb.xha.com.networkapplication.modules.test.bean.News;
import xhb.xha.com.networkapplication.modules.test.contract.TestContract;
import xhb.xha.com.networkapplication.modules.test.presenter.TestPresenter;
import xhb.xha.com.networkapplication.utils.DensityUtil;
import xhb.xha.com.networkapplication.utils.StatusBarUtils;

public class MainActivity extends BaseActivity<TestPresenter> implements TestContract.View {

    @Bind(R.id.tv_dinaji)
    TextView tv_dinaji;
    @Bind(R.id.tv_pop)
    TextView tv_pop;
    @Bind(R.id.iv_img)
    ImageView iv_img;

    private TestPop testPop;

    int i=180;

    @OnClick(R.id.tv_dinaji)
    public void onViewClicked(){
        Log.e("TAG","点击到了tv_dinaji====");
        iv_img.animate().rotation(i);
        iv_img.animate().start();
        i+=180;
        //状态栏
//        Eyes.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.colorAccent));
        StatusBarUtils.setColorNoTranslucent(MainActivity.this,getResources().getColor(R.color.colorAccent));

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


    @OnClick(R.id.iv_img)
    public void onImgViewClicked(){
        Toast.makeText(MainActivity.this,"点击到图片啦啦啦啦啦啦,什么孽哒哒哒哒哒",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.tv_pop)
    public void ANpop(){

        //状态栏设置为透明状态栏
//        Eyes.translucentStatusBar(MainActivity.this,true);
        StatusBarUtils.setColorNoTranslucent(MainActivity.this,getResources().getColor(R.color.colorPrimary));


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

    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            setWindowTranslucence(1.0f);
        }
    };


    @Override
    public void initCreateView() {
        setContentView(R.layout.activity_main);
    }

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

        testPop = new TestPop(MainActivity.this,R.layout.layout_testdialog);
        ViewGroup.LayoutParams layoutParams = tv_pop.getLayoutParams();
        layoutParams.width= DensityUtil.dp2px(MainActivity.this,300.5f);
        tv_pop.setLayoutParams(layoutParams);
        mPresenter.getTestData("news_regimen");
        testPop.setOnDismissListener(onDismissListener);

        Picasso.with(MainActivity.this).load("https://222.74.63.136:5000/images/2019071011/11201907101136.jpg").into(iv_img);

    }

    @Override
    public void setStatusBar() {
        super.setStatusBar();

//        StatusBarUtils.setTransparent(this);

        StatusBarUtils.setColorNoTranslucent(MainActivity.this,getResources().getColor(R.color.colorPrimaryDark));
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
