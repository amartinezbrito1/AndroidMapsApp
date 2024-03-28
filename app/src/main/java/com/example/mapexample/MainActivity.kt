package com.example.mapexample

import android.graphics.Color
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tomtom.online.sdk.common.location.BoundingBox
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.map.CameraFocusArea
import com.tomtom.online.sdk.map.Icon
import com.tomtom.online.sdk.map.MapFragment
import com.tomtom.online.sdk.map.Marker
import com.tomtom.online.sdk.map.MarkerBuilder
import com.tomtom.online.sdk.map.OnMapReadyCallback
import com.tomtom.online.sdk.map.RouteBuilder
import com.tomtom.online.sdk.map.RouteStyle
import com.tomtom.online.sdk.map.RouteStyleBuilder
import com.tomtom.online.sdk.map.SimpleMarkerBalloon
import com.tomtom.online.sdk.map.TomtomMap
import com.tomtom.online.sdk.map.TomtomMapCallback

class MainActivity : AppCompatActivity()
{
    companion object
    {
        private var instance : MainActivity? = null
        public fun getInstance() : MainActivity
        {
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        instance = this
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as MapFragment

        var handler = Handler()

        mapFragment.getAsyncMap(handler)

    }

    inner class Handler : OnMapReadyCallback, TomtomMapCallback.OnMapClickListener, TomtomMapCallback.OnMapLongClickListener, TomtomMapCallback.OnMapDoubleClickListener, TomtomMapCallback.OnMapPanningListener, TomtomMapCallback.OnMarkerClickListener
    {
        private var tomtomMap : TomtomMap? = null
        var ucaTopLeft : LatLng? = null
        var ucaBottomRight : LatLng?=null
        var boundingBox  : BoundingBox?=null
        var focusArea : CameraFocusArea? = null
        private var geocoder = Geocoder(getInstance())

        override fun onMapReady(tomtomMap: TomtomMap)
        {
            println("Map Ready")
            this.tomtomMap = tomtomMap

            ucaTopLeft = LatLng(35.076580, -92.55)
            ucaBottomRight = LatLng(35.0, -92.35)
            boundingBox = BoundingBox(ucaTopLeft, ucaBottomRight)
            focusArea = CameraFocusArea.Builder(boundingBox!!).build()

            //registering
            tomtomMap?.centerOn(focusArea!!)
            tomtomMap?.addOnMapClickListener(this);
            tomtomMap?.addOnMapLongClickListener(this);
            tomtomMap?.addOnMapDoubleClickListener(this);
            tomtomMap?.addOnMapPanningListener(this);

            //getting the middle point of the box by diviging by two
            val position = LatLng( (ucaTopLeft!!.latitude + ucaBottomRight!!.latitude)/2.0, (ucaTopLeft!!.longitude + ucaBottomRight!!.longitude)/2.0)
            //balloon marker
            val markerBuilder = MarkerBuilder(position).markerBalloon(SimpleMarkerBalloon("Center"))

            //add it to the map
            tomtomMap?.addMarker(markerBuilder)

            tomtomMap?.addOnMarkerClickListener(this)
            //latLng from the JSON
            var route = ArrayList<LatLng>()
            route.add(LatLng(34.9715546327395, -92.4167190737408))
            route.add(LatLng(35.11740532184071, -92.4487898332373))

            //it will go bring the map icon from the internet, you have to specify the map route
            val startIcon = Icon.Factory.fromResources(MainActivity.getInstance(), com.tomtom.online.sdk.map.R.drawable.ic_map_route_departure)

            var endIcon = Icon.Factory.fromResources(MainActivity.getInstance(), com.tomtom.online.sdk.map.R.drawable.ic_map_route_destination)

            val rsb = RouteStyleBuilder.create().withWidth(1.0).withFillColor(Color.BLACK) .withOutlineColor(Color.RED).build()
            //it will build a rounte,, BUT you have to use json data and give it the lat and lng
            var routeBuilder = RouteBuilder(route).style(rsb).startIcon(startIcon).endIcon(endIcon)
            tomtomMap.addRoute(routeBuilder)


            //geocoder to get the address
            var list = geocoder.getFromLocation(34.9715546327395, -92.4167190737408, 3)
            var address = list?.get(0)  //Should make sure the list is not empty...
            println(address)


            var result = geocoder.getFromLocationName("201 S. Donaghey, Conway, AR", 1)
            var address1 = result?.get(0)
            println(address1?.postalCode)
            println(address1?.thoroughfare)



        }

        override fun onMapClick(latLng: LatLng)
        {
            println("Click")
            println(latLng)

            var left_top = LatLng(latLng.latitude + 0.001f ,latLng.longitude - 0.001f)
            var right_bottom = LatLng(latLng.latitude - 0.001f, latLng.longitude + 0.001f)
            var boundingBox = BoundingBox(left_top, right_bottom)
            var focusArea = CameraFocusArea.Builder(boundingBox).build()
            tomtomMap?.centerOn(focusArea)
        }

        override fun onMapLongClick(latLng: LatLng)
        {
            println("Long Click")
            println(latLng)
        }
        override fun onMapDoubleClick(latLng: LatLng)
        {
            println("Double Click")
            println(latLng)
        }

        override fun onMapPanningStarted()
        {
            println("Pan Start")
        }
        override fun onMapPanningOngoing()
        {
            println("Pan on going")
            println(tomtomMap?.currentBounds)

        }
        override fun onMapPanningEnded()
        {
            println("Pan End")
        }
        override fun onMarkerClick(marker: Marker)
        {
            println("Balloon Clicked")
        }


    }
}