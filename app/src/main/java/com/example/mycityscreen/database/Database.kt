package com.example.mycityscreen.database
import com.example.mycityscreen.R

class CityRepository {
    private val cities = listOf(

        City(
            id = 1,
            name = "Paris",
            img = R.drawable.paris,
            address = R.string.cn1,
            pop = "2 million",
            locationIds = listOf(1,2,3,4,5,6),
            rating = "91",
            des = R.string.cd1,
        ),
        City(
            id = 2,
            name = "Barcelona",
            img = R.drawable.barca,
            address = R.string.cn2,
            pop = "2 Million",
            locationIds = listOf(10,11,12,13,14,15,16),
            rating = "80",
            des = R.string.cd2,
        ),
        City(
            id = 3,
            img = R.drawable.tokyo,
            name = "Tokyo",
            address = R.string.cn3,
            pop = "14 Million",
            locationIds = listOf(21,22,23,24,25,26,27,28),
            rating = "95",
            des = R.string.cd3,
        ),
        City(
            id = 4,
            img = R.drawable.dubai,
            name = "Dubai",
            address = R.string.cn4,
            pop = "9 Million",
            locationIds = listOf(30,31,32,33,34,35,36),
            rating = "75",
            des = R.string.cd4,
        ),
        City(
            id = 5,
            img = R.drawable.nyc,
            name = "New York",
            address = R.string.cn5,
            pop = "7 Million",
            locationIds = listOf(41,42,43,44,45),
            rating = "79",
            des = R.string.cd5,
        )
    )


