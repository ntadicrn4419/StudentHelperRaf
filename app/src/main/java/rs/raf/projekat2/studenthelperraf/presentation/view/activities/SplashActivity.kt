package rs.raf.projekat2.studenthelperraf.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.studenthelperraf.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val bgdImg: ImageView = findViewById(R.id.splash_icon)
        val slideAnimation = android.view.animation.AnimationUtils.loadAnimation(this,R.anim.slide)
        bgdImg.startAnimation(slideAnimation)

        Handler().postDelayed({

            val pref = applicationContext.getSharedPreferences(packageName, MODE_PRIVATE)
            val username = pref.getString("username", null)
            val password = pref.getString("password", null)

            if(username != null && password != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}