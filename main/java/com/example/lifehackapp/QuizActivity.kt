package com.example.lifehackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    var index = 0
    var score = 0
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
    val questions = arrayOf(
        "Drinking water helps you stay focused and energized.",
        "Getting errors means you are bad at coding",
        "Writing things down helps you remember them better",
        "Getting 7-8 hours of sleep improves memory and mood",
        "Ai can replace learning how to code completely"
    )

    val answers = arrayOf(true,false, true, true, false)
    val explanations = arrayOf(
        "Hack: drinking water helps you stay focused and energized.",
        "Myth: every programmer gets errors, even seniors",
        "Hack: Yes writing down= active recall.",
        "Hack: Sleep is like a reset button of your brain.",
        "Myth: Ai is a tool, not a replacement for understanding logic."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)

        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        loadQuestion()

        hackButton.setOnClickListener { checkAnswer(userAnswer = true) }
        mythButton.setOnClickListener { checkAnswer(userAnswer = false) }

        nextButton.setOnClickListener {
            index++

            if (index < questions.size) {
                loadQuestion()
                feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        questionText.text = questions[index]
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            score++
            feedbackText.text = "Correct! ${explanations[index]}"
        } else {
            feedbackText.text = "Incorrect. ${explanations[index]}"
        }
    }
}
