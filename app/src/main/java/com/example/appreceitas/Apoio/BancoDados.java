package com.example.appreceitas.Apoio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class BancoDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 3;
    private static final String BANCO_LOCATION = "bd_location";

    private static final String QUERY_USUARIO = "CREATE TABLE IF NOT EXISTS Usuario ( " +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "senha TEXT NOT NULL, " +
            "tipo TEXT NOT NULL " +
            ");";

    private static final String QUERY_INGREDIENTE = "CREATE TABLE IF NOT EXISTS Ingrediente ( " +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "tipo TEXT NOT NULL " +
            ");";

    private static final String QUERY_RECEITA = "CREATE TABLE IF NOT EXISTS Receita ( " +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "idAutor INTEGER NOT NULL, " +
            "modoPreparo TEXT NOT NULL, " +
            "FOREIGN KEY (idAutor) REFERENCES Usuario(id) " +
            ");";

    private static final String QUERY_RECEITA_INGREDIENTE = "CREATE TABLE IF NOT EXISTS Receita_Ingrediente ( " +
            "id INTEGER PRIMARY KEY, " +
            "idReceita INTEGER NOT NULL, " +
            "idIngrediente INTEGER NOT NULL, " +
            "quantidade TEXTO NOT NULL, " +
            "tipoQtd TEXTO NOT NULL, " +
            "FOREIGN KEY (idReceita) REFERENCES Receita(id), " +
            "FOREIGN KEY (idIngrediente) REFERENCES Ingrediente(id) " +
            ");";

    private static final String QUERY_COMENTARIO = "CREATE TABLE IF NOT EXISTS Comentario ( " +
            "id INTEGER PRIMARY KEY, " +
            "idUsuario INTEGER NOT NULL, " +
            "idReceita INTEGER NOT NULL, " +
            "texto TEXT NOT NULL, " +
            "FOREIGN KEY (idUsuario) REFERENCES Usuario(id), " +
            "FOREIGN KEY (idReceita) REFERENCES Receita(id) " +
            ");";

    private static final String QUERY_RECEITA_FAVORITA = "CREATE TABLE IF NOT EXISTS Receita_Favorita( " +
            "id INTEGER PRIMARY KEY, " +
            "idUsuario INTEGER NOT NULL, " +
            "idReceita INTEGER NOT NULL, " +
            "favorito TEXT NOT NULL, " +
            "FOREIGN KEY (idUsuario) REFERENCES Usuario(id), " +
            "FOREIGN KEY (idReceita) REFERENCES Receita(id) " +
            ");";

    private static final String INSERT_USUARIO = "INSERT INTO Usuario(nome, senha, tipo) VALUES ( " +
            "'Augusto', " +
            "'da7a12cc87257e61ee52fcf7283aded557fb92e293c24cb127f1858b69d97b2b', " +
            "'Admin' " +
            ");";

    private static final String INSERT_COMENTARIO = "INSERT INTO Comentario (idUsuario, idReceita, texto) VALUES ( " +
            "1, " +
            "1, " +
            "'Comentario teste para a aplicacao' " +
            ");";

    private static final String INSERT_RECEITA_FAVORITA = "INSERT INTO Receita_Favorita (idUsuario, idReceita, favorito) VALUES ( " +
            "1, " +
            "1, " +
            "'TRUE' " +
            ");";

    public BancoDados(@Nullable Context context) {
        super(context, BANCO_LOCATION, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_USUARIO);
        db.execSQL(QUERY_INGREDIENTE);
        db.execSQL(QUERY_RECEITA);
        db.execSQL(QUERY_RECEITA_INGREDIENTE);
        db.execSQL(QUERY_COMENTARIO);
        db.execSQL(QUERY_RECEITA_FAVORITA);

        db.execSQL(INSERT_USUARIO);
        insertUsuarios(db);
        insertIngredientes(db);
        insertReceitas(db);
        db.execSQL(INSERT_COMENTARIO);
        InsertReceitaIngrediente(db);
        db.execSQL(INSERT_RECEITA_FAVORITA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void resetaDB() {
        SQLiteDatabase db = getWritableDatabase();
        onOpen(db);
        db.execSQL("DELETE FROM Usuario");
        db.execSQL("DELETE FROM Ingrediente");
        db.execSQL("DELETE FROM Receita");
        db.execSQL("DELETE FROM Receita_Ingrediente");
        db.execSQL("DELETE FROM Comentario");
        db.execSQL("DELETE FROM Receita_Favorita");

        db.execSQL(INSERT_USUARIO);
        insertUsuarios(db);
        insertIngredientes(db);
        insertReceitas(db);
        db.execSQL(INSERT_COMENTARIO);
        InsertReceitaIngrediente(db);
        db.execSQL(INSERT_RECEITA_FAVORITA);
        db.close();
    }

    public void insertIngredientes(SQLiteDatabase db) {
        ArrayList<String> ingredientes = new ArrayList<>();
        ingredientes.add("Ovo");
        ingredientes.add("Leite Condensado");
        ingredientes.add("Margarina");
        ingredientes.add("Achocolatado");
        ingredientes.add("Milho Verde");
        ingredientes.add("Leite");
        ingredientes.add("??leo");
        ingredientes.add("Queijo Parmes??o");
        ingredientes.add("Queijo Mussarela");
        ingredientes.add("Fub??");
        ingredientes.add("A????car");
        ingredientes.add("Farinha de trigo");
        ingredientes.add("Fermento");
        ingredientes.add("Canela em p??");
        ingredientes.add("??gua");
        ingredientes.add("Caldo Galinha");
        ingredientes.add("Manteiga");
        ingredientes.add("Alho");
        ingredientes.add("Salsa");
        ingredientes.add("Sal");
        ingredientes.add("Chocolate Granulado");
        for (int i = 0; i < ingredientes.size(); i++) {
            String sql = "INSERT INTO Ingrediente (nome, tipo) VALUES (" +
                    "'" + ingredientes.get(i) + "', " +
                    "'');";
            db.execSQL(sql);
        }
    }

    public void insertReceitas(SQLiteDatabase db) {
        //      INSERT RECEITAS
        LinkedHashMap<String, String> receitas = new LinkedHashMap<String, String>();
        receitas.put("Brigadeiro", "1. Em uma panela funda, acrescente o leite condensado, a margarina e o achocolatado (ou 4 colheres de sopa de chocolate em p??).\n\n" +
                "2. Cozinhe em fogo m??dio e mexa at?? que o brigadeiro comece a desgrudar da panela.\n\n" +
                "3. Deixe esfriar e fa??a pequenas bolas com a m??o passando a massa no chocolate granulado.");

        receitas.put("Bolo de Milho Junino", "1. No liquidificador, bata o milho, o leite, os ovos, o ??leo, o queijo e o fub?? at?? ficar homog??neo.\n\n" +
                "2. Transfira para uma tigela, adicione o a????car, a farinha, o fermento e misture com uma colher.\n\n" +
                "3. Despeje em uma f??rma de 25cm x 35cm forrada com papel-manteiga e leve ao forno m??dio, preaquecido, por 35 minutos ou at?? firmar e dourar.\n\n" +
                "4. Retire, deixe esfriar, corte em peda??os e polvilhe com a????car e canela antes de servir.");

        receitas.put("Polenta Frita", "1. Misture o fub?? na ??gua at?? diminuir a quantidade de grumos. S?? ap??s esse processo acenda a fogo.\n\n" +
                "2. Acrescente o caldo de galinha, o ??leo, a manteiga, o alho, o queijo parmes??o ralado, a farinha de trigo, o cheiro-verde e mexa at?? engrossar.\n\n" +
                "3. Despeje a polenta em uma assadeira e deixe esfriar.\n\n" +
                "4. Corte a polenta em ret??ngulos e passe no fub??.\n\n" +
                "5. Frite em ??leo quente.");

        receitas.put("P??o Caseiro", "1. Misture o fermento de padaria na ??gua morna.\n\n" +
                "2. Leve ao liquidificador: o a????car, o ??leo, o sal, o a????car, o ovo e a ??gua com o fermento.\n\n" +
                "3. Bate por alguns minutos.\n\n" +
                "4. Coloque em uma bacia grande esta mistura e acrescentar o trigo aos poucos, misturando com as m??os. A quantidade de trigo suficiente se d?? quando a massa n??o grudar em suas m??os.\n\n" +
                "5. Deixe a massa crescer por 1 hora.\n\n" +
                "6. Divida a massa em partes e enrole os p??es.\n\n" +
                "7. Deixe crescer novamente por 40 minutos.\n\n" +
                "8. Leve para assar por mais ou menos 30 minutos.\n\n" +
                "9. Se desejar, pode substituir o ??leo por banha. Tamb??m pode acrescentar, ?? massa j?? pronta, torresmo ou lingui??a.");

        receitas.put("Ovos Mexidos", "1. Quebre os 3 ovos e misture com o leite, at?? formar um caldo uniforme.\n\n" +
                "2. Pique as fatias de queijo em pequenos peda??os e acrescente sobre a mistura.\n\n" +
                "3. Coloque a manteiga na panela e a derreta em fogo alto, em seguida passe para fogo baixo e jogue a mistura.\n\n" +
                "4. N??o pare de mexer, tomando cuidado para n??o formar uma casca no fundo da panela.\n\n" +
                "5. Espere at?? o ovo atingir o ponto e sirva acrescentando sal.");


        for (Map.Entry<String, String> entry : receitas.entrySet()) {
            String nome = entry.getKey();
            String modoPreparo = entry.getValue();

            int min = 1;
            int max = 5;
            int range = max - min + 1;
            int idAutor = (int) (Math.random() * range) + min;

            String sql = "INSERT INTO Receita (nome, idAutor, modoPreparo) VALUES ( " +
                    "'" + nome + "', " +
                    "" + idAutor + "," +
                    "'" + modoPreparo + "'" +
                    ");";
            db.execSQL(sql);
        }
    }

    public void insertUsuarios(SQLiteDatabase db) {
        LinkedHashMap<String, String> usuarios = new LinkedHashMap<String, String>();
        usuarios.put("usuario2", "usuario2");
        usuarios.put("usuario3", "usuario3");
        usuarios.put("usuario4", "usuario4");
        usuarios.put("usuario5", "usuario5");
        for (Map.Entry<String, String> entry : usuarios.entrySet()) {
            String nome = entry.getKey();
            String senha = entry.getValue();

            String senhaEncriptada = encriptarSenha(senha);
            String sql = "INSERT INTO Usuario (nome, senha, tipo) VALUES (" +
                    "'" + nome + "'," +
                    "'" + senhaEncriptada + "', " +
                    "'Usu??rio');";
            db.execSQL(sql);
        }


    }

    public void InsertReceitaIngrediente(SQLiteDatabase db) {
//        Brigadeiro
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 1, 2, '1', 'Caixa');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 1, 3, '1', 'Colher (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 1, 4, '7', 'Colheres (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 1, 21, '', '');");

//        Bolo de Milho Junino
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 5, '2', 'Latas');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 6, '1 e 1/2', 'X??cara (ch??)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 1, '3', '');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 7, '1/2', 'X??cara (ch??)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 8, '1', 'X??cara (ch??)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 10, '1/2', 'X??cara (ch??)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 11, '2', 'X??cara (ch??)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 12, '1', 'X??cara (ch??)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 13, '1', 'Colher');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 11, '', 'Para polvilhar');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 2, 14, '', 'Para polvilhar');");

//        Polenta Frita
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 15, '1', 'Litro');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 10, '1 e 1/2', 'X??cara');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 16, '1', 'Tablete');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 7, '2', 'Colheres (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 17, '1', 'Colher (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 18, '2', 'Dentes');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 8, '2', 'Colheres (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 12, '2', 'Colheres (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 7, '', '');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 3, 19, '', '');");

//        P??o Caseiro
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 4, 15, '2 e 1/2', 'Copos');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 4, 11, '2', 'Colheres (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 4, 20, '1', 'Colher');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 4, 1, '1', '');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 4, 7, '1', 'Copo');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 4, 12, '1', 'Kg');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 4, 13, '50', 'g');");

//        Ovo Mexido
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 5, 1, '3', '');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 5, 6, '3', 'Colheres (sopa)');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 5, 9, '2', 'Fatias');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 5, 17, '', 'A gosto');");
        db.execSQL("INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( 5, 20, '', 'A gosto');");
    }

    public static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static String encriptarSenha(String senha) {
        byte[] shaInBytes = digest(senha.getBytes(UTF_8));
        return bytesToHex(shaInBytes);
    }
}
