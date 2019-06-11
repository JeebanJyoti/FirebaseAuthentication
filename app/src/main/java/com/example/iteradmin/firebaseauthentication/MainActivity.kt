package com.example.iteradmin.firebaseauthentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth=FirebaseAuth.getInstance()

        val e=findViewById<EditText>(R.id.email)
        val p=findViewById<EditText>(R.id.password)

        val signup=findViewById<Button>(R.id.sign_up)

        signup.setOnClickListener{
            val email:String=e.text.toString()
            val password:String=p.text.toString()
            signInFirebase(email,password)
        }
    }

    private fun signInFirebase(email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                val user:FirebaseUser?=mAuth.currentUser
                Toast.makeText(this,user?.uid,Toast.LENGTH_LONG).show()
                sign_up.visibility=View.INVISIBLE
            }
            else{
                Toast.makeText(this,"Something got wrong",Toast.LENGTH_LONG).show()
            }
        }
    }
}
