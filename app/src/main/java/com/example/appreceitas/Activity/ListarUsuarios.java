package com.example.appreceitas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

import java.util.ArrayList;

public class ListarUsuarios extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    ListView listaUsuarios;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        iniciarFindViewById();
        setArrayAdapter(alimentarLista());

        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abrirEditarUsuario(alimentarLista().get(i).toString());
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retornar();
            }
        });
    }


    private void iniciarFindViewById() {
        listaUsuarios = (ListView) findViewById(R.id.listaUsuarios);
        btnVoltar = (Button) findViewById(R.id.btnListaUsuarioVoltar);
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listaUsuarios.setAdapter(arrayAdapter);
    }

    private ArrayList<String> alimentarLista() {
        ArrayList<Usuario> listaUsuarios = new UsuarioDAO(db).listarTodos();
        ArrayList<String> listaNomeUsuarios = new ArrayList<String>();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            listaNomeUsuarios.add(listaUsuarios.get(i).getNome());
        }

        return listaNomeUsuarios;
    }

    private void retornar() {
        this.finish();
    }

    private void abrirEditarUsuario(String loginUsuario) {
        Intent myIntent = new Intent(this, EditarUsuario.class);

        myIntent.putExtra("loginUsuario", loginUsuario);

        this.startActivity(myIntent);
    }
}