package kr.co.tjeit.lecturemanager.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by the on 2017-08-31.
 */

public class Reply implements Serializable {

    private String cotent;
    private Calendar createdAt;

    private User writer;

    public Reply() {

    }

    public Reply(String cotent, Calendar createdAt, User writer) {
        this.cotent = cotent;
        this.createdAt = createdAt;
        this.writer = writer;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
