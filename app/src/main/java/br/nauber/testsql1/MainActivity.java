package br.nauber.testsql1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE="sql-lite-test-db1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase=getBaseContext().openOrCreateDatabase(DATABASE,MODE_PRIVATE,null);


        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS  contact (name TEXT, phone INTEGER, email TEXT)");
        sqLiteDatabase.execSQL("insert into  contact values ('Joao',32222222,'teste@teste.com')");
        sqLiteDatabase.execSQL("insert into  contact values ('Pedro',32222222,'teste@teste.com')");
        sqLiteDatabase.execSQL("insert into  contact values ('Antonio',32222222,'teste@teste.com')");
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from contact ",null);
        Log.d("SQL", "before if");
        if(cursor.moveToFirst()){
            do {
                Log.d("SQL", "in if");
                String name = cursor.getString(0);
                int phone = cursor.getInt(1);
                String email = cursor.getString(2);
                Toast.makeText(getApplicationContext(), "Name " + name + " Phone " + phone + " Email " + email, Toast.LENGTH_LONG).show();
            }while (cursor.moveToNext());
        }
        else{
            Toast.makeText(getApplicationContext(),"Erro retrieving data ",Toast.LENGTH_LONG).show();
        }
        sqLiteDatabase.close();
    }
}
