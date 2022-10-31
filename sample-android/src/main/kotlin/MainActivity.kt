package dev.eSolovei.eXpresso.sample.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dev.eSolovei.eXpresso.sample.android.databinding.ActivityMainBinding
import dev.eSolovei.eXpresso.sample.android.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var contentMainBinding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        contentMainBinding = mainBinding.includedContentMain
        val viewRoot = mainBinding.root
        setContentView(viewRoot)
        setSupportActionBar(mainBinding.toolbar)
        contentMainBinding.showListButton.setOnClickListener {
            val goToListIntent = Intent(this, ListActivity::class.java)
            startActivity(goToListIntent)
        }
        contentMainBinding.showContributors.setOnClickListener {
            val goToContributorsIntent = Intent(this, ContributionList::class.java)
            goToContributorsIntent.putExtras(intent)
            startActivity(goToContributorsIntent)
        }
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
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}