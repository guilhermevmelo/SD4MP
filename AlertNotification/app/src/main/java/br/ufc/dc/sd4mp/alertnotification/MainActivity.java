package br.ufc.dc.sd4mp.alertnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void updateReceivers(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        Intent intent = new Intent();


        switch (view.getId()) {
            case R.id.cbBattery:
                intent.setAction(checked ? "br.ufc.dc.action.BATTERY_CHANGED_TRUE" : "br.ufc.dc.action.BATTERY_CHANGED_FALSE");
                break;
            case R.id.cbAirplane:
                intent.setAction(checked ? "br.ufc.dc.action.AIRPLANE_MODE_TRUE" : "br.ufc.dc.action.AIRPLANE_MODE_FALSE");
                break;
            case R.id.cbChargerOn:
                intent.setAction(checked ? "br.ufc.dc.action.ACTION_POWER_CONNECTED_TRUE" : "br.ufc.dc.action.ACTION_POWER_CONNECTED_FALSE");
                break;
            case R.id.cbChargerOff:
                intent.setAction(checked ? "br.ufc.dc.action.ACTION_POWER_DISCONNECTED_TRUE" : "br.ufc.dc.action.ACTION_POWER_DISCONNECTED_FALSE");
                break;
            default:
                Toast.makeText(getApplicationContext(), "You should never see this text =)", Toast.LENGTH_SHORT).show();
        }

        sendBroadcast(intent);
    }
}
