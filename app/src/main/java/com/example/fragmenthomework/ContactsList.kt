package com.example.fragmenthomework

object ContactsList {
    val contacts = mutableListOf<Contact>()

    init {

        for (i in 1..100) {
            val contact = Contact(
                id = i,
                firstName = "FirstName$i",
                lastName = "LastName$i",
                number = generateRandomPhoneNumber()
            )
            contacts.add(contact)
        }
    }

    fun generateRandomPhoneNumber(): String {
        val random = java.util.Random()
        val stringBuilder = StringBuilder()

        for (i in 1..7) {
            val digit = random.nextInt(10) // Генерируем случайную цифру от 0 до 9
            stringBuilder.append(digit)
        }

        return stringBuilder.toString()
    }
}