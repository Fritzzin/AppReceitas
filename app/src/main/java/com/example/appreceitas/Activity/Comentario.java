package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appreceitas.R;

public class Comentario extends AppCompatActivity {

    Button btnVoltar;
    Button btnEnviar;
    TextView titulo;
    EditText textoComentario;
    ListView listaComentarios;

    int idUsuario;
    int idReceita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        iniciarFindViewById();




        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }

    private void iniciarFindViewById(){
        btnVoltar = (Button) findViewById(R.id.comentarioBtnVoltar);
        btnEnviar = (Button) findViewById(R.id.comentarioBtnEnviar);
        titulo = (TextView) findViewById(R.id.comentarioTitulo);
        textoComentario = (EditText) findViewById(R.id.comentarioEtComentario);

    }

    private void voltar(){
        this.finish();
    }

    private void setUsuarioReceita(){
        if (getIntent().hasExtra("idReceita")) {
            Bundle extras = getIntent().getExtras();
            idReceita = extras.getInt("idReceita");
            idUsuario = extras.getInt("idUsuario");
        }
    }
}