package br.ufc.dc.sd4mp.mtgmaster;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class NovaPartidaActivity extends FragmentActivity implements MenuFragment.OnFragmentInteractionListener{

    Partida partida;
    TextView p1;
    TextView p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_partida);
        partida = new Partida();
        p1 = (TextView) findViewById(R.id.player1);
        p2 = (TextView) findViewById(R.id.player2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nova_partida, menu);
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

    public void p1Plus(View v) {
        partida.p1Plus();
        p1.setText(Integer.toString(partida.getPlayer1()));

    }

    public void p1Minus(View v) {
        partida.p1Minus();
        p1.setText(Integer.toString(partida.getPlayer1()));
    }

    public void p2Plus(View v) {
        partida.p2Plus();
        p2.setText(Integer.toString(partida.getPlayer2()));
    }

    public void p2Minus(View v) {
        partida.p2Minus();
        p2.setText(Integer.toString(partida.getPlayer2()));
    }

    @Override
    public void novaPartida(View v) {
        Intent intent = new Intent(NovaPartidaActivity.this, NovaPartidaActivity.class);
        startActivity(intent);
        fimDeJogo(v);
    }
    @Override
    public void rolarDado(View v) {
        Intent intent = new Intent(NovaPartidaActivity.this, RolarDadoActivity.class);
        startActivity(intent);
    }

    @Override
    public void showHistory(View v) {
        Intent intent = new Intent(NovaPartidaActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    public void fimDeJogo(View v) {
        ContentValues valores = new ContentValues();
        valores.put(PartidaProvider.P1, partida.getPlayer1());
        valores.put(PartidaProvider.P2, partida.getPlayer2());

        getContentResolver().insert(PartidaProvider.CONTENT_URI, valores);

        createNotification("Jogador " + partida.getVencedor() + " ganhou.", "Partida Salva", "Esta partida foi adicionada com sucesso ao banco de dados.");

        //Toast.makeText(getBaseContext(), "Partida salva.", Toast.LENGTH_LONG).show();

        finish();
    }

    protected void createNotification(String status, String title, String content) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker(status);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        manager.notify(R.string.app_name, notification);
    }
}
