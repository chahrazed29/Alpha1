package com.example.alpha;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EntrepriseDAO {
    public static final String TAG = "EntrepriseDAO";

    private Context mContext;

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;

    private String[] mAllColumns = { DatabaseHelper.ENTREPRISE_ID_NUM , DatabaseHelper.ENTREPRISE_USER_NAME , DatabaseHelper.INDIVIDUE_PHONE ,
            DatabaseHelper.ENTREPRISE_NRC , DatabaseHelper.ENTREPRISE_EMAIL , DatabaseHelper.ENTREPRISE_PASS_WORD};

    public EntrepriseDAO(Context context){

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

    public Entreprise createEntreprise( String username, long phone,long numrc , String email, String password){

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.ENTREPRISE_USER_NAME,username);
        values.put(DatabaseHelper.ENTREPRISE_PHONE,phone);
        values.put(DatabaseHelper.ENTREPRISE_NUMRC,email);
        values.put(DatabaseHelper.ENTREPRISE_EMAIL,password);
        values.put(DatabaseHelper.ENTREPRISE_PASS_WORD,password);
        long insertId = mDatabase
                .insert(DatabaseHelper.ENTREPRISE_TABLE, null, values);
        Cursor cursor = mDatabase.query(DatabaseHelper.ENTREPRISE_TABLE, mAllColumns,
                DatabaseHelper.ENTREPRISE_ID_NUM+ " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Entreprise newEntreprise = cursorToEntreprise(cursor);
        cursor.close();
        return newEntreprise;
    }

    public void deleteEntreprise(Entreprise entreprise) {
        long id = entreprise.getIdnum();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(DatabaseHelper.ENTREPRISE_TABLE, DatabaseHelper.ENTREPRISE_ID_NUM
                + " = " + id, null);
    }

    public List<Entreprise> getAllEntreprises() {
        List<Entreprise> listEntreprises = new ArrayList<Entreprise>();

        Cursor cursor = mDatabase.query(DatabaseHelper.ENTREPRISE_TABLE, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Entreprise entreprise = cursorToEntreprise(cursor);
            listEntreprises.add(entreprise);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listEntreprises;
    }

    private Entreprise cursorToEntreprise(Cursor cursor) {
        Entreprise entreprise = new Entreprise();
        entreprise.setIdnum(cursor.getLong(0));
        entreprise.setUsername(cursor.getString(1));
        entreprise.setPhone(cursor.getLong(2));
        entreprise.setNumrc(cursor.getLong(3));
        entreprise.setEmail(cursor.getString(4));
        entreprise.setPassword(cursor.getString(5));


        return entreprise;
    }

}
