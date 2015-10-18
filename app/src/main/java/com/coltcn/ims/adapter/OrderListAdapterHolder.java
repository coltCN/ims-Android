package com.coltcn.ims.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.coltcn.ims.R;
import com.coltcn.ims.pojo.Order;
import com.coltcn.ims.pojo.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by majfc on 2015/10/18.
 */
public class OrderListAdapterHolder extends RecyclerView.Adapter<OrderListAdapterHolder.ViewHolder> {

    private List<Order> orders = new ArrayList<>();

    public OrderListAdapterHolder(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view= inflater.inflate(R.layout.order_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position > orders.size()) return;
        Order order = orders.get(position);
        holder.noView.setText(order.getOrderNo());
        holder.addressView.setText(order.getAddress());
        holder.buyerView.setText(order.getBuyer());
        holder.detailView.setAdapter(new DetailViewAdpter(order.getStocks()));
    }

    @Override
    public int getItemCount() {
        if (orders == null) return 0;
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noView,dateView,buyerView,addressView;
        ListView detailView;
        public ViewHolder(View itemView) {
            super(itemView);
            noView = (TextView) itemView.findViewById(R.id.item_order_no);
            dateView = (TextView) itemView.findViewById(R.id.item_order_date);
            buyerView = (TextView) itemView.findViewById(R.id.item_order_buyer);
            addressView = (TextView) itemView.findViewById(R.id.item_order_address);
            detailView = (ListView) itemView.findViewById(R.id.item_order_details);
        }
    }

    public class DetailViewAdpter extends BaseAdapter{

        private List<Stock> detail ;

        public DetailViewAdpter(List<Stock> detail) {
            super();
            this.detail=detail;
        }

        @Override
        public int getCount() {
            if (detail==null) return 0;
            return detail.size();
        }

        @Override
        public Object getItem(int position) {
            return detail.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_list_item,null);
            }
            TextView stockNameView = (TextView) convertView.findViewById(R.id.item_stock_name);
            TextView quantityView = (TextView) convertView.findViewById(R.id.item_stock_quantity);
            stockNameView.setText(detail.get(position).getName());
            quantityView.setText(String.valueOf(detail.get(position).getQuantity()));
            return convertView;
        }
    }
}
