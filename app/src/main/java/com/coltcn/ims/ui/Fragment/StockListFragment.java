package com.coltcn.ims.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coltcn.ims.R;
import com.coltcn.ims.StockListAdapterHolder;
import com.coltcn.ims.pojo.Stock;
import com.coltcn.ims.ui.base.BaseFragment;
import com.coltcn.ims.ui.custom.DividerItemDecoration;

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
        super.onCreate(savedInstanceState);
        fatchData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
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
        stockAdapter = new StockListAdapterHolder(getActivity(),stocks);
        recyclerView.setAdapter(stockAdapter);
    }
}
