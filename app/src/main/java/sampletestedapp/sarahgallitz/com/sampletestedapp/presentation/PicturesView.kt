package sampletestedapp.sarahgallitz.com.sampletestedapp.presentation

import android.content.Context
import android.net.Uri
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.image_grid.view.*
import kotlinx.android.synthetic.main.image_sort_options_view.view.*
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.ImageService

class PicturesView(
        val view: View,
        imageService: ImageService
) {
    private val context: Context
        get() = view.context

    private val adapter = ImageListAdapter(context, imageService)

    fun bind(presenter: PicturesPresenter) {
        val recyclerView = view.gridview
        recyclerView.layoutManager = GridLayoutManager(context, GridUtils.getNumColumns(context))
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        view.sort_mode_button.setOnClickListener { view.sort_mode_picker.visibility = View.VISIBLE }
        view.sort_options_dismiss.setOnClickListener { view.sort_mode_picker.visibility = View.GONE }
        view.retry_btn.setOnClickListener { presenter.loadPictures() }
        view.sort_modes_group.setOnCheckedChangeListener { group, checkedId ->
            val sortMode = group.findViewById<RadioButton>(checkedId).text.toString()
            view.sort_mode_picker.visibility = View.GONE
            presenter.setSortMode(sortMode)
        }
    }

    fun showSortMode(sortMode: String) {
        view.sort_mode_button.text = sortMode
    }

    fun showLoading() {
        adapter.setImages(listOf())
        view.images_view.visibility = View.GONE
        view.error_view.visibility = View.GONE
        view.loading_view.visibility = View.VISIBLE
    }

    fun hideLoading() {
        view.loading_view.visibility = View.GONE
    }

    fun showPictures(pictureUris: List<Uri>) {
        view.images_view.visibility = View.VISIBLE
        adapter.setImages(pictureUris)
    }

    fun showError() {
        view.images_view.visibility = View.GONE
        view.error_view.visibility = View.VISIBLE
    }
}