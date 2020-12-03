package com.github.aleksandrgrebenkin.poplibs.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubRepository(
    @Expose val name: String,
    @Expose val forksCount: Int
) : Parcelable