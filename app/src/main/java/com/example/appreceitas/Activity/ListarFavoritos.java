package com.example.appreceitas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Receita;
import com.example.appreceitas.DAO.ReceitaDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;

public class ListarFavoritos extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnVoltar;
    TextView textoTitulo;
    ListView listaDeFavoritos;

    ArrayList<Receita> listaReceitas = new ArrayList<Receita>();
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_favoritos);
        iniciarFindViewById();
        getExtras();
        setArrayAdapter(alimentarLista());

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
        listaDeFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abrirReceita(listaReceitas.get(i));
            }
        });
    }

    private void iniciarFindViewById() {
        btnVoltar = (Button) findViewById(R.id.listaFavoritoBtnVoltar);
        textoTitulo = (TextView) findViewById(R.id.listaFavoritoTvTitulo);
        listaDeFavoritos = (ListView) findViewById(R.id.listaFavoritoListaReceitas);
    }

    public void getExtras() {
        if (getIntent().hasExtra("idUsuario")) {
            Bundle extras = getIntent().getExtras();
            this.idUsuario = extras.getInt("idUsuario");
        }
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listaDeFavoritos.setAdapter(arrayAdapter);
    }

    private ArrayList<String> alimentarLista() {
        listaReceitas = new ReceitaDAO(db).buscarFavoritos(idUsuario);
        ArrayList<String> listaNomes = new ArrayList<String>();

        for (int i = 0; i < listaReceitas.size(); i++) {
            listaNomes.add(listaReceitas.get(i).getNome());
        }

        return listaNomes;
    }

    public void voltar() {
        this.finish();
    }

    public void abrirReceita(Receita receita) {
        Intent myIntent = new Intent(this, VisualizacaoReceita.class);

        myIntent.putExtra("idReceita", receita.getId());
        myIntent.putExtra("nomeReceita", receita.getNome());
        myIntent.putExtra("autorReceita", receita.getIdAutor());
        myIntent.putExtra("idUsuario", idUsuario);

        this.startActivity(myIntent);
    }
}