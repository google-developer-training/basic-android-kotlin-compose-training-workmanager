package com.example.bluromatic.domain

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Create imageUri.
 */
@RequiresApi(29)
class CreateImageUriUseCase {
    suspend operator fun invoke(resolver: ContentResolver, filename: String): Uri? {
        return withContext(Dispatchers.IO) {
            val imageCollection =
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Blur-O-Matic")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
            val imageUri = resolver.insert(imageCollection, values)
            imageUri
        }
    }
}
