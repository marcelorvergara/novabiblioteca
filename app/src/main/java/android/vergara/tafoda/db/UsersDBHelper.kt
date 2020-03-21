package android.vergara.tafoda.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.*
import android.vergara.tafoda.Model.User
import android.vergara.tafoda.frags.CadastroFrag

class UsersDBHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
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
    fun insertUser(user: User): Boolean{
        //abrir o repositório de dados em modo de escrita
        val db = writableDatabase
        //criar um novo mapa de valores onde o nome das colunas são as keys
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_USER_ID,user.userid)
        values.put(DBContract.UserEntry.COLUMN_NAME,user.nome)
        values.put(DBContract.UserEntry.COLUMN_PASS, user.pass)

        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME,null,values)
        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser(userid: String): Boolean{
        //acionar o repositório de dados em modo escrita
        val db = writableDatabase
        //definir a cláusula where
        val selection = DBContract.UserEntry.COLUMN_USER_ID + " LIKE ?"
        //especificar a ordem dos argumentos
        val selectionArgs = arrayOf(userid)
        //Realizar o comando SQL
        db.delete(DBContract.UserEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun readUser(name: String): ArrayList<User>{
        val users = ArrayList<User>()
        val db = writableDatabase
        var cursor : Cursor? = null
        try{
            cursor =db.rawQuery("Select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.COLUMN_NAME + "='" + name + "'", null)

        }catch (e: SQLiteException){
            //se a tabela ainda não existe, cria-la
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var userid: String
        var name: String
        var password : String
        if(cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){
                userid = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                password = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PASS))

                users.add(User(userid,name,password))
                cursor.moveToNext()
            }

        }
        return users
    }

    fun readPass(name: String): String?{
        val users = ArrayList<User>()
        val db = writableDatabase
        var cursor : Cursor? = null
        try{
            cursor =db.rawQuery("Select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.COLUMN_NAME + "='" + name + "'", null)

        }catch (e: SQLiteException){
            //se a tabela ainda não existe, cria-la
            db.execSQL(SQL_CREATE_ENTRIES)
            return "Not found?"
        }
        var userid: String
        var name: String
        var password : String? = null
        if(cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){
                userid = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                password = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PASS))

                users.add(User(userid,name,password))
                cursor.moveToNext()
            }

        }
        return password
    }

    fun readAllUsers(): ArrayList<User>{
        val users = ArrayList<User>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("Select * from "+ DBContract.UserEntry.TABLE_NAME,null)

        }catch (e: SQLException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var userid:String
        var user: String
        var pass: String
        if(cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){
                userid = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                user = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                pass = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PASS))

            users.add(User(userid,user,pass))
            cursor.moveToNext()
            }
        }
    return users
    }

    companion object{
        //se o esquema do banco mudar, devemos incrementar a versão abaixo
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " ("+
                DBContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                DBContract.UserEntry.COLUMN_NAME + " TEXT," +
                DBContract.UserEntry.COLUMN_PASS + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }

}