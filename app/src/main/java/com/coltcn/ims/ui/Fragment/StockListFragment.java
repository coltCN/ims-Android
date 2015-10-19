package com.coltcn.ims.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coltcn.ims.R;
import com.coltcn.ims.adapter.StockListAdapterHolder;
import com.coltcn.ims.pojo.Stock;
import com.coltcn.ims.ui.MainActivity;
import com.coltcn.ims.ui.StockDetailActivity;
import com.coltcn.ims.ui.base.BaseFragment;
import com.coltcn.ims.ui.custom.DividerItemDecoration;
import com.coltcn.ims.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by majf on 2015/10/16.
 */
public class StockListFragment extends BaseFragment {

    RecyclerView recyclerView;

    private List<Stock> stocks = new ArrayList<>();
    private StockListAdapterHolder stockAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        L.i("StockListFragment onCreate");
        super.onCreate(savedInstanceState);
        fatchData();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("StockListFragment onResume");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("StockListFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_stock_list,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        L.i("StockListFragment onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onStart() {
        L.i("StockListFragment onStart");
        super.onStart();
    }

    @Override
    public void onPause() {
        L.i("StockListFragment onPause");
        super.onPause();
    }

    public void fatchData(){
        for (int i=0; i<30;i++) {
            Stock stock = new Stock();
            stock.setId(String.valueOf(i));
            stock.setName("货物" + i);
            stock.setPrice(200.0 - i * 3);
            stock.setQuantity(i*5);
            stocks.add(stock);
        }
    }

    private void initView(View view){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        stockAdapter = new StockListAdapterHolder(stocks);
        recyclerView.setAdapter(stockAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StockDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("opt", "add");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.i(String.format("onActivityResult requestCode:%d,resultCode:%d",requestCode,resultCode));
        if (resultCode == 1){
            Stock stock = (Stock) data.getSerializableExtra("result");
            stocks.add(0,stock);
            stockAdapter.notifyDataSetChanged();
        }
    }
}
