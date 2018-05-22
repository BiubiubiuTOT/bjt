package com.bangjiat.bjt.common;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;

import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/22 0022
 */

public class RefreshViewHolder extends BGARefreshViewHolder {
    private TextView mHeaderStatusTv;
    private ImageView mHeaderArrowIv;
    private ImageView mHeaderChrysanthemumIv;
    private AnimationDrawable mHeaderChrysanthemumAd;
    private RotateAnimation mUpAnim;
    private RotateAnimation mDownAnim;
    private String mPullDownRefreshText = "下拉刷新";
    private String mReleaseRefreshText = "释放立即刷新";
    private String mRefreshingText = "正在刷新...";

    public RefreshViewHolder(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
        this.initAnimation();
    }

    private void initAnimation() {
        this.mUpAnim = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
        this.mUpAnim.setDuration(150L);
        this.mUpAnim.setFillAfter(true);
        this.mDownAnim = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
        this.mDownAnim.setFillAfter(true);
    }

    public void setPullDownRefreshText(String pullDownRefreshText) {
        this.mPullDownRefreshText = pullDownRefreshText;
    }

    public void setReleaseRefreshText(String releaseRefreshText) {
        this.mReleaseRefreshText = releaseRefreshText;
    }

    public void setRefreshingText(String refreshingText) {
        this.mRefreshingText = refreshingText;
    }

    public View getRefreshHeaderView() {
        if (this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.view_refresh, (ViewGroup) null);
            this.mRefreshHeaderView.setBackgroundColor(0);
            if (this.mRefreshViewBackgroundColorRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }

            if (this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }

            this.mHeaderStatusTv = (TextView) this.mRefreshHeaderView.findViewById(R.id.tv_normal_refresh_header_status);
            this.mHeaderArrowIv = (ImageView) this.mRefreshHeaderView.findViewById(R.id.iv_normal_refresh_header_arrow);
            this.mHeaderChrysanthemumIv = (ImageView) this.mRefreshHeaderView.findViewById(R.id.iv_normal_refresh_header_chrysanthemum);
            this.mHeaderChrysanthemumAd = (AnimationDrawable) this.mHeaderChrysanthemumIv.getDrawable();
            this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        }

        return this.mRefreshHeaderView;
    }
    @Override
    public void handleScale(float scale, int moveYDistance) {
    }

    @Override
    public void changeToIdle() {
    }

    @Override
    public void changeToPullDown() {
        mHeaderStatusTv.setText(mPullDownRefreshText);
        mHeaderChrysanthemumIv.setVisibility(View.INVISIBLE);
        mHeaderChrysanthemumAd.stop();
        mHeaderArrowIv.setVisibility(View.VISIBLE);
        mDownAnim.setDuration(150);
        mHeaderArrowIv.startAnimation(mDownAnim);
    }

    @Override
    public void changeToReleaseRefresh() {
        mHeaderStatusTv.setText(mReleaseRefreshText);
        mHeaderChrysanthemumIv.setVisibility(View.INVISIBLE);
        mHeaderChrysanthemumAd.stop();
        mHeaderArrowIv.setVisibility(View.VISIBLE);
        mHeaderArrowIv.startAnimation(mUpAnim);
    }

    @Override
    public void changeToRefreshing() {
        mHeaderStatusTv.setText(mRefreshingText);
        // 必须把动画清空才能隐藏成功
        mHeaderArrowIv.clearAnimation();
        mHeaderArrowIv.setVisibility(View.INVISIBLE);
        mHeaderChrysanthemumIv.setVisibility(View.VISIBLE);
        mHeaderChrysanthemumAd.start();
    }

    @Override
    public void onEndRefreshing() {
        mHeaderStatusTv.setText(mPullDownRefreshText);
        mHeaderChrysanthemumIv.setVisibility(View.INVISIBLE);
        mHeaderChrysanthemumAd.stop();
        mHeaderArrowIv.setVisibility(View.VISIBLE);
        mDownAnim.setDuration(0);
        mHeaderArrowIv.startAnimation(mDownAnim);
    }

}
