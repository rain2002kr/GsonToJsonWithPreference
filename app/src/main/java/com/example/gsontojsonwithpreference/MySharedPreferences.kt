package com.example.gsontojsonwithpreference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class tContact(val name: String?, val sms: String?, val time: String?) : Parcelable


class MySharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_EDITTEXT = "myEditText"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    /* 파일 이름과 EditText 를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */

    fun setV(key:String, value:String?){
        prefs.edit().putString(key,value).apply()

    }
    fun getV(key:String):String?{
        return prefs.getString(key,"")
    }

    var myEditText: String?
        get() = prefs.getString(PREF_KEY_MY_EDITTEXT, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_EDITTEXT, value).apply()
    /* get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 ""
     * set(value) 실행 시 value로 값을 대체한 후 저장 */

}

class App : Application() {

    companion object {
        lateinit var prefs : MySharedPreferences
    }
    /* prefs라는 이름의 MySharedPreferences 하나만 생성할 수 있도록 설정. */

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}