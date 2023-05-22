package com.azimzada.retrofitrecycleexamson.model

import java.io.Serializable

data class PostDTO(var userId : Int, var id : Int, var title : String, var body : String) :
    Serializable