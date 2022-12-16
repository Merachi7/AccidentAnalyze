package org.merachi7.accidentanalyze.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.merachi7.accidentanalyze.data.AccidentData;

import java.util.ArrayList;
import java.util.List;

public class NotesDbAdapter {
    public static final String KEY_CONSTRUCTION_SPECIES_BIG = "construction_species_big";
    public static final String KEY_CONSTRUCTION_SPECIES_MEDIUM = "construction_species_medium";
    public static final String KEY_FACILITY_BIG = "facility_big";
    public static final String KEY_FACILITY_MEDIUM = "facility_medium";
    public static final String KEY_FACILITY_SMALL = "facility_small";
    public static final String KEY_HAZARD_OBJECTION_BIG = "hazard_object_big";
    public static final String KEY_HAZARD_OBJECTION_MEDIUM = "hazard_object_medium";
    public static final String KEY_HAZARD_POSITION_BIG = "hazard_object_position_big";
    public static final String KEY_HAZARD_POSITION_MEDIUM = "hazard_object_position_medium";
    public static final String KEY_HAZARD_POSITION_CODE_MEDIUM = "hazard_object_position_medium";
    public static final String KEY_HAZARD_POSITION_SMALL = "hazard_object_position";
    public static final String KEY_PROCESS = "process";
    public static final String KEY_DAMAGE_MATERIAL = "damage_material";
    public static final String KEY_DAMAGE_HUMAN = "damage_human";
    public static final String KEY_ACCIDENT_REASON = "accident_reason";
    public static final String KEY_POSSIBILITY = "possibility";
    public static final String KEY_SEVERITY = "severity";
    public static final String KEY_METHOD_DESIGN = "method_design";
    public static final String KEY_METHOD_CONSTRUCTION = "method_construction";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "NotesDbAdapter";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     *
     * Database creation sql statement
     */

    private static final String DATABASE_CREATE = "create table notes (_id integer primary key autoincrement, "
            + "title text not null, body text not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 2;
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    public NotesDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public NotesDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createNote(AccidentData data) {
        ContentValues args = new ContentValues();
        args.put(KEY_CONSTRUCTION_SPECIES_BIG, data.getConstruction_species_big());
        args.put(KEY_CONSTRUCTION_SPECIES_MEDIUM, data.getConstruction_species_medium());
        args.put(KEY_FACILITY_BIG, data.getFacility_big());
        args.put(KEY_FACILITY_MEDIUM, data.getFacility_medium());
        args.put(KEY_FACILITY_SMALL, data.getFacility_small());
        args.put(KEY_HAZARD_OBJECTION_BIG, data.getHazard_object_big());
        args.put(KEY_HAZARD_OBJECTION_MEDIUM, data.getHazard_object_medium());
        args.put(KEY_HAZARD_POSITION_BIG, data.getHazard_position_big());
        args.put(KEY_HAZARD_POSITION_MEDIUM, data.getHazard_position_medium());
        args.put(KEY_HAZARD_POSITION_SMALL, data.getHazard_position_small());
        args.put(KEY_HAZARD_POSITION_CODE_MEDIUM, data.getHazard_position_code_medium());
        args.put(KEY_PROCESS, data.getProcess());
        args.put(KEY_DAMAGE_MATERIAL, data.getDamage_material());
        args.put(KEY_DAMAGE_HUMAN, data.getDamage_human());
        args.put(KEY_ACCIDENT_REASON, data.getAccident_reason());
        args.put(KEY_POSSIBILITY, data.getPossibility());
        args.put(KEY_SEVERITY, data.getSeverity());
        args.put(KEY_METHOD_DESIGN, data.getMethod_design());
        args.put(KEY_METHOD_CONSTRUCTION, data.getMethod_construction_());
        return mDb.insert(DATABASE_TABLE, null, args);
    }

