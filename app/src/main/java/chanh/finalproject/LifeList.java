package chanh.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class LifeList extends Activity {

    static DataBaseOpen dataBaseOpen;
    static SQLiteDatabase db;

    ListView listView;
    MyAdapter adapter;
    ArrayList<MyData> arrayList;

    static Spinner spinner;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_list);

        dataBaseOpen = new DataBaseOpen(this);
        db = dataBaseOpen.getWritableDatabase();

        spinner = (Spinner) findViewById(R.id.spinnerL);
        ArrayAdapter<CharSequence> adapterLife = ArrayAdapter.createFromResource(
                this, R.array.life_list_array, R.layout.spinner_item);
        adapterLife.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterLife);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
                if (category.equalsIgnoreCase("일상 모두 보기")) {
                    selectBigCategory("일상");
                } else {
                    selectSmallCategory(category);
                }
                adapter = new MyAdapter(LifeList.this, arrayList);

                listView = (ListView) findViewById(R.id.listViewLife);
                listView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onClickBack(View v) {
        finish();
    }

    // 일상 모든 데이터 읽기
    public void selectBigCategory(String category) {
        arrayList = new ArrayList<MyData>();
        String sql = "select * from db_table where bigCategory = '" + category + "';";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            String date = results.getString(1);
            String time = results.getString(2);
            String position = results.getString(3);
            double pointX = results.getDouble(4);
            double pointY = results.getDouble(5);
            String bigCategory =  results.getString(6);
            String smallCategory = results.getString(7);
            String story = results.getString(8);

            arrayList.add(new MyData(date, time, position, pointX, pointY, bigCategory, smallCategory, story));
            results.moveToNext();
        }
        results.close();
    }

    // 일상 분야별 읽어오기
    public void selectSmallCategory(String category) {
        arrayList = new ArrayList<MyData>();
        String sql = "select * from db_table where smallCategory = '" + category + "';";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            String date = results.getString(1);
            String time = results.getString(2);
            String position = results.getString(3);
            double pointX = results.getDouble(4);
            double pointY = results.getDouble(5);
            String bigCategory =  results.getString(6);
            String smallCategory = results.getString(7);
            String story = results.getString(8);

            arrayList.add(new MyData(date, time, position, pointX, pointY, bigCategory, smallCategory, story));
            results.moveToNext();
        }
        results.close();
    }

    public void mapView(double x, double y) {
//        Uri uri = Uri.parse("geo:37.4944732,127.05413818333334");
        Uri uri = Uri.parse("geo:" + Double.toString(x) + "," + Double.toString(y));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }
}
