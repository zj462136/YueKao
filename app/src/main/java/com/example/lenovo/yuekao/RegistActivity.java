package com.example.lenovo.yuekao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity {

    @BindView(R.id.regist_back)
    ImageView registBack;
    @BindView(R.id.regist_name)
    EditText registName;
    @BindView(R.id.regist_psw)
    EditText registPsw;
    @BindView(R.id.regist_psw1)
    EditText registPsw1;
    @BindView(R.id.regist_email)
    EditText registEmail;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    private HashMap<String, String> map;
    private CartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.regist_back, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist_back:
                finish();
                break;
            case R.id.btn_regist:
                String name = registName.getText().toString().trim();
                String psw = registPsw.getText().toString().trim();
                String psw1 = registPsw1.getText().toString().trim();
                String email = registEmail.getText().toString().trim();
                if (psw.equals(psw1)){
                    if (email!=null){
                        presenter = new CartPresenter();
                        map = new HashMap<>();
                        map.put("mobile",name);
                        map.put("password",psw);
                        presenter.getRegistData(map);
                        presenter.attachView(new IView() {
                            @Override
                            public void onSuccess(Object o) {
                                if (o!=null){
                                    RegistBean bean = (RegistBean) o;
                                    Toast.makeText(RegistActivity.this,bean.getMsg(), Toast.LENGTH_SHORT).show();
                                    if (bean.getMsg().equals("注册成功")){
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailed(Exception e) {

                            }
                        });
                    }else {
                        Toast.makeText(this, "邮箱不能为空！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "两密码不一致！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.dattachView();
        }
    }
}  
