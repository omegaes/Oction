package com.mega4tech.oction.mvp.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mega4tech.oction.mvp.account.view.impl.AccountFragment;
import com.mega4tech.oction.mvp.auction.view.impl.AuctionFragment;
import com.mega4tech.oction.mvp.credit.view.impl.CreditFragment;

/**
 * Created by aboodba on 15/06/2017.
 */

public class MainScreenViewPagerAdapter extends FragmentPagerAdapter {


    public MainScreenViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return AuctionFragment.newInstance();
            case 1:
                return CreditFragment.newInstance();
            case 2:
                return AccountFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
