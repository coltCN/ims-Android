package com.coltcn.ims.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coltcn.ims.R;
import com.coltcn.ims.dao.Stock;
import com.coltcn.ims.utils.L;

import java.util.List;

/**
 * Created by majf on 2015/10/16.
 */
public class StockListAdapterHolder extends RecyclerView.Adapter<StockListAdapterHolder.ViewHoder> {

    OnItemClickListener listItemClickListener;
    private List<Stock> stocks;

    public StockListAdapterHolder(List<Stock> stocks) {
        this.stocks=stocks;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View view = inflater.inflate(R.layout.stock_list_item,viewGroup,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(ViewHoder viewHoder, int i) {
        if (i>stocks.size()) return;
        viewHoder.nameView.setText(stocks.get(i).getName());
        viewHoder.priceView.setText(stocks.get(i).getPrice().toString());
        viewHoder.quantityView.setText(String.valueOf(stocks.get(i).getQuantity()));
    }

    @Override
    public int getItemCount() {
        if (stocks == null) return 0;
        return stocks.size();
    }

    public void setOnItemClickListener(final OnItemClickListener listener){
        this.listItemClickListener=listener;
    }


    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameView,priceView,quantityView;
        public ViewHoder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.item_stock_name);
            priceView = (TextView) itemView.findViewById(R.id.item_stock_price);
            quantityView = (TextView) itemView.findViewById(R.id.item_stock_quantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listItemClickListener != null){
                L.i("adapterHolder onClick");
                listItemClickListener.onItemClick(view,getLayoutPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
