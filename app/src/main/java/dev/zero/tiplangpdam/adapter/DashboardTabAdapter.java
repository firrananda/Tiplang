package dev.zero.tiplangpdam.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import dev.zero.tiplangpdam.fragment.BaruFragment;
import dev.zero.tiplangpdam.fragment.HelpFragment;
import dev.zero.tiplangpdam.fragment.ProfileFragment;
import dev.zero.tiplangpdam.fragment.RevisiFragment;

public class DashboardTabAdapter extends FragmentStatePagerAdapter {

    public DashboardTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BaruFragment();
        } else if (position == 1) {
            return new RevisiFragment();
        } else if (position == 2) {
            return new HelpFragment();
        } else if (position == 3) {
            return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "BARU";
        } else if (position == 1) {
            return "REVISI";
        } else if (position == 2) {
            return "BANTUAN";
        } else if (position == 3) {
            return "PROFIL";
        }
        return null;
    }
}
