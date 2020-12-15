package com.example.beacon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.example.adapter.ItemAdapter;
import com.example.model.Item;
import com.example.network.GetDataService;
import com.example.network.RetrofitClientInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BeaconManager beaconManager;
    private BeaconRegion region;
    private static final Map<String, String> beaconsData = new HashMap<>();
    RecyclerView recyclerView;
    private ItemAdapter adapter;
    String prevQuery = "";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        beaconManager = new BeaconManager(this);
        region = new BeaconRegion("test",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);


        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, final List<Beacon> beacons) {
                count ++;
                if (!beacons.isEmpty()) {
                    String queryParam = placesNearBeacon(beacons.get(0));
                    if (queryParam != prevQuery && count > 4) {
                        prevQuery = queryParam;
                        getStoreItems(queryParam);
                          Log.d("demo", queryParam);
                        Log.d("demo", "count " +count);
                        Log.d("demo", "onBeaconsDiscovered: Discovered beacons ");
                          count =0;
                    }
                } else
                    getStoreItems("");
            }
        });

    }

    static {
        beaconsData.put("45153:9209", "lifestyle"); //A6
        beaconsData.put("21839:34812", "produce"); //A99
        beaconsData.put("15326:56751", "grocery"); //C3
        //  beaconsData.put("47152:61548", "D6");
    }


    private void generateDataList(List<Item> itemList) {
        adapter = new ItemAdapter(this, itemList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    private String placesNearBeacon(Beacon beacon) {
        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
        if (beaconsData.containsKey(beaconKey)) {
            return beaconsData.get(beaconKey);
        }
        return "";
    }

    private void getStoreItems(String region) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Item>> call = service.getItems(region);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                getStoreItems("");
                beaconManager.startRanging(region);
                Log.d("demo", "onServiceReady: ");
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);
        super.onPause();
    }

    public void listen() {
        Log.d("demo", "Listen k ander");

    }
}