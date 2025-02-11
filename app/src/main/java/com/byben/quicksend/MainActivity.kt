package com.byben.quicksend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etPhoneNumber: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        btnSend.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val message = etMessage.text.toString().trim()

            if (phoneNumber.isEmpty() || message.isEmpty()) {
                Toast.makeText(this, "Nomor telepon dan pesan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                sendMessageToWhatsApp(phoneNumber, message)
            }
        }
    }

    private fun sendMessageToWhatsApp(phoneNumber: String, message: String) {
        val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
