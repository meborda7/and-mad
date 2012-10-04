package map.practice;

import java.io.IOException;
import java.util.List;

import android.app.AlertDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;


public class MapPracticeActivity extends MapActivity {
    /** Called when the activity is first created. */
	
	
	//
	MapController mController;
	GeoPoint geoP;    									//finding stuff in the map
	MapView mapV;
	Geocoder geoC;
	MyLocationOverlay compass;							//
	
	ParaTouch pt;
	List <Overlay> overlayList;
	
	//default lat and longi
	private double lat = 10.18; 
    private double longi = 123.54;
    
    //para timer sa pag touch
    private long start;
    private long stop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapV = (MapView) findViewById(R.id.mapView);
        mapV.displayZoomControls(true);					//zoomable
        mapV.setBuiltInZoomControls(true);				//para ni contorl ktoh naa minus ug plus
        geoC = new Geocoder(this);
        try {
			
			List <Address> adds = geoC.getFromLocationName("Moalboal Cebu", 1);  
			geoP = new GeoPoint ((int) (adds.get(0).getLatitude() * 1E6), (int) (adds.get(0).getLatitude() * 1E6));

	        //set default map center   (latitude and longitude) in degrees
	        //set sa geoPoint, convert to microdegrees sumthing
	        //geoP = new GeoPoint ((int) (lat * 1E6 ), (int) ( longi * 1E6 ));
	       
	        //set the center of the map to the assigned lat and longi int the geoP
	        mController =  mapV.getController();
	        mController.animateTo(geoP);
	        //set zoom to the center
	        mController.setZoom(9);
	        
	        //add compass overlay to the mapV
	        //compass = new MyLocationOverlay (this, mapV);
	        //mapV.getOverlays().add(compass);
	        
	        //para touch events
	        pt = new ParaTouch();
	        overlayList = mapV.getOverlays();
	        overlayList.add(pt);							//add ang touch sa map overlays
	       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//compass.disableCompass();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//compass.enableCompass();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	
	//para handle sa mga touch events
	
	class ParaTouch extends Overlay{

		@Override
		public boolean onTouchEvent(MotionEvent me, MapView mv) {
			// TODO Auto-generated method stub
			
			if (me.getAction() == MotionEvent.ACTION_DOWN)
			{
				start = me.getEventTime();
			}
			
			if (me.getAction() == MotionEvent.ACTION_UP){
				stop = me.getEventTime();
			}
			
			if (stop - start > 1500){
				AlertDialog alert = new AlertDialog.Builder(MapPracticeActivity.this).create();
				alert.setTitle("Optioins");
				alert.setMessage("Just pick anything");
			}
			return false;
		}
		
		
	}
	 
}