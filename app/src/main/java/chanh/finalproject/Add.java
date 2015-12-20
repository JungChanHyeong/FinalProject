package chanh.finalproject;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Add extends Activity {

    static DataBaseOpen dataBaseOpen;
    static SQLiteDatabase db;

    static TextView textViewDate;
    static TextView textViewTime;
    static TextView textViewPosition;

    static Button reset;

    // stroy 변수
    static EditText editTextLife;
    static EditText editTextIssue;

    String storyLife;
    String storyIssue;

    // 추가 버튼 변수
    static Button btnLife;
    static Button btnIssue;

    // 카테고리 변수
    static Spinner spinnerLife;
    static Spinner spinnerIssue;

    String categoryLife;
    String categoryIssue;

    // 현재 날짜 시간 구하기
    long now = System.currentTimeMillis();
    Date date = new Date(now);

    // 날짜 시간 포맷 지정
    SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    SimpleDateFormat CurTimeFormat = new SimpleDateFormat("HH시 mm분");
    String strCurDate = CurDateFormat.format(date);
    String strCurTime = CurTimeFormat.format(date);

    // 현재 위치 구하기
    private MyLocation myLocation;

    double latitude = 0;
    double longitude = 0;
    String address = "주소를 찾는 중 입니다.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dataBaseOpen = new DataBaseOpen(this);
        db = dataBaseOpen.getWritableDatabase();

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewPosition = (TextView) findViewById(R.id.textViewPosition);

        reset = (Button) findViewById(R.id.reset);

        // 현재 시간 세팅
        textViewDate.setText(strCurDate);
        textViewTime.setText(strCurTime);

        // 현재 위치 세팅
        myLocation = new MyLocation(Add.this);
        if (myLocation.isGetLocation()) {
            latitude = myLocation.getLatitude();
            longitude = myLocation.getLongitude();

            textViewPosition.setText(getAddress(latitude, longitude));
        } else {
            // GPS 미사용시
            textViewPosition.setText("GPS 를 켜주세요.");
            myLocation.showSettingsAlert();
        }

        // 위치 갱신
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now = System.currentTimeMillis();
                date = new Date(now);
                strCurTime = CurTimeFormat.format(date);
                strCurDate = CurDateFormat.format(date);
                textViewTime.setText(strCurTime);
                textViewDate.setText(strCurDate);

                myLocation = new MyLocation(Add.this);
                if (myLocation.isGetLocation()) {
                    latitude = myLocation.getLatitude();
                    longitude = myLocation.getLongitude();

                    textViewPosition.setText(getAddress(latitude, longitude));
                } else {
                    latitude = 0;
                    longitude = 0;
                    textViewPosition.setText("GPS 를 켜주세요.");
                    myLocation.showSettingsAlert();
                }
            }
        });

        // spinner 설정
        spinnerLife = (Spinner) findViewById(R.id.spinnerLife);
        ArrayAdapter<CharSequence> adapterLife = ArrayAdapter.createFromResource(
                this, R.array.life_array, R.layout.spinner_item);
        adapterLife.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLife.setAdapter(adapterLife);

        spinnerIssue = (Spinner) findViewById(R.id.spinnerIssue);
        ArrayAdapter<CharSequence> adapterIssue = ArrayAdapter.createFromResource(
                this, R.array.issue_array, R.layout.spinner_item);
        adapterIssue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIssue.setAdapter(adapterIssue);

        spinnerLife.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryLife = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerIssue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryIssue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 일상 이슈 입력 설정
        editTextLife = (EditText) findViewById(R.id.editTextLife);
        editTextIssue = (EditText) findViewById(R.id.editTextIssue);

        // 일상 이슈 db에 추가
        btnLife = (Button) findViewById(R.id.btnLife);
        btnIssue = (Button) findViewById(R.id.btnIssue);

        btnLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyLife = editTextLife.getText().toString();
                if (latitude == 0 || longitude == 0) {
                    Toast.makeText(getApplicationContext(), "GPS 를 켜고 데이터 갱신을 해주세요.", Toast.LENGTH_SHORT).show();
                } else if (categoryLife.equalsIgnoreCase("일상 선택하세요")) {
                    Toast.makeText(getApplicationContext(), "일상 카테고리에서 분류를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (storyLife.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "일상 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(strCurDate, strCurTime, getAddress(latitude, longitude), latitude, longitude, "일상", categoryLife, storyLife);
                    Toast.makeText(getApplicationContext(), "일상을 추가했습니다!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btnIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyIssue = editTextIssue.getText().toString();
                if (latitude == 0 || longitude == 0) {
                    Toast.makeText(getApplicationContext(), "GPS 를 켜고 데이터 갱신을 해주세요.", Toast.LENGTH_SHORT).show();
                } else if (categoryIssue.equalsIgnoreCase("이슈 선택하세요")) {
                    Toast.makeText(getApplicationContext(), "이슈 카테고리에서 분류를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (storyIssue.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "이슈 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(strCurDate, strCurTime, getAddress(latitude, longitude), latitude, longitude, "이슈", categoryIssue, storyIssue);
                    Toast.makeText(getApplicationContext(), "이슈를 추가했습니다!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void onClickBack(View v) {
        finish();
    }

    public String getAddress(double latitude, double longitude) {
        String str = "주소를 찾는 중 입니다.";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> list;
        try {
            if (geocoder != null) {
                list = geocoder.getFromLocation(latitude, longitude, 1);
                if (list != null && list.size() > 0) {
                    str = list.get(0).getAddressLine(0).toString();
                }
                return str;
            }
        } catch (IOException e) {
            return str;
        }
        return str;
    }

    public void insertData(String date, String time, String position, double pointX, double pointY, String bigCategory, String smallCategory, String story) {
        db.execSQL("INSERT INTO db_table "
                + "VALUES(NULL, '" + date
                + "', '" + time
                + "', '" + position
                + "', " + pointX
                + ", " + pointY
                + ", '" + bigCategory
                + "', '" + smallCategory
                + "', '" + story
                + "');");
    }
}
