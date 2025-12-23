
package com.ilkproje.bitirme.viewmodel

import androidx.lifecycle.ViewModel


class BabyViewModel : ViewModel() {
    // Bebek bilgileri
    var babyName: String = ""
    var babySurname: String = ""
    var birthDate: String = ""
    var gender: String = ""
    var tcNumber: String = ""

    // Bebek bilgilerini kaydetme işlevi
    fun saveBabyInfo(
        name: String,
        surname: String,
        date: String,
        genderInput: String,
        tc: String
    ) {
        babyName = name
        babySurname = surname
        birthDate = date
        gender = genderInput
        tcNumber = tc
    }
}
