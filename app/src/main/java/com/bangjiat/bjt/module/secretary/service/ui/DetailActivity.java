package com.bangjiat.bjt.module.secretary.service.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.FullImageActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.service.adapter.ImageAdapter;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 服务申请 详情
 */
public class DetailActivity extends BaseColorToolBarActivity {
    @BindView(R.id.recycler_view_photo)
    RecyclerView recycler_view_photo;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;

    private List<String> photoList;
    private ServiceApplyHistoryResult.RecordsBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    /**
     * 关于竖线的问题用 string.split("\\|")解决。 关于星号的问题用 string.split("\\*")解决。
     * 关于斜线的问题用 sring.split("\\\\")解决。 关于中括号的问题用 sring.split("\\[\\]")解决。
     */
    private void initData() {
        data = (ServiceApplyHistoryResult.RecordsBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            tv_company_name.setText(data.getCompanyName());
            tv_content.setText(data.getContent());
            tv_title.setText(data.getApplication());

            String sources = data.getSources();
            if (sources != null) {
                Logger.d(sources);
                String[] split = sources.split("\\|");
                Logger.d(split[0]);
                photoList = Arrays.asList(split);
            } else photoList = new ArrayList<>();

            Logger.d(photoList.toString());
            setPhotoAdapter();
        }
    }

    private void setPhotoAdapter() {
        recycler_view_photo.setLayoutManager(new GridLayoutManager(mContext, 4));
        recycler_view_photo.setHasFixedSize(true);
        ImageAdapter adapter = new ImageAdapter(photoList, mContext);
        recycler_view_photo.setAdapter(adapter);

        adapter.setOnItemClickListener(new ImageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, FullImageActivity.class);
                intent.putExtra("path", photoList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_datail;
    }

    @Override
    protected String getTitleStr() {
        return "申请详情";
    }
}
