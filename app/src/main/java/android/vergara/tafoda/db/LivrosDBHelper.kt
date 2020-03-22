package android.vergara.tafoda.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.vergara.tafoda.Model.Note

class LivrosDBHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //dados somente online. Política de upgrade é descartar dados e iniciar novamente
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    @Throws(SQLiteConstraintException::class)
    fun insertLivro(livro: Note): Boolean{
        //abrir o repositório de dados em modo de escrita
        val db = writableDatabase
        //criar um novo mapa de valores onde o nome das colunas são as keys
        val values = ContentValues()
        values.put(DBContractLivros.LivroEntry.COLUMN_TITLE,livro.title)
        values.put(DBContractLivros.LivroEntry.COLUMN_DESC,livro.description)
        values.put(DBContractLivros.LivroEntry.COLUMN_AUTOR, livro.autor)
         values.put(DBContractLivros.LivroEntry.COLUMN_RESUMO, livro.resumo)
        values.put(DBContractLivros.LivroEntry.COLUMN_PAGINAS, livro.paginas)
        values.put(DBContractLivros.LivroEntry.COLUMN_IND, livro.ind)

        val newRowId = db.insert(DBContractLivros.LivroEntry.TABLE_LIVROS,null,values)
        return true
    }

    fun readLivros(livro: String): ArrayList<Note>{
        val livros = ArrayList<Note>()
        val db = writableDatabase
        var cursor : Cursor? = null
        try{
            cursor =db.rawQuery("Select * from " + DBContractLivros.LivroEntry.TABLE_LIVROS + " WHERE " + DBContractLivros.LivroEntry.COLUMN_TITLE + "='" + livro + "'", null)


        }catch (e: SQLiteException){
            //se a tabela ainda não existe, cria-la
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var title: String
        var description: String
        var autor: String
        var resumo: String
        var paginas: String
        var ind: String
        if(cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){
                title = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_TITLE))
                description = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_DESC))
                autor = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_AUTOR))
                resumo = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_RESUMO))
                paginas = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_PAGINAS))
                ind = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_IND))

                livros.add(Note(title,description,autor,resumo,paginas,ind))
                cursor.moveToNext()
            }

        }
        return livros
    }

    fun readAllLivros(): ArrayList<Note> {
        val livros = ArrayList<Note>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContractLivros.LivroEntry.TABLE_LIVROS, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var title: String
        var description: String
        var autor: String
        var resumo: String
        var paginas: String
        var ind: String
        if(cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){
                title = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_TITLE))
                description = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_DESC))
                autor = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_AUTOR))
                resumo = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_RESUMO))
                paginas = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_PAGINAS))
                ind = cursor.getString(cursor.getColumnIndex(DBContractLivros.LivroEntry.COLUMN_IND))

                livros.add(Note(title,description,autor,resumo,paginas,ind))
                cursor.moveToNext()
            }

        }
        return livros
    }

    companion object{
        //se o esquema do banco mudar, devemos incrementar a versão abaixo
        val DATABASE_VERSION = 18
        val DATABASE_NAME = "FeedReader.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContractLivros.LivroEntry.TABLE_LIVROS + " ("+
                DBContractLivros.LivroEntry.COLUMN_TITLE + " TEXT," +
                DBContractLivros.LivroEntry.COLUMN_DESC + " TEXT," +
                DBContractLivros.LivroEntry.COLUMN_AUTOR + " TEXT," +
                DBContractLivros.LivroEntry.COLUMN_RESUMO + " TEXT," +
                DBContractLivros.LivroEntry.COLUMN_PAGINAS + " TEXT," +
                DBContractLivros.LivroEntry.COLUMN_IND + " TEXT)"
                //DBContractLivros.LivroEntry.COLUMN_INDEX + " INT PRIMARY KEY)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContractLivros.LivroEntry.TABLE_LIVROS
    }
}