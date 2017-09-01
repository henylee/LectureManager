package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
    private android.widget.Button logoutBtn;
    private Button fbPageBtn;
    private TextView genderTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtil.logout(mContext);

                LoginManager.getInstance().logOut();

                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void setValues() {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(final JSONObject object, GraphResponse response) {
                Log.d("사용자정보", object.toString());
                fbPageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        try {
                            String gender = object.getString("gender");
                            if (gender.equals("male")) {
                                genderTxt.setText("남성");
                            }
                            else {
                                genderTxt.setText("여성");
                            }
                            String link = object.getString("link");
                            intent.setData(Uri.parse(link));
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        Bundle parangs = new Bundle();
        parangs.putString("fields", "id,name, link, gender, email");
        request.setParameters(parangs);
        request.executeAsync();

        Glide.with(this).load(ContextUtil.getLoginUser(mContext).getProfileURL()).into(profileImg);
        nameTxt.setText(ContextUtil.getLoginUser(mContext).getName());
    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.fbPageBtn = (Button) findViewById(R.id.fbPageBtn);
        this.genderTxt = (TextView) findViewById(R.id.genderTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
