package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Receita;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.ReceitaDAO;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;

public class ListarReceitasTodas extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    ListView listaReceitas;
    EditText textoPesquisar;
    Button btnVoltar;
    Button btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_receitas_todas);
        iniciarFindViewById();
        setArrayAdapter(alimentarLista());


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retornar();
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setArrayAdapter(alimentarListaPesquisa(textoPesquisar.getText().toString()));
            }
        });


    }

    private void iniciarFindViewById() {
        listaReceitas = (ListView) findViewById(R.id.listaReceitas);
        btnVoltar = (Button) findViewById(R.id.listaReceitasBtnVoltar);
        btnPesquisar = (Button) findViewById(R.id.listaReceitasBtnPesquisar);
        textoPesquisar = (EditText) findViewById(R.id.listaReceitasEtPesquisar);
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listaReceitas.setAdapter(arrayAdapter);
    }


    private void retornar() {
        this.finish();
    }

    private ArrayList<String> alimentarLista() {
        ArrayList<Receita> listaReceitas = new ReceitaDAO(db).listarTodos();
        ArrayList<String> listaNomeReceitas = new ArrayList<String>();

        for (int i = 0; i < listaReceitas.size(); i++) {
            listaNomeReceitas.add(listaReceitas.get(i).getNome());
            Log.i("teste", ""+listaReceitas.get(i).getId());
            Log.i("teste", ""+listaReceitas.get(i).getNome());
            Log.i("teste", ""+listaReceitas.get(i).getIdAutor());
        }

        return listaNomeReceitas;
    }

    private ArrayList<String> alimentarListaPesquisa(String pesquisa) {
        if (pesquisa.equals("")) {
            return alimentarLista();
        } else {

            ArrayList<Receita> listaReceitas = new ReceitaDAO(db).buscarReceitaNome(pesquisa);
            ArrayList<String> listaNomeReceitas = new ArrayList<String>();

            for (int i = 0; i < listaReceitas.size(); i++) {
                listaNomeReceitas.add(listaReceitas.get(i).getNome());
            }

            return listaNomeReceitas;
        }
    }
}