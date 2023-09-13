package com.example.fragmenthomework

object ContactsList {
    val contacts = mutableListOf<Contact>()

    init {
        contacts.addAll(
            listOf(
                Contact(1,"John", "Doe", "123-456-7890"),
                Contact(2,"Jane", "Smith", "987-654-3210"),
                Contact(3,"Alice", "Johnson", "555-123-4567"),
                Contact(4,"Bob", "Brown", "111-222-3333"),
                Contact(5,"Eva", " Williams", "999-888-7777"),
                Contact(6,"Michael", "Davis", "444-555-6666")
            )
        )
    }
}