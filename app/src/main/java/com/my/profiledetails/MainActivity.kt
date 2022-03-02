package com.my.profiledetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.my.profiledetails.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(
            layoutInflater,null,false
        )
        setContentView(binding.root)
        getDetails()

    }



    private fun getDetails(){
        val details =ProfileService.profileInstance.getUserData()

        details.enqueue(object :Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                val details=response.body()
                if (details!=null) {
                    binding.txtName.setText(details.title+". "+details.firstName+" "+details.lastName)
                    binding.txtId.setText(details.id)
                    val url=details.picture
                    Glide.with(this@MainActivity).load(url).into(binding.ImgProfile)
                    binding.txtGender.setText(details.gender)
                    binding.txtMail.setText(details.email)
                    binding.txtDob.setText(details.dateOfBirth)
                    binding.txtRegisterDate.setText(details.registerDate)
                    binding.txtUpdatedDate.setText(details.updatedDate)
                    binding.txtPhone.setText(details.phone)
                    Log.d("manish", details.toString())
                }
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                Log.d("tag", "error",t)
            }

        })
    }

}