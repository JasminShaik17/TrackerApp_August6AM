package cubex.mahesh.trackerapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var need_permission : Boolean  = false

     if(ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED)
     {
         need_permission = true
     }
        if(ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
        {
            need_permission = true
        }
        if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED)
        {
            need_permission = true
        }
        if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_DENIED)
        {
            need_permission = true
        }
        if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED)
        {
            need_permission = true
        }
        if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
        {
            need_permission = true
        }

        if(true){
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.READ_CALL_LOG,
                            Manifest.permission.READ_SMS),123)
        }
    }

    fun readContacts( )
    {
        var resolver = contentResolver
       var c =  resolver.query(
               ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
               null,null,null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        while (c.moveToNext()){
          var name_col_index =   c.getColumnIndex(
                  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
          var number_col_index =   c.getColumnIndex(
                  ContactsContract.CommonDataKinds.Phone.NUMBER)
            var name = c.getString(name_col_index)
            var number = c.getString(number_col_index)
        }
    }

    @SuppressLint("MissingPermission")
    fun readCallLog( )
    {
        var resolver = contentResolver
        var c =  resolver.query(
                CallLog.Calls.CONTENT_URI,
                null,null,null,
                null)
        while (c.moveToNext()){
            var name_col_index =   c.getColumnIndex(
                    CallLog.Calls.CACHED_NAME)
            var number_col_index =   c.getColumnIndex(
                    CallLog.Calls.NUMBER)
            var date_index = c.getColumnIndex(
                    CallLog.Calls.DATE)
            var call_type_index = c.getColumnIndex(
                    CallLog.Calls.TYPE)
            var call_duration_index = c.getColumnIndex(
                    CallLog.Calls.DURATION)

            var name = c.getString(name_col_index)
            var number = c.getString(number_col_index)
            var date = c.getString(date_index)
            var type = c.getString(call_type_index)
            var duration = c.getString(call_duration_index)

        }
    }
    fun readSMS( )
    {
        var resolver = contentResolver
        var c =  resolver.query(
                Uri.parse("content://sms/inbox"),
                null,null,null,
                null)
        while (c.moveToNext()){
           var from =  c.getString(2)
            var msg_body = c.getString(11)

        }
    }

    @SuppressLint("MissingPermission")
    fun accessLocation( )
    {
      var lManager = getSystemService(Context.LOCATION_SERVICE)
                                    as LocationManager
      lManager.requestLocationUpdates(
              LocationManager.NETWORK_PROVIDER,
              1000.toLong(),1.toFloat(),
              object : LocationListener {
                  override fun onLocationChanged(p0: Location?) {
                        var lati:Double = p0!!.latitude
                        var longi:Double = p0!!.longitude
                  }
                  override fun onProviderEnabled(p0: String?) {

                  }
                  override fun onProviderDisabled(p0: String?) {

                  }
                  override fun onStatusChanged(p0: String?, p1: Int,
                                               p2: Bundle?) {

                  }
      })


    }
}
