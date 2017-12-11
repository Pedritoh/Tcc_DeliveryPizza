package com.example.pedrito.delivery_pizza.Delivery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedrito.delivery_pizza.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Cardapio extends AppCompatActivity {


    final static String urlAddress = "http://pediuchegou.ddns.net/aplicativo/cardapio.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        final ListView lv = (ListView) findViewById(R.id.lv);

        new Downloader(Cardapio.this, urlAddress, lv).execute();
    }

     final public class DataParser extends AsyncTask<Void, Void, Boolean> {
        Context c;
        String jsonData;
        ListView lv;
        ProgressDialog pd;
        ArrayList<String> spacecrafts = new ArrayList<>();
        ArrayList<String> spacecrafts2 = new ArrayList<>();

        public DataParser(Context c, String jsonData, ListView lv) {
            this.c = c;
            this.jsonData = jsonData;
            this.lv = lv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(c);
            pd.setTitle("Carregando");
            pd.setMessage("Carregando.. aguarde !");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return this.parseData();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            super.onPostExecute(success);
            pd.dismiss();
            if (success) {
                ArrayAdapter adapter = new ArrayAdapter(c, android.R.layout.simple_list_item_1, spacecrafts);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(c, spacecrafts.get(position), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(c.getApplicationContext(), Auxprodutos.class);
                        String Dado = spacecrafts.get(position).toString();
                        intent.putExtra("Produto",Dado);
                        //------------------------------------------------------
                        //------------Envio de valor -----------------------------
                        String Dado2 = spacecrafts2.get(position).toString();
                        intent.putExtra("valor",Dado2);
                        //-----------------ENVIO DE ID--------------------

                        startActivity(intent);


                    }
                });
            } else {

            }
        }

        private Boolean parseData() {
            try {
                JSONArray ja = new JSONArray(jsonData);
                JSONObject jo;
                spacecrafts.clear();
                spacecrafts2.clear();

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);

                    String id = jo.getString("codProduto");
                    String name = jo.getString("nomeProduto");
                    String descricao = jo.getString("descricao");
                    String valor = jo.getString("valorProduto");


                    spacecrafts.add( "R$"+ valor+",00" +" - "+ name +"                       " +descricao);
                    spacecrafts2.add(valor);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

    }
    class Downloader extends AsyncTask<Void,Void,String>{
        Context c;
        String urlAddess;
        ListView lv;
        ProgressDialog pd;
        public Downloader(Context c, String urlAddess, ListView lv) {
            this.c = c;
            this.urlAddess = urlAddess;
            this.lv = lv;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(c);
            pd.setTitle("Carregando");
            pd.setMessage("Carregando ..aguarde!");
            pd.show();
        }
        @Override
        protected String doInBackground(Void... params) {
            return this.downloadData();
        }
        @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);
            pd.dismiss();
            if(jsonData.startsWith("Error"))
            {
                Toast.makeText(c,"Unsuccessful "+jsonData,Toast.LENGTH_SHORT).show();
            }else
            {
                //PARSE
                new Cardapio.DataParser(c,jsonData,lv).execute();

            }
        }
        private String downloadData()
        {
            Object connection=Connector.connect(urlAddess);
            if(connection.toString().startsWith("Error"))
            {
                return connection.toString();
            }
            try {
                HttpURLConnection con= (HttpURLConnection) connection;
                InputStream is=new BufferedInputStream(con.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer jsonData=new StringBuffer();
                while ((line=br.readLine()) != null)
                {
                    jsonData.append(line+"n");
                }
                br.close();
                is.close();
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error "+e.getMessage();
            }
        }
    }

}

