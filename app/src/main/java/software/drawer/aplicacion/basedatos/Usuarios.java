package software.drawer.aplicacion.basedatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Usuarios extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            datos = new String[]{
                    fila.getString(0)
            };

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

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_sesion) {
            Intent i = new Intent(this, Login.class );
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
