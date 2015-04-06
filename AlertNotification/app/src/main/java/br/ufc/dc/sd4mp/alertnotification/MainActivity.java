package br.ufc.dc.sd4mp.alertnotification;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends Activity {
    private boolean listeners[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeners = new boolean[]{false, false, false, false};
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
        boolean checked = ((CheckBox)view).isChecked();

        switch(view.getId()) {
            case R.id.cbBattery:
                listeners[0] = checked;
                break;
            case R.id.cbAirplane:
                listeners[1] = checked;
                break;
            case R.id.cbChargerOn:
                listeners[2] = checked;
                break;
            case R.id.cbChargerOff:
                listeners[3] = checked;
                break;
            default:
                Toast.makeText(getApplicationContext(), "You should never see this text =)", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean getListenerStatus(int whichOne) {
        return (whichOne >= 0 && whichOne < 4)? listeners[whichOne] : false;
    }
}
