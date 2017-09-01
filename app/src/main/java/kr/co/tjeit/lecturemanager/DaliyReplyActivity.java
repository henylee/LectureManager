package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.DateAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;

public class DaliyReplyActivity extends BaseActivity {

    private android.widget.TextView deliyTxt;
    private android.widget.ListView deliyReplyListView;
    private android.widget.EditText replyEdt;
    private android.widget.Button replyBtn;

    DateAdapter mDateAdpater;
    List<Reply> replyList = new ArrayList<>();
    CalendarDay mCalendarDay = null;
    private Button delitChkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daliy_reply);
        mCalendarDay = (CalendarDay) getIntent().getParcelableExtra("클릭된날짜");

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        delitChkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DaliyCheckActivity.class);
                intent.putExtra("출석확인날짜", mCalendarDay);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        mDateAdpater = new DateAdapter(mContext, replyList);
        deliyReplyListView.setAdapter(mDateAdpater);
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        deliyTxt.setText(sdf.format(mCalendarDay.getDate()));
    }

    @Override
    public void bindViews() {
        this.replyBtn = (Button) findViewById(R.id.replyBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.deliyReplyListView = (ListView) findViewById(R.id.deliyReplyListView);
        this.delitChkBtn = (Button) findViewById(R.id.delitChkBtn);
        this.deliyTxt = (TextView) findViewById(R.id.deliyTxt);
    }
}
