package sampletestedapp.sarahgallitz.com.sampletestedapp.presentation

import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.PictureRepository

class PicturesPresenter(
        private val view: PicturesView,
        private val pictureRepository: PictureRepository,
        private val runOnUiThread: (() -> Unit) -> Unit
) {
    private var currentSortMode = "New"

    fun start() {
        view.showSortMode(currentSortMode)
        loadPictures()
    }

    fun setSortMode(sortMode: String) {
        currentSortMode = sortMode
        view.showSortMode(sortMode)
        loadPictures()
    }

    fun loadPictures() {
        view.showLoading()
        pictureRepository.getPictures(
                currentSortMode,
                onSuccess = {
                    runOnUiThread {
                        view.hideLoading()
                        view.showPictures(it)
                    }
                },
                onError = {
                    runOnUiThread {
                        view.hideLoading()
                        view.showError()
                    }
                }
        )
    }
}