package com.example.pedrito.delivery_pizza.Delivery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.Pojo.Cliente;
import com.example.pedrito.delivery_pizza.R;

import org.w3c.dom.Text;

import static android.R.attr.id;

public class TelaInicial extends AppCompatActivity {

    TextView nomeCliente,idTemp;
    Button Cardapio;
    Button verPe;
    static String nomeUsuario;
    static String idUsuario;

    String id ;
    static String nome;
    static String idUsers;
    static String nomeUser;

    String url = "";
    String parametros = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        nomeCliente = (TextView) findViewById(R.id.TextNomeCliente);
        nomeUsuario = getIntent().getExtras().getString("nome_usuario");
        nomeCliente.setText(nomeUsuario);

        idTemp = (TextView) findViewById(R.id.idTemp);
        idUsuario = getIntent().getExtras().getString("id_usuario");
        idTemp.setText(idUsuario);

        Cliente user = new Cliente(idUsuario,nomeUsuario);
        user.setId(idUsuario);
        user.setNome(nomeUsuario);
        id = idUsuario;
        acessa();

        nome = nomeUsuario;
        Toast.makeText(this, acessa(), Toast.LENGTH_SHORT).show();

        verPe = (Button) findViewById(R.id.VerPedidos);
        verPe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicial.this, listarPedidos.class);
                startActivity(intent);
            }
        });




        Cardapio = (Button) findViewById(R.id.btnCardapio);
        Cardapio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(TelaInicial.this, Cardapio.class);
                startActivity(intent);

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

            }else{


            }



        }

    }

    public static String acessa(){
        idUsers =idUsuario;
        return idUsers;
    }
    public static String acessanome(){
        nomeUser = nome;
        return nomeUser;
    }

}
