package com.example.ass_androidnangcao;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity {

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        createDatabase();
    }

    public void createDatabase() {
        database = openOrCreateDatabase("htht.db", MODE_PRIVATE, null);
        Toast.makeText(this, "Tạo dữ liệu thành công", Toast.LENGTH_LONG)
                .show();
        createTable();
    }
    public void createTable() {
        String sqlClass = "create table if not exists Lớp(stt integer primary key, MãLớp text, TênLớp text)";
        String sqlStudent = "create table if not exists SinhViên(stt integer primary key, MãSinhViên text, TênSinhViên text, TênLớp text)";
        database.execSQL(sqlClass);
        database.execSQL(sqlStudent);
        Toast.makeText(this, "Tạo bảng thành công", Toast.LENGTH_LONG).show();
    }
    public void reasetDatabase(View v) {
        if (deleteDatabase("htht.db")) {
            Toast.makeText(this, "Xóa dữ liệu thành công", Toast.LENGTH_LONG)
                    .show();
            createDatabase();
        } else {
            Toast.makeText(this, "Xóa dữ liệu không thành công", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void themLop(View v) {
        Intent intent = new Intent(this, AddLayerActivity.class);
        startActivity(intent);
    }
    public void xemDanhSachLop(View v) {
        Intent intent = new Intent(this, ListClassActivity.class);
        startActivity(intent);
    }
    public void quanLySinhVien(View v) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }
}
