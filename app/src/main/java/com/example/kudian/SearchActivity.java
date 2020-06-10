package com.example.kudian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kudian.db.RecordsDao;
import com.example.kudian.db.SearchRecordsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText searchContentEt;
    private SearchRecordsAdapter recordsAdapter;
    private View recordsHistoryView;
    private ListView recordsListLv;
    private TextView clearAllRecordsTv;
    private LinearLayout searchRecordsLl;
    private List<String> searchRecordsList;
    private List<String> tempList;
    private RecordsDao recordsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetContentView(R.layout.activity_search);

        initView();
        initData();
        bindAdapter();
        initListener();
    }

    private void SetContentView(int activity_search) {
    }

    private void initView() {
        setHideHeader();
        initRecordsView();

        searchRecordsLl = (LinearLayout) findViewById(R.id.search_content_show_ll);
        searchContentEt = (EditText) findViewById(R.id.input_search_content_et);

       //添加搜索view
        searchRecordsLl.addView(recordsHistoryView);
    }

    //初始化搜索view
    private void initRecordsView() {
        recordsHistoryView = LayoutInflater.from(this).inflate(R.layout.listview_item,null);
        //显示搜索记录
        recordsListLv = (ListView)recordsHistoryView.findViewById(R.id.search_records_lv);
        //清除搜索记录
        clearAllRecordsTv = (TextView)recordsHistoryView.findViewById(R.id.clear_all_records_tv);
    }

    private  void initData(){
        recordsDao = new RecordsDao(this);
        searchRecordsList = new ArrayList<>();
        tempList = new ArrayList<>();
        tempList.addAll(recordsDao.getRecordsList());
        reversedList();
        checkRecordsSize();
    }

    private void bindAdapter(){
        recordsAdapter = new SearchRecordsAdapter(this,searchRecordsList);
        recordsListLv.setAdapter(recordsAdapter);
    }

    private void initListener(){
        clearAllRecordsTv.setOnClickListener(this);
        searchContentEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if (searchContentEt.getText().toString().length() > 0){
                        String record = searchContentEt.getText().toString();
                        //判断数据库是否存在该记录
                        if(!recordsDao.isHasRecord(record)){
                            tempList.add(record);
                        }
                        //保存搜索记录
                        recordsDao.addRecords(record);
                        reversedList();
                        checkRecordsSize();
                        recordsAdapter.notifyDataSetChanged();
                        //根据关键字搜索
                    }else {
                        Toast.makeText(SearchActivity.this,"搜索内容不能为空！",Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
        //模糊搜索
        searchContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tempName = searchContentEt.getText().toString();
                tempList.clear();
                tempList.addAll(recordsDao.querySimlarRecord(tempName));
                reversedList();
                checkRecordsSize();
                recordsAdapter.notifyDataSetChanged();
            }
        });
        //历史记录点击事件
        recordsListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将获取到的字符传到搜索界面
            }
        });
    }

    //没有匹配到数据时，不显示历史记录
    private void checkRecordsSize() {
        if (searchRecordsList.size() == 0){
            searchRecordsLl.setVisibility(View.GONE);
        }else {
            searchRecordsLl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //清空所有历史记录
            //case R.id.clear_all_records_tv;
            //tempList.clear();
            //reversedList();
           // recordsDao.deleteAllRecords();
           // recordsAdapter.notifyDataSetChanged();
            //searchRecordsLl.setVisibility(View.GONE);
           // break;
        }
    }

    private void reversedList(){
       searchRecordsList.clear();
       for (int i = tempList.size() - 1;i >= 0;i--){
           searchRecordsList.add(tempList.get(i));
       }
    }
    private void setHideHeader() {

    }
}