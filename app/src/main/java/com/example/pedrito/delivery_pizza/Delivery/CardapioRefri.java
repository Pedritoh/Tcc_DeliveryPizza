package com.example.pedrito.delivery_pizza.Delivery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class CardapioRefri extends AppCompatActivity {


    final static String urlAddress = "http://pediuchegou.ddns.net/aplicativo/cardapioRefri.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_refri);

        final ListView lv2 = (ListView) findViewById(R.id.lv2);

        new CardapioRefri.Downloader2(CardapioRefri.this, urlAddress, lv2).execute();
    }

    final public class DataParser2 extends AsyncTask<Void, Void, Boolean> {
        Context c2;
        String jsonData2;
        ListView lv2;
        ProgressDialog pd2;
        ArrayList<String> spacecrafts3 = new ArrayList<>();
        ArrayList<String> spacecrafts4 = new ArrayList<>();

        public DataParser2(Context c2, String jsonData2, ListView lv2) {
            this.c2 = c2;
            this.jsonData2 = jsonData2;
            this.lv2 = lv2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd2 = new ProgressDialog(c2);
            pd2.setTitle("Parse");
            pd2.setMessage("Pasring..Please wait");
            pd2.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return this.parseData2();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            super.onPostExecute(success);
            pd2.dismiss();
            if (success) {
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(c2, android.R.layout.simple_list_item_1, spacecrafts3);
                lv2.setAdapter(adapter2);
                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(c2, spacecrafts3.get(position), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(c2.getApplicationContext(), qtdRefri.class);
                        String Dado = spacecrafts3.get(position).toString();
                        intent.putExtra("refri", Dado);
                        //------------------------------------------------------
                        //------------Envio de valor -----------------------------
                        String Dado2 = spacecrafts4.get(position).toString();
                        intent.putExtra("valorRefri", Dado2);
                        //-----------------ENVIO DE ID--------------------

                        startActivity(intent);


                    }
                });
            } else {

            }
        }

        private Boolean parseData2() {
            try {
                JSONArray ja = new JSONArray(jsonData2);
                JSONObject jo;
                spacecrafts3.clear();
                spacecrafts4.clear();

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);

                    String id = jo.getString("codProduto");
                    String name = jo.getString("nomeProduto");
                    String des = jo.getString("descricao");
                    String valor = jo.getString("valorProduto");


                    spacecrafts3.add("R$" + valor + ",00" +" - "+ name );
                    spacecrafts4.add(valor);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

    }

    class Downloader2 extends AsyncTask<Void, Void, String> {
        Context c2;
        String urlAddess;
        ListView lv2;
        ProgressDialog pd2;

        public Downloader2(Context c2, String urlAddess, ListView lv2) {
            this.c2 = c2;
            this.urlAddess = urlAddess;
            this.lv2 = lv2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd2 = new ProgressDialog(c2);
            pd2.setTitle("Retrieve");
            pd2.setMessage("Retrieving..Please wait");
            pd2.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            return this.downloadData2();
        }

        @Override
        protected void onPostExecute(String jsonData2) {
            super.onPostExecute(jsonData2);
            pd2.dismiss();
            if (jsonData2.startsWith("Error")) {
                Toast.makeText(c2, "Unsuccessful " + jsonData2, Toast.LENGTH_SHORT).show();
            } else {
                //PARSE
                new CardapioRefri.DataParser2(c2, jsonData2, lv2).execute();

            }
        }

        private String downloadData2() {
            Object connection = Connector.connect(urlAddess);
            if (connection.toString().startsWith("Error")) {
                return connection.toString();
            }
            try {
                HttpURLConnection con = (HttpURLConnection) connection;
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer jsonData2 = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    jsonData2.append(line + "n");
                }
                br.close();
                is.close();
                return jsonData2.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error " + e.getMessage();
            }
        }
    }
}


