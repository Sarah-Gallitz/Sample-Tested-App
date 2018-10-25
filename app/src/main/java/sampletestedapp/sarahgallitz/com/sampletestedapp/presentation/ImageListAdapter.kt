package sampletestedapp.sarahgallitz.com.sampletestedapp.presentation

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.image_list_item.view.*
import sampletestedapp.sarahgallitz.com.sampletestedapp.R
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.ImageService

class ImageListAdapter(
        private val context: Context,
        private val imageService: ImageService
) :
        RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    private var bitmapList: MutableList<Uri> = mutableListOf()

    fun setImages(imageUris: List<Uri>) {
        bitmapList = imageUris.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.bitmapList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uri = bitmapList[position]
        imageService.loadImageToFit(
                image = uri,
                view = holder.itemView.list_item_image_view,
                placeholder = R.drawable.image_loading_placeholder,
                onError = {
                    Handler().post {
                        bitmapList.remove(uri)
                        notifyDataSetChanged()
                    }
                }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder internal constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView)
}