package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myfirstapp.model.Book;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BookDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS = "books";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";

    private static final String[] COLUMNS = {KEY_ID, KEY_TITLE, KEY_AUTHOR};

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "author TEXT )";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS books");
        this.onCreate(db);
    }

    public void addBook(Book book) {

        // for logging
        Log.d("addBook", book.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());

        // 3. insert
        db.insert(TABLE_BOOKS, null, values);

        // 4. close
        db.close();
    }

    public Book getBook(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        // build query
        Cursor cursor = db.query(TABLE_BOOKS,               // a. table
                COLUMNS,                                    // b. columns
                " id = ?",                          // c. selections
                new String[]{String.valueOf(id)},           // d. selectId
                null,                               // e. groupBy
                null,                                // f. having
                null,                               // g. order by
                null                                  // h. limit
        );

        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setTitle(cursor.getString(1));
        book.setAuthor(cursor.getString(2));

        // log
        Log.d("getBook(" + id + ")", book.toString());

        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }

    public int updateBook(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle()); // get title
        values.put("author", book.getAuthor()); // get author

        // 3. updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(book.getId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    public void deleteBook(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(book.getId()) }); //selections args

        // 3. close
        db.close();

        //log
    Log.d("deleteBook", book.toString());

    }
}
