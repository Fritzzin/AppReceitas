package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Receita;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.ReceitaIngredienteDAO;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultadoPesquisa extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnVoltar;
    TextView textoTitulo;
    ListView listaDeResultados;

    ArrayList<String> listaIngredientes = new ArrayList<String>();
    ArrayList<Receita> listaReceitas = new ArrayList<Receita>();

    ArrayList<String> listaTeste = new ArrayList<String>();

    int idUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_pesquisa);
        iniciarFindViewById();

        int qtd = 0;
        if (getIntent().hasExtra("idUsuario")) {
            Bundle extras = getIntent().getExtras();
            idUsuario = extras.getInt("idUsuario");
            qtd = extras.getInt("qtd");
        }

        setListaIngredientes(qtd);
        setSimpleAdapter(alimentarTabela());

        for (int i = 0; i < listaIngredientes.size(); i++) {
            Log.i("teste", "Ingrediente:"+listaIngredientes.get(i));
        }

        for (int i = 0; i < listaReceitas.size(); i++) {
            Log.i("teste", "Receitas:"+listaReceitas.get(i).getNome());
            Log.i("teste", "idAutor:"+listaReceitas.get(i).getIdAutor());
            Log.i("teste", "idReceita:"+listaReceitas.get(i).getId());
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        listaDeResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abrirReceita(listaReceitas.get(i));
            }
        });
    }

    private void iniciarFindViewById() {
        btnVoltar = (Button) findViewById(R.id.resultadoPesquisaBtnVoltar);
        listaDeResultados = (ListView) findViewById(R.id.resultadoPesquisaListView);
        textoTitulo = (TextView) findViewById(R.id.resultadoPesquisaTvTitulo);
    }

    private void setSimpleAdapter(LinkedHashMap<String, String> lista){
        List<LinkedHashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});


        Iterator it = lista.entrySet().iterator();
        while (it.hasNext())
        {
            LinkedHashMap<String, String> resultsMap = new LinkedHashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        listaDeResultados.setAdapter(adapter);
    }

    private LinkedHashMap<String, String> alimentarTabela(){
        LinkedHashMap<String, String> lista = new LinkedHashMap<>();

        ArrayList<Receita> buscarReceitas = new ReceitaIngredienteDAO(db).resultadoPesquisaLista(listaIngredientes);

        for (int i = 0; i < buscarReceitas.size(); i++) {
            if (!listaTeste.contains(buscarReceitas.get(i).getNome())){
                listaTeste.add(buscarReceitas.get(i).getNome());
                listaReceitas.add(buscarReceitas.get(i));
                lista.put(buscarReceitas.get(i).getNome(), "");
            }
        }

//        for (int i = 0; i < listaTeste.size(); i++) {
//            Log.i("teste","ListaTeste:"+listaTeste.get(i));
//        }
//
//        Set<String> keys = lista.keySet();
//        for (String key : keys) {
//            Log.i("teste", key + " -- "+ lista.get(key));
//        }

        return lista;
    }

    private void voltar() {
        this.finish();
    }

    public void abrirReceita(Receita receita){
        Intent myIntent = new Intent(this, VisualizacaoReceita.class);

        myIntent.putExtra("idReceita", receita.getId());
        myIntent.putExtra("nomeReceita", receita.getNome());
        myIntent.putExtra("autorReceita", receita.getIdAutor());
        myIntent.putExtra("idUsuario", idUsuario);

        Log.i("teste", "idreceita: "+receita.getId());
        Log.i("teste", "nomeReceita: "+receita.getNome());
        Log.i("teste", "autorReceita: "+receita.getIdAutor());
        Log.i("teste", "idUsuario: "+idUsuario);

        this.startActivity(myIntent);
    }

    private void setListaIngredientes(int qtdIngredientes){
        for (int i = 0; i < qtdIngredientes; i++) {
            String tag = "ingrediente"+i;
            if (getIntent().hasExtra(tag)) {
                Bundle extras = getIntent().getExtras();
                listaIngredientes.add(extras.getString(tag));
            }
        }
    }
}