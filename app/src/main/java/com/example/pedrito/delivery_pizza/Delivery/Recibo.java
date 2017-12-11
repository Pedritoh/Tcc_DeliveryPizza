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

import com.example.pedrito.delivery_pizza.R;

public class Recibo extends AppCompatActivity {

    public static String idUser;
    public static String idUsers;
    public String nomeCliente;
    public String NomePizza;
    public String QuantidadePizza;
    public String ValorPizza;
    public String NomeBorda;
    public String ValorBorda;
    public String nomeBebida;
    public String ValorBebida;
    public String Quantidade;
    public String FormaPagamento;
    public String Troco;
    public String Endereco;
    public String Telefone;
    public int ValorTotal;
    public String SubTotal1;
    public String SubTotal2;
    public int valor1;
    public int valor2;
    public int valorTroco;
    public String Resultado;
    public int devolver;
    public String ReDevolver;
    public static String valorDeolver;
    public static String ReObservacao;
    public static String devolverString;
    public static String dinheiro = "Dinheiro";
    TextView RidUser;
    TextView RnomeCliente;
    TextView RNomePizza;
    TextView RQuantidadePizza;
    TextView RValorPizza;
    TextView RNomeBorda;
    TextView RValorBorda;
    TextView RnomeBebida;
    TextView RValorBebida;
    TextView RQuantidade;
    TextView RFormaPagamento;
    TextView RTroco;
    TextView REndreco;
    TextView RTelefone;
    TextView RValorTotal;
    TextView RDevolver;
    TextView Robservacao;

    Button buttonConfirma;


