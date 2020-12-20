package com.example.augmentedfurniture.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R

class CategoryActivity : AppCompatActivity() {

    private var categoryRecyclerView : RecyclerView? = null

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        toolbar = findViewById(R.id.toolbarCategory)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("name")
        supportActionBar?.title = title

        categoryRecyclerView = findViewById(R.id.category_recyclerview)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate Menu
        menuInflater.inflate(R.menu.search_icon,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle ActionBar Item click
        val id : Int = item.itemId

        if(id == R.id.searchCategory){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}