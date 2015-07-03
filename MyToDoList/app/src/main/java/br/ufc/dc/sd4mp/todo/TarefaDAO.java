package br.ufc.dc.sd4mp.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by guilherme on 5/11/15.
 */
public class TarefaDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyToDoList.db";
    public static final int DATABASE_VERSION = 1;

    public TarefaDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sql = new StringBuffer();
        sql.append("create table tarefas (");
        sql.append("id integer primary key autoincrement,");
        sql.append("titulo text,");
        sql.append("descricao text,");
        sql.append("dataCriacao integer default current_timestamp,");
        sql.append("concluida integer default 0)");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists notes");
        onCreate(db);
    }

    public void create(Tarefa tarefa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", tarefa.getTitulo());
        contentValues.put("descricao", tarefa.getDescricao());
        long id = db.insert("tarefas", null, contentValues);
        Log.v("SQLite", "inserted with id " + id);
    }

    public void read(Integer id) {

    }

    public void update(Tarefa tarefa) {

    }

    public void delete(Integer id) {

    }
}
