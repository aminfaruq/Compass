package id.co.aminfaruq.compass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

    public ImageView imgCompass;
    public float currentDegree = 0f;
    public SensorManager sensorManager;

    TextView txtDegrees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCompass = (ImageView) findViewById(R.id.imgCompass);
        txtDegrees =(TextView) findViewById(R.id.txtDegrees);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        txtDegrees .setText("Rotation :" + Float.toString(degree) + "degress");
        RotateAnimation ra = new RotateAnimation(currentDegree,-degree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(120);
        ra.setFillAfter(true);
        imgCompass.startAnimation(ra);
        currentDegree=-degree;

    }

    @Override
    public void onAccuracyChanged(Sensor p1, int p2) {

    }
}
