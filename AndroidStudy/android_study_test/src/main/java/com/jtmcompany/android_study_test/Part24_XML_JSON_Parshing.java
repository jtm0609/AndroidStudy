package com.jtmcompany.android_study_test;

import android.os.Bundle;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Part24_XML_JSON_Parshing extends AppCompatActivity implements View.OnClickListener {

    Button domBtn;
    Button saxBtn;
    Button pullBtn;
    Button jsonBtn;

    TextView cityView;
    TextView tempView;
    TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part24__xml__json__parshing);

        domBtn = findViewById(R.id.lab2_dom);
        saxBtn =  findViewById(R.id.lab2_sax);
        pullBtn = findViewById(R.id.lab2_pull);
        jsonBtn =  findViewById(R.id.lab2_json);

        cityView = findViewById(R.id.lab2_city);
        tempView =  findViewById(R.id.lab2_temperature);
        resultView =  findViewById(R.id.lab2_result_title);

        domBtn.setOnClickListener(this);
        saxBtn.setOnClickListener(this);
        pullBtn.setOnClickListener(this);
        jsonBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == domBtn) {

            domParsing();

        } else if (v == saxBtn) {

            saxParsing();

        } else if (v == pullBtn) {

        } else if (v == jsonBtn) {
            jsonParsing();

        }

    }

    private void domParsing(){
        try{
            //파일읽기
            InputStream inputStream=getAssets().open("test.xml");
            //DOM파싱
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc=builder.parse(inputStream,null);

            //태크 attribute 값 획득
            org.w3c.dom.Element tempElement=(org.w3c.dom.Element)
                    (doc.getElementsByTagName("temperature").item(1));
            String temperature=tempElement.getAttribute("value");

            org.w3c.dom.Element cityElement=(org.w3c.dom.Element)
                    (doc.getElementsByTagName("city").item(0));
            String city=cityElement.getAttribute("name");

            //결과 화면 출력
            resultView.setText("DOM Parsing Result");
            cityView.setText(city);
            tempView.setText(temperature);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saxParsing(){
        resultView.setText("SAX Parsing Result");

        RootElement root=new RootElement("current");
        android.sax.Element cityElement=root.getChild("city");
        android.sax.Element tempElement =root.getChild("temperature");

        cityElement.setStartElementListener(new StartElementListener() {
            @Override
            public void start(Attributes attributes) {
                cityView.setText(attributes.getValue("name"));
            }
        });

        tempElement.setStartElementListener(new StartElementListener() {
            @Override
            public void start(Attributes attributes) {
                tempView.setText(attributes.getValue("value"));
            }
        });

        try{
            InputStream inputStream=getAssets().open("test.xml");
            Xml.parse(inputStream,Xml.Encoding.UTF_8,root.getContentHandler());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void jsonParsing(){
        resultView.setText("JSON Parsing Result");
        //파일 읽기
        String json=null;

        try{
            InputStream is=getAssets().open("test.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            json=new String(buffer, "UTF-8");

            JSONObject root=new JSONObject(json);

            cityView.setText(root.getString("name"));
            JSONObject main=root.getJSONObject("main");
            tempView.setText(main.getString("temp"));


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
