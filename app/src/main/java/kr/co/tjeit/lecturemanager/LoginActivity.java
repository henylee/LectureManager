package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class LoginActivity extends BaseActivity {

    private Button signUpBtn;
    private Button loginBtn;

    public static LoginActivity myActivity;

    KakaoSessionCallback ksc;
    CallbackManager callbackManager;

    private com.facebook.login.widget.LoginButton fbLoginBtn;
    private com.kakao.usermgmt.LoginButton kakaoLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();
        setupEvents();
        setValues();

        myActivity = this;
    }

    @Override
    public void setupEvents() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, SignUpActivity.class);
                startActivity(myIntent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {

        ksc = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(ksc);

        callbackManager = CallbackManager.Factory.create();

        fbLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        ProfileTracker pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile==null) {
                    Toast.makeText(mContext, "로그아웃 처리 완료.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mContext, currentProfile.getName()+"님 접속", Toast.LENGTH_SHORT).show();

                    ContextUtil.login(mContext, currentProfile.getId(),"없음", currentProfile.getName(), currentProfile.getProfilePictureUri(500,500).toString());
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindViews() {
        this.kakaoLoginBtn = (LoginButton) findViewById(R.id.kakaoLoginBtn);
        this.fbLoginBtn = (com.facebook.login.widget.LoginButton) findViewById(R.id.fbLoginBtn);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
    }

    private class KakaoSessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile result) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(mContext, result.getNickname()+"님이 로그인했습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }

}
