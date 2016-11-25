package ir.ounegh.kikojast;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.widget.SearchView.*;
import android.view.*;

public class MyMocation extends Activity
{
	locservice gps;
	Button btnShowLocation;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myloc);
		btnShowLocation = (Button) findViewById(R.id.mylocButton);
		btnShowLocation.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					show();
				}
				
			
		});

       } // show location button click event
        private void show(){
					gps = new locservice(MyMocation.this);

					// check if GPS enabled     
					if(gps.canGetLocation()){

						double latitude = gps.getLatitude();
						double longitude = gps.getLongitude();

						// \n is for new line
						Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
					}else{
						// can't get location
						// GPS or Network is not enabled
						// Ask user to enable GPS/network in settings
						gps.showSettingsAlert();
					}

				}
		
	
	
}
