package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Comentario;
import com.example.appreceitas.DAO.ComentarioDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;

public class VisualizarComentario extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnVoltar;
    Button btnEnviar;
    TextView titulo;
    EditText textoComentario;
    ListView listViewComentarios;

    int idUsuario;
    int idReceita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        iniciarFindViewById();
        setUsuarioReceita();
        setArrayAdapter(alimentarLista());

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarComentario();
            }
        });
    }

    private void iniciarFindViewById() {
        btnVoltar = (Button) findViewById(R.id.comentarioBtnVoltar);
        btnEnviar = (Button) findViewById(R.id.comentarioBtnEnviar);
        titulo = (TextView) findViewById(R.id.comentarioTitulo);
        textoComentario = (EditText) findViewById(R.id.comentarioEtComentario);
        listViewComentarios = (ListView) findViewById(R.id.comentarioListaComentarios);

    }

    private void voltar() {
        this.finish();
    }

    private void setUsuarioReceita() {
        if (getIntent().hasExtra("idReceita")) {
            Bundle extras = getIntent().getExtras();
            idReceita = extras.getInt("idReceita");
            idUsuario = extras.getInt("idUsuario");
        }
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listViewComentarios.setAdapter(arrayAdapter);
    }

    private ArrayList<String> alimentarLista() {
        ArrayList<Comentario> listaComentariosBanco = new ComentarioDAO(db).listarTodosReceitaId(idReceita);
        ArrayList<String> listaComentariosString = new ArrayList<String>();

        for (int i = 0; i < listaComentariosBanco.size(); i++) {
            String texto = listaComentariosBanco.get(i).getTexto();
            String autor = listaComentariosBanco.get(i).getNomeUsuario();
            listaComentariosString.add(autor+": "+texto);
        }

        return listaComentariosString;
    }

    private void enviarComentario(){
        String texto = textoComentario.getText().toString();
        if(!texto.trim().equals("")){
            Comentario comentario = new Comentario(idUsuario, idReceita, texto);
            new ComentarioDAO(db).salvar(comentario);

            textoComentario.setText("");
            textoComentario.clearFocus();

            setArrayAdapter(alimentarLista());
        } else {
            textoComentario.setText("");
        }
    }
}