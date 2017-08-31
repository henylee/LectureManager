package kr.co.tjeit.lecturemanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.Reply;

/**
 * Created by the on 2017-08-31.
 */

public class DateAdapter extends ArrayAdapter<Reply> {

    Context mContext;
    List<Reply> mList;
    LayoutInflater inf;

    public DateAdapter(Context context, List<Reply> list) {
        super(context, R.layout.reply_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row==null) {
            row = inf.inflate(R.layout.reply_list_item, null);
        }
        return row;
    }

    @Override
    public int getCount() {
        return 15;
    }
}
