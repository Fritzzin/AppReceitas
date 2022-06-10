package com.example.appreceitas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Receita;

import java.util.ArrayList;

public class ReceitaDAO {
    BancoDados bd;

    private static final String TABELA = "Receita";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_IDAUTOR = "idAutor";

    public ReceitaDAO(BancoDados bd) {
        this.bd = bd;
    }

    public boolean salvar(Receita receita) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUNA_NOME, receita.getNome());
            values.put(COLUNA_IDAUTOR, receita.getIdAutor());

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

    public boolean atualizar(Receita receita, int id) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = COLUNA_CODIGO + "=" + id;

            ContentValues values = new ContentValues();
            values.put(COLUNA_NOME, receita.getNome());
            values.put(COLUNA_IDAUTOR, receita.getIdAutor());
            db.update(TABELA, values, where, null);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Receita> listarTodos() {
        ArrayList<Receita> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                int idAutor = cursor.getInt(2);

                Receita receita = new Receita(codigo, nome, idAutor);
                lista.add(receita);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
        }
        return lista;
    }

    public ArrayList<Receita> buscarReceitaNome(String nomeReceita) {
        ArrayList<Receita> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA +" " +
                "WHERE nome LIKE '%"+nomeReceita+"%';";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try{
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                int idAutor = cursor.getInt(2);

                Receita receita = new Receita(codigo, nome, idAutor);
                lista.add(receita);
            } while (cursor.moveToNext());
        }catch (CursorIndexOutOfBoundsException c) {

        }
        return lista;
    }

    public Receita buscarReceitaId(int id) {
        Receita receita = new Receita();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA +" " +
                "WHERE id = '"+id+"';";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try{
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                int idAutor = cursor.getInt(2);

                receita.setId(id);
                receita.setNome(nome);
                receita.setIdAutor(idAutor);
            } while (cursor.moveToNext());
        }catch (CursorIndexOutOfBoundsException c) {

        }
        return receita;
    }
}
