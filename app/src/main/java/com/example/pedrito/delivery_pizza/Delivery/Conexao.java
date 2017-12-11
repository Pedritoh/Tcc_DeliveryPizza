package com.example.pedrito.delivery_pizza.Delivery;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Conexao {

    public static String postDados(String urlUsuario, String parametroUsuario) {

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(urlUsuario);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

        RequestBody body = RequestBody.create(mediaType, parametroUsuario);
        builder.post(body);
        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException erro) {
            return null;
        }
    }
}



/*public static String postDados(String urlUsuario,String parametroUsuario) {
        URL url;
        HttpURLConnection connection = null;

        try{
            url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Lenght",""+ Integer.toString(parametroUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language","pt-BR");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            /*DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(parametroUsuario);
            dataOutputStream.flush();
            dataOutputStream.close();


            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            outputStreamWriter.write(parametroUsuario);
            outputStreamWriter.flush();

            InputStream inputStream = connection.getInputStream();
    //            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-15"));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while((linha= bufferedReader.readLine())!= null){
                resposta.append(linha);
                resposta.append('\r');

            }
            bufferedReader.close();
            return "b";


        }catch(Exception erro){
           return "a";

        }finally{
            if(connection!=null){
                connection.disconnect();
            }

        }
*/


