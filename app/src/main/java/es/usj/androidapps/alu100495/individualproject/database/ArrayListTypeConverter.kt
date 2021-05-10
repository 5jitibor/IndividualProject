package es.usj.androidapps.alu100495.individualproject.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArrayListTypeConverter{
    @TypeConverter
    fun fromString(value: String?): List<Int>{
        val listType = object: TypeToken<ArrayList<Int>>(){}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String{
        return Gson().toJson(list)
    }

}