package br.ufc.dc.sd4mp.mtgmaster;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class PartidaProvider extends ContentProvider {

    static final String PROVIDER_NAME = "br.ufc.dc.sd4mp.mtgmaster.Partida";
    static final String URL = "content://" + PROVIDER_NAME + "/partidas";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String ID = "_id";
    static final String P1 = "p1";
    static final String P2 = "p2";

    private static HashMap<String, String> NOTES_PROJECTION_MAP;


    static final int PARTIDAS = 1;
    static final int PARTIDAS_ID = 2;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "partidas", PARTIDAS);
        uriMatcher.addURI(PROVIDER_NAME, "partidas/#", PARTIDAS_ID);
    }

    private SQLiteDatabase database;
    static final String DATABASE_NAME = "MTGPartidas_.db";
    static final int DATABASE_VERSION = 2;
    static final String PARTIDAS_TABLE_NAME = "mtg_partidas";
    static final String CREATE_DB_TABLE = "create table " + PARTIDAS_TABLE_NAME +
            " (_id integer primary key autoincrement," +
            " p1 integer, p2 integer)";

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper helper = new DatabaseHelper(context);
        database = helper.getWritableDatabase();

        return (database == null) ? false : true;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            /**
             * Get all note records
             */
            case PARTIDAS:
                return "vnd.android.cursor.dir/vnd.br.ufc.dc.sd4mp.mtgmaster.Partida";
            /**
             * Get a particular note
             */
            case PARTIDAS_ID:
                return "vnd.android.cursor.item/vnd.br.ufc.dc.sd4mp.mtgmaster.Partida";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(PARTIDAS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case PARTIDAS:
                queryBuilder.setProjectionMap(NOTES_PROJECTION_MAP);
                break;
            case PARTIDAS_ID:
                queryBuilder.appendWhere(ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || sortOrder.isEmpty()) {
            sortOrder = ID;
        }

        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = database.insert(PARTIDAS_TABLE_NAME, "", values);

        if (rowID > 0) {
            Uri uriAux = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uriAux, null);
            return uriAux;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case PARTIDAS:
                count = database.delete(PARTIDAS_TABLE_NAME, selection, selectionArgs);
                break;
            case PARTIDAS_ID:
                String id = uri.getPathSegments().get(1);
                count = database.delete(PARTIDAS_TABLE_NAME, ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case PARTIDAS:
                count = database.update(PARTIDAS_TABLE_NAME, values,
                        selection, selectionArgs);
                break;
            case PARTIDAS_ID:
                count = database.update(PARTIDAS_TABLE_NAME, values, ID +
                        " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL("drop table if exists " + PARTIDAS_TABLE_NAME);
            onCreate(database);
        }
    }
}
