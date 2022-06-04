package com.example.appreceitas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appreceitas.R;

public class DashboardUsuario extends AppCompatActivity {

    TextView tvTeste;
    TextView tvNomeUsuario;
    Button btnBuscarNome;
    Button btnBuscarLista;
    Button btnVoltar;
    Button btnListarUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_usuario);
        iniciarFindById();
        setUsuario();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retorno();
            }
        });

        btnListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirListaUsuarios();
            }
        });


    }

    public void iniciarFindById() {
        tvTeste = (TextView) findViewById(R.id.tvTeste);
        tvNomeUsuario = (TextView) findViewById(R.id.tvNomeUsuario);
        btnBuscarNome = (Button) findViewById(R.id.btnBuscarNome);
        btnBuscarLista = (Button) findViewById(R.id.btnBuscarLista);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnListarUsuarios = (Button) findViewById(R.id.btnEditarUsuarios);
    }

    public void abrirListaUsuarios() {
        Intent myIntent = new Intent(this, ListarUsuarios.class);
        this.startActivity(myIntent);
    }

    public void setUsuario() {
        int idUsuario = 0;
        String nome = "";
        String tipo = "";

        if (getIntent().hasExtra("idUsuario")) {
            Bundle extras = getIntent().getExtras();
            idUsuario = extras.getInt("idUsuario");
            nome = extras.getString("nome");
            tipo = extras.getString("tipo");
        }

        tvNomeUsuario.append(" " + nome);

        if (tipo.equals("Admin")) {
            tvTeste.setVisibility(View.VISIBLE);
        } else {
            tvTeste.setVisibility(View.INVISIBLE);
        }

        Log.i("Teste nova Activity", "" + idUsuario);
        Log.i("Teste nova Activity", "" + nome);
        Log.i("Teste nova Activity", "" + tipo);
    }

    public void retorno() {
        this.finish();
    }
}