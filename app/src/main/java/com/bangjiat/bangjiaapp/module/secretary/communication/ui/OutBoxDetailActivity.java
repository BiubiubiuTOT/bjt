package com.bangjiat.bangjiaapp.module.secretary.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.FullImageActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.secretary.service.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OutBoxDetailActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<String> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        photoList = new ArrayList<>();
        photoList.add("https://img6.bdstatic.com/img/image/public/jingtianshouxie.jpg");
        photoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524285062940&di=4cdaee551ca0cb2d0d79fab9e6f4f12a&imgtype=0&src=http%3A%2F%2Fimg0.ph.126.net%2FdWGD1ToHPqmML8XYUOnZyg%3D%3D%2F6630522407933417361.jpg");
        photoList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3763658861,4163786043&fm=27&gp=0.jpg");
        photoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524285092085&di=bed1257144ccf3f530be1f77191a7edb&imgtype=0&src=http%3A%2F%2Fs9.sinaimg.cn%2Fmiddle%2F5227549bha1025687dd58%26690");
        setPhotoAdapter();
    }

    private void setPhotoAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setHasFixedSize(true);
        ImageAdapter adapter = new ImageAdapter(photoList, mContext);
        recyclerView.setAdapter(adapter);

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
        return R.layout.activity_out_box_detail;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("发件箱");
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
