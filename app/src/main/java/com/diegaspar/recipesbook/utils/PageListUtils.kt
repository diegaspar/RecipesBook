package com.diegaspar.recipesbook.utils

import androidx.paging.PagedList

fun pagedListConfig() = PagedList.Config.Builder()
    .setInitialLoadSizeHint(5)
    .setEnablePlaceholders(false)
    .setPageSize(5 * 2)
    .build()