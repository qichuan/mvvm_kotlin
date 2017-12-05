package com.zqc.mvvm.viewModel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import com.zqc.mvvm.BR
import com.zqc.mvvm.model.User
import java.util.Observable
import java.util.Observer
import java.util.Locale

/**
 * Created by Qichuan on 02/12/17.
 */
class UserViewModel(private val user: User) : Observer, BaseObservable() {

    /// Register itself as the observer of Model
    init {
        user.addObserver(this)
    }

    /// Notify the UI when change event emitting from Model is received.
    override fun update(p0: Observable?, p1: Any?) {
        if (p1 is String) {
            if (p1 == "age") {
                notifyPropertyChanged(BR.age)
            } else if (p1 == "firstName" || p1 == "lastName") {
                notifyPropertyChanged(BR.name)
            } else if (p1 == "imageUrl") {
                notifyPropertyChanged(BR.imageUrl)
            } else if (p1 == "tagline") {
                notifyPropertyChanged(BR.tagline)
            } else if (p1 == "female") {
                notifyPropertyChanged(BR.gender)
            }
        }
    }

    val name: String
        @Bindable get() {
            return user.firstName + " " + user.lastName
        }

    val age: String
        @Bindable get() {
            return if (user.age <= 0) return ""
            else String.format(Locale.ENGLISH, "%d years old", user.age)
        }

    val gender: String
        @Bindable get() {
            return if (user.female) return "Female" else "Male"
        }

    val imageUrl: String
        @Bindable get() {
            return user.imageUrl
        }

    val tagline: String
        @Bindable get() {
            return "Tagline: " + user.tagline
        }

    fun onButtonClick(view: View) {
        this.user.age = 35
        this.user.imageUrl = "https://media.giphy.com/media/w7M8g9cTom0Du/giphy.gif"
        this.user.tagline = "Now he has grown up..."
    }

}