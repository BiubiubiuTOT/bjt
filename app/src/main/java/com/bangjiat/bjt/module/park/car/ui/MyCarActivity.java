package com.bangjiat.bjt.module.park.car.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.park.car.adapter.MyCarAdapter;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bangjiat.bjt.module.park.car.contract.CarListContract;
import com.bangjiat.bjt.module.park.car.presenter.CarListPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyCarActivity extends BaseToolBarActivity implements CarListContract.View {
    private static final int ADD_CAR = 3;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;

    private Dialog dialog;
    private CarListContract.Presenter presenter;
    private List<CarBean> carBean;
    private MyCarAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        initData();

    }

    private void initData() {
        carBean = new ArrayList<>();
        presenter = new CarListPresenter(this);
        presenter.getCarList(DataUtil.getToken(mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        mAdapter = new MyCarAdapter(carBean, mContext);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyCarAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, MyCarDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", carBean.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onDelete(View view, int pos) {
                String[] strings = new String[1];
                CarBean bean = carBean.get(pos);
                strings[0] = String.valueOf(bean.getCarId());
                presenter.deleteCar(DataUtil.getToken(mContext), strings);

                carBean.remove(pos);
                mAdapter.notifyItemRemoved(pos);
                mAdapter.notifyItemRangeChanged(pos, carBean.size() - pos);

                if (carBean.size() == 0)
                    ll_none.setVisibility(View.VISIBLE);
            }
        });
        // 可以用在：当点击外部空白处时，关闭正在展开的侧滑菜单。我个人觉得意义不大，
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    SwipeMenuLayout viewCache = SwipeMenuLayout.getViewCache();
                    if (null != viewCache) {
                        viewCache.smoothClose();
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_car;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView add = toolbar.findViewById(R.id.toolbar_other);
        TextView title = toolbar.findViewById(R.id.toolbar_title);
        add.setText("新增车辆");
        title.setText("我的车辆");
        title.setTextColor(getResources().getColor(R.color.black));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(mContext, AddCarActivity.class), ADD_CAR);
            }
        });
        toolbar.setNavigationIcon(R.mipmap.back_black);
        add.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
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
        Logger.d(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getCarListSuccess(List<CarBean> bean) {
        if (bean != null && bean.size() > 0) {
            carBean = bean;
            mAdapter.setLists(carBean);
            ll_none.setVisibility(View.GONE);
            return;
        }
        ll_none.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_CAR) {
                presenter.getCarList(DataUtil.getToken(mContext));
            }
        }
    }

    @Override
    public void addCarSuccess() {
    }

    @Override
    public void deleteCarSuccess(String str) {
        Constants.showErrorDialog(mContext, "删除车辆成功");
    }
}
