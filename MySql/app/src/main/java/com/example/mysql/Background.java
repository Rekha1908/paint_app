package com.example.mysql;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class Background extends AsyncTask<String,Void,String> {
    AlertDialog dialog;
    Context context;


public Background()
{
this.context=context;

}



    @Override
    protected void onPreExecute() {
       dialog= new AlertDialog.Builder(context).create();
       dialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String s)
    {
       dialog.setMessage(s);
       dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String result="";
        String user=strings[0];
        String pass=strings[1];

        String connstr="http://localhost:8080/login.php";
        try {
            URL url= new URL(connstr);
            HttpsURLConnection http=(HttpsURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);

            OutputStream ops=http.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data= URLEncoder.encode("user","UTF-8")+""+URLEncoder.encode(user,"UTF-8")+"&&"+
                    URLEncoder.encode("pass","UTF-8")+""+URLEncoder.encode(pass,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips=http.getInputStream();
            BufferedReader reader= new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line="";
            while ((line=reader.readLine())!=null)
            {
                result +=line;
            }
                reader.close();
                ips.close();
http.disconnect();
return result;
        } catch (MalformedURLException e) {
            result=e.getMessage();
        }catch (IOException ex)
        {
            result=ex.getMessage();
        }
        return result;
    }
}
