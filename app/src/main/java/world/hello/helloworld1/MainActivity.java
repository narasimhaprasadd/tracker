package world.hello.helloworld1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button btnShowLocation;
    private TextView longitude;
    private TextView latitude;


    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);


        btnShowLocation = (Button) findViewById(R.id.show_location);
        gps = new GPSTracker(MainActivity.this);

        if (gps.canGetLocation()) {
            latitude.setText(String.valueOf(gps.getLatitude()));
            longitude.setText(String.valueOf(gps.getLongitude()));
        } else {
            gps.showSettingsAlert();
        }

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {

                                                   // gps.initializeLocation();
                                                   if (gps.canGetLocation()) {
                                                       double latitudeValue = gps.getLatitude();
                                                       double longitudeValue = gps.getLongitude();
                                                       Toast.makeText(
                                                               getApplicationContext(),
                                                               "Your Location is -\nLat: " + latitudeValue + "\nLong: "
                                                                       + longitudeValue, Toast.LENGTH_LONG).show();
                                                   } else {
                                                       gps.showSettingsAlert();
                                                   }
                                               }

                                           }

        );


    }


}
