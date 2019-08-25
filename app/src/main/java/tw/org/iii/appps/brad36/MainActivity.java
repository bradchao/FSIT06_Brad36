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
    private MyListener2 myListener2;
    private TextView x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);

        sensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors){
            Log.v("brad", sensor.getName() + ":" + sensor.getStringType() +
                    ":" + sensor.getVendor());
        }

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myListener = new MyListener(); myListener2 = new MyListener2();
        sensorManager.registerListener(myListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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
            x.setText("X: " + (int)(values[0]));
            y.setText("Y: " + (int)(values[1]));
            z.setText("Z: " + (int)(values[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    private class MyListener2 implements SensorEventListener2 {

        @Override
        public void onFlushCompleted(Sensor sensor) {

        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

}
