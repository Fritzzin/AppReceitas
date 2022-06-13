package com.example.appreceitas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Usuario;

import java.util.ArrayList;

/**
 * @author Fritzzin
 */
public class UsuarioDAO {

    BancoDados bd;

    private static final String TABELA = "Usuario";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_SENHA = "senha";
    private static final String COLUNA_TIPO = "tipo";


    public UsuarioDAO(BancoDados bd) {
        this.bd = bd;
    }

    public boolean salvar(Usuario usuario) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUNA_NOME, usuario.getNome());
            values.put(COLUNA_SENHA, usuario.getSenha());
            values.put(COLUNA_TIPO, usuario.getTipo());

            db.insert(TABELA, null, values);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = COLUNA_CODIGO + "=" + id;
            db.delete(TABELA, where, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Usuario usuario, int id) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = COLUNA_CODIGO + "=" + id;


            ContentValues values = new ContentValues();
            values.put(COLUNA_NOME, usuario.getNome());
            values.put(COLUNA_SENHA, usuario.getSenha());
            values.put(COLUNA_TIPO, usuario.getTipo());
            db.update(TABELA, values, where, null);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                String senha = cursor.getString(2);
                String tipo = cursor.getString(3);

                Usuario usuario = new Usuario(codigo, nome, senha, tipo);
                lista.add(usuario);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
        }
        return lista;
    }

    public Usuario buscarUsuario(String login) {
        Usuario usuario = new Usuario();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA + " " +
                "WHERE nome = '" + login + "';";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                String senha = cursor.getString(2);
                String tipo = cursor.getString(3);

                usuario = new Usuario(codigo, nome, senha, tipo);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {

        }

        return usuario;
    }

    public Usuario buscarUsuarioId(int id) {
        Usuario usuario = new Usuario();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA + " " +
                "WHERE id = '" + id + "';";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                String senha = cursor.getString(2);
                String tipo = cursor.getString(3);

                usuario = new Usuario(codigo, nome, senha, tipo);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {

        }

        return usuario;
    }

    public boolean existeUsuario(String login) {
        boolean retorno = false;

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA + " " +
                "WHERE nome = '" + login + "';";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                String senha = cursor.getString(2);
                String tipo = cursor.getString(3);
                retorno = true;

            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {

        }

        return retorno;
    }
}
