package chanh.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Help extends Activity {

    DataBaseOpen dataBaseOpen;
    SQLiteDatabase db;

    static TextView textViewLife;
    static TextView textViewIssue;
    static TextView textViewAdd;
    static TextView textViewStats;

    static Button btnResetData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        dataBaseOpen = new DataBaseOpen(this);
        db = dataBaseOpen.getWritableDatabase();

        textViewLife = (TextView) findViewById(R.id.textViewLife);
        textViewIssue = (TextView) findViewById(R.id.textViewIssue);
        textViewAdd = (TextView) findViewById(R.id.textViewAdd);
        textViewStats = (TextView) findViewById(R.id.textViewStats);

        btnResetData = (Button) findViewById(R.id.btnResetData);

        String strLife = "일상\n일어난 일상들을\n리스트로 보여줍니다.";
        String strIssue = "이슈\n발생한 이슈들을\n리스트로 보여줍니다.";
        String strAdd = "추가\n지금 일어나고 있는 일상, 이슈를 기록합니다.";
        String strStats = "통계\n기록된 데이터의\n통계를 보여줍니다.";

        // 문장의 일부분 사이즈 조절
        final SpannableStringBuilder sps1 = new SpannableStringBuilder(strLife);
        sps1.setSpan(new AbsoluteSizeSpan(60), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewLife.setText(sps1);
        final SpannableStringBuilder sps2 = new SpannableStringBuilder(strIssue);
        sps2.setSpan(new AbsoluteSizeSpan(60), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewIssue.setText(sps2);
        final SpannableStringBuilder sps3 = new SpannableStringBuilder(strAdd);
        sps3.setSpan(new AbsoluteSizeSpan(60), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewAdd.setText(sps3);
        final SpannableStringBuilder sps4 = new SpannableStringBuilder(strStats);
        sps4.setSpan(new AbsoluteSizeSpan(60), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewStats.setText(sps4);

        btnResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void onClickBack(View v) {
        finish();
    }

    public void showDialog() {
        // 알림창 속성 설정
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("모든 기록된 데이터를 초기화 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseOpen.onUpgrade(db, 1, 1);
                        Toast.makeText(getApplicationContext(), "초기화에 성공했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        builder.show();
    }
}
