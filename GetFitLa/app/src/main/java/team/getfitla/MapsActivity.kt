package team.getfitla

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.io.File
import java.io.InputStream
import com.google.maps.android.data.kml.KmlLayer

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
        GoogleMap.onMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMarkerClick(p0: Marker?) = false

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Centering on user location
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)


        // Add a marker in Los Angeles
        val Losangeles = LatLng(34.0522, -118.2437)
        mMap.addMarker(MarkerOptions().position(Losangeles).title("Marker in LA"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Losangeles, 12.0f))

        //Add KML Layer
        val layer = KmlLayer(mMap, R.raw.engmap, applicationContext)
        layer.addLayerToMap()

        setupMap()
    }


    // Adding companion object to request user's permission
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    // Check if the user has granted the app permission to access location
    private fun setupMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_REQUEST_CODE))
            return
        }

    }
}
