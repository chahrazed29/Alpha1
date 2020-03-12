package com.example.alpha;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class IndividueDAO {

    public static final String TAG = "IndividueDAO";

    private Context mContext;

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;

    private String[] mAllColumns = { DatabaseHelper.INDIVIDUE_ID_NUM , DatabaseHelper.INDIVIDUE_USER_NAME , DatabaseHelper.INDIVIDUE_PHONE ,
            DatabaseHelper.INDIVIDUE_EMAIL , DatabaseHelper.INDIVIDUE_PASS_WORD};

    public IndividueDAO(Context context){

        mDbHelper = new DatabaseHelper(context);
        this.mContext = context;

        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Individue createIndividue( String username, long phone, String email, String password){

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.INDIVIDUE_USER_NAME,username);
        values.put(DatabaseHelper.INDIVIDUE_PHONE,phone);
        values.put(DatabaseHelper.INDIVIDUE_EMAIL,email);
        values.put(DatabaseHelper.INDIVIDUE_PASS_WORD,password);
        long insertId = mDatabase
                .insert(DatabaseHelper.INDIVIDUE_TABLE, null, values);
        Cursor cursor = mDatabase.query(DatabaseHelper.INDIVIDUE_TABLE, mAllColumns,
                DatabaseHelper.INDIVIDUE_ID_NUM + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Individue newIndividue = cursorToIndividue(cursor);
        cursor.close();
        return newIndividue;
    }

    public void deleteIndividue(Individue individue) {
        long id = individue.getIdnum();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(DatabaseHelper.INDIVIDUE_TABLE, DatabaseHelper.INDIVIDUE_ID_NUM
                + " = " + id, null);
    }

    public List<Individue> getAllIndividues() {
        List<Individue> listIndividues = new ArrayList<Individue>();

        Cursor cursor = mDatabase.query(DatabaseHelper.INDIVIDUE_TABLE, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Individue individue = cursorToIndividue(cursor);
            listIndividues.add(individue);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listIndividues;
    }

    private Individue cursorToIndividue(Cursor cursor) {
        Individue individue = new Individue();
        individue.setIdnum(cursor.getLong(0));
        individue.setUsername(cursor.getString(1));
        individue.setPhone(cursor.getLong(2));
        individue.setEmail(cursor.getString(3));
        individue.setPassword(cursor.getString(4));


        return individue;
    }

}
