package com.itonghui.tfdz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class VPMainAdapter extends FragmentStatePagerAdapter {
      
    private List<Fragment> fragments;
      
    public VPMainAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);  
        this.fragments = fragments;  
    }  
  
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);  
    }  
  
    @Override
    public int getCount() {  
        return fragments.size();  
    }  
  
}  