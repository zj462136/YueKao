package com.example.lenovo.yuekao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.image_user)
    SimpleDraweeView imageUser;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.forget_psw)
    TextView forgetPsw;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private HashMap<String, String> map;
    private CartPresenter presenter;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_regist, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_regist:
                final Intent intent = new Intent(MainActivity.this,RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String name = etName.getText().toString().trim();
                String psw = etPsw.getText().toString().trim();
                String md5 = md5(psw);
                Log.i(TAG, "onViewClicked: "+psw);
                map = new HashMap<>();
                map.put("mobile",name);
                map.put("password",psw);
                presenter = new CartPresenter();
                presenter.getloginData(map);
                presenter.attachView(new IView() {
                    @Override
                    public void onSuccess(Object o) {
                        if (o!=null){
                            LoginBean bean = (LoginBean) o;
                            Toast.makeText(MainActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            if(bean.getMsg().equals("登录成功")){
                                Intent intent1 = new Intent(MainActivity.this, ShowActivity.class);
                                intent1.putExtra("uid",bean.getData().getUid()+"");
                                startActivity(intent1);
                            }
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {

                    }
                });
                break;
        }
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.dattachView();
        }
    }
}  
