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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.Pojo.Pedidos;
import com.example.pedrito.delivery_pizza.R;

import org.w3c.dom.Text;

public class qtdRefri extends AppCompatActivity {
    String parametros = "";
    TextView RefriNome;
    TextView ValorRefri;
    EditText qtd ;
    TextView subTotalRefri;
    int subtotal;
    int quantidade;
    int valor;
    Button adicionarqtd;
    Button prosseguir3;

    public static String bebida;
    public static String valorBebida;
    public static String quantidad;
    public static String subtotal3;

    public static String rBebida;
    public static String rValorBebida;
    public static String rQuantidad;
    public static String rsubtotal3;
    public static String subtotal2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtd_refri);


        RefriNome = (TextView) findViewById(R.id.textRefri);
        final String dadoRefri = getIntent().getExtras().getString("refri");
        RefriNome.setText(dadoRefri);

        ValorRefri = (TextView) findViewById(R.id.textValor);
        final String dadoRefri2 = getIntent().getExtras().getString("valorRefri");
        ValorRefri.setText("R$"+ dadoRefri2+",00");


        adicionarqtd = (Button) findViewById(R.id.adicionarQtd);

        adicionarqtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtd = (EditText) findViewById(R.id.qtdRefri);




                quantidade = Integer.parseInt(qtd.getText().toString());
                valor = Integer.parseInt(dadoRefri2);


                subtotal = valor * quantidade;

                subTotalRefri = (TextView) findViewById(R.id.subTotalRefri);
                subtotal2 = Integer.toString(subtotal);
                subTotalRefri.setText("R$"+subtotal2+",00");

            }
        });

        prosseguir3 = (Button) findViewById(R.id.buttonProsseguir2);

        prosseguir3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aqui envia os dados

                ConnectivityManager connMgr =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    bebida = dadoRefri;
                    valorBebida =dadoRefri2;
                    quantidad = qtd.getText().toString();
                    subtotal3 = subTotalRefri.getText().toString();

                    Intent abreFormas = new Intent(qtdRefri.this,FormasPagamento.class);
                    startActivity(abreFormas);
                    /*
                        url = "http://10.0.2.2/html/login/pedidos.php";


                        parametros ="idUsuario=" + idusers + "&PizzaNome=" + Produto + "&ValorPizza=" + valorProduto+ "&NomeBorda="+ borda2 + "&ValorBorda="+ValorBorda +"&SubTotal="+Subs;

                        new Auxprodutos.SolicitaDados().execute(url);
                        */
                    Toast.makeText(qtdRefri.this, "Enviou", Toast.LENGTH_SHORT).show();

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
       /*  if (resultado.contains("Registro_ok")){
                Toast.makeText(getApplicationContext(), "Enviou !", Toast.LENGTH_SHORT).show();

            } else{

                Toast.makeText(getApplicationContext(), "Não foi possivel fazer o pedido", Toast.LENGTH_LONG).show();
            }*/
                 }



        }

    public static String AcessaRefri(){
        rBebida = bebida;
        return rBebida;
    }
    public static String AcessaValorRefri(){
        rValorBebida = subtotal2;
        return rValorBebida;
    }
    public static String AcessaQuantidade(){
        rQuantidad = quantidad;
        return rQuantidad;
    }
    public static String AcessasubTotal2(){
        rsubtotal3 = subtotal2;
        return rsubtotal3;
    }

}




