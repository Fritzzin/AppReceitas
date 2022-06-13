package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.DAO.ReceitaDAO;
import com.example.appreceitas.R;

public class VisualizarModoPreparo extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    TextView textoModoPreparo;
    TextView textoTitulo;
    Button btnVoltar;

    int idReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_modo_preparo);
        iniciarViewById();
        setExtras();
        getModoPreparo();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }

    private void iniciarViewById() {
        textoModoPreparo = (TextView) findViewById(R.id.modoPreparoTexto);
        textoTitulo = (TextView) findViewById(R.id.modoPreparoTitulo);
        btnVoltar = (Button) findViewById(R.id.modoPreparoBtnVoltar);
    }

    private void setExtras() {
        if (getIntent().hasExtra("idReceita")) {
            Bundle extras = getIntent().getExtras();
            idReceita = extras.getInt("idReceita");
        }
    }

    private void voltar() {
        this.finish();
    }

    private void getModoPreparo () {
        String texto = new ReceitaDAO(db).getModoPreparo(this.idReceita);
        this.textoModoPreparo.setText(texto);
    }
}