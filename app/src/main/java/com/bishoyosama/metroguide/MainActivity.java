package com.bishoyosama.metroguide;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenu;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MainActivity extends AppCompatActivity implements LocationListener {
    TapBarMenu tapBarMenu;
    FabSpeedDial fabSpeedDial;
    StartFragment fragmentS;
    ResultsFragment fragmentR;
//    HistoryFragment fragmentH;
    String startsta, endsta, json = null;
    send SEND = new send();
    LocationManager locmanager;
    ArrayList<String> station_name = new ArrayList<>();
    ArrayList<Double> station_lat = new ArrayList<>();
    ArrayList<Double> station_long = new ArrayList<>();
    ArrayList<String> station_id = new ArrayList<>();
    double latitude, longitude;
    Double Stalat, Stalong;

    int statid = 0;

    public String getJson() {
        json = "";
        try {
            InputStream is = getAssets().open("json/stations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("stations");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                station_name.add(object.getString("station_name"));
                station_id.add(object.getString("id"));
                station_lat.add(object.getDouble("lat"));
                station_long.add(object.getDouble("long"));

            }
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tapBarMenu = findViewById(R.id.tapBarMenu);
        fragmentS = new StartFragment();
        fragmentR = new ResultsFragment();
//        fragmentH = new HistoryFragment();
        getJson();
        locmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perm = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, perm, 1);
        } else {
            locmanager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perm = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, perm, 2);
        } else {
            locmanager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }
        EventBus.getDefault().register(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentS).commit();
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });
        fabSpeedDial = findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nearest_station) {
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Nearest+Metro+Station"));
                    startActivity(in);


                } else if (menuItem.getItemId() == R.id.current_station) {
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + fragmentS.StartPoint.getSelectedItem().toString() + "+Cairo+Metro+Station"));
                    startActivity(in);
                } else if (menuItem.getItemId() == R.id.menu_item_info) {

                    Intent in = new Intent(getApplicationContext(), MyReceiver.class);
                    PendingIntent pe = PendingIntent.getBroadcast(getApplicationContext(), 0, in, 0);
                    try {

                        if (station_name.contains(fragmentS.Direction.getSelectedItem().toString())) {
                            for (int i = 0; i < station_name.size(); i++) {
                                statid = station_name.indexOf(fragmentS.Direction.getSelectedItem().toString());
                                Stalat = station_lat.get(statid);
                                Stalong = station_long.get(statid);
                            }
                            Toast.makeText(MainActivity.this, "Station id is: " + statid, Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(MainActivity.this, "wrong id", Toast.LENGTH_LONG).show();


                        locmanager.addProximityAlert(Stalat, Stalong, 100, -1, pe);

                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }

                return true;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    locmanager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        locmanager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(this, "feature not supported", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

//    @Subscribe
//    public void onMessage(StartFragment.Station s) {
//
////        SEND.first=startsta;
////        SEND.last=endsta;
////        EventBus.getDefault().post(SEND);
////        Bundle b=new Bundle();
////        b.putString("start",s.start);
////       fragment=new StartFragment();
////        fragment.setArguments(b);
////        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
//
//    }

    @Subscribe
    public void resultf(StartFragment.Station s) {
        if (s.gotoresfragment) {
            Toast.makeText(this, "receved", Toast.LENGTH_SHORT).show();
            startsta = s.start;
            endsta = s.end;
//            fragmentR.sent(startsta,endsta);
            Bundle b = new Bundle();
            b.putString("start", startsta);
            b.putString("end", endsta);
            fragmentR = new ResultsFragment();
            fragmentR.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentR).commit();

        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
//       TODO return lat& long of start| end
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public static class send {
        public String first, last;
    }

    public void exit(View view) {
        finishAffinity();
    }

    public void goStartFragment(View view) {
        fragmentS = new StartFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentS).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }


//
//    public void goHistoryFragment(View view) {
//        fragmentH = new HistoryFragment();
////        Bundle b = new Bundle();
////        b.putString("start", startsta);
////        b.putString("end", endsta);
////        fragmentH.setArguments(b);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentH).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
//    }
}