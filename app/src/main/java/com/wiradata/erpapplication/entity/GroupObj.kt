package com.wiradata.erpapplication.entity

data class GroupObj (
    var id_:String,
    var name_ : String,
    var access_: List<AccessObj>
)