package com.yalin.style.data.repository.datasource.io

import android.content.ContentProviderOperation
import android.content.Context
import android.text.TextUtils
import com.google.gson.JsonElement
import com.yalin.style.data.repository.datasource.provider.StyleContract
import com.yalin.style.data.utils.isTreeUri
import com.yalin.style.data.utils.processUriPermission
import com.yalin.style.domain.GalleryWallpaper
import java.util.ArrayList

/**
 * @author jinyalin
 * @since 2017/5/24.
 */
class GalleryWallpapersHandler(val context: Context,
                               val uris: List<GalleryWallpaper>) : JSONHandler(context) {

    companion object {
        private val TAG = "GalleryWallpapersHandler"
    }

    override fun makeContentProviderOperations(list: ArrayList<ContentProviderOperation>) {
        val uri = StyleContract.GalleryWallpaper.CONTENT_URI
        for (wallpaperEntity in uris) {
            if (TextUtils.isEmpty(wallpaperEntity.uri)) {
                continue
            }
            wallpaperEntity.isTreeUri = isTreeUri(uri)
            processUriPermission(context, wallpaperEntity)

            val builder = ContentProviderOperation.newInsert(uri)
            builder.withValue(StyleContract.GalleryWallpaper.COLUMN_NAME_CUSTOM_URI,
                    wallpaperEntity.uri)
            builder.withValue(StyleContract.GalleryWallpaper.COLUMN_NAME_IS_TREE_URI,
                    if (wallpaperEntity.isTreeUri) 1 else 0)
            list.add(builder.build())
        }
    }

    override fun process(element: JsonElement) {

    }
}