package io.moj.mojioandroid;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import Mojio.MojioClient;


/**
 * An activity representing a list of Vehicles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link VehicleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link VehicleListFragment} and the item details
 * (if present) is a {@link VehicleDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link VehicleListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class VehicleListActivity extends FragmentActivity
        implements VehicleListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        // Show the Up button in the action bar.
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragment = ((VehicleListFragment) getSupportFragmentManager().findFragmentById(R.id.vehicle_list));

        if (findViewById(R.id.vehicle_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            fragment.setActivateOnItemClick(true);
        }

        Intent i = getIntent();
        String id = i.getStringExtra(LoginActivity.MOJIO_CLIENT_ID);
        mojio.setMojioAPIToken(id);
        fragment.setMojioClient(mojio);

    }
    VehicleListFragment fragment;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private MojioClient mojio = new MojioClient(null);

    /**
     * Callback method from {@link VehicleListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(VehicleDetailFragment.ARG_ITEM_ID, id);
//            VehicleDetailFragment fragment = new VehicleDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.vehicle_detail_container, fragment)
//                    .commit();

            Intent detailIntent = new Intent(this, MainTabbedActivity.class);
            detailIntent.putExtra(VehicleDetailFragment.ARG_ITEM_ID, id);
            detailIntent.putExtra(LoginActivity.MOJIO_CLIENT_ID, mojio.getMojioAPIToken());

            startActivity(detailIntent);


        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, MainTabbedActivity.class);
            detailIntent.putExtra(VehicleDetailFragment.ARG_ITEM_ID, id);
            detailIntent.putExtra(LoginActivity.MOJIO_CLIENT_ID, mojio.getMojioAPIToken());

            startActivity(detailIntent);
        }
    }
}
