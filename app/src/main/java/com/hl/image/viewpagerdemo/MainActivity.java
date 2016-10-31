package com.hl.image.viewpagerdemo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView index;
    private TextView found;
    private IndexFragment indexFragment;
    private FoundFragment foundFragment;
    private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = (TextView) findViewById(R.id.index);
        found = (TextView) findViewById(R.id.found);
        index.setOnClickListener(this);
        found.setOnClickListener(this);
        indexFragment = new IndexFragment();
        foundFragment = new FoundFragment();
        getFragmentManager().beginTransaction().add(R.id.frame,indexFragment).commit();
        mContent = indexFragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.index:

                changeFrament(mContent ,indexFragment);
//                mContent = indexFragment;
                break;
            case R.id.found:

                changeFrament(mContent ,foundFragment);
//                mContent = foundFragment;
                break;
        }
//        if (view == index) {
//        }else if (view == found) {
//
//        }
    }

    /**
     * @param fromFragment
     * @param toFragment
     */
    private void changeFrament(Fragment fromFragment, Fragment toFragment) {
        if (mContent != toFragment) {
            mContent = toFragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (!toFragment.isAdded()) {
                transaction.hide(fromFragment).add(R.id.frame,toFragment).commit();
            }else {
                transaction.hide(fromFragment).show(toFragment).commit();
            }
        }
    }

}
