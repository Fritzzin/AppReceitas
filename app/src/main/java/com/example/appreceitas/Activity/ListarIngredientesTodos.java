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
import com.example.appreceitas.Class.Ingrediente;
import com.example.appreceitas.DAO.IngredienteDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;

public class ListarIngredientesTodos extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnVoltar;
    Button btnPesquisar;
    EditText textoPesquisa;
    ListView listViewIngredientes;

    ArrayList<Ingrediente> listaIngredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ingredientes_todos);
        iniciarFindViewById();

        ArrayList<String> lista = alimentarLista();
        setArrayAdapter(lista);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        listViewIngredientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adicionarItem(lista.get(i).toString(), i);
            }
        });
    }

    private void iniciarFindViewById(){
        btnVoltar = (Button) findViewById(R.id.listarTodosIngredientesBtnVoltar);
        btnPesquisar = (Button) findViewById(R.id.listarTodosIngredientesBtnPesquisar);
        textoPesquisa = (EditText) findViewById(R.id.listarTodosIngredientesEtPesquisa);
        listViewIngredientes = (ListView) findViewById(R.id.listarTodosIngredientesLista);
    }

    private ArrayList<String> alimentarLista(){
        listaIngredientes = new IngredienteDAO(this.db).listarTodos();
        ArrayList<String> listaNomes = new ArrayList<String>();

        for (int i = 0; i < listaIngredientes.size(); i++) {
            listaNomes.add(listaIngredientes.get(i).getNome());
        }

        return listaNomes;
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listViewIngredientes.setAdapter(arrayAdapter);
    }

    private void voltar(){
        this.finish();
    }

    private void adicionarItem(String nome, int i){
        Intent intent = new Intent();
        String id = String.valueOf(listaIngredientes.get(i).getId());

        intent.putExtra("nome", nome);
        intent.putExtra("idIngrediente", id);

        setResult(78, intent);

        voltar();
    }
}