    public boolean deleteNote(long rowId) {
        Log.i("Delete called", "value__" + rowId);
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor fetchAllNotes() {
        return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CONSTRUCTION_SPECIES_BIG, KEY_CONSTRUCTION_SPECIES_MEDIUM, KEY_FACILITY_BIG,KEY_FACILITY_MEDIUM, KEY_FACILITY_SMALL, KEY_HAZARD_OBJECTION_BIG,KEY_HAZARD_OBJECTION_MEDIUM, KEY_HAZARD_POSITION_BIG,KEY_HAZARD_POSITION_MEDIUM,KEY_HAZARD_POSITION_SMALL, KEY_HAZARD_POSITION_CODE_MEDIUM,KEY_PROCESS,KEY_DAMAGE_MATERIAL, KEY_DAMAGE_HUMAN,KEY_ACCIDENT_REASON, KEY_POSSIBILITY, KEY_SEVERITY, KEY_METHOD_DESIGN, KEY_METHOD_CONSTRUCTION}, null, null, null, null, null);
    }

    public Cursor fetchNote(long rowId) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CONSTRUCTION_SPECIES_BIG, KEY_CONSTRUCTION_SPECIES_MEDIUM, KEY_FACILITY_BIG,KEY_FACILITY_MEDIUM, KEY_FACILITY_SMALL, KEY_HAZARD_OBJECTION_BIG,KEY_HAZARD_OBJECTION_MEDIUM, KEY_HAZARD_POSITION_BIG,KEY_HAZARD_POSITION_MEDIUM,KEY_HAZARD_POSITION_SMALL, KEY_HAZARD_POSITION_CODE_MEDIUM,KEY_PROCESS,KEY_DAMAGE_MATERIAL, KEY_DAMAGE_HUMAN,KEY_ACCIDENT_REASON, KEY_POSSIBILITY, KEY_SEVERITY, KEY_METHOD_DESIGN, KEY_METHOD_CONSTRUCTION }, KEY_ROWID
                + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateNote(long rowId, AccidentData data) {
        ContentValues args = new ContentValues();
        args.put(KEY_CONSTRUCTION_SPECIES_BIG, data.getConstruction_species_big());
        args.put(KEY_CONSTRUCTION_SPECIES_MEDIUM, data.getConstruction_species_medium());
        args.put(KEY_FACILITY_BIG, data.getFacility_big());
        args.put(KEY_FACILITY_MEDIUM, data.getFacility_medium());
        args.put(KEY_FACILITY_SMALL, data.getFacility_small());
        args.put(KEY_HAZARD_OBJECTION_BIG, data.getHazard_object_big());
        args.put(KEY_HAZARD_OBJECTION_MEDIUM, data.getHazard_object_medium());
        args.put(KEY_HAZARD_POSITION_BIG, data.getHazard_position_big());
        args.put(KEY_HAZARD_POSITION_MEDIUM, data.getHazard_position_medium());
        args.put(KEY_HAZARD_POSITION_SMALL, data.getHazard_position_small());
        args.put(KEY_HAZARD_POSITION_CODE_MEDIUM, data.getHazard_position_code_medium());
        args.put(KEY_PROCESS, data.getProcess());
        args.put(KEY_DAMAGE_MATERIAL, data.getDamage_material());
        args.put(KEY_DAMAGE_HUMAN, data.getDamage_human());
        args.put(KEY_ACCIDENT_REASON, data.getAccident_reason());
        args.put(KEY_POSSIBILITY, data.getPossibility());
        args.put(KEY_SEVERITY, data.getSeverity());
        args.put(KEY_METHOD_DESIGN, data.getMethod_design());
        args.put(KEY_METHOD_CONSTRUCTION, data.getMethod_construction_());
        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public void insertAllData(List<String[]> data, NotesDbAdapter dbAdapter){
        List<AccidentData> dataList = new ArrayList<AccidentData>();

        for(String[] i : data) {
            AccidentData new_data = new AccidentData();
            new_data.setConstruction_species_big(i[3]);
            new_data.setConstruction_species_medium(i[4]);
            new_data.setFacility_big(i[0]);
            new_data.setFacility_medium(i[1]);
            new_data.setFacility_small(i[2]);
            new_data.setHazard_object_big(i[5]);
            new_data.setHazard_object_medium(i[6]);
            new_data.setHazard_position_big(i[7]);
            new_data.setHazard_position_medium(i[9]);
            new_data.setHazard_position_small(i[10]);
            new_data.setHazard_position_code_medium(i[8]);
            new_data.setProcess(i[11]);
            new_data.setDamage_material(i[12]);
            new_data.setDamage_human(i[13]);
            new_data.setAccident_reason(i[14]);
            new_data.setPossibility(i[15]);
            new_data.setSeverity(i[16]);
            new_data.setMethod_design(i[17]);
            new_data.setMethod_construction_(i[18]);
            dataList.add(new_data);
        }

        for(AccidentData i : dataList){
            dbAdapter.createNote(i);
        }
    }
}