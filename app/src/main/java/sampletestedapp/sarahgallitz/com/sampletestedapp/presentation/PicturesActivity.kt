package sampletestedapp.sarahgallitz.com.sampletestedapp.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import sampletestedapp.sarahgallitz.com.sampletestedapp.PicturesApplication
import sampletestedapp.sarahgallitz.com.sampletestedapp.R

class PicturesActivity : AppCompatActivity() {
    private val application: PicturesApplication
        get() = this.applicationContext as PicturesApplication

    private var presenter: PicturesPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.title)
        setContentView(R.layout.image_grid)

        val view = PicturesView(findViewById<View>(R.id.activity_view), application.imageService)
        val picturesPresenter = PicturesPresenter(view, application.picturesRepository, { runOnUiThread { it() } })

        view.bind(picturesPresenter)

        presenter = picturesPresenter
    }

    override fun onStart() {
        super.onStart()
        presenter?.start()
    }
}
