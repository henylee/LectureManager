package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import kr.co.tjeit.lecturemanager.adapter.StudentAdapter;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.GlobalData;

public class MainActivity extends BaseActivity {

    private android.widget.Button profileBtn;
    private ListView studentListView;
    StudentAdapter mStudentAdapter;

//    협업하고 브런치기능 활성화.

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

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(mContext, ViewStudentInfoActivity.class);
                myIntent.putExtra("사용자정보", GlobalData.allUsers.get(position));
                startActivity(myIntent);
            }
        });

        studentListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

//                Toast.makeText(MainActivity.this, position + "번 학생 삭제 요청", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setTitle("삭제 확인");
                myBuilder.setMessage("정말 삭제하시겠습니까?");
                myBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                myBuilder.setNegativeButton("취소", null);
                myBuilder.show();

                return false;
            }
        });

    }

    @Override
    public void setValues() {
        mStudentAdapter = new StudentAdapter(mContext, GlobalData.allUsers);
        studentListView.setAdapter(mStudentAdapter);
    }

    @Override
    public void bindViews() {
        this.studentListView = (ListView) findViewById(R.id.studentListView);
        this.profileBtn = (Button) findViewById(R.id.profileBtn);
    }
}


