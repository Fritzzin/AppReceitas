package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

public class EditarUsuario extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    private EditText textLogin;
    private EditText textSenha;
    private EditText textSenhaConfirmar;

    private TextView erroUsuario;
    private TextView erroUsuario2;
    private TextView erroSenha;
    private TextView erroSenhaConfirmar;
    private TextView erroSenhaConfirmar2;

    private Button btnAtualizar;
    private Button btnVoltar;

    private RadioGroup radioGroup;
    private RadioButton radioAdmin;
    private RadioButton radioUsuario;

    private String loginAntigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        iniciarViewById();
        setUsuario();

        if(radioAdmin.isSelected()){
            Log.i("teste","SELECIONADO ADMIN");
        }

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificarAtualizacao()) {
                    realizarAtualizacao();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecharTela();
            }
        });
    }

    public void iniciarViewById(){
        textLogin = (EditText) findViewById(R.id.atualizarUsuarioEtLogin);
        textSenha = (EditText) findViewById(R.id.atualizarUsuarioEtSenha);
        textSenhaConfirmar = (EditText) findViewById(R.id.atualizarUsuarioEtSenhaConfirmar);

        btnAtualizar = (Button) findViewById(R.id.atualizarUsuarioBtnAtualizar);
        btnVoltar = (Button) findViewById(R.id.atualizarUsuarioBtnVoltar);

        erroUsuario = (TextView) findViewById(R.id.atualizarUsuarioErroLogin);
        erroSenha = (TextView) findViewById(R.id.atualizarUsuarioErroSenha);
        erroSenhaConfirmar = (TextView) findViewById(R.id.atualizarUsuarioErroSenhaConfirmar);
        erroUsuario2 = (TextView) findViewById(R.id.atualizarUsuarioErroLogin2);
        erroSenhaConfirmar2 = (TextView) findViewById(R.id.atualizarUsuarioErroSenhaConfirmar2);

        radioGroup = (RadioGroup) findViewById(R.id.atualizarUsuarioRadioGroup);
        radioAdmin = (RadioButton) findViewById(R.id.atualizarUsuarioRadioAdmin);
        radioUsuario = (RadioButton) findViewById(R.id.atualizarUsuarioRadioUsuario);

        erroUsuario.setText("");
        erroSenha.setText("");
        erroSenhaConfirmar.setText("");
        erroUsuario2.setText("");
        erroSenhaConfirmar2.setText("");

        erroUsuario.setVisibility(View.INVISIBLE);
        erroSenha.setVisibility(View.INVISIBLE);
        erroSenhaConfirmar.setVisibility(View.INVISIBLE);
        erroUsuario2.setVisibility(View.INVISIBLE);
        erroSenhaConfirmar2.setVisibility(View.INVISIBLE);
    }

    public void realizarAtualizacao() {
        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();
        Usuario usuario = new UsuarioDAO(db).buscarUsuario(loginAntigo);
        usuario.setNome(login);
        usuario.setSenha(senha);

        if(radioAdmin.isChecked()) {
            usuario.setTipo("Admin");
        } else {
            usuario.setTipo("Usuário");
        }

        UsuarioDAO dao = new UsuarioDAO(db);
        dao.atualizar(usuario, usuario.getId());

        textLogin.setText("");
        textSenha.setText("");
        textSenhaConfirmar.setText("");

        fecharTela();
    }

    public void fecharTela(){
        this.finish();
    }

    public boolean verificarAtualizacao() {
        boolean retorno = false;

        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();
        String senhaConfirmar = textSenhaConfirmar.getText().toString();

        boolean checkTamanhoLogin = (login.length() >= 5);
        boolean checkTamanhoSenha = (senha.length() >= 5);
        boolean checkTamanhoSenhaConfirmar = (senhaConfirmar.length() >= 5);
        boolean checkLoginExistente = new UsuarioDAO(db).existeUsuario(login);
        boolean checkSenhasIguais = (senha.equals(senhaConfirmar));

//        Log.i("Teste", ""+login.length());
//        Log.i("Teste", ""+checkTamanhoLogin);

        if (checkTamanhoLogin) {
            erroUsuario.setVisibility(View.INVISIBLE);
            erroUsuario.setText("");
        } else {
            erroUsuario.setText("Necessário mais de 5 caracteres");
            erroUsuario.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoSenha) {
            erroSenha.setVisibility(View.INVISIBLE);
            erroSenha.setText("");
        } else {
            erroSenha.setText("Necessário mais de 5 caracteres");
            erroSenha.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoSenhaConfirmar) {
            erroSenhaConfirmar.setVisibility(View.INVISIBLE);
            erroSenhaConfirmar.setText("");
        } else {
            erroSenhaConfirmar.setText("Necessário mais de 5 caracteres");
            erroSenhaConfirmar.setVisibility(View.VISIBLE);
        }

        if(!login.equals(loginAntigo)){
            if (checkLoginExistente) {
                erroUsuario2.setText("\n Usuário já existente. Tente outro usuário!");
                erroUsuario2.setVisibility(View.VISIBLE);
                Log.i("Teste", "JA EXISTE LOGIN!");
            } else {
                erroUsuario2.setVisibility(View.INVISIBLE);
                erroUsuario2.setText("");
                Log.i("Teste", "Usuario disponivel!");
            }
        } else {
            erroUsuario2.setVisibility(View.INVISIBLE);
            erroUsuario2.setText("");
            checkLoginExistente = false;
        }


        if (checkSenhasIguais) {
            erroSenhaConfirmar2.setVisibility(View.INVISIBLE);
            erroSenhaConfirmar2.setText("");
        } else {
            erroSenhaConfirmar.setText("As senhas não são iguais! Confirme a senha!");
            erroSenhaConfirmar.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoLogin && checkTamanhoLogin && checkTamanhoSenhaConfirmar
                && !checkLoginExistente && checkSenhasIguais) {
            retorno = true;
        }

        return retorno;
    }

    public void setUsuario(){
        if (getIntent().hasExtra("loginUsuario")) {
            Bundle extras = getIntent().getExtras();
            loginAntigo = extras.getString("loginUsuario");
            Log.i("teste", "Usuario antigo: "+loginAntigo);
        }

        Usuario usuario = new UsuarioDAO(db).buscarUsuario(loginAntigo);
        textLogin.setText(usuario.getNome());
        textSenha.setText(usuario.getSenha());
        textSenhaConfirmar.setText(usuario.getSenha());

        if(usuario.getTipo().equals("Admin")) {
            radioGroup.check(radioAdmin.getId());
        } else {
            radioGroup.check(radioUsuario.getId());
        }
    }
}