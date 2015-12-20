package chanh.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    static DataBaseOpen dataBaseOpen;
    static SQLiteDatabase db;

    private BackPressCloseSystem backPressCloseSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseOpen = new DataBaseOpen(this);
        db = dataBaseOpen.getReadableDatabase();

        backPressCloseSystem = new BackPressCloseSystem(this);
    }

    public void onClickMainImage(View v) {
        Intent intent = new Intent(this, SubMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
}
