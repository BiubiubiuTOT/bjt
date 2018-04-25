package com.bangjiat.bangjiaapp.module.park.apply.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bangjiaapp.module.park.apply.adapter.CarAdapter;
import com.bangjiat.bangjiaapp.module.park.apply.beans.CarInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewApplyActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.tv_parking_name)
    TextView tv_parking_name;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<CarInfoBean> list;

    public static final int SELECT_PARKING = 1;
    public static final int SELECT_CAR = 2;
    private CarAdapter adapter;

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
                tv_parking_name.setText(data.getStringExtra("data"));
            } else if (requestCode == SELECT_CAR) {
                Bundle extras = data.getExtras();
                List<CarInfoBean> beans = (List<CarInfoBean>) extras.getSerializable(("data"));
                if (beans != null) {
                    adapter.setLists(beans);
                }
            }
        }
    }
}
