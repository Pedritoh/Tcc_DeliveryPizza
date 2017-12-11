package com.example.pedrito.delivery_pizza.Delivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pedrito.delivery_pizza.R;

import org.w3c.dom.Text;

public class pedidoFeito extends AppCompatActivity {

    Button Voltar;
    TextView codPedidobf;
    TextView nomeUsuariobf;
    TextView PizzaNomebf;
    TextView QuantidadePizzabf;
    TextView ValorPizzabf;
    TextView nomeBordabf;
    TextView ValorBordabf;
    TextView nomeBebidabf;
    TextView quantidadeBebidaBf;
    TextView valorBebidabf;
    TextView FormaPagamentobf;
    TextView trocobf;
    TextView devolverbf;
    TextView enderecobf;
    TextView telefonebf;
    TextView observacaobf;
    TextView totalApagarbf;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_feito);



        String codPedido = getIntent().getExtras().getString("codPedido");
        String nomeUsuario = getIntent().getExtras().getString("nomeUsuario");
        String PizzaNome = getIntent().getExtras().getString("PizzaNome");





        String devolver = getIntent().getExtras().getString("devolver");


        String totalApagar = getIntent().getExtras().getString("totalApagar");



        codPedidobf = (TextView) findViewById(R.id.codPedidoBf);
        nomeUsuariobf = (TextView) findViewById(R.id.nomeF);
        PizzaNomebf = (TextView) findViewById(R.id.pizzaF);
        nomeBebidabf = (TextView) findViewById(R.id.bebidaF);
        devolverbf = (TextView) findViewById(R.id.devBf);
        totalApagarbf = (TextView) findViewById(R.id.totalBf);

        codPedidobf.setText(codPedido);
        nomeUsuariobf.setText(nomeUsuario);
        PizzaNomebf.setText(PizzaNome);
        devolverbf.setText(devolver);
        totalApagarbf.setText(totalApagar);


    }
}
