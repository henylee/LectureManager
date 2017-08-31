package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import kr.co.tjeit.lecturemanager.util.GlobalData;

public class MainActivity extends BaseActivity {

    private com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//                Toast.makeText(mContext, "선택된 날짜 : " + date.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DaliyReplyActivity.class);
                intent.putExtra("클릭된날짜", date);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
    }
}
