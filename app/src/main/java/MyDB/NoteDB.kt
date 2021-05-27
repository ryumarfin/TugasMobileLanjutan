package MyDB

import android.provider.BaseColumns

//buat sebuah object untuk menampung nama table dan column dari database
object NoteDB{
    //lalu buat sebuah class untuk merepresentasikan table dan kolom dari
    // database dengan mengimplementasikan BaseColumns agar ketika kita
    // membuat column untuk table, semua nama sudah tersimpan dalam object NoteDB
    class noteTable : BaseColumns{
        companion object{
            val TABLE_NOTE = "note"
            val COLUMN_ID : String = "note_id"
            val COLUMN_JUDUL : String = "judul"
            val COLUMN_KONTEN : String ="konten"
        }
    }
}