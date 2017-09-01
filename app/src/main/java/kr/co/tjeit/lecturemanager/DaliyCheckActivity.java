package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DaliyCheckActivity extends BaseActivity {

    CalendarDay mCalendarDAY = null;
    private TextView daliyChkTxt;
    private android.widget.ToggleButton studentCheckToggleBtn;

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

        Calendar today = Calendar.getInstance();
        Date todayDate = today.getTime();
        Date selectedDate = mCalendarDAY.getDate();

        if (selectedDate.after(todayDate)) {
            Toast.makeText(mContext, "오늘보다 이후의 날짜", Toast.LENGTH_SHORT).show();
        }
        else if (today.get(Calendar.YEAR)==mCalendarDAY.getYear() && today.get(Calendar.MONTH)==mCalendarDAY.getMonth() && today.get(Calendar.DAY_OF_MONTH)==mCalendarDAY.getDay()) {
            Toast.makeText(mContext, "오늘날짜 입니다,", Toast.LENGTH_SHORT).show();
        }
        else {
            studentCheckToggleBtn.setEnabled(false);
            Toast.makeText(mContext, "이미 지나간 날짜입니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void bindViews() {
        this.studentCheckToggleBtn = (ToggleButton) findViewById(R.id.studentCheckToggleBtn);
        this.daliyChkTxt = (TextView) findViewById(R.id.daliyChkTxt);
    }
}
