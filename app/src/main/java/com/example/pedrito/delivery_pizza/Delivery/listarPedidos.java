package com.example.pedrito.delivery_pizza.Delivery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class listarPedidos extends AppCompatActivity {

    String id;
    public static int idUser;
    String url = "";
    String parametros = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedidos);

        id = TelaInicial.acessa();
        idUser = Integer.parseInt(id);
        String urlAddress = "http://pediuchegou.ddns.net/aplicativo/ConsultaPedido.php?id="+idUser;


        final ListView lv3 = (ListView) findViewById(R.id.lv3);

        new listarPedidos.Downloader(listarPedidos.this, urlAddress, lv3).execute();
             }



     public class DataParser extends AsyncTask<Void, Void, Boolean> {
        Context c;
        String jsonData;
        ListView lv3;
        ProgressDialog pd;
         ArrayList<String> spacecrafts = new ArrayList<>();
         ArrayList<String> spacecrafts2 = new ArrayList<>();
         ArrayList<String> spacecrafts3 = new ArrayList<>();
         ArrayList<String> spacecrafts4 = new ArrayList<>();
         ArrayList<String> spacecrafts5 = new ArrayList<>();
         ArrayList<String> spacecrafts6 = new ArrayList<>();




        public DataParser(Context c, String jsonData, ListView lv3) {
            this.c = c;
            this.jsonData = jsonData;
            this.lv3 = lv3;
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
                lv3.setAdapter(adapter);
                lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(c, spacecrafts.get(position), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(c.getApplicationContext(), pedidoFeito.class);





                        String Dado3 = spacecrafts3.get(position).toString();
                        intent.putExtra("codPedido",Dado3);

                        String Dado4 = spacecrafts4.get(position).toString();
                        intent.putExtra("nomeUsuario",Dado4);

                        String Dado2 = spacecrafts2.get(position).toString();
                        intent.putExtra("totalApagar",Dado2);

                        String Dado5 = spacecrafts5.get(position).toString();
                        intent.putExtra("devolver",Dado5);

                        String Dado6 = spacecrafts6.get(position).toString();
                        intent.putExtra("PizzaNome",Dado6);



                        //------------------------------------------------------


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

                    String codP = jo.getString("codPedido");
                    String name = jo.getString("nomeUsuario");

                    String valor = jo.getString("totalApagar");
                    String Pizza = jo.getString("PizzaNome");

                    String dev = jo.getString("devolver");



                    spacecrafts.add( codP + " - " +name+ " - " +valor);
                    spacecrafts3.add(codP);
                    spacecrafts4.add(name);
                    spacecrafts6.add(Pizza);
                    spacecrafts2.add(valor);
                    spacecrafts5.add(dev);







                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

    }
    public class Downloader extends AsyncTask<Void,Void,String>{
            Context c;
            String urlAddress;
            ListView lv3;
            ProgressDialog pd;
            public Downloader(Context c, String urlAddress, ListView lv3) {
                this.c = c;
                this.urlAddress = urlAddress;
                this.lv3 = lv3;
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
                    new listarPedidos.DataParser(c,jsonData,lv3).execute();

                }
            }
            private String downloadData(){
                Object connection=Connector.connect(urlAddress);
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

