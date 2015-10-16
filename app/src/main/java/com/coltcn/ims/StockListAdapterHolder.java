package com.coltcn.ims;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coltcn.ims.pojo.Stock;

import java.util.List;

/**
 * Created by majf on 2015/10/16.
 */
public class StockListAdapterHolder extends RecyclerView.Adapter<StockListAdapterHolder.ViewHoder> {

    private Context context;
    private List<Stock> stocks;

    public StockListAdapterHolder(Context context,List<Stock> stocks) {
        this.context = context;
        this.stocks=stocks;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
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
        return stocks.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView nameView,priceView,quantityView;
        public ViewHoder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.item_stock_name);
            priceView = (TextView) itemView.findViewById(R.id.item_stock_price);
            quantityView = (TextView) itemView.findViewById(R.id.item_stock_quantity);
        }
    }
}
