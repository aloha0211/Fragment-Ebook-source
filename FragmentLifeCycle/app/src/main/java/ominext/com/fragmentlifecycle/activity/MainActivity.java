package ominext.com.fragmentlifecycle.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ominext.com.fragmentlifecycle.R;
import ominext.com.fragmentlifecycle.fragment.DetailsFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean isMultiPane() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    public void showDetails(int index) {
        Log.v(TAG, "in MainActivity showDetails(" + index + ")");
        if (isMultiPane()) {
        // Check what fragment is shown, replace if needed.
            DetailsFragment details = (DetailsFragment)
                    getFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                details = DetailsFragment.newInstance(index);
                // Execute a transaction, replacing any existing
                // fragment with this one inside the frame.
                Log.v(TAG, "about to run FragmentTransaction...");
                FragmentTransaction ft
                        = getFragmentManager().beginTransaction();
                ft.setTransition(
                        FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                //ft.addToBackStack("details");
                ft.replace(R.id.details, details);
                ft.commit();
            }
            // The rest was left out to save space.
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            startActivity(intent);
        }
    }

        //    Listing 1-13. Fragment-to-Target-Fragment Setup
        //    mCalledFragment = new CalledFragment();
        //    mCalledFragment.setTargetFragment(this, 0);
        //    fm.beginTransaction().add(mCalledFragment, "work").commit();

//    TextView tv = (TextView) getTargetFragment().getView().findViewById(R.id.text1);
//    tv.setText("Set from the called fragment");
}
