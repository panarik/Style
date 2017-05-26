package com.yalin.style.data.repository.datasource

import android.content.Context
import com.yalin.style.data.cache.SourcesCache
import com.yalin.style.data.lock.OpenInputStreamLock
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author jinyalin
 * @since 2017/5/23.
 */
@Singleton
class SourcesDataStoreFactory @Inject
constructor(val context: Context,
            val sourcesCache: SourcesCache,
            val inputStreamLock: OpenInputStreamLock) {

    fun create(): SourcesDataStore {
        return SourcesDataStoreImpl(context, sourcesCache, inputStreamLock)
    }
}