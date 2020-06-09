package com.example.kudian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudian.db.MyDbHelper;

public class RegisterActivity extends AppCompatActivity {
//3 定义对象
    EditText et_name,et_pwd,et_email,et_phone;
    Button btn_register,btn_cancel;
    MyDbHelper mhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //4 绑定事件
        initView();
        //5 注册按钮功能的实现
        btnRegister();
        //6 取消按钮功能的实现
        btnCancel();
    }



    //4 绑定控件--代码
    private void initView() {
        et_name=findViewById(R.id.rename);
        et_pwd=findViewById(R.id.repassword);
        et_email=findViewById(R.id.reemail);
        et_phone=findViewById(R.id.rephone);
        btn_register=findViewById(R.id.btn_reg_ok);
        btn_cancel=findViewById(R.id.btn_reg_cancel);
        mhelper=new MyDbHelper(RegisterActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //5 注册按钮功能的实现--代码
    private void btnRegister() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建对象，封装数据
                ContentValues values=new ContentValues();
                values.put("name",et_name.getText().toString());
                values.put("pwd",et_pwd.getText().toString());
                values.put("email",et_email.getText().toString());
                values.put("phone",et_phone.getText().toString());

                //将数据放入到数据的表中
                db.insert("tb_userinfo",null,values);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //取消按钮功能的实现--代码
    private void btnCancel() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}