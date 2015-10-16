package com.coltcn.ims.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.coltcn.ims.R;
import com.coltcn.ims.ui.Fragment.StockListFragment;
import com.coltcn.ims.ui.base.BaseActivity;
import com.coltcn.ims.utils.L;

public class MainActivity extends AppCompatActivity {

    private StockListFragment stockListFragment;
    private DrawerLayout mDrawerLayout;
    private ActionBar ab;

//    @Override
//    protected int provideContentViewId() {
//        return R.layout.activity_main;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {

//        setStatusColor(android.R.color.transparent);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(mToolbar);


        ab = getSupportActionBar();

        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        selectFragment(R.id.nav_stock_manage);

    }

    private void selectFragment(int fragmentId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideAllFragment(transaction);
        switch (fragmentId) {
            case R.id.nav_stock_manage:
                if (stockListFragment == null) {
                    stockListFragment = new StockListFragment();
                    ab.setTitle("库存管理");
                    transaction.add(R.id.id_main_frame_container, stockListFragment,"库存管理");
                } else {
                    transaction.show(stockListFragment);
                }
                break;

            case R.id.nav_order_manage:
              /*  if (hotReposFragment == null) {
                    hotReposFragment = new HotReposFragment();
                    ab.setTitle("Hot Repositories");
                    transaction.add(R.id.id_main_frame_container, hotReposFragment, "hotRepos");
                } else {
                    transaction.show(hotReposFragment);
                }*/
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (stockListFragment != null) {
            transaction.hide(stockListFragment);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        //selectFragment(menuItem.getItemId());
                        return true;
                    }
                });
    }

}
