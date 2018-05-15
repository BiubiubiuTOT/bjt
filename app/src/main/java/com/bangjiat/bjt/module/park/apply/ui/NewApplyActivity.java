package com.bangjiat.bjt.module.park.apply.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.apply.adapter.CarAdapter;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyHistoryResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.apply.contract.ParkApplyContract;
import com.bangjiat.bjt.module.park.apply.presenter.ParkApplyPresenter;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewApplyActivity extends BaseWhiteToolBarActivity implements ParkApplyContract.View {
    @BindView(R.id.tv_parking_name)
    TextView tv_parking_name;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<CarBean> list;

    public static final int SELECT_PARKING = 1;
    public static final int SELECT_CAR = 2;
    private CarAdapter adapter;
    private ParkingResult.PageDataBean.RecordsBean recordsBean;
    private String spaceName;
    private int spaceId;
    private List<ParkApplyInput.Detail> details;
    private Dialog dialog;
    private ParkApplyContract.Presenter presenter;
    private List<CarBean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new ParkApplyPresenter(this);
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
                if (recordsBean != null) {
                    tv_parking_name.setText(recordsBean.getName());
                    spaceName = recordsBean.getName();
                    spaceId = recordsBean.getSpaceId();
                }
            } else if (requestCode == SELECT_CAR) {
                Bundle extras = data.getExtras();
                List<CarBean> beans = (List<CarBean>) extras.getSerializable(("data"));
                if (beans != null) {
                    adapter.setLists(beans);
                }
            }
        }
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

    }

    @Override
    public void getParkSpaceSuccess(ParkingResult s) {

    }

    @Override
    public void parkApplySuccess() {
        Constants.showSuccessExitDialog(this, "申请成功");
    }

    @Override
    public void dealParkApplySuccess() {

    }

    @Override
    public void getParkApplyHistorySuccess(ParkApplyHistoryResult result) {

    }

    @OnClick(R.id.btn_submit)
    public void clickSubmit(View view) {
        beans = adapter.getLists();
        details = new ArrayList<>();
        for (CarBean bean : beans) {
            details.add(new ParkApplyInput.Detail(bean.getName(),
                    bean.getPlateNumber(), bean.getUserId(), bean.getType() == 0 ? 2 : bean.getType(), bean.getCarId()));
        }

        ParkApplyInput input = new ParkApplyInput();
        input.setSpaceId(spaceId);
        input.setSpaceName(spaceName);
        input.setDetailList(details);

        Logger.d(input.toString());

        presenter.parkApply(DataUtil.getToken(mContext), input);
    }
}
