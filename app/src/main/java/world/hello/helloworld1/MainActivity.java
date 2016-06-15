package world.hello.helloworld1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button btnShowLocation;

    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TextView latitude = (TextView) findViewById(R.id.latitude);
        final TextView longitude = (TextView) findViewById(R.id.longitude);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.show_location);
        gps = new GPSTracker(MainActivity.this);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {

                                                   // gps.initializeLocation();

                                                   if (gps.canGetLocation()) {
                                                       double latitude = gps.getLatitude();
                                                       double longitude = gps.getLongitude();

                                                       Toast.makeText(
                                                               getApplicationContext(),
                                                               "Your Location is -\nLat: " + latitude + "\nLong: "
                                                                       + longitude, Toast.LENGTH_LONG).show();
                                                   } else {
                                                       gps.showSettingsAlert();
                                                   }
                                               }

                                           }

        );


    }


}