    String url = "";
    String parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo);

        RnomeCliente = (TextView) findViewById(R.id.nomeF);
        RNomePizza = (TextView) findViewById(R.id.pizzaF);
        RQuantidadePizza = (TextView) findViewById(R.id.qtdPF);
        RValorPizza = (TextView) findViewById(R.id.valorPf);
        RNomeBorda = (TextView) findViewById(R.id.nomeBF);
        RValorBorda = (TextView) findViewById(R.id.reciboValorBorda);
        RnomeBebida = (TextView) findViewById(R.id.bebidaF);
        RValorBebida = (TextView) findViewById(R.id.valbeF);
        RQuantidade = (TextView) findViewById(R.id.qtdBF);
        RFormaPagamento = (TextView) findViewById(R.id.formaBf);
        RTroco = (TextView) findViewById(R.id.trocoBf);
        REndreco = (TextView) findViewById(R.id.endBf);
        RTelefone = (TextView) findViewById(R.id.telBF);
        RValorTotal = (TextView) findViewById(R.id.reciboValorTotal);
        Robservacao = (TextView) findViewById(R.id.obsBf);
        RDevolver = (TextView) findViewById(R.id.devBf);
        //------------------------------------------------------------
        //-----
        idUser = TelaInicial.acessa();
        nomeCliente = TelaInicial.acessanome();
        //--
        NomePizza = Auxprodutos.AcessaProduto();
        QuantidadePizza = Auxprodutos.AcessaQuantidade();
        ValorPizza = Auxprodutos.AcessaValorProduto();
        NomeBorda = Auxprodutos.AcessaBorda();
        ValorBorda = Auxprodutos.AcessaValorBorda();
        SubTotal1 = Auxprodutos.AcessaSubs();
        ReObservacao = Auxprodutos.AcessaObservacao();
        //--
        nomeBebida = qtdRefri.AcessaRefri();
        ValorBebida = qtdRefri.AcessaValorRefri();
        Quantidade = qtdRefri.AcessaQuantidade();
        SubTotal2 = qtdRefri.AcessasubTotal2();
        FormaPagamento = FormasPagamento.AcessaForma();
        Troco = FormasPagamento.AcessaTroco();
        Endereco = FormasPagamento.AcessaEndereco();
        Telefone = FormasPagamento.AcessaTelefone();

        //--------------------------------------------

        RnomeCliente.setText(nomeCliente);
        RNomePizza.setText(NomePizza);
        RQuantidadePizza.setText(QuantidadePizza + "unid");
        RValorPizza.setText("R$" + ValorPizza + ",00");
        RNomeBorda.setText(NomeBorda);
        RValorBorda.setText("R$" + ValorBorda + ",00");
        RnomeBebida.setText(nomeBebida);
        RValorBebida.setText("R$" + ValorBebida + ",00");
        RQuantidade.setText(Quantidade + "unid");
        RFormaPagamento.setText(FormaPagamento);
        RTroco.setText("R$" + Troco + ",00");

        Robservacao.setText(ReObservacao);
        REndreco.setText(Endereco);
        RTelefone.setText(Telefone);


        valor1 = Integer.parseInt(SubTotal1);
        valor2 = Integer.parseInt(SubTotal2);

        ValorTotal = valor1 + valor2;
        Resultado = Integer.toString(ValorTotal);
        RValorTotal.setText("R$" + Resultado + ",00");

        valorTroco = Integer.parseInt(Troco);
        devolver = ValorTotal - valorTroco;
        devolverString = Integer.toString(devolver);
        if (valorTroco == 0 ) {
            Toast.makeText(this, "Cartão de Credito Escolhido", Toast.LENGTH_SHORT).show();
        } else {
            RDevolver.setText("R$" + devolverString + ",00");
        }




        buttonConfirma = (Button) findViewById(R.id.Voltarlist);

        buttonConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (FormaPagamento.equals(dinheiro) ){
                    if (valorTroco < ValorTotal) {
                        Toast.makeText(Recibo.this, "O valor do troco é menor que o valor total", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Recibo.this, "Aguarde", Toast.LENGTH_SHORT).show();
                    }

                }

                ConnectivityManager connMgr =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    String BidUsuario = idUser;
                    String BnomeUsuario = RnomeCliente.getText().toString();
                    String BPizzaNome = RNomePizza.getText().toString();
                    String BQuantidadePizza = RQuantidadePizza.getText().toString();
                    String BValorPizza = RValorPizza.getText().toString();
                    String BNomeBorda = RNomeBorda.getText().toString();
                    String BValorBorda = RValorBorda.getText().toString();
                    String BnomeBebida = RnomeBebida.getText().toString();
                    String BquantidadeBebida = RQuantidade.getText().toString();
                    String BvalorBebida = RValorBebida.getText().toString();
                    String BFormasPagamento = RFormaPagamento.getText().toString();
                    String Btroco = RTroco.getText().toString();
                    String Bdevolver = RDevolver.getText().toString();
                    String Bendereco = REndreco.getText().toString();
                    String Btelefone = RTelefone.getText().toString();
                    String Bobservacao = Robservacao.getText().toString();
                    String BtotalApagar = RValorTotal.getText().toString();



                    if (BidUsuario.isEmpty() || BnomeUsuario.isEmpty() || BPizzaNome.isEmpty() || BQuantidadePizza.isEmpty() || BValorPizza.isEmpty() || BNomeBorda.isEmpty() || BValorBorda.isEmpty() || BnomeBebida.isEmpty() || BquantidadeBebida.isEmpty() || BvalorBebida.isEmpty() || BFormasPagamento.isEmpty() || Btroco.isEmpty() || Bdevolver.isEmpty() || Bendereco.isEmpty() || Btelefone.isEmpty()  || BtotalApagar.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://pediuchegou.ddns.net/aplicativo/pedidos.php";

                        parametros = "idUsuario=" +BidUsuario+ "&nomeUsuario=" + BnomeUsuario + "&PizzaNome=" + BPizzaNome + "&QuantidadePizza=" + BQuantidadePizza +"&ValorPizza=" + BValorPizza + "&NomeBorda=" + BNomeBorda + "&ValorBorda=" + BValorBorda + "&nomeBebida=" + BnomeBebida +"&quantidadeBebida=" + BquantidadeBebida + "&valorBebida=" + BvalorBebida + "&FormaPagamento=" + BFormasPagamento +"&troco=" + Btroco + "&devolver=" + Bdevolver + "&endereco=" + Bendereco + "&telefone=" + Btelefone + "&observacao=" + Bobservacao+"&totalApagar="+BtotalApagar;

                        new SolicitaDados().execute(url);
                        Toast.makeText(Recibo.this, "Pedido Efetuado com Sucesso", Toast.LENGTH_SHORT).show();
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

            if (resultado.contains("Registro_ok")) {


                Intent abreInicio = new Intent(Recibo.this, TelaInicial.class);
                abreInicio.putExtra("nome_usuario", TelaInicial.acessanome());
                abreInicio.putExtra("id_usuario",idUser);
                startActivity(abreInicio);
            } else if(resultado.contains("Registro_ERRO")) {

                Toast.makeText(getApplicationContext(), "Não foi possivel realizar o pedido !", Toast.LENGTH_LONG).show();
            }


        }

    }
    public static String acessaId(){
        idUsers = idUser;
        return idUsers;
    }
}
