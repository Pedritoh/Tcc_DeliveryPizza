package com.example.pedrito.delivery_pizza.Delivery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.R;

public class Cadastro extends Activity {


    EditText nomeCad,sobrenomeCad,dddCad,telefoneCad,cidadeCad,ufCad, logradouroCad, numeroCad,bairroCad,complementoCad,emailCad, senhaCad;
    Button Cadastrar, Cancelar;


    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeCad = (EditText) findViewById(R.id.editNome);
        sobrenomeCad = (EditText) findViewById(R.id.editSobrenome);
        dddCad = (EditText) findViewById(R.id.editDDD);
        telefoneCad = (EditText) findViewById(R.id.editTelefone);
        cidadeCad = (EditText) findViewById(R.id.editCidade);
        ufCad = (EditText) findViewById(R.id.editEstado);
        logradouroCad = (EditText) findViewById(R.id.editEndereco);
        numeroCad = (EditText) findViewById(R.id.editNumero);
        bairroCad = (EditText) findViewById(R.id.editBairro);
        complementoCad = (EditText) findViewById(R.id.editComplemento);
        emailCad = (EditText) findViewById(R.id.editCadEmail);
        senhaCad = (EditText) findViewById(R.id.editCadSenha);

        Cadastrar = (Button) findViewById(R.id.btnCadastrar);
        Cancelar = (Button) findViewById(R.id.btnCancelar);


        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    String nome = nomeCad.getText().toString();
                    String sobrenome = sobrenomeCad.getText().toString();
                    String ddd = dddCad.getText().toString();
                    String telefone = telefoneCad.getText().toString();
                    String cidade = cidadeCad.getText().toString();
                    String uf = ufCad.getText().toString();
                    String logradouro = logradouroCad.getText().toString();
                    String numero = numeroCad.getText().toString();
                    String bairro = bairroCad.getText().toString();
                    String complemento = complementoCad.getText().toString();
                    String email2 = emailCad.getText().toString();
                    String senha2 = senhaCad.getText().toString();


                    if (nome.isEmpty() ||sobrenome.isEmpty()||ddd.isEmpty() || telefone.isEmpty() ||cidade.isEmpty()||uf.isEmpty()|| logradouro.isEmpty() || numero.isEmpty() ||bairro.isEmpty() || email2.isEmpty() || senha2.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "nenhum campo pode estar vazio, Exceto complementos", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://pediuchegou.ddns.net/aplicativo/registrar.php";

                        parametros = "nome=" + nome +"&sobrenome="+sobrenome+ "&ddd="+ddd+ "&telefone=" + telefone + "&cidade=" + cidade + "&uf="+uf+
                                "&logradouro=" + logradouro + "&numero=" + numero + "&bairro="+bairro+ "&complemento="+complemento+ "&email=" + email2 + "&senha=" + senha2;

                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão detectada", Toast.LENGTH_LONG).show();
                }

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
            if (resultado.contains("email_erro")) {
                Toast.makeText(getApplicationContext(), "Já existe cadastro neste email !", Toast.LENGTH_LONG).show();

            }else
            if (resultado.contains("Registro_ok")){
                Toast.makeText(getApplicationContext(), "Cadastro feito com sucesso !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Cadastro.this, TelaLogin.class));
                    } else
                    {

                        Toast.makeText(getApplicationContext(), "Não foi possivel Cadastrar", Toast.LENGTH_LONG).show();
                    }
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}
