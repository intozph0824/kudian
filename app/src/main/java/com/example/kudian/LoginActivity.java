package com.example.kudian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudian.db.MyDbHelper;

public class LoginActivity extends AppCompatActivity {
//1 定义对象
    EditText et_name,et_pwd;
    Button btn_newregister,btn_login;
    MyDbHelper mhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //2 绑定事件
        initView();
        //3 登录按钮的实现
        btnLogin();
        //4 新用户注册按钮的实现
        btnNewregister();
    }

    //2 绑定事件--代码
    private void initView() {
        et_name=findViewById(R.id.logname);
        et_pwd=findViewById(R.id.logpassword);
        btn_newregister=findViewById(R.id.btn_log_new);
        btn_login=findViewById(R.id.btn_log_ok);
        mhelper=new MyDbHelper(LoginActivity.this);
        db=mhelper.getWritableDatabase();

    }

    //3 登录按钮的实现--代码
    private void btnLogin() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入名和密码
                String inputname=et_name.getText().toString();
                String inputpwd=et_pwd.getText().toString();
                //判断所获取的密码和用户名
                if(inputname.equals("")||inputpwd.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    //查询注册时输入的用户名和密码
                    Cursor cursor=db.rawQuery("select * from tb_userinfo where name=? and pwd=?",new String[]{inputname,inputpwd});
                    //根据查询到的结果进行判断
                    if(cursor.moveToNext()){
                        //查询到时
                        String getname=cursor.getString(cursor.getColumnIndex("name"));
                        String getpwd=cursor.getString(cursor.getColumnIndex("pwd"));
                        if(inputname.equalsIgnoreCase(getname)&&inputpwd.equalsIgnoreCase(getpwd)){
                            Toast.makeText(LoginActivity.this,"用户名和密码正确，欢迎登录！",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,Homepage.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        //没查询到
                        Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                        //清空
                        et_name.setText("");
                        et_pwd.setText("");
                    }
                }
            }
        });
    }

    //4 新用户注册按钮的实现--代码
    private void btnNewregister() {
        btn_newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}