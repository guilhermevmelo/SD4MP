package br.ufc.dc.sd4mp.mtgmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PartidaDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MTGPartidas.db";
    public static final int DATABASE_VERSION = 1;

    public PartidaDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public PartidaDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO add location data
        StringBuffer sql = new StringBuffer();
        sql.append("create table mtg_partidas (");
        sql.append("id integer primary key autoincrement,");
        sql.append("p1 integer,");
        sql.append("p2 integer,");
        sql.append("creation_time integer default current_timestamp)");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists mtg_partidas");
        onCreate(db);
    }

    public void create(Partida partida) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p1", partida.getPlayer1());
        contentValues.put("p2", partida.getPlayer2());
        long id = db.insert("mtg_partidas", null, contentValues);
        Log.v("SQLite", "create id = " + id);
    }

    public Partida retrieve(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select id, p1, p2, strftime('%d-%m-%Y %H:%M:%S', creation_time) from mtg_partidas where id = ?", new String[] { Integer.toString(id) });
        Partida partida = null;
        if (result != null && result.getCount() > 0) {
            partida = new Partida();
            partida.setId(result.getInt(0));
            partida.setPlayer1(result.getInt(1));
            partida.setPlayer2(result.getInt(2));
            partida.setCreation_time(result.getString(3));
        }
        return partida;
    }

    public void update(Partida partida) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p1", partida.getPlayer1());
        contentValues.put("p2", partida.getPlayer2());
        db.update("mtg_partidas", contentValues, " id = ? ", new String[]{Integer.toString(partida.getId())});
    }

    public void delete(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("mtg_partidas", " id = ? ", new String[] { Integer.toString(id) });
    }

    public List<Partida> list() {
        List<Partida> partidas = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select id, p1, p2, strftime('%d- %m-%Y %H:%M:%S', creation_time) from mtg_partidas order by id", null);
        if (result != null && result.getCount() > 0) {
            partidas = new ArrayList<Partida>();
            result.moveToFirst();
            while (result.isAfterLast() == false) {
                Partida partida = new Partida();
                partida.setId(result.getInt(0));
                partida.setPlayer1(result.getInt(1));
                partida.setPlayer2(result.getInt(2));
                partida.setCreation_time(result.getString(3));
                partidas.add(partida);
                result.moveToNext();
            }
        }
        return partidas;
    }

}
