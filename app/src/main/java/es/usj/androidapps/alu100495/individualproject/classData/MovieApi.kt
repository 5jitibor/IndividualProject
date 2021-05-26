package es.usj.androidapps.alu100495.individualproject.classData



data class MovieApi(var id: Int, val title: String, val genres : List<Int>, val description : String, val director : String, val actors: List<Int>, val year: Int, val runtime: Int, val rating: Float, val votes: Int, val revenue: Float)

