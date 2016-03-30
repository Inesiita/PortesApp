package software.drawer.aplicacion.basedatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Usuarios extends AppCompatActivity {


    ListView lista;
    //String [] args = new String [] { "usu1"};

    /*String[] datos = {
            "holaa",
            "adioosss",
            "hastaluegoo"

    };*/

    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
            "administracion", null, 1);
    SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
    Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
            "select nombre,colegio,nromesa from votantes", null);

    String[] datos = {fila.getString(0)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        lista = (ListView) findViewById(R.id.listView1);

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datos);

        lista.setAdapter(adaptador);
        /*Cursor personas = bd.rawQuery("SELECT nombre FROM votantes", null);

        final String[] datos = personas.getColumnNames();*/

       // final String resultado = personas.getString(0);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "posicion " + (i + 1) + datos[i], Toast.LENGTH_SHORT).show();
            }
        });
    }



}
