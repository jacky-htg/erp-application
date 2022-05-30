package com.wiradata.erpapplication.entity

data class UserObj (
    var id_:String,
    var companyId_ : String,
    var name_: String,
    var email_: String,
    var group_ : GroupObj
)