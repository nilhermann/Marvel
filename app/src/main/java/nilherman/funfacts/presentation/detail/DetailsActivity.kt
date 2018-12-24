package nilherman.funfacts.presentation.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nilherman.funfacts.R
import nilherman.funfacts.data.model.ResultsItem

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var character = intent.getParcelableExtra<ResultsItem>("SUPERHERO")
    }
}