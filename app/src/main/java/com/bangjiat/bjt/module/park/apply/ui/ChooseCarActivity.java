package com.bangjiat.bjt.module.park.apply.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.park.apply.adapter.ChooseCarAdapter;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.apply.contract.ParkApplyContract;
import com.bangjiat.bjt.module.park.apply.presenter.ParkApplyPresenter;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class ChooseCarActivity extends BaseToolBarActivity implements ParkApplyContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<CarBean> list;
    private ChooseCarAdapter mAdapter;
    private Dialog dialog;
    private ParkApplyContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        presenter = new ParkApplyPresenter(this);
        presenter.getWorkersCar(DataUtil.getToken(mContext));

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

    }

    private void setAdapter() {
        mAdapter = new ChooseCarAdapter(list, mContext);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_choose_car;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);
        tv_done.setText("完成");
        tv_done.setVisibility(View.VISIBLE);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("选择车辆");
        toolbar.setNavigationIcon(R.mipmap.back_black);

        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_done.setTextColor(getResources().getColor(R.color.black));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) getData());
                intent.putExtras(bundle);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private List<CarBean> getData() {
        List<CarBean> beans = new ArrayList<>();
        HashMap<Integer, Boolean> map = mAdapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                beans.add(list.get(entry.getKey()));
            }
        }
        return beans;
    }

    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getWorkersCarSuccess(List<CarBean> list) {
        if (list != null && list.size() > 0) {
            this.list = list;
            Logger.d(list.toString());
            setAdapter();
        }
    }

    @Override
    public void getParkSpaceSuccess(ParkingResult s) {

    }

    @Override
    public void parkApplySuccess() {

    }

    @Override
    public void dealParkApplySuccess() {

    }
}