    private val allLocations = listOf(

        // CITY 1 = Paris
        Location(id = 1,
            lname = "Arc de Triomphe",
            banner = R.drawable.history,
            limg = R.drawable.arc,
            ldescription = "Honorific toward the fallen",
            category = R.string.history,
            desc = R.string.c1l1

        ),
        Location(id = 2,
            lname = "Paris Catacombs",
            banner = R.drawable.history,
            limg = R.drawable.catacombs,
            ldescription = "Underground maze of skeletons",
            category = R.string.history
            ,
            desc = R.string.c1l2
        ),
        Location(id = 3,
            lname = "Eiffel Towers ",
            banner = R.drawable.history,
            limg = R.drawable.eiffel,
            ldescription = "Iconic and breath-taking tower",
            category = R.string.history
            ,
            desc = R.string.c1l3
        ),
        Location(id = 4,
            lname = "The Louvre",
            banner = R.drawable.entertain,
            limg = R.drawable.louvre,
            ldescription = "World most famous museum",
            category = R.string.entertainment
            ,
            desc = R.string.c1l4
        ),
        Location(id = 5,
            lname = "Seine River",
            banner = R.drawable.nature,
            limg = R.drawable.seine,
            ldescription = "River through Paris",
            category = R.string.nature
            ,
            desc = R.string.c1l5
        ),
        Location(id = 6,
            lname = "The Champs-Élysées",
            banner = R.drawable.shopping,
            limg = R.drawable.champs,
            ldescription = "Famous Shopping Street",
            category = R.string.shopping
            ,
            desc = R.string.c1l5
        ),


        // city 2: Barcelona

        Location(id = 12,
            lname = "Basílica de la Sagrada Família",
            banner = R.drawable.history,
            limg = R.drawable.bassilica,
            ldescription = "World Heritage Cathedral",
            category = R.string.history
            ,
            desc = R.string.c2l1
        ),
        Location(id = 11,
            lname = "Park Guell",
            banner = R.drawable.nature,
            limg = R.drawable.guell,
            ldescription = "A park full of gardens and architecture",
            category = R.string.nature
            ,
            desc = R.string.c2l2
        ),
        Location(id = 13,
            lname = "Picasso Museum",
            banner = R.drawable.entertain,
            limg = R.drawable.picasso,
            ldescription = "Sanctuary of a Generational Artist",
            category = R.string.entertainment
            ,
            desc = R.string.c2l3
        ),
        Location(id = 14,
            lname = "Camp Nau Stadium",
            banner = R.drawable.entertain,
            limg = R.drawable.nau,
            ldescription = "World famous FCB Stadium",
            category = R.string.entertainment
            ,
            desc = R.string.c2l4
        ),
        Location(id = 15,
            lname = "Gothic Quarter",
            banner = R.drawable.history,
            limg = R.drawable.gothic,
            ldescription = "Ancient street of old Barcelona",
            category = R.string.history
            ,
            desc = R.string.c2l5
        ),

        Location(id = 16,
            lname = "La Rambla Market",
            banner = R.drawable.shopping,
            limg = R.drawable.rambla,
            ldescription = "Mesmerizing Market",
            category = R.string.shopping
            ,
            desc = R.string.c2l6
        ),



        //CITY #3 TOKYO

        Location(id = 21,
            lname = "Shibuya Crossing",
            banner = R.drawable.shopping,
            limg = R.drawable.shibuya,
            ldescription = "Busy and chaotic road crossing",
            category = R.string.entertainment
            ,
            desc = R.string.c3l1
        ),
        Location(id = 22,
            lname = "Tokyo Skytree",
            banner = R.drawable.entertain,
            limg = R.drawable.skytree,
            ldescription = "Tower with breathtaking views",
            category = R.string.entertainment,
            desc = R.string.c3l2
        ),
        Location(id = 23,
            lname = "Ueno Zoological Gardens",
            banner = R.drawable.nature,
            limg = R.drawable.ueno,
            ldescription = "Historical zoo and park",
            category = R.string.nature
            ,
            desc = R.string.c3l3
        ),
        Location(id = 24,
            lname = "Tokyo Disney Resort",
            banner = R.drawable.hotels,
            limg = R.drawable.disney,
            ldescription = "Magical Resort",
            category = R.string.hotel
            ,
            desc = R.string.c3l8
        ),
        Location(id = 28,
            lname = "Sensō-ji",
            banner = R.drawable.history,
            limg = R.drawable.senso,
            ldescription = "Ancient buddhist temple",
            category = R.string.history
            ,
            desc = R.string.c3l4
        ),
        Location(id = 25,
            lname = "Tokyo National Museum",
            banner = R.drawable.entertain,
            limg = R.drawable.tokyonational,
            ldescription = "Museum of Japanese Culture",
            category = R.string.entertainment
            ,
            desc = R.string.c3l5
        ),
        Location(id = 26,
            lname = "Tokyo National Museum",
            banner = R.drawable.history,
            limg = R.drawable.palace,
            ldescription = "Residence of the Monarch",
            category = R.string.history
            ,
            desc = R.string.c3l6
        ),
        Location(id = 27,
            lname = "Mount Fuji",
            banner = R.drawable.nature,
            limg = R.drawable.fuji,
            ldescription = "Giant towering mountain",
            category = R.string.nature
            ,
            desc = R.string.c3l7
        ),

        // city #4 Dubai
        Location(id = 31,
            lname = "Burk Khalifa",
            banner = R.drawable.entertain,
            limg = R.drawable.burj,
            ldescription = "Tallest building in the world",
            category = R.string.entertainment
            ,
            desc = R.string.c4l1
        ),
        Location(id = 32,
            lname = "Atlantis Aquaventure Waterpark",
            banner = R.drawable.hotels,
            limg = R.drawable.atlantis,
            ldescription = "Fun Oasis in the desert",
            category = R.string.hotel
            ,
            desc = R.string.c4l2
        ),
        Location(id = 33,
            lname = "The Dubai mall",
            banner = R.drawable.shopping,
            limg = R.drawable.dubaimall,
            ldescription = "Mega shopping spot",
            category = R.string.shopping
            ,
            desc = R.string.c4l3
        ),
        Location(id = 34,
            lname = "Burj al-arab",
            banner = R.drawable.hotels,
            limg = R.drawable.arab,
            ldescription = "Beautiful island hotel",
            category = R.string.hotel
            ,
            desc = R.string.c4l4
        ),
        Location(id = 35,
            lname = "Dubai Aquarium",
            banner = R.drawable.nature,
            limg = R.drawable.aqua,
            ldescription = "Huge underwater Aquarium",
            category = R.string.nature
            ,
            desc = R.string.c4l5
        ),

        Location(id = 30,
            lname = "Dubai Miracle Garden",
            banner = R.drawable.nature,
            limg = R.drawable.miracle,
            ldescription = "Brethtaking Garden",
            category = R.string.nature
            ,
            desc = R.string.c4l6
        ),

        // New York

        Location(id = 41,
            lname = "The Empire State Building",
            banner = R.drawable.history,
            limg = R.drawable.empire,
            ldescription = "Historical skyscraper",
            category = R.string.history
            ,
            desc = R.string.c5l1
        ),
        Location(id = 42,
            lname = "Statue of Liberty",
            banner = R.drawable.history,
            limg = R.drawable.liberty,
            ldescription = "Monument of freedom",
            category = R.string.history
            ,
            desc = R.string.c5l2
        ),
        Location(id = 43,
            lname = "Times Square",
            banner = R.drawable.shopping,
            limg = R.drawable.times,
            ldescription = "World renowned Street square ",
            category = R.string.shopping
            ,
            desc = R.string.c5l3
        ),

        Location(id = 44,
            lname = "Central park",
            banner = R.drawable.nature,
            limg = R.drawable.central,
            ldescription = "Green oasis in city center",
            category = R.string.nature
            ,
            desc = R.string.c5l4
        ),
        Location(id = 45,
            lname = "Metropolitan Museum Of Art",
            banner = R.drawable.entertain,
            limg = R.drawable.metropolitan,
            ldescription = "Sanctuary of arts",
            category = R.string.entertainment
            ,
            desc = R.string.c5l5
        ),
    )


    fun getCities(): List<City> = cities

    fun getAllLocations(): List<Location> = allLocations

    fun getLocationsForCity(cityId: Int): List<Location> {
        val city = getCities().find { it.id == cityId }
        if (city == null) {
            throw IllegalArgumentException("City with ID $cityId not found")
        }
        val locationIds = city.locationIds
        val locations = allLocations.filter { it.id in locationIds }

        if (locations.isEmpty()) {
            throw IllegalArgumentException("No locations found for city with ID $cityId")
        }
        return locations
    }
    fun getLocationsByCategory(categoryId: Int): List<Location> {
        return allLocations.filter { it.category == categoryId }
    }
}
