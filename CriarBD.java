package com.gleidsonviana.meusom;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBD extends SQLiteOpenHelper {



    private static final String nome_banco = "banco.db";
    public static final String tabela = "musicas";
    public static final String id = "_id";
    public static final String _id = "id";
    public static final String titulo = "titulo";
    public static final String artista = "artista";
    public static final String duracao = "duracao";
    public static final String data = "data";
    public static final String display_name = "display_name";
    public static final String flgFavorito = "flgFavorito";
    public static final String flgExcluir = "flgExcluir";
    private static final int versao = 4;

    public CriarBD(Context context){
        super(context, nome_banco,null,versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+tabela+" ( "
                + id + " integer primary key autoincrement,"
                + _id + " text,"
                + titulo + " text,"
                + artista + " text,"
                + duracao + " text,"
                + data + " text,"
                + display_name + " text,"
                + flgFavorito + " text,"
                + flgExcluir + " text "
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabela);
        onCreate(db);
    }
}
