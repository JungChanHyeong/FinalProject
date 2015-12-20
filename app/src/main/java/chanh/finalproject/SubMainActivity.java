package chanh.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SubMainActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_main);
    }

    public void onClickLife(View v) {
        Intent intent = new Intent(this, LifeList.class);
        startActivity(intent);
    }

    public void onClickIssue(View v) {
        Intent intent = new Intent(this, IssueList.class);
        startActivity(intent);
    }

    public void onClickAdd(View v) {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }

    public void onClickStats(View v) {
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }

    public void onClickHelp(View v) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
}
