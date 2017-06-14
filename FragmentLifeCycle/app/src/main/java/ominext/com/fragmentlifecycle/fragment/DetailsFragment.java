package ominext.com.fragmentlifecycle.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ominext.com.fragmentlifecycle.Shakespeare;
import ominext.com.fragmentlifecycle.activity.MainActivity;
import ominext.com.fragmentlifecycle.R;

/**
 * Created by LuongHH on 3/29/2017.
 */

public class DetailsFragment extends Fragment {

    private int mIndex = 0;

    public static DetailsFragment newInstance(int index) {
        Log.v(MainActivity.TAG, "in DetailsFragment newInstance(" +
                index + ")");
        DetailsFragment df = new DetailsFragment();
// Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        df.setArguments(args);
        return df;
    }

    public static DetailsFragment newInstance(Bundle bundle) {
        int index = bundle.getInt("index", 0);
        return newInstance(index);
    }

    @Override
    public void onCreate(Bundle myBundle) {
        Log.v(MainActivity.TAG,
                "in DetailsFragment onCreate. Bundle contains:");
        if (myBundle != null) {
            for (String key : myBundle.keySet()) {
                Log.v(MainActivity.TAG, " " + key);
            }
        } else {
            Log.v(MainActivity.TAG, " myBundle is null");
        }
        super.onCreate(myBundle);
        mIndex = getArguments().getInt("index", 0);
    }

    public int getShownIndex() {
        return mIndex;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.v(MainActivity.TAG,
                "in DetailsFragment onCreateView. container = " +
                        container);
// Don't tie this fragment to anything through the inflater.
// Android takes care of attaching fragments for us. The
// container is only passed in so you can know about the
// container where this View hierarchy is going to go.
        View v = inflater.inflate(R.layout.details, container, false);
        TextView text1 = (TextView) v.findViewById(R.id.text1);
        text1.setText(Shakespeare.DIALOGUE[mIndex]);
        return v;
    }
}