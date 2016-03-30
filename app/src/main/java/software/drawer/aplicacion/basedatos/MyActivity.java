package software.drawer.aplicacion.basedatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MyActivity extends Activity {

    private EditText et1, et2, et3, et4;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        et1 = (EditText) findViewById(R.id.et_dni);
        et2 = (EditText) findViewById(R.id.et_nombreyapellido);
        et3 = (EditText) findViewById(R.id.et_colegio);
        et4 = (EditText) findViewById(R.id.et_mesa);
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this, Usuarios.class));
            }
        });


    }


    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromesa = et4.getText().toString();
        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromesa", nromesa);
        bd.insert("votantes", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se cargaron los datos de la persona",
                Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {

        ListView lista;
        String[] datos = null;
        setContentView(R.layout.activity_usuarios);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        //String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre from votantes", null);
        if (fila.moveToNext()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                datos = new String[]{fila.getString(0)};

        } else
            Toast.makeText(this, "No existe una persona con dicho dni" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();

        lista = (ListView) findViewById(R.id.listView1);

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datos);

        lista.setAdapter(adaptador);

        final String[] finalDatos = datos;
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "posicion " + (i + 1) + finalDatos[i], Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        int cant = bd.delete("votantes", "dni=" + dni, null); // (votantes es la nombre de la tabla, condición)
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró la persona con dicho documento",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una persona con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromesa = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromesa", nromesa);
        int cant = bd.update("votantes", registro, "dni=" + dni, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe una persona con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }

    public void registros(View v) {

        ListView lista;
        String[] datos = null;
        setContentView(R.layout.activity_usuarios);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre from votantes", null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            datos = new String[]{fila.getString(1)};

        } else
            Toast.makeText(this, "No existe una persona con dicho dni" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();

        lista = (ListView) findViewById(R.id.listView1);

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datos);

        lista.setAdapter(adaptador);

        final String[] finalDatos = datos;
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "posicion " + (i + 1) + finalDatos[i], Toast.LENGTH_SHORT).show();
            }
        });

    }
}