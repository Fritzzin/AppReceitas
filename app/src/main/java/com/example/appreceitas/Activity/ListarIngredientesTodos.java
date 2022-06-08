package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.R;

import java.util.ArrayList;

public class ListarIngredientesTodos extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnVoltar;
    Button btnPesquisar;
    EditText textoPesquisa;
    ListView listaIngredientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ingredientes_todos);
        iniciarFindViewById();

        ArrayList<String> lista = new ArrayList<String>();
//        lista.add("Teste 1");
//        lista.add("Teste 2");
//        lista.add("Teste 3");
//        lista.add("Teste 4");
//        lista.add("Teste 5");
        setArrayAdapter(lista);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        listaIngredientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adicionarItem(lista.get(i).toString());
            }
        });
    }

    private void iniciarFindViewById(){
        btnVoltar = (Button) findViewById(R.id.listarTodosIngredientesBtnVoltar);
        btnPesquisar = (Button) findViewById(R.id.listarTodosIngredientesBtnPesquisar);
        textoPesquisa = (EditText) findViewById(R.id.listarTodosIngredientesEtPesquisa);
        listaIngredientes = (ListView) findViewById(R.id.listarTodosIngredientesLista);
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listaIngredientes.setAdapter(arrayAdapter);
    }

    private void voltar(){
        this.finish();
    }

    private void adicionarItem(String nome){
        Log.i("teste", nome);
        Intent intent = new Intent();
        intent.putExtra("nome", nome);
        setResult(78, intent);
        voltar();
    }
}