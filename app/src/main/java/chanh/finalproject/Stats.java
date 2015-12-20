package chanh.finalproject;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Stats extends Activity {

    static DataBaseOpen dataBaseOpen;
    static SQLiteDatabase db;

    TextView t0;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    TextView t9;

    TextView v0;
    TextView v1;
    TextView v2;
    TextView v3;
    TextView v4;
    TextView v5;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        dataBaseOpen = new DataBaseOpen(this);
        db = dataBaseOpen.getWritableDatabase();

        t0 = (TextView) findViewById(R.id.t0);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        t6 = (TextView) findViewById(R.id.t6);
        t7 = (TextView) findViewById(R.id.t7);
        t8 = (TextView) findViewById(R.id.t8);
        t9 = (TextView) findViewById(R.id.t9);

        v0 = (TextView) findViewById(R.id.v0);
        v1 = (TextView) findViewById(R.id.v1);
        v2 = (TextView) findViewById(R.id.v2);
        v3 = (TextView) findViewById(R.id.v3);
        v4 = (TextView) findViewById(R.id.v4);
        v5 = (TextView) findViewById(R.id.v5);

        selectBigCategory("일상");
        t0.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("게임");
        t1.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("공부");
        t2.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("독서");
        t3.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("쇼핑");
        t4.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("여행");
        t5.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("영화");
        t6.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("운동");
        t7.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("휴식");
        t8.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("기타");
        t9.setText(Integer.toString(count));
        count = 0;

        selectBigCategory("이슈");
        v0.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("교통 사고");
        v1.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("유명인 포착");
        v2.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("자연 재해");
        v3.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("화재");
        v4.setText(Integer.toString(count));
        count = 0;
        selectSmallCategory("기타");
        v5.setText(Integer.toString(count));
        count = 0;
    }

    public void onClickBack(View v) {
        finish();
    }

    // 일상 이슈 데이터 카운트
    public void selectBigCategory(String category) {
        String sql = "select * from db_table where bigCategory = '" + category + "';";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            count++;
            results.moveToNext();
        }
        results.close();
    }

    // 분야별 데이터 카운트
    public void selectSmallCategory(String category) {
        String sql = "select * from db_table where smallCategory = '" + category + "';";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            count++;
            results.moveToNext();
        }
        results.close();
    }
}
