package com.coltcn.ims.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coltcn.ims.R;
import com.coltcn.ims.pojo.Stock;
import com.coltcn.ims.utils.L;

/**
 * Created by colt on 15/10/19.
 */
public class StockDetailActivity extends AppCompatActivity {
    private ActionBar ab;

    private Button okButton;
    private EditText nameEditor;
    private EditText priceEditor;
    private EditText quantityEditor;

    private String opt;
    private Stock stock;
    private int listPosiston=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);
        initView();
    }

    private void initView(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(mToolbar);

        ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        nameEditor = (EditText) findViewById(R.id.item_stock_name);
        priceEditor = (EditText) findViewById(R.id.item_stock_price);
        quantityEditor = (EditText) findViewById(R.id.item_stock_quantity);
        okButton = (Button) findViewById(R.id.button_save);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                L.i("save button click");
                String name = nameEditor.getText().toString();
                Double parice = Double.parseDouble(priceEditor.getText().toString());
                int quantity = Integer.parseInt(quantityEditor.getText().toString());
                if ("edit".equals(opt)){
                    stock.setPrice(parice);
                    stock.setQuantity(quantity);
                }else {
                    if (!"".equals(name) &&
                            parice > 0 &&
                            quantity > 0) {
                        stock = new Stock();
                        stock.setName(name);
                        stock.setPrice(parice);
                        stock.setQuantity(quantity);
                    }
                }
                if (stock != null) {
                    Intent intent = new Intent();
                    intent.putExtra("result", stock);
                    intent.putExtra("position", listPosiston);
                    StockDetailActivity.this.setResult(1, intent);
                    StockDetailActivity.this.finish();
                }
            }
        });

        opt = this.getIntent().getStringExtra("opt");
        if ("edit".equals(opt)){
            stock = (Stock) this.getIntent().getSerializableExtra("stock");
            nameEditor.setText(stock.getName());
            nameEditor.setEnabled(false);
            priceEditor.setText(String.valueOf(stock.getPrice()));
            quantityEditor.setText(String.valueOf(stock.getQuantity()));
            listPosiston = this.getIntent().getIntExtra("position",-1);
        }
    }
}
