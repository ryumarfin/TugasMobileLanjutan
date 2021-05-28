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
        //untuk mengupdate, kita akan menghapus table terlebih dahulu
        db?.execSQL("DROP TABLE IF EXISTS ${NoteDB.noteTable.TABLE_NOTE}")
        //kemudian panggil onCreate untuk membentuk table
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

    //membaca data Judul dari database
    fun tampilkanJudul():List<String>{
        //buat sebuah arrayList untuk menampung listjudul
        val listJudul = ArrayList<String>()
        //buat query untuk membaca data judul pada database
        val sql = "SELECT ${NoteDB.noteTable.COLUMN_JUDUL} FROM ${NoteDB.noteTable.TABLE_NOTE}"

        //gunakan readableDatabse untuk melakukan proses baca database
        val db = this.readableDatabase

        //inisialisasi cursor untuk membantu dalam membaca data dari database
        var cursor : Cursor?= null
        try{
            //cursor akan membaca dan menyimpan data judul dlam bentuk tabel
            cursor = db.rawQuery(sql, null)
        }catch (e: SQLException){
            return ArrayList()
        }
        //buat variable untuk menampung judul
        var judultemp = ""
        //pastikan cursor berada pada posisi awal
        if(cursor.moveToFirst()){
            //lakukan proses pembacaan hngga cursor berada diposisi akhir
            do{
                //cursor akan membaca data judul per baris dan simpan ke var judultemp
                judultemp = cursor.getString(cursor.getColumnIndex(NoteDB.noteTable.COLUMN_JUDUL))
                //kemudian kita tambahkan atua masukkn ke dalam listJudul
                listJudul.add(judultemp)
            } while (cursor.moveToNext()) //geser cursor ke baris selanjutnya
        }
        return listJudul
    }

    //membaca seluruh data di table
    fun tampilkanData():MutableList<Notes>{
        //buat sebuah arrayList untuk menampung seluruh data
        val listNote = ArrayList<Notes>()
        //buat query untuk membaca seluruh data pada database
        val sql = "SELECT * FROM ${NoteDB.noteTable.TABLE_NOTE}"

        //gunakan readableDatabse untuk melakukan proses baca database
        val db = this.readableDatabase
        //inisialisasi cursor untuk membantu dalam membaca data dari database
        var cursor : Cursor?= null
        try{
            //cursor akan membaca dan menyimpan data judul dlam bentuk tabel
            cursor = db.rawQuery(sql, null)
        }catch (e: SQLException){
            return ArrayList()
        }
        //pastikan cursor berada pada posisi awal
        if(cursor.moveToFirst()){
            //lakukan proses pembacaan hingga cursor berada diposisi akhir
            do{
                //buat var penampung
                var cttn = Notes()
                //cursor akan membaca data per baris
                cttn.id = cursor.getInt(cursor.getColumnIndex(NoteDB.noteTable.COLUMN_ID))
                cttn.judul = cursor.getString(cursor.getColumnIndex(NoteDB.noteTable.COLUMN_JUDUL))
                cttn.konten = cursor.getString(cursor.getColumnIndex(NoteDB.noteTable.COLUMN_KONTEN))
                //kemudian kita tambahkan atua masukkn ke dalam listNote
                listNote.add(cttn)
            } while (cursor.moveToNext()) // geser cursor ke baris selanjutnya
        }
        return listNote
    }

    //menghapus note
    fun hapusNote(judul : String){
        val db = this.writableDatabase
        //tentukan baris yg akan dihapus dari table berdasarkan judul
        val selection = "${NoteDB.noteTable.COLUMN_JUDUL} = ?"
        db.delete(NoteDB.noteTable.TABLE_NOTE, selection, arrayOf(judul))
    }

    //mengupdate isi konten
    fun ubahKonten(judul: String, isi:String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //kirimkan data baru yang ingin disimpan ke database
        contentValues.put(NoteDB.noteTable.COLUMN_KONTEN, isi)
        //lalu update data tersebut pada baris yg dtentukan
        val selection = "${NoteDB.noteTable.COLUMN_JUDUL} = ?"
        db.update(NoteDB.noteTable.TABLE_NOTE, contentValues, selection, arrayOf(judul))
    }
}