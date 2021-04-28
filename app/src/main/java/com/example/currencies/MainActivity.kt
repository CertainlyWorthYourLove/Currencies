package com.example.currencies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val myList = arrayListOf<Currency>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recView.layoutManager = GridLayoutManager(this, 1)
        getRSS()
    }

    private fun getRSS() {
        val client = OkHttpClient()
        val request = Request.Builder().url("http://www.nbg.ge/rss.php").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Here is an Error", e.message.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val content = response.body!!.string()
                val changedContent = content.replace("<![CDATA[ <table border=\"0\">", "")
                    .replace("</table> ]]>", "").replace("</tr>", "<tr>")
                    .replace("</td>", "<td>").replace(" <td>", "<td>")
                val split1 = changedContent.split("<tr>")
                for(each in split1){
                    if(each.split("<td>").size != 1){
                        val myText = each.split("<td>")[3]
                        val myValue = each.split("<td>")[5]
                        myList.add(Currency(myText, myValue))
                    }
                }
                runOnUiThread {
                    recView.adapter = CurAdapter(myList)
                }
            }
        })
    }

}