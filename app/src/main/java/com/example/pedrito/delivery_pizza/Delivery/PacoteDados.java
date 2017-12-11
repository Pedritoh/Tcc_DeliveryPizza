package com.example.pedrito.delivery_pizza.Delivery;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;



public class PacoteDados {

    String nome,endereco,numero,cidade,telefone,email,senha;

        public PacoteDados(String nome,String endereco,String numero,String cidade,String telefone,String email,String senha){
            this.nome = nome;
            this.endereco = endereco;
            this.numero = numero;
            this.cidade = cidade;
            this.telefone = telefone;
            this.email = email;
            this.senha = senha;
        }

        public String packData(){
            JSONObject jo = new JSONObject();
            StringBuffer pacote = new StringBuffer();
            try
            {
                jo.put("nome",nome);
                jo.put("endereco",endereco);
                jo.put("numero",numero);
                jo.put("cidade",cidade);
                jo.put("telefone",telefone);
                jo.put("email",email);
                jo.put("senha",senha);

                Boolean firstValue = true;
                Iterator it = jo.keys();
                do{
                    String key =it.next().toString();
                    String value=jo.get(key).toString();
                    if(firstValue){
                        firstValue=false;
                    }else{
                        pacote.append("&");
                    }
                    pacote.append(URLEncoder.encode(key,"UTF-8"));
                    pacote.append("=");
                    pacote.append(URLEncoder.encode(value,"UTF-8"));
                }while(it.hasNext());
                return pacote.toString();
            }catch (JSONException e){
                e.printStackTrace();
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            return null;
        }

}
