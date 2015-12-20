package chanh.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpen extends SQLiteOpenHelper {
    public DataBaseOpen(Context context) {
        super(context, "db_table", null, 1);
    }

    // 최초 실행시 Data Base 한번만 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE db_table"
                + "(id integer primary key autoincrement, "
                + "date TEXT, "
                + "time TEXT, "
                + "position TEXT, "
                + "pointX REAL, "
                + "pointY REAL, "
                + "bigCategory TEXT, "
                + "smallCategory TEXT, "
                + "story TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS db_table");
        onCreate(db);
    }
}
