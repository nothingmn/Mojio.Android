package io.moj.mojioandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import Mojio.Configuration;
import Mojio.MojioClient;
import Mojio.Vehicle;

/**
 * A fragment representing a single Vehicle detail screen.
 * This fragment is either contained in a {@link VehicleListActivity}
 * in two-pane mode (on tablets) or a {@link VehicleDetailActivity}
 * on handsets.
 */
public class VehicleDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Vehicle selectedVehicle;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VehicleDetailFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String mojioId = getArguments().getString(LoginActivity.MOJIO_CLIENT_ID);
        MojioClient client = new MojioClient(Configuration.getDefault());
        client.setMojioAPIToken(mojioId);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String vehicleId = getArguments().getString(ARG_ITEM_ID);
            selectedVehicle = MojioClient.VehicleByMojioId(vehicleId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vehicle_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (selectedVehicle != null) {
            ((TextView) rootView.findViewById(R.id.vehicle_detail)).setText(selectedVehicle.MojioId);
        }

        return rootView;
    }
}
