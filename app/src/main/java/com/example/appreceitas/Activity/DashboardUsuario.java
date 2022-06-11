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

    int idUsuario;
    String nomeUsuario;
    String tipoUsuario;

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

        btnBuscarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirListaReceitas();
            }
        });

        btnBuscarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirBuscarPorLista();
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

    public void abrirBuscarPorLista() {
        Intent myIntent = new Intent(this, ListaDeIngredientes.class);
        this.startActivity(myIntent);
    }

    public void abrirListaUsuarios() {
        Intent myIntent = new Intent(this, ListarUsuarios.class);
        this.startActivity(myIntent);
    }

    public void abrirListaReceitas() {
        Intent myIntent = new Intent(this, ListarReceitasTodas.class);

        myIntent.putExtra("idUsuario", idUsuario);

        this.startActivity(myIntent);
    }

    public void setUsuario() {
        if (getIntent().hasExtra("idUsuario")) {
            Bundle extras = getIntent().getExtras();
            this.idUsuario = extras.getInt("idUsuario");
            this.nomeUsuario = extras.getString("nome");
            this.tipoUsuario = extras.getString("tipo");
        }

        tvNomeUsuario.append(" " + nomeUsuario);

        if (tipoUsuario.equals("Admin")) {
            tvTeste.setVisibility(View.VISIBLE);
        } else {
            tvTeste.setVisibility(View.INVISIBLE);
        }

        Log.i("Teste nova Activity", "" + idUsuario);
        Log.i("Teste nova Activity", "" + nomeUsuario);
        Log.i("Teste nova Activity", "" + tipoUsuario);
    }

    public void retorno() {
        this.finish();
    }
}