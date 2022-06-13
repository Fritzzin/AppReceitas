package com.example.appreceitas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.appreceitas.Apoio.BancoDados;

public class ReceitaFavoritaDAO {
    BancoDados bd;

    private static final String TABELA = "Receita_Favorita";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_IDUSUARIO = "idUsuario";
    private static final String COLUNA_IDRECEITA = "idReceita";
    private static final String COLUNA_FAVORITO = "favorito";

    public ReceitaFavoritaDAO(BancoDados bd) {
        this.bd = bd;
    }

    public boolean adicionarFavorito(int idUsuario, int idReceita) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUNA_IDUSUARIO, idUsuario);
            values.put(COLUNA_IDRECEITA, idReceita);
            values.put(COLUNA_FAVORITO, "TRUE");

            db.insert(TABELA, null, values);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean removerFavorito(int idUsuario, int idReceita) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = COLUNA_IDUSUARIO + "=" + idUsuario + " AND " + COLUNA_IDRECEITA + "=" + idReceita;
            db.delete(TABELA, where, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFavorite(int idUsuario, int idReceita) {
        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA + " " +
                "WHERE " + COLUNA_IDUSUARIO + " = " + idUsuario + " " +
                "AND " + COLUNA_IDRECEITA + " = " + idReceita + "; ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                String favorito = cursor.getString(3);

                if (favorito.equals("TRUE")) {
                    return true;
                }
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
            return false;
        }
        return false;
    }
}
