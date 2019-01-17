package subhash.proximitysensor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager smgr;
    Sensor ses;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(LinearLayout)findViewById(R.id.linear);
        smgr=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        ses=smgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        smgr.registerListener(this,ses,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x=event.values[0];
        Toast.makeText(MainActivity.this,""+x,Toast.LENGTH_LONG).show();
        if(x>0)
        {
            //Toast.makeText(MainActivity.this, "Object go far to you", Toast.LENGTH_SHORT).show();
            layout.setBackgroundColor(Color.RED);

        }
        else
        {
            layout.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        smgr.unregisterListener(this);
    }
}
