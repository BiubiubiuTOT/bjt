package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.adapter.TipsAdapter;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.PoiAddressBean;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchLocationActivity extends BaseWhiteToolBarActivity implements PoiSearch.OnPoiSearchListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText et_search;
    private TipsAdapter mAdapter;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private List<PoiAddressBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        mAdapter = new TipsAdapter(list, mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new TipsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                List<PoiAddressBean> data = mAdapter.getLists();
                Double latitude = Double.valueOf(data.get(pos).getLatitude()).doubleValue();
                Double longitude = Double.valueOf(data.get(pos).getLongitude()).doubleValue();

                Intent intent = new Intent();
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                setResult(RESULT_OK, intent);
                finish();

                Log.d("ceshi", "postion" + pos + "lat" + data.get(pos).getLatitude() + "long:" + data.get(pos).getLongitude());
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String keyWord = String.valueOf(charSequence);
                if (!keyWord.isEmpty()) {
                    doSearchQuery(keyWord);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /***
     * POI搜索变
     * @param
     */
    private void doSearchQuery(String keyWord) {
        //不输入城市名称有些地方搜索不到
        query = new PoiSearch.Query(keyWord, "", "贵阳");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        //这里没有做分页加载了,默认给50条数据
        query.setPageSize(50);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);// 设置查第一页

        poiSearch = new PoiSearch(mContext, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();

    }

    /**
     * POI信息查询回调方法
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {

        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {  // 搜索poi的结果
                if (result.getQuery().equals(query)) {  // 是否是同一条
                    final ArrayList<PoiAddressBean> data = new ArrayList<PoiAddressBean>();//自己创建的数据集合
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = result
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    for (PoiItem item : poiItems) {
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        double lon = llp.getLongitude();
                        double lat = llp.getLatitude();

                        String title = item.getTitle();
                        String text = item.getSnippet();
                        String provinceName = item.getProvinceName();
                        String cityName = item.getCityName();
                        String adName = item.getAdName();
                        data.add(new PoiAddressBean(String.valueOf(lon), String.valueOf(lat), title, text, provinceName,
                                cityName, adName));
                    }

                    mAdapter.setLists(data);
                }
            } else {
                Toast.makeText(mContext, "no_result", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(mContext, "" + rCode, Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * POI信息查询回调方法
     */
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_seach_location;
    }

    @Override
    protected String getTitleStr() {
        return "搜索位置";
    }

}
