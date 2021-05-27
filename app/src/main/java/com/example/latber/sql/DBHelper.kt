package com.example.latber.sql

import MyDB.NoteDB
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.latber.data.Notes

//implementasi SQLiteOpenHelper untuk membantu kita dalam mengelola database nantinya
class DBHelper(context : Context) : SQLiteOpenHelper(
        context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        //definisikan nama database
        private val DATABASE_NAME = "myNote.db"
        //definisikan versi database
        private val DATABASE_VERSION = 1
    }

    //onCreate digunakan kita pertama kali memanggil database
    override fun onCreate(db: SQLiteDatabase?) {
        //buat perintah sql ntuk membuat table
        var CREATE_NOTE_TABLE = ("CREATE TABLE ${NoteDB.noteTable.TABLE_NOTE} " +
                "(${NoteDB.noteTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${NoteDB.noteTable.COLUMN_JUDUL} TEXT," +
                "${NoteDB.noteTable.COLUMN_KONTEN} TEXT)")
        //lalu jalankan perintah sql nya
        db!!.execSQL(CREATE_NOTE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${NoteDB.noteTable.TABLE_NOTE}")
        onCreate(db)
    }

    //buat method baru untuk menambahkan note baru
    fun tambahNote(note : Notes) : Long{
        //untuk menulis data, gunakan writeableDatabase
        val db = this.writableDatabase
        //tentukan data yang akan diisi ke dalam table
        val contentValues = ContentValues().apply {
            put(NoteDB.noteTable.COLUMN_JUDUL, note.judul)
            put(NoteDB.noteTable.COLUMN_KONTEN, note.konten)
        }
        //masukkan data ke table dengan menggunakan perintah insert.
        //tampung hasil insert yg akan mengembalikan Long untuk dilakukan pengecekan
        //apakah proses insert berhasil dijalankan
        val success = db.insert(MyDB.NoteDB.noteTable.TABLE_NOTE,
                null, contentValues)
        //tutup database setelah proses selesai
        db.close()
        //kembalikan nilai success
        return success
    }

    fun tampilkanData():List<String>{
        val listJudul = ArrayList<String>()
        val judul = "SELECT ${NoteDB.noteTable.COLUMN_JUDUL} FROM ${NoteDB.noteTable.TABLE_NOTE}"

        val db = this.readableDatabase

        var cursor : Cursor?= null
        try{
            cursor = db.rawQuery(judul, null)
        }catch (e: SQLException){
            return ArrayList()
        }
        var judultemp = ""
        if(cursor.moveToFirst()){
            do{
                judultemp = cursor.getString(cursor.getColumnIndex(NoteDB.noteTable.COLUMN_JUDUL))
                listJudul.add(judultemp)
            } while (cursor.moveToNext())
        }
        return listJudul
    }
}