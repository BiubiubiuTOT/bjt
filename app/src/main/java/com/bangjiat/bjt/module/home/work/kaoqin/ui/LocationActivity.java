package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.adapter.PoiAdapter;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.PoiString;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class LocationActivity extends BaseToolBarActivity implements GeocodeSearch.OnGeocodeSearchListener,
        LocationSource, AMapLocationListener {
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private AMap aMap;
    private static final int SEARCH = 1;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private List<PoiString> pois;
    private LatLng point;
    private Marker marker;
    private PoiAdapter mAdapter;
    private Double weidu;
    private Double jingdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.marker, null);
        pois = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new PoiAdapter(pois, mContext);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new PoiAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mAdapter.setCheck(position);
            }
        });

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(locationStyle);
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));

        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                //发生变化时获取到经纬度传递逆地理编码获取周边数据
                weidu = cameraPosition.target.latitude;
                jingdu = cameraPosition.target.longitude;
                regeocodeSearch(weidu, jingdu, 1000);
                point = new LatLng(weidu, jingdu);

                Logger.d(point.toString());
                if (marker != null)
                    marker.remove();//将上一次描绘的mark清除
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                //地图发生变化之后描绘mark
                marker = aMap.addMarker(new com.amap.api.maps.model.MarkerOptions()
                        .position(point)
                        .title(""));
            }
        });

//        recyclerView.setAdapter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_location;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitle("");


        TextView tv_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);

        tv_cancel.setText("取消");
        tv_done.setText("完成");
        tv_title.setText("位置");
        tv_done.setVisibility(View.VISIBLE);
        tv_cancel.setTextColor(getResources().getColor(R.color.black));
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<PoiString> lists = mAdapter.getLists();
                PoiString string = new PoiString();
                Map<Integer, Boolean> map = mAdapter.getMap();
                Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
                for (Map.Entry<Integer, Boolean> entry : entries) {
                    if (entry.getValue()) {
                        string = lists.get(entry.getKey());
                        break;
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("name", string.getName());
                intent.putExtra("jingdu", jingdu);
                intent.putExtra("weidu", weidu);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @OnClick(R.id.et_search)
    public void clickSearch(View view) {
        startActivityForResult(new Intent(mContext, SearchLocationActivity.class), SEARCH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SEARCH) {
                Double latitude = data.getDoubleExtra("latitude", 0);
                Double longitude = data.getDoubleExtra("longitude", 0);
                //通过经纬度重新再地图获取位置
                CameraPosition cp = aMap.getCameraPosition();
                CameraPosition cpNew = CameraPosition.fromLatLngZoom(new LatLng(latitude, longitude), cp.zoom);
                CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cpNew);
                aMap.moveCamera(cu);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        Logger.d(geocodeResult.getGeocodeAddressList());
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(mContext);
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setOnceLocation(true); //只定位一次
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
        mLocationOption = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                Double weidu = amapLocation.getLatitude();
                Double jingdu = amapLocation.getLongitude();
                regeocodeSearch(weidu, jingdu, 100);
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Logger.e(errText);
            }
        }
    }


    /***
     * 逆地理编码获取定位后的附近地址
     * @param weidu
     * @param jingdu
     * @param distances 设置查找范围
     */
    private void regeocodeSearch(Double weidu, Double jingdu, int distances) {
        final LatLonPoint point = new LatLonPoint(weidu, jingdu);
        GeocodeSearch geocodeSearch = new GeocodeSearch(mContext);
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(point, distances, geocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(regeocodeQuery);

        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int rCode) {
                String preAdd = "";//地址前缀
                if (1000 == rCode) {
                    final RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
                    StringBuffer stringBuffer = new StringBuffer();
                    String area = address.getProvince();//省或直辖市
                    String loc = address.getCity();//地级市或直辖市
                    String subLoc = address.getDistrict();//区或县或县级市
                    String ts = address.getTownship();//乡镇
                    String thf = null;//道路
                    List<RegeocodeRoad> regeocodeRoads = address.getRoads();//道路列表
                    if (regeocodeRoads != null && regeocodeRoads.size() > 0) {
                        RegeocodeRoad regeocodeRoad = regeocodeRoads.get(0);
                        if (regeocodeRoad != null) {
                            thf = regeocodeRoad.getName();
                        }
                    }
                    String subthf = null;//门牌号
                    StreetNumber streetNumber = address.getStreetNumber();
                    if (streetNumber != null) {
                        subthf = streetNumber.getNumber();
                    }
                    String fn = address.getBuilding();//标志性建筑,当道路为null时显示
                    if (area != null) {
                        stringBuffer.append(area);
                        preAdd += area;
                    }
                    if (loc != null && !area.equals(loc)) {
                        stringBuffer.append(loc);
                        preAdd += loc;
                    }
                    if (subLoc != null) {
                        stringBuffer.append(subLoc);
                        preAdd += subLoc;
                    }
                    if (ts != null)
                        stringBuffer.append(ts);
                    if (thf != null)
                        stringBuffer.append(thf);
                    if (subthf != null)
                        stringBuffer.append(subthf);
                    if ((thf == null && subthf == null) && fn != null && !subLoc.equals(fn))
                        stringBuffer.append(fn + "附近");

                    List<PoiItem> list = address.getPois();
                    pois.clear();
                    for (PoiItem item : list) {
                        pois.add(new PoiString(item.getTitle()));
                    }

                    LocationActivity.this.pois.add(0, new PoiString(stringBuffer.toString(), true));
                    mAdapter.setLists(LocationActivity.this.pois);
                }


            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });

    }
}
