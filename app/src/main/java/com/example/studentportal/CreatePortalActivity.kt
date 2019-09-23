package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_portal.*


const val EXTRA_PORTAL = "EXTRA_PORTAL"

class CreatePortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_portal)

        initView()
    }

    private fun initView() {
        addPortalBtn.setOnClickListener { onAddPortalClick() }
    }

    private fun onAddPortalClick() {
        if(etPortalTitle.text.toString().isNotBlank() ||
            etPortalUrl.text.toString().isNotBlank()) {
            val portal = Portal(etPortalTitle.text.toString(), etPortalUrl.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        } else {
            Snackbar.make(etPortalTitle, getString(R.string.no_input_warning),
                Snackbar.LENGTH_SHORT).show()
        }
    }
}
