package com.example.myfirstapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.MySQLiteHelper;
import com.example.myfirstapp.model.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NOME = "com.example.myfirstapp.NOME";
    public static final String EXTRA_COGNOME = "com.example.myfirstapp.COGNOME";
    public static final String EXTRA_TELEFONO = "com.example.myfirstapp.TELEFONO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MySQLiteHelper db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Books
        db.addBook(new Book("Android Application Development Cookbook", "Wei Meng Lee"));
        db.addBook(new Book("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips and Brian Hardy"));
        db.addBook(new Book("Learn Android App Development", "Wallace Jackson"));

        // get all books
        List<Book> list = db.getAllBooks();

        // delete one book
        db.deleteBook(list.get(0));

        // get all books
        db.getAllBooks();
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editNome = (EditText) findViewById(R.id.nome);
        EditText editCognome = (EditText) findViewById(R.id.cognome);
        EditText editTelefono = (EditText) findViewById(R.id.telefono);
        String nome = editNome.getText().toString();
        String cognome = editCognome.getText().toString();
        String telefono = editTelefono.getText().toString();
        intent.putExtra(EXTRA_NOME,nome);
        intent.putExtra(EXTRA_COGNOME,cognome);
        intent.putExtra(EXTRA_TELEFONO,telefono);
        startActivity(intent);
    }
}
