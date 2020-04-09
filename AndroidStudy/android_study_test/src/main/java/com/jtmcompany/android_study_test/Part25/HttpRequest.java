package com.jtmcompany.android_study_test.Part25;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpRequest {

    HttpTask http;

    public void request(String url, HashMap<String,String> param, HttpCallback callback){
        http=new HttpTask(url,param,callback);
        http.execute();
        Log.d("tak","http-request");
    }

    public void cancel(){
        if(http!=null)
            http.cancel(true);

    }

    private class HttpTask extends AsyncTask<Void, Void, String>{
        String url;
        HashMap<String, String>param;
        HttpCallback callback;

        public HttpTask(String url, HashMap<String,String> param,HttpCallback callback){
            this.url=url;
            this.param=param;
            this.callback=callback;

        }

        @Override
        protected String doInBackground(Void... nothing) {
            String response="";
            String postData="";
            PrintWriter pw=null;
            BufferedReader in=null;

            try{
                Log.d("tak","doInBackground");
                URL text=new URL(url);
                HttpURLConnection http=(HttpURLConnection)text.openConnection();
                http.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setReadTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setDoOutput(true);

                if(param !=null && param.size()>0){
                    Log.d("tak","write");
                    Iterator<Map.Entry<String,String>> entries=param.entrySet().iterator();
                    int index=0;
                    while(entries.hasNext()){
                        if(index !=0){
                            postData=postData+"&";
                        }
                        Map.Entry<String, String> mapEntry=(Map.Entry<String, String>) entries.next();
                        postData=postData+mapEntry.getKey()+"="+ URLEncoder.encode(mapEntry.getValue(),"UTF-8");
                        index++;
                        Log.d("tak",postData);
                    }
                    pw=new PrintWriter(new OutputStreamWriter(http.getOutputStream(),"UTF-8"));
                    pw.write(postData);
                    pw.flush();


                }
                in = new BufferedReader(new InputStreamReader(http.getInputStream(),"UTF-8"));
                StringBuffer sb= new StringBuffer();
                String inputLine;
                while((inputLine=in.readLine())!=null){
                    sb.append(inputLine);
                }
                response=sb.toString();
                Log.d("tak",response);

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if(pw!=null) pw.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(in!=null) in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            return response;
        }

        @Override
        protected void onPostExecute(String reuslt) {
            this.callback.onResult(reuslt);
        }
    }
}
