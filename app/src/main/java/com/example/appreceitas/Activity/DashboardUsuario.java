package com.example.appreceitas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appreceitas.R;

public class DashboardUsuario extends AppCompatActivity {

    TextView tvNomeUsuario;
    Button btnBuscarNome;
    Button btnBuscarLista;
    Button btnVoltar;
    Button btnPainelAdmin;
    Button btnFavorito;

    int idUsuario;
    String nomeUsuario;
    String tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_usuario);
        iniciarFindById();
        setExtras();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retorno();
            }
        });

        btnPainelAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirPainelAdministrativo();
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

        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFavoritos();
            }
        });
    }

    public void iniciarFindById() {
        tvNomeUsuario = (TextView) findViewById(R.id.tvNomeUsuario);
        btnBuscarNome = (Button) findViewById(R.id.btnBuscarNome);
        btnBuscarLista = (Button) findViewById(R.id.btnBuscarLista);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnPainelAdmin = (Button) findViewById(R.id.btnPainelAdmin);
        btnFavorito = (Button) findViewById(R.id.dashboardBtnFavoritos);
    }

    public void abrirBuscarPorLista() {
        Intent myIntent = new Intent(this, ListaDeIngredientes.class);
        myIntent.putExtra("idUsuario", idUsuario);
        this.startActivity(myIntent);
    }

    public void abrirPainelAdministrativo() {
        Intent myIntent = new Intent(this, PainelAdmin.class);
        this.startActivity(myIntent);
    }

    public void abrirListaReceitas() {
        Intent myIntent = new Intent(this, ListarReceitasTodas.class);
        myIntent.putExtra("idUsuario", idUsuario);
        this.startActivity(myIntent);
    }

    public void abrirFavoritos() {
        Intent myIntent = new Intent(this, ListarFavoritos.class);
        myIntent.putExtra("idUsuario", idUsuario);
        this.startActivity(myIntent);
    }

    public void setExtras() {
        if (getIntent().hasExtra("idUsuario")) {
            Bundle extras = getIntent().getExtras();
            this.idUsuario = extras.getInt("idUsuario");
            this.nomeUsuario = extras.getString("nome");
            this.tipoUsuario = extras.getString("tipo");
        }

        tvNomeUsuario.append(" " + nomeUsuario);

        if (tipoUsuario.equals("Admin")) {
            btnPainelAdmin.setVisibility(View.VISIBLE);
        } else {
            btnPainelAdmin.setVisibility(View.INVISIBLE);
        }
    }

    public void retorno() {
        this.finish();
    }
}