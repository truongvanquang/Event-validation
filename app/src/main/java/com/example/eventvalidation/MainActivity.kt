package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtHoTen = findViewById<EditText>(R.id.edtHoTen)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtSdt = findViewById<EditText>(R.id.edtSdt)
        val edtMsv = findViewById<EditText>(R.id.edtMsv)
        val edtLop = findViewById<EditText>(R.id.edtLop)
        val btnDangKi = findViewById<Button>(R.id.btnDangKi)

        btnDangKi.setOnClickListener { view ->
            val hoten = edtHoTen.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val sdt = edtSdt.text.toString().trim()
            val msv = edtMsv.text.toString().trim()
            val lop = edtLop.text.toString().trim()

            var isValid = true

            if (hoten.isEmpty()) { edtHoTen.error = "Vui lòng nhập họ tên"; isValid = false }
            if (msv.isEmpty()) { edtMsv.error = "Vui lòng nhập MSV"; isValid = false }
            if (lop.isEmpty()) { edtLop.error = "Vui lòng nhập lớp"; isValid = false }

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.error = "Email không hợp lệ"; isValid = false
            }
            if (sdt.isEmpty() || sdt.length < 10 || !sdt.matches("^[0-9]+$".toRegex())) {
                edtSdt.error = "SĐT phải là số và có ít nhất 10 ký tự"; isValid = false
            }

            if (isValid) {
                val snackbar = Snackbar.make(view, "Đăng ký thành công!", Snackbar.LENGTH_LONG)
                snackbar.setAction("XEM CHI TIẾT") {
                    val intent = Intent(this, ChiTietActivity::class.java)
                    intent.putExtra("HOTEN", hoten)
                    intent.putExtra("EMAIL", email)
                    intent.putExtra("SDT", sdt)
                    intent.putExtra("MSV", msv)
                    intent.putExtra("LOP", lop)
                    startActivity(intent)
                }
                snackbar.show()
            }
        }
    }
}