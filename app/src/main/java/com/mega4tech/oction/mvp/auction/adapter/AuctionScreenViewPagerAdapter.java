package com.mega4tech.oction.mvp.auction.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mega4tech.oction.R;
import com.mega4tech.oction.mvp.auction.view.impl.AuctionFragment;
import com.mega4tech.oction.mvp.credit.view.impl.CreditFragment;
import com.mega4tech.oction.mvp.current.auctions.view.impl.CurrentAuctionsFragment;
import com.mega4tech.oction.mvp.upcoming.auctions.view.impl.UpcomingAuctionsFragment;

/**
 * Created by aboodba on 15/06/2017.
 */

public class AuctionScreenViewPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public AuctionScreenViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return CurrentAuctionsFragment.newInstance();
            case 1:
                return UpcomingAuctionsFragment.newInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.tab_current_auctions);
            case 1:
                return mContext.getString(R.string.tab_upcoming_auctions);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
