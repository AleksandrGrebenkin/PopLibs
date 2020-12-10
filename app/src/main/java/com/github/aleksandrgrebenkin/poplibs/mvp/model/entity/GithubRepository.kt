package com.github.aleksandrgrebenkin.poplibs.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubRepository(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null,
    @Expose val fullName: String? = null
) : Parcelable