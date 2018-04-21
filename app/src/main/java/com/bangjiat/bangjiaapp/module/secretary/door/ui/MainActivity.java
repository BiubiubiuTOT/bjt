package com.bangjiat.bangjiaapp.module.secretary.door.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseColorToolBarActivity;

import butterknife.OnClick;

public class MainActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_door_apply;
    }

    @Override
    protected String getTitleStr() {
        return "门禁申请";
    }

    @OnClick(R.id.ll_add_people)
    public void clickAddPeople(View v) {
        startActivity(new Intent(mContext, AddPeopleActivity.class));
    }

    @OnClick(R.id.ll_apply_history)
    public void clickApplyHistory(View view) {
        startActivity(new Intent(mContext, HistoryActivity.class));
    }
}
