package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Receita;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResultadoPesquisa extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnVoltar;
    TextView textoTitulo;
    ListView listaDeResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_pesquisa);
        iniciarFindViewById();

//        ArrayList<String> lista = new ArrayList<String>();
//        lista.add("3/3 Ing. | TESTE2");
//        lista.add("2/3 Ing. | Teste");
//        lista.add("2/3 Ing. | Teste");
//        lista.add("2/3 Ing. | Teste");
//        lista.add("2/3 Ing. | Teste");
//        lista.add("1/3 Ing. | Teste");
//
//        setArrayAdapter(lista);

        HashMap<String, String> nameAddresses = new HashMap<>();
        nameAddresses.put("Salada", "Ovo, Tomate, Cebola");
        nameAddresses.put("Tyga", "343 Rack City Drive");
        nameAddresses.put("Rich Homie Quan", "111 Everything Gold Way");
        nameAddresses.put("Donna", "789 Escort St");
        nameAddresses.put("Bartholomew", "332 Dunkin St");
        nameAddresses.put("Eden", "421 Angelic Blvd");

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});


        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        listaDeResultados.setAdapter(adapter);




        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }

    private void iniciarFindViewById() {
        btnVoltar = (Button) findViewById(R.id.resultadoPesquisaBtnVoltar);
        listaDeResultados = (ListView) findViewById(R.id.resultadoPesquisaListView);
        textoTitulo = (TextView) findViewById(R.id.resultadoPesquisaTvTitulo);
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listaDeResultados.setAdapter(arrayAdapter);
    }

//    private ArrayList<String> alimentarLista() {
//        ArrayList<Receita> listaReceitas = new UsuarioDAO(db).listarTodos();
//        ArrayList<String> resultados = new ArrayList<String>();
//
//        for (int i = 0; i < listaReceitas.size(); i++) {
//            resultados.add(listaReceitas.get(i).getNome());
//        }
//
//        return resultados;
//    }

    private void voltar() {
        this.finish();
    }
}