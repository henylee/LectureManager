package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;

public class DaliyCheckActivity extends BaseActivity {

    CalendarDay mCalendarDAY = null;
    private TextView daliyChkTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daliy_check);
        mCalendarDAY = getIntent().getParcelableExtra("출석확인날짜");

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        daliyChkTxt.setText(sdf.format(mCalendarDAY.getDate()));
    }

    @Override
    public void bindViews() {
        this.daliyChkTxt = (TextView) findViewById(R.id.daliyChkTxt);
    }
}
