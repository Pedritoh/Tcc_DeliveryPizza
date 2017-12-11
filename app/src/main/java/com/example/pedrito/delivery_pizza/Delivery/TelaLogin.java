package com.example.pedrito.delivery_pizza.Delivery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.R;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelaLogin extends Activity {

    private EditText email1, senha1;
    private Button Login;
    private TextView Cadastro;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        email1 = (EditText) findViewById(R.id.editEmail);
        senha1 = (EditText) findViewById(R.id.editSenha);
        Login = (Button) findViewById(R.id.btn_Logar);
        Cadastro = (TextView) findViewById(R.id.ir_Cadastro);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    String email = email1.getText().toString();
                    String senha = senha1.getText().toString();

                    if (email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://pediuchegou.ddns.net/aplicativo/logar.php";

                        parametros = "email=" + email + "&senha=" + senha;

                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão detectada", Toast.LENGTH_LONG).show();
                }

            }
        });

        //Função para ir para a area de Cadastro.
        Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaLogin.this, Cadastro.class));
            }
        });




    }
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);

        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("login_ok,")){
                String[] dados = resultado.split(",");

              //  email1.setText(resultado + dados[0]);
              Intent abreInicio = new Intent(TelaLogin.this,TelaInicial.class);
              abreInicio.putExtra("nome_usuario",dados[2]);
              abreInicio.putExtra("id_usuario",dados[1]);

                if(resultado.contains("cardapio_ok")) {
                String[] dados2 = resultado.split(",");
                    abreInicio.putExtra("nomeProduto",dados2[1]);
                }
                startActivity(abreInicio);

            }
            else{

                Toast.makeText(getApplicationContext(),"Email ou Senha incorretos !",Toast.LENGTH_LONG).show();
            }



        }

    }
}


