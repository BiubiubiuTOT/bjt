package com.bangjiat.bjt.module.park.apply.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.apply.adapter.CarAdapter;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.car.beans.CarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewApplyActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.tv_parking_name)
    TextView tv_parking_name;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<CarBean> list;

    public static final int SELECT_PARKING = 1;
    public static final int SELECT_CAR = 2;
    private CarAdapter adapter;
    private ParkingResult.PageDataBean.RecordsBean recordsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        adapter = new CarAdapter(list, mContext);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.card_select_car)
    public void clickSelectCar(View view) {
        startActivityForResult(new Intent(mContext, ChooseCarActivity.class), SELECT_CAR);
    }


    @OnClick(R.id.card_select_parking)
    public void clickSelectParking(View view) {
        startActivityForResult(new Intent(mContext, ChooseParkingActivity.class), SELECT_PARKING);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_new_apply2;
    }

    @Override
    protected String getTitleStr() {
        return "新增申请";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PARKING) {
                recordsBean = (ParkingResult.PageDataBean.RecordsBean) data.getSerializableExtra("data");
                tv_parking_name.setText(recordsBean.getName());
            } else if (requestCode == SELECT_CAR) {
                Bundle extras = data.getExtras();
                List<CarBean> beans = (List<CarBean>) extras.getSerializable(("data"));
                if (beans != null) {
                    adapter.setLists(beans);
                }
            }
        }
    }
}
