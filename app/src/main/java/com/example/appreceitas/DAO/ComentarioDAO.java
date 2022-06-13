package com.example.appreceitas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Comentario;

import java.util.ArrayList;

public class ComentarioDAO {
    BancoDados bd;

    private static final String TABELA = "Comentario";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_IDUSUARIO = "idUsuario";
    private static final String COLUNA_IDRECEITA = "idReceita";
    private static final String COLUNA_TEXTO = "texto";

    public ComentarioDAO(BancoDados bd) {
        this.bd = bd;
    }

    public boolean salvar(Comentario comentario) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUNA_IDUSUARIO, comentario.getIdUsuario());
            values.put(COLUNA_IDRECEITA, comentario.getIdReceita());
            values.put(COLUNA_TEXTO, comentario.getTexto());

            db.insert(TABELA, null, values);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public ArrayList<Comentario> listarTodosReceitaId(int idReceita) {
        ArrayList<Comentario> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();

        String query = "SELECT c.id, c.idUsuario, c.idReceita, c.texto, u.nome " +
                "FROM Comentario c, Usuario u " +
                "WHERE " + COLUNA_IDRECEITA + "=" + idReceita + " " +
                "AND u.id = c.idUsuario";

//        String query = "SELECT * FROM " + TABELA + " " +
//                "WHERE " + COLUNA_IDRECEITA + "=" + idReceita;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                int codigo = cursor.getInt(0);
                int idUsuario = cursor.getInt(1);
//                int idReceita = cursor.getInt(2);
                String texto = cursor.getString(3);
                String nomeUsuario = cursor.getString(4);

                Comentario comentario = new Comentario(codigo, idUsuario, idReceita, texto, nomeUsuario);
                lista.add(comentario);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
        }
        return lista;
    }

}
