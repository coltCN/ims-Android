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
import com.coltcn.ims.adapter.OrderListAdapterHolder;
import com.coltcn.ims.pojo.Order;
import com.coltcn.ims.pojo.Stock;
import com.coltcn.ims.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by majfc on 2015/10/18.
 */
public class OrderListFragment extends BaseFragment {

    RecyclerView recyclerView;
    private List<Order> orders ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list, container, false);
        initView(view);
        view.setBackgroundColor(getResources().getColor(R.color.ligth_gray));
        return view;
    }

    private void fetchData(){
        orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setAddress("广东佛山XXXXXXXXXXXXXXXX");
            order.setBuyer("张" + (i + 1));
            order.setOrderNo("201510102500" + i);
            List<Stock> stocks = new ArrayList<>();
            Stock stock = new Stock();
            stock.setName("货物1");
            stock.setQuantity(2);
            stocks.add(stock);
            stock = new Stock();
            stock.setName("货物4");
            stock.setQuantity(1);
            stocks.add(stock);
            order.setStocks(stocks);
            orders.add(order);
        }
    }

    private void initView(View view){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        OrderListAdapterHolder adapterHolder = new OrderListAdapterHolder(orders);
        recyclerView.setAdapter(adapterHolder);

    }
}
