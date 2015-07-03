package br.ufc.dc.sd4mp.mtgmaster;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements MenuFragment.OnFragmentInteractionListener {

    //private MenuFragment menuFragment;
    //private Partida partida;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(R.string.app_name);

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

    @Override
    public void novaPartida(View v) {
        Intent intent = new Intent(MainActivity.this, NovaPartidaActivity.class);
        startActivity(intent);
    }

    @Override
    public void rolarDado(View v) {
        Intent intent = new Intent(MainActivity.this, RolarDadoActivity.class);
        startActivity(intent);
    }

    @Override
    public void showHistory(View v) {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }
}
