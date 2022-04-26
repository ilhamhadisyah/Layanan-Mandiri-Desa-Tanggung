package com.pemdestanggung.layananmandiridesatanggung.utils

import android.graphics.Camera
import android.graphics.Color
import android.text.style.StyleSpan
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*


class MapController : OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val polyCoordinates = TanggungPolyCoordinatesV2.getPolyCoordinates()

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .addAll(polyCoordinates)
                .fillColor(Color.argb(40, 0, 204, 204))
                .strokeWidth(1.30F)
        )

        val tanggung = LatLng(-8.171903336031054, 112.67503556790739)

        mMap.addMarker(
            MarkerOptions().position(tanggung).title("kantor Desa Tanggung")
        )
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    -8.174842127057001, 112.67577816174733
                ), 13.5f
            )
        )

    }


}