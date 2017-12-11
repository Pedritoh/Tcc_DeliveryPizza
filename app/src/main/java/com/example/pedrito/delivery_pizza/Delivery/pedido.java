package com.example.pedrito.delivery_pizza.Delivery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.R;

/**
 * Created by Pedrito on 30/08/2017.
 */

public class pedido {


    String idPedido ;

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }
}
/*

        spinnerCard.setOnClickListener(new View.OnClickListener() {
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
                        url = "http://10.0.2.2/html/login/logar.php";

                        parametros = "email=" + email + "&senha=" + senha;

                        new TelaLogin.SolicitaDados().execute(url);
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
                abreInicio.putExtra("nome_usuario",dados[1]);
                // abreInicio.putExtra("id_usario",dados[1]);
                startActivity(abreInicio);

            }
            else{

                Toast.makeText(getApplicationContext(),"Email ou Senha incorretos !",Toast.LENGTH_LONG).show();
            }



        }

    }
}
*/