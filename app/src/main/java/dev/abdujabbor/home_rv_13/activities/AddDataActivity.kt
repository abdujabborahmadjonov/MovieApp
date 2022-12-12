package dev.abdujabbor.home_rv_13.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import dev.abdujabbor.home_rv_13.MySharedpreferances
import dev.abdujabbor.home_rv_13.databinding.ActivityAddDataBinding
import dev.abdujabbor.home_rv_13.models.MyList
import java.util.*

class AddDataActivity : AppCompatActivity() {
    var doubleBackToExitPressedOnce = false
    lateinit var binding: ActivityAddDataBinding

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedpreferances.init(this)
        val c = Calendar.getInstance()
        val yearw = c.get(Calendar.YEAR)
        val monthw = c.get(Calendar.MONTH)
        val dayw = c.get(Calendar.DAY_OF_MONTH)
        binding.tvDate.setOnClickListener {
            val dpd = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                binding.tvDate.text = "$dayOfMonth/$month/$year"
            }, yearw, monthw, dayw)
            dpd.show()
        }
        val name = intent.getStringExtra("name")
        val about = intent.getStringExtra("about")
        val date = intent.getStringExtra("date")
        val author = intent.getStringExtra("author")
        val position = intent.getStringExtra("position")
        val list = MySharedpreferances.catchList
        binding.tvName.setText(name)
        binding.tvAbout.setText(about)
        binding.tvDate.text = date
        binding.tvAuthors.setText(author)
        binding.apply {

            btnSave.setOnClickListener {
                if (tvName.text.isNotEmpty() && tvAuthors.text.isNotEmpty() && tvDate.text.isNotEmpty() && tvAbout.text.isNotEmpty()) {
                    val myList = MyList(
                        tvName.text.toString().trim(),
                        tvAuthors.text.toString().trim(),
                        tvAbout.text.toString().trim(),
                        tvDate.text.toString().trim()
                    )
                    if (!list.contains(
                            MyList(
                                tvName.text.toString().trim(),
                                tvAuthors.text.toString().trim(),
                                tvAbout.text.toString().trim(),
                                tvDate.text.toString().trim()
                            )
                        )
                    ) {
                        if (position != null) {
                            list.removeAt(position.toInt())
                            MySharedpreferances.catchList = list


                        }
                        val list = MySharedpreferances.catchList
                        list.add(myList)
                        MySharedpreferances.catchList = list
                    }
                    finish()
                } else {
                    Toast.makeText(this@AddDataActivity, "Comlete data", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}