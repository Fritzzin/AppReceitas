package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Ingrediente;
import com.example.appreceitas.Class.Receita;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.IngredienteDAO;
import com.example.appreceitas.DAO.ReceitaDAO;
import com.example.appreceitas.DAO.ReceitaFavoritaDAO;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;

public class VisualizacaoReceita extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    TextView textoTitulo;
    TextView textoAutor;
    TextView textoIngredientes;
    Button btnVoltar;
    Button btnComentarios;
    ImageView imagem;
    ListView listaIngredientes;
    CheckBox checkBoxFavorito;

    int idReceita;
    int idAutor;
    int idUsuario;
    String nomeReceita;
    Usuario autor;
    Receita receita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_receita);
        buscarInformacoesReceita();
        iniciarFindViewById();
        setArrayAdapter(alimentarLista());
        setReceitaFavorita();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        checkBoxFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBoxFavorito.isChecked()) {
                    checkBoxFavorito.setChecked(false);
                    removerFavorito();
                } else {
                    checkBoxFavorito.setChecked(true);
                    tornarFavorito();
                }
            }
        });

        btnComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirComentarios();
            }
        });

    }

    private void iniciarFindViewById() {
        textoTitulo = (TextView) findViewById(R.id.receitaEtTitulo);
        textoAutor = (TextView) findViewById(R.id.receitaEtAutor);
        textoIngredientes = (TextView) findViewById(R.id.receitaEtIngredientes);
        btnVoltar = (Button) findViewById(R.id.receitaBtnVoltar);
        btnComentarios = (Button) findViewById(R.id.receitaBtnComentarios);
        imagem = (ImageView) findViewById(R.id.receitaImage);
        listaIngredientes = (ListView) findViewById(R.id.receitaListaIngredientes);
        checkBoxFavorito = (CheckBox) findViewById(R.id.receitaCheckBoxFavorito);

        textoTitulo.setText(receita.getNome());
        textoAutor.setText(autor.getNome());
        checkBoxFavorito.setChecked(false);

    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listaIngredientes.setAdapter(arrayAdapter);
    }

    private ArrayList<String> alimentarLista() {
        ArrayList<Ingrediente> listaReceitas = new IngredienteDAO(db).buscarIngredientesReceita(this.idReceita);
        ArrayList<String> listaNomeReceitas = new ArrayList<String>();

        for (int i = 0; i < listaReceitas.size(); i++) {
            String quantidade = listaReceitas.get(i).getQuantidade();
            String tipoQtd = listaReceitas.get(i).getTipoQtd();
            String nomeIngrediente = listaReceitas.get(i).getNome();


            listaNomeReceitas.add(quantidade+" "+tipoQtd+" de " + nomeIngrediente);
        }

        return listaNomeReceitas;
    }

    private void buscarInformacoesReceita() {
        if (getIntent().hasExtra("idReceita")) {
            Bundle extras = getIntent().getExtras();
            idReceita = extras.getInt("idReceita");
            nomeReceita = extras.getString("nomeReceita");
            idAutor = extras.getInt("autorReceita");
            idUsuario = extras.getInt("idUsuario");
        }

        this.autor = new UsuarioDAO(db).buscarUsuarioId(this.idAutor);
        this.receita = new ReceitaDAO(db).buscarReceitaId(this.idReceita);
    }

    private void setReceitaFavorita(){
        boolean favorita = new ReceitaFavoritaDAO(db).isFavorite(this.idUsuario, this.idReceita);

        if(favorita) {
            checkBoxFavorito.setChecked(true);
        } else {
            checkBoxFavorito.setChecked(false);
        }
    }

    private void removerFavorito(){
        new ReceitaFavoritaDAO(db).removerFavorito(this.idUsuario, this.idReceita);
    }

    private void tornarFavorito() {
        new ReceitaFavoritaDAO(db).adicionarFavorito(this.idUsuario, this.idReceita);
    }

    private void voltar() {
        this.finish();
    }

    private void abrirComentarios() {
        Intent myIntent = new Intent(this, VisualizarComentario.class);

        myIntent.putExtra("idUsuario", idUsuario);
        myIntent.putExtra("idReceita", idReceita);

        this.startActivity(myIntent);
    }
}