package br.ufc.dc.sd4mp.mtgmaster;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class RolarDadoActivity extends FragmentActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor sensor;

    private float lastValue;
    private long lastMeasure;

    private TextView diceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rolar_dado);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lastMeasure = System.currentTimeMillis();

        diceValue = (TextView) findViewById(R.id.valorDoDado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rolar_dado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        long currMeasure = System.currentTimeMillis();
        float lux = sensorEvent.values[0];

        //Toast.makeText(getApplicationContext(), lux+"", Toast.LENGTH_SHORT).show();

        if (currMeasure - lastMeasure >= 100) {
            if (lux < lastValue) {
                manager.unregisterListener(this);
                diceValue.setText(Integer.toString(Math.abs(new Random().nextInt() % 20 + 1)));
            }

            lastValue = lux;
            lastMeasure = currMeasure;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void voltar(View v) {
        finish();
    }
}
