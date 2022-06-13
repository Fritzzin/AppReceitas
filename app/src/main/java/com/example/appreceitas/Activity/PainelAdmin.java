package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appreceitas.R;

public class PainelAdmin extends AppCompatActivity {

    int idUsuario;
    Button btnVoltar;
    Button btnEditarUsuarios;
    TextView textoTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_admin);
        iniciarFindViewById();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        btnEditarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirListaUsuarios();
            }
        });
    }

    public void abrirListaUsuarios() {
        Intent myIntent = new Intent(this, ListarUsuarios.class);
        this.startActivity(myIntent);
    }

    private void iniciarFindViewById() {
        btnVoltar = (Button) findViewById(R.id.painelAdminBtnVoltar);
        btnEditarUsuarios = (Button) findViewById(R.id.painelAdminBtnEditarUsuarios);
        textoTitulo = (TextView) findViewById(R.id.painelAdminTvTitulo);
    }

    private void voltar() {
        this.finish();
    }
}