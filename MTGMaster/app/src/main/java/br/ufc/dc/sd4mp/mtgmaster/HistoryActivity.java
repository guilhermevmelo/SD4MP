package br.ufc.dc.sd4mp.mtgmaster;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class HistoryActivity extends Activity {

    ListView historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historico = (ListView) findViewById(R.id.history);
        //PartidaDAO pDAO = new PartidaDAO(this);
        //List<Partida> partidas = pDAO.list();
        String URL = PartidaProvider.URL;
        Uri partidasURI = Uri.parse(URL);
        String[] sel = {""};
        Cursor cursor = getContentResolver().query(partidasURI, null, null, null, PartidaProvider.ID);


        CursorAdapter cAdpt = new CursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return LayoutInflater.from(context).inflate(R.layout.history_item, viewGroup, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView p1 = (TextView) view.findViewById(R.id.item_p1);
                TextView p2 = (TextView) view.findViewById(R.id.item_p2);

                int vida_p1 = cursor.getInt(cursor.getColumnIndexOrThrow("p1"));
                int vida_p2 = cursor.getInt(cursor.getColumnIndexOrThrow("p2"));

                p1.setText(Integer.toString(vida_p1));
                p2.setText(Integer.toString(vida_p2));
            }
        };

        historico.setAdapter(cAdpt);



        //StringBuffer buffer = new StringBuffer();


        //TextView list = (TextView) findViewById(R.id.history);
        //list.setText(buffer.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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

    public void sairHistorico(View v) {
        finish();
    }
}
