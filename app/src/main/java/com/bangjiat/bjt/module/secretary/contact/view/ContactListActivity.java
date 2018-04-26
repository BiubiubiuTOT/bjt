package com.bangjiat.bjt.module.secretary.contact.view;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.contact.util.ContactAdapter;
import com.bangjiat.bjt.module.secretary.contact.util.ContactInfo;
import com.bangjiat.bjt.module.secretary.contact.util.ContactsUtils;
import com.bangjiat.bjt.module.secretary.contact.util.SideLetterBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;

/**
 *
 */

public class ContactListActivity extends BaseToolBarActivity {
    private List<ContactInfo> mContactList;
    private List<ContactInfo> mSearchList;
    private ContactAdapter mContactAdapter;

    @BindView(R.id.et_search)
    EditText mSearchBox;
    @BindView(R.id.iv_search_clear)
    ImageView mClearBtn;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.side_bar)
    SideLetterBar mSideLetterBar;
    @BindView(R.id.letter_overlay)
    TextView mOverlay;
    @BindView(R.id.pb)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initClick();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_list;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.back_black);

        ImageView image = toolbar.findViewById(R.id.toolbar_image);
        TextView tv_message = toolbar.findViewById(R.id.toolbar_title);
        tv_message.setText("通讯录");
        tv_message.setTextColor(getResources().getColor(R.color.black));
        image.setImageResource(R.mipmap.add_contact);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void initView() {
        mSideLetterBar.setOverlay(mOverlay);
    }

    private void initClick() {
        mSideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                scrollToLetter(letter);
            }
        });

        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchBox.setText("");
                mClearBtn.setVisibility(View.GONE);
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
                    mClearBtn.setVisibility(View.GONE);
                    mSideLetterBar.setVisibility(View.VISIBLE);
                    mSearchList.clear();
                    mContactAdapter.setContactList(mContactList);
                } else {
                    mClearBtn.setVisibility(View.VISIBLE);
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
                startActivity(new Intent(mContext, ContactDetailActivity.class));
            }
        });


    }


    /**
     * 搜索联系人
     *
     * @param searchKey 搜索key
     */
    private void searchContacts(String searchKey) {
        for (ContactInfo info : mContactList) {
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

            if (mContactList.size() > 0) {
                mSearchBox.setEnabled(true);
                mContactAdapter.setContactList(mContactList);
                mSideLetterBar.setVisibility(View.VISIBLE);
            }
            return true;
        }
    });


    private void initData() {
        mSearchList = new ArrayList<>();
        mContactList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mContactAdapter = new ContactAdapter(this, mContactList);
        mRecyclerView.setAdapter(mContactAdapter);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mContactList = ContactsUtils.getContactList(ContactListActivity.this);
                handler.sendEmptyMessage(0);
            }
        });
    }
}
