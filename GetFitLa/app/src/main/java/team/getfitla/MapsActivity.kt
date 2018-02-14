package team.getfitla

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity() { //OnMapReadyCallback {

    //private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting the layout file as the content view
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        //val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        //mapFragment.getMapAsync(this)
    }
}
/**
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Centering the map on user's location
            mMap.setMyLocationEnabled(true)
            mMap.setOnMyLocationButtonClickListener { this }
            mMap.setOnMyLocationClickListener { this }



    }
}
*/