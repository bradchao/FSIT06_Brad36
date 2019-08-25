package tw.org.iii.appps.brad36;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private MyListener myListener;
    private MyView myView;
    private float viewW, viewH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = findViewById(R.id.myView);
        viewW = getWindowManager().getDefaultDisplay().getWidth();
        viewH = getWindowManager().getDefaultDisplay().getHeight();
        Log.v("brad", viewW + "x" + viewH);

        sensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors){
            Log.v("brad", sensor.getName() + ":" + sensor.getStringType() +
                    ":" + sensor.getVendor());
        }

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myListener = new MyListener();
        sensorManager.registerListener(myListener, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(myListener);
    }

    private class MyListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            changeBall((int)(values[0]*10),(int)(values[1]*10) ,(int)(values[2]*10));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    private void changeBall(int x,int y, int z){
        float xx = x*viewW/200 + viewW/2;
        float yy = y*viewH/200 + viewH/2;
        float zz = z - 50;
        myView.setBallXY(xx,yy, zz);

        //Log.v("brad", "x = " + x + "; xx = " + xx);
    }



}
