package com.example.gsontojsonwithpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

data class Tcon(val name: String, val sms: String, val time: String)

class MainActivity : AppCompatActivity() {
    var gson = GsonBuilder().create()
    // 저장 타입 지정
    var listType : TypeToken<MutableList<Tcon>> = object : TypeToken<MutableList<Tcon>>() {}
    var setType : TypeToken<MutableSet<Tcon>> = object : TypeToken<MutableSet<Tcon>>() {}
    var mapType : TypeToken<MutableMap<String,Tcon>> = object : TypeToken<MutableMap<String,Tcon>>() {}
    private var g1 : MutableList<Tcon> = mutableListOf(
        Tcon("hoons","가","1000"),
        Tcon("sunny","나","2000"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var strContact1 = gson.toJson(g1,listType.type)

        btPlus.setOnClickListener({
            g1.add(Tcon("les","다","3000"))
            App.prefs.setV("1",strContact1)

        })
        btLoad.setOnClickListener({
            val g11= App.prefs.getV("1")
            var data1: MutableList<Tcon> = gson.fromJson(g11,listType.type)

            data1.forEach({
                if(it.name=="sunny"){
                    println(it.toString())
                }
            })
            Log.d("main","${data1?.size}")
            Log.d("main","${data1}")

        })


    }
    fun println(data:String){
        textView.setText("${data}\n")
    }
}
