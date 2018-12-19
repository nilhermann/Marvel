package nilherman.funfacts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fun_facts.*

class FunFactsActivity : AppCompatActivity() {
//    private val factBook = FactBook()
//    private val colorWheel = ColorWheel()
    val facts: List<String> = FactBook().factsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun_facts)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = FrasesAdapter(facts, this)

       /* button!!.setOnClickListener {
            val fact = factBook.getFact()
            val color = colorWheel.getColor()

            // Update the screen with a new fact
            factTextView!!.text = fact
            relativeLayout!!.setBackgroundColor(color)
            button!!.setTextColor(color)
        }*/
    }
}
