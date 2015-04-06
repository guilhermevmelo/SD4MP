package br.ufc.dc.sd4mp.mymail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SendMailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_mail, menu);
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

    public void cancelarEnvio(View view) {
        Toast.makeText(getApplicationContext(), "Envio cancelado.", Toast.LENGTH_LONG).show();
        finish();
    }

    public void validaCampos(View view) {
        EditText inputDestinatario, inputAssunto, inputMensagem;
        inputDestinatario = (EditText) findViewById(R.id.inputDestinatario);
        inputAssunto = (EditText) findViewById(R.id.inputAssunto);
        inputMensagem = (EditText) findViewById(R.id.inputMensagem);

        if (inputAssunto.getText().length() > 0 &&
            inputDestinatario.getText().length() > 0 &&
            inputMensagem.getText().length() > 0) {

            Intent emailSend = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", inputDestinatario.getText().toString(), null));
            emailSend.putExtra(Intent.EXTRA_SUBJECT, Uri.encode(inputAssunto.getText().toString()));
            emailSend.putExtra(Intent.EXTRA_TEXT, Uri.encode(inputMensagem.getText().toString()));
            startActivity(Intent.createChooser(emailSend, "Enviar email usando..."));

        } else {
            Toast.makeText(getApplicationContext(), "Todos os campos são obrigatórios.", Toast.LENGTH_LONG).show();
        }

    }

    private void enviar() {}
}
