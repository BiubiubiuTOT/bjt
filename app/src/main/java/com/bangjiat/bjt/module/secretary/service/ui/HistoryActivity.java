package com.bangjiat.bjt.module.secretary.service.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.adapter.ApplyHistoryAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryActivity extends BaseColorToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private List<ApplyHistoryBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_history;
    }

    private void initData() {
        list = new ArrayList<>();
        ApplyHistoryBean bean = new ApplyHistoryBean();
        bean.setStatus(2);
        bean.setStatusDes("已通过");
        bean.setCompanyName("贵州邦佳大数据科技有限公司");

        ApplyHistoryBean.ApplyPeople applyPeople =
                new ApplyHistoryBean.ApplyPeople("王力", "18786166716", "522121199303287546");
        bean.setApplyPeople(applyPeople);

        ApplyHistoryBean.HandleHistory handleHistory =
                new ApplyHistoryBean.HandleHistory("王力", "2018.04.02  14:57", "发起申请", 0);
        bean.setHandleHistory(handleHistory);

        ApplyHistoryBean bean1 = new ApplyHistoryBean();
        bean1.setStatus(3);
        bean1.setStatusDes("未通过");
        bean1.setCompanyName("贵州邦佳大数据科技有限公司");

        ApplyHistoryBean.ApplyPeople applyPeople1 =
                new ApplyHistoryBean.ApplyPeople("李易", "18083608929", "52212119930328754x");
        bean1.setApplyPeople(applyPeople1);

        ApplyHistoryBean.HandleHistory handleHistory1 =
                new ApplyHistoryBean.HandleHistory("李易", "2018.04.02  14:57", "发起申请", 0);
        bean1.setHandleHistory(handleHistory1);

        ApplyHistoryBean bean2 = new ApplyHistoryBean();
        bean2.setStatus(1);
        bean2.setStatusDes("待审核");
        bean2.setCompanyName("贵州邦佳大数据科技有限公司");

        ApplyHistoryBean.ApplyPeople applyPeople2 =
                new ApplyHistoryBean.ApplyPeople("张无忌（入驻申请）", "17685302679", "52212119930328754x");
        bean2.setApplyPeople(applyPeople2);

        ApplyHistoryBean.HandleHistory handleHistory2 =
                new ApplyHistoryBean.HandleHistory("张无忌", "2018.04.02  14:57", "发起申请", 0);
        bean2.setHandleHistory(handleHistory2);

        list.add(bean);
        list.add(bean1);
        list.add(bean2);

        setAdapter();
    }

    private void setAdapter() {
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        ApplyHistoryAdapter mAdapter = new ApplyHistoryAdapter(list, mContext);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ApplyHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, DetailActivity.class));
            }
        });
    }

    @Override
    protected String getTitleStr() {
        return "申请记录";
    }
}
