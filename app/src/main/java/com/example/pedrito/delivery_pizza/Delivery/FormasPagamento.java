package com.example.pedrito.delivery_pizza.Delivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.R;

public class FormasPagamento extends AppCompatActivity {

    Button buttons;
    Button Prosseguir;
    CheckBox CartaoCredito1;
    CheckBox CartaoDebito;
    CheckBox Dinheiro1;
    TextView newTeste;
    public static String nomes;
    EditText Troco;
    EditText endereco;
    EditText telefone;
    public static String enderecoNovo;
    public static String endereconew;
    public static String formaPagamento;
    public static String Troco1;
    public static String Troconew;
    public static String numeroNovo;
    public static String Ntelefone;

    public static String subtotal1;
    public static String subtotal2;
    public static String soma;
    public int valor1;
    public int valor2;
    public  int variavelValor;
    TextView verValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formas_pagamento);
        addListenerOnButtonClick();

        subtotal1 = Auxprodutos.AcessaSubs();
        subtotal2 =qtdRefri.AcessasubTotal2();
        valor1 = Integer.parseInt(subtotal1);
        valor2 = Integer.parseInt(subtotal2);
        verValor = (TextView) findViewById(R.id.verTotal);
        variavelValor = valor1 + valor2;
        soma = Integer.toString(variavelValor);
        verValor.setText("R$" + soma + ",00");





        Prosseguir = (Button) findViewById(R.id.btnProsseguir);

        Prosseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endereco = (EditText) findViewById(R.id.editEnderecoEntrega);
                enderecoNovo = endereco.getText().toString();

                telefone = (EditText) findViewById(R.id.telefoneId);
                numeroNovo = telefone.getText().toString();

                Troco = (EditText) findViewById(R.id.editTroco);
                Troco1 = Troco.getText().toString();



                if(enderecoNovo.isEmpty()||numeroNovo.isEmpty()) {
                    Toast.makeText(FormasPagamento.this, "Endereço e Telefone são Obrigatórios", Toast.LENGTH_LONG).show();
                }else {




                    Intent abreRecibo = new Intent(FormasPagamento.this, Recibo.class);
                    startActivity(abreRecibo);
                }
            }
        });

        buttons.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                StringBuilder resulte = new StringBuilder();



                if (CartaoCredito1.isChecked()) {
                    resulte.append("Cartão de Credito");
                    Toast.makeText(FormasPagamento.this, "Cartão de Crédito - MotoBoy levará a maquina para sua disposição", Toast.LENGTH_SHORT).show();


                }else{


                }


                if (CartaoDebito.isChecked()) {
                    resulte.append("Cartão de Débito ");
                    Toast.makeText(FormasPagamento.this, "Cartão de Débito - MotoBoy levará a maquina para sua disposição", Toast.LENGTH_SHORT).show();


                }else{


                }




                if (Dinheiro1.isChecked()) {
                    resulte.append("Dinheiro ");

                    Toast.makeText(FormasPagamento.this, "Necessita de Troco? Digite o valor ! ", Toast.LENGTH_SHORT).show();


                }else{


                }

                nomes = resulte.toString();
                newTeste = (TextView) findViewById(R.id.textForma);
                newTeste.setText(nomes);
            }


        });
    }
    public void addListenerOnButtonClick(){
        CartaoCredito1 = (CheckBox) findViewById(R.id.CartaoCredito);
        CartaoDebito = (CheckBox) findViewById(R.id.CartaoDebito);
        Dinheiro1 = (CheckBox) findViewById(R.id.Dinheiro);
        buttons = (Button) findViewById(R.id.addForm);

    }

    public static String AcessaEndereco(){
        endereconew = enderecoNovo;
        return endereconew;
    }
    public static String AcessaForma(){
        formaPagamento = nomes;
        return formaPagamento;
    }
    public static String AcessaTroco(){

        Troconew = Troco1;
        return Troconew;

    }
    public static String AcessaTelefone(){

        Ntelefone = numeroNovo;
        return Ntelefone;

    }

}
