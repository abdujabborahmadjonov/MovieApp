package dev.abdujabbor.home_rv_13.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.abdujabbor.home_rv_13.MySharedpreferances
import dev.abdujabbor.home_rv_13.R
import dev.abdujabbor.home_rv_13.adapter.MyRVAdapter
import dev.abdujabbor.home_rv_13.adapter.RvClick
import dev.abdujabbor.home_rv_13.databinding.ActivityMainBinding
import dev.abdujabbor.home_rv_13.models.MyList

class MainActivity : AppCompatActivity(),RvClick {
    lateinit var list: ArrayList<MyList>
    lateinit var myRVAdapter: MyRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedpreferances.init(this)
        list = MySharedpreferances.catchList
        myRVAdapter = MyRVAdapter(list, this)
        binding.myRcycleView.adapter = myRVAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.myplus_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AddDataActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    override fun deleteMovie(moview: MyList, position: Int) {
        list.removeAt(position)
        MySharedpreferances.myName = position.toString()
        MySharedpreferances.catchList = list
        myRVAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun editMovie(moview: MyList, position: Int) {
//        list.removeAt(position)
//        list.addAll(MySharedpreferances.catchList)

        myRVAdapter.notifyItemRemoved(position)

        val intent = Intent(this, AddDataActivity::class.java)
        intent.putExtra("name", list[position].name)
        intent.putExtra("position", position.toString())
        intent.putExtra("author", list[position].author)
        intent.putExtra("about", list[position].about)
        intent.putExtra("date", list[position].date)
        startActivity(intent)
    }

    override fun onResume() {
        list.clear()
        list.addAll(MySharedpreferances.catchList)
        myRVAdapter.notifyDataSetChanged()
        super.onResume()

    }

}