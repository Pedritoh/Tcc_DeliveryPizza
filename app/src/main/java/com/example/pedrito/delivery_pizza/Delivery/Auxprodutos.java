package com.example.pedrito.delivery_pizza.Delivery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.R;

public class Auxprodutos extends Activity {

    public static  TextView PizzaNome;
    private TextView Valor;
    String nomeUsuario;
    String url = "";
    String parametros = "";
    CheckBox nenhum,cheedar,catupiry,goiabada;
    Button buttonOrder;
    int totalamount;
    int valorNenhum ,valorChedar ,valorCatupiry ,valorGoiabada ;

    boolean idnenhum,idcheedar,idcatupiry,idgoiabada;
    String bordas;
    public String id;
    public String nome;
    private TextView  teste;
    public String nomeBorda;
    public String bordinha;
    public String subtotal2;
    public String resultNomeborda ;
    TextView newTeste;
    TextView valorBorda;
    TextView valorTotal;
    EditText quantidades;
    EditText observacao;

    public static String nomeProduto;
    public static String Produto;
    public static String valorp;
    public static String valorProduto;
    public static String borda2;
    public static String ValorBorda;
    public static String Subs;
    public static String borda;
    public static String vBorda;
    public static String vSubs;
    public static String subtotal;
    public static String nomes;
    public static String dadoPizza2;
    public static String dadoPizza;
    public static String quantidade;
    public static String qtdString;
    public static String Robservacao;
    public static String enviaObservacao;
    public static int qtd ;
    public static int valor;
    public static int Total;
    public static String Totalz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auxprodutos);
        addListenerOnButtonClick();



        id = TelaInicial.acessa();

        nome = TelaInicial.acessanome();





        PizzaNome = (TextView) findViewById(R.id.PizzaNome);
        dadoPizza = getIntent().getExtras().getString("Produto");
        PizzaNome.setText(dadoPizza);

        Valor = (TextView) findViewById(R.id.pizValor);
        dadoPizza2 = getIntent().getExtras().getString("valor");



        Button Prosseguir = (Button) findViewById(R.id.btnProsseguir2);

        Prosseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String nomeUser = nome;
                    Produto = dadoPizza;
                    valorProduto = dadoPizza2;
                    borda2 = nomes;
                    ValorBorda = valorBorda.getText().toString();
                    Subs = valorTotal.getText().toString();

                    observacao = (EditText) findViewById(R.id.obsBf);
                    Robservacao = observacao.getText().toString();


                    Intent abreCardapioRefri = new Intent(Auxprodutos.this,CardapioRefri.class);
                    startActivity(abreCardapioRefri);
                    /*
                        url = "http://10.0.2.2/html/login/pedidos.php";


                        parametros ="idUsuario=" + idusers + "&PizzaNome=" + Produto + "&ValorPizza=" + valorProduto+ "&NomeBorda="+ borda2 + "&ValorBorda="+ValorBorda +"&SubTotal="+Subs;

                        new Auxprodutos.SolicitaDados().execute(url);
                        */
                        Toast.makeText(Auxprodutos.this, "Enviou", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão detectada", Toast.LENGTH_LONG).show();
                }
            }
        });







        buttonOrder.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                StringBuilder result = new StringBuilder();



                if (nenhum.isChecked()) {
                    result.append("\rSem borda");
                    valorNenhum =  0;
                    String borda = Integer.toString(valorNenhum);

                    TextView valorPizza = (TextView) findViewById(R.id.textBorda);
                    valorPizza.setText(borda);

                }else{


                }


                if (cheedar.isChecked()) {
                    result.append("\rCheedar");
                    valorChedar = 3;

                    //aqui to setando o valor 3 para o chedar
                    TextView valorBorda = (TextView) findViewById(R.id.textBorda);
                    String cheedar = "1";
                    String borda = Integer.toString(totalamount);
                    valorBorda.setText(borda);

                }else{

                    valorChedar = 0;
                    String borda = Integer.toString(totalamount);
                    TextView valorBorda = (TextView) findViewById(R.id.textBorda);
                    valorBorda.setText(borda);
                }




                if (catupiry.isChecked()) {
                    result.append("\rCatupiry");
                    valorCatupiry = 2;

                    //setando valor para o catupiry
                    String borda = Integer.toString(totalamount);
                    TextView valorBorda = (TextView) findViewById(R.id.textBorda);
                    valorBorda.setText(borda);

                }else{

                    valorCatupiry=0;
                    String borda = Integer.toString(totalamount);
                    TextView valorPizza = (TextView) findViewById(R.id.textBorda);
                    valorPizza.setText(borda);
                }


                if(goiabada.isChecked()){
                    result.append("\rGoiabada");
                    valorGoiabada = 3;


                    String borda = Integer.toString(totalamount);
                    TextView valorBorda = (TextView) findViewById(R.id.textBorda);
                    valorBorda.setText(borda);


                }else{


                    valorGoiabada=0;
                    String borda = Integer.toString(totalamount);
                    TextView valorBorda = (TextView) findViewById(R.id.textBorda);
                    valorBorda.setText(borda);

                }

                nomes = result.toString();

                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();


                //somando o valor das borda e setando no totalamount
                totalamount = valorNenhum+valorCatupiry+valorChedar+valorGoiabada;
               //convertendo o totalamount de int para  string e alocando na STRING BORDA
                String borda = Integer.toString(totalamount);
                valorBorda = (TextView) findViewById(R.id.textBorda);
                // seto o valor da borda no texdtView
                valorBorda.setText(borda);


                //recupero o valor da pizza selecionado na intent
                String dadoPizza3 = getIntent().getExtras().getString("valor");


                //converto o valor de pizza para INT (valor Inteiro)
                valor = Integer.parseInt(dadoPizza3);

                quantidades = (EditText) findViewById(R.id.editQtd);
                quantidade = quantidades.getText().toString();

                qtd = Integer.parseInt(quantidade);


                qtdString = Integer.toString(qtd);
                Total =  valor * qtd;

                Totalz = Integer.toString(Total);
                Valor.setText(Totalz);
                //Faço a soma do valor o resultado é em INT
                int Total2 = Total + totalamount;

                //Converto de int para STRING para setar no TextView
                subtotal = Integer.toString(Total2);

                //aloco o subvalor na string para captar.
                subtotal2 = subtotal;

                // Mostra valor Total
                valorTotal = (TextView) findViewById(R.id.TextTotal);
                valorTotal.setText(subtotal+",00");

                if(qtd == 0){
                    qtd = 1;
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




    public void addListenerOnButtonClick(){
        nenhum = (CheckBox) findViewById(R.id.BoxNenhum);
        catupiry = (CheckBox) findViewById(R.id.checkCatupiry);
        cheedar = (CheckBox) findViewById(R.id.checkCheedar);
        buttonOrder = (Button) findViewById(R.id.ordemBox);
        goiabada = (CheckBox) findViewById(R.id.CheckGoiabada);




    }
   /* static String Produto = dadoPizza;
    String valorProduto = Valor.getText().toString();
    String borda2 = newTeste.getText().toString();
    String ValorBorda = valorBorda.getText().toString();
    String Subs = valorTotal.getText().toString();
*/
    public static String AcessaProduto(){
        nomeProduto = PizzaNome.getText().toString();
        return nomeProduto;
    }
    public static String AcessaQuantidade(){
        quantidade = qtdString;
        return quantidade;

    }
    public static String AcessaValorProduto(){
        valorp = dadoPizza2;
        return valorp;
    }
    public static String AcessaBorda(){
        borda = borda2;
        return borda;
    }
    public static String AcessaValorBorda(){
        vBorda = ValorBorda;
        return vBorda;
    }
    public static String AcessaSubs(){
        vSubs = subtotal;
        return vSubs;
    }
    public static String AcessaObservacao(){
        enviaObservacao = Robservacao;
        return enviaObservacao;
    }

}
