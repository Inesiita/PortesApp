package software.drawer.aplicacion.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //llamamos al constructor
    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    //creamos la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table votantes(dni integer primary key, nombre text, colegio text, nromesa integer)");
        db.execSQL("create table ofertas(id integer autoincrement not null primary key, origen text, destino text, capacidad integer, fecha datetime)");
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists votantes");
        db.execSQL("drop table if exists ofertas");
        db.execSQL("create table votantes(dni integer primary key, nombre text, colegio text, nromesa integer)");
        db.execSQL("create table ofertas(id integer autoincrement not null primary key, origen text, destino text, capacidad integer, fecha datetime)");

    }
}