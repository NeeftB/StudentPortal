package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_PORTAL_REQUEST_CODE = 100

class PortalActivity : AppCompatActivity() {
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals, { portalItem: Portal -> portalClicked(portalItem) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initView()
    }

    private fun initView() {
        rvListOfPortals.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvListOfPortals.adapter = portalAdapter
        portalAdapter.notifyDataSetChanged()


        fab.setOnClickListener{ addPortal() }
    }

    private fun addPortal() {
        val createPortalIntent = Intent(this, CreatePortalActivity::class.java)
        startActivityForResult(createPortalIntent, ADD_PORTAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal)



                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun portalClicked(portalItem: Portal) {
        val openURL = Intent(Intent.ACTION_VIEW)
        val url = portalItem.portalUrl
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
