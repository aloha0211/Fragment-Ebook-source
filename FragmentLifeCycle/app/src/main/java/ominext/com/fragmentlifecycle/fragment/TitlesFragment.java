package ominext.com.fragmentlifecycle.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ominext.com.fragmentlifecycle.Shakespeare;
import ominext.com.fragmentlifecycle.activity.MainActivity;

/**
 * Created by LuongHH on 3/29/2017.
 */

public class TitlesFragment extends ListFragment {
    private MainActivity myActivity = null;
    int mCurCheckPosition = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.myActivity = (MainActivity) context;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        Log.v(MainActivity.TAG,
                "in TitlesFragment onActivityCreated. savedState contains:");
        if (savedState != null) {
            for (String key : savedState.keySet()) {
                Log.v(MainActivity.TAG, " " + key);
            }
        } else {
            Log.v(MainActivity.TAG, " savedState is null");
        }
        super.onActivityCreated(savedState);
// Populate list with your static array of titles.
        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                Shakespeare.TITLES));
        if (savedState != null) {
// Restore last state for checked position.
            mCurCheckPosition = savedState.getInt("curChoice", 0);
        }
// Get your ListFragment's ListView and update it
        ListView lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setSelection(mCurCheckPosition);
// Activity is created, fragments are available
// Go ahead and populate the details fragment
        myActivity.showDetails(mCurCheckPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.v(MainActivity.TAG, "in TitlesFragment onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        Log.v(MainActivity.TAG,
                "in TitlesFragment onListItemClick. pos = "
                        + pos);
        myActivity.showDetails(pos);
        mCurCheckPosition = pos;
    }

    @Override
    public void onDetach() {
        Log.v(MainActivity.TAG, "in TitlesFragment onDetach");
        super.onDetach();
        myActivity = null;
    }
}
