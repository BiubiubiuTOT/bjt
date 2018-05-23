package com.bangjiat.bjt.module.secretary.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.contract.ContactContract;
import com.bangjiat.bjt.module.secretary.contact.presenter.ContactPresenter;
import com.bangjiat.bjt.module.secretary.contact.util.ContactAdapter;
import com.bangjiat.bjt.module.secretary.contact.util.ContactsUtils;
import com.bangjiat.bjt.module.secretary.contact.util.SideLetterBar;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class SelectContactsActivity extends BaseToolBarActivity implements ContactContract.View {
    private List<ContactBean> mContactList;
    private List<ContactBean> mSearchList;
    private ContactAdapter mContactAdapter;

    @BindView(R.id.et_search)
    ClearEditText mSearchBox;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.side_bar)
    SideLetterBar mSideLetterBar;
    @BindView(R.id.letter_overlay)
    TextView mOverlay;
    @BindView(R.id.pb)
    ProgressBar mProgressBar;
    private ContactContract.Presenter presenter;
    private TextView tv_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initClick();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_contacts;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");

        TextView tv_message = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        tv_other = toolbar.findViewById(R.id.toolbar_other);
        tv_message.setText("添加联系人");
        tv_cancel.setText("关闭");
        tv_cancel.setTextColor(getResources().getColor(R.color.mine_bg));
        tv_other.setText("完成");
        tv_other.setVisibility(View.VISIBLE);
        tv_other.setTextColor(getResources().getColor(R.color.mine_bg));

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ContactBean> selectItems = getSelectItems();
                Logger.d("size: " + selectItems.size() + " " + selectItems.toString());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) selectItems);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        tv_message.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

    }

    private void initView() {
        // 注册订阅者
        mSideLetterBar.setOverlay(mOverlay);
        presenter = new ContactPresenter(this);
        presenter.getAllContacts(DataUtil.getToken(mContext), null);
    }


    private void initClick() {
        mSideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                scrollToLetter(letter);
            }
        });

        mSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchKey = s.toString().trim();
                if (mContactList.size() == 0) return;

                mSearchList.clear();
                searchContacts(searchKey);
                if (TextUtils.isEmpty(searchKey)) {
                    mSideLetterBar.setVisibility(View.VISIBLE);
                    mSearchList.clear();
                    mContactAdapter.setContactList(mContactList);
                } else {
                    mContactAdapter.setContactList(mSearchList);
                    if (mSearchList == null || mSearchList.size() <= 0) {
                        mSideLetterBar.setVisibility(View.GONE);
                    } else {
                        mSideLetterBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //联系人未获取到之前搜索不可用
        mSearchBox.setEnabled(false);

        mContactAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mContactAdapter.setCheck(position);
            }
        });


    }


    /**
     * 搜索联系人
     *
     * @param searchKey 搜索key
     */
    private void searchContacts(String searchKey) {
        for (ContactBean info : mContactList) {
            if (ContactsUtils.searchContact(searchKey, info)) {
                mSearchList.add(info);
            }
        }
    }

    /**
     * 滑动到索引字母出
     */
    private void scrollToLetter(String letter) {
        for (int i = 0; i < mContactList.size(); i++) {
            if (TextUtils.equals(letter, mContactList.get(i).getLetter())) {
                ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                break;
            }
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mProgressBar.setVisibility(View.GONE);

            mSearchBox.setEnabled(true);
            mContactAdapter.setContactList(mContactList);
            mSideLetterBar.setVisibility(View.VISIBLE);
            return true;
        }
    });


    private void initData() {
        mSearchList = new ArrayList<>();
        mContactList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mContactAdapter = new ContactAdapter(this, mContactList, 1);
        mRecyclerView.setAdapter(mContactAdapter);
        mContactAdapter.setOnCheckChangedListener(new ContactAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int select = getSelectCount();
                if (select == 0) {
                    tv_other.setText("完成");
                } else {
                    tv_other.setText("完成（" + select + "）");
                }
            }
        });
    }

    private int getSelectCount() {
        int select = 0;
        HashMap<Integer, Boolean> map = mContactAdapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                select++;
        }
        Logger.d("map: " + map.size() + " select: " + select);
        return select;
    }

    private List<ContactBean> getSelectItems() {
        List<ContactBean> contactBeans = new ArrayList<>();
        List<ContactBean> beanList = mContactAdapter.getmContactList();
        HashMap<Integer, Boolean> map = mContactAdapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                contactBeans.add(beanList.get(entry.getKey()));
            }
        }
        return contactBeans;
    }

    @Override
    public void showDialog() {
    }

    @Override
    public void dismissDialog() {
    }

    @Override
    public void fail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(List<ContactBean> bean) {
        Logger.d(bean.toString());
        mContactList = ContactsUtils.getContactList(bean);
        handler.sendEmptyMessage(0);
    }
}
