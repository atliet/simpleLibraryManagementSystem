package org.lms

fun main() {

    /**
     * This class is meant for initializing the program and determining if user is admin or customer
     */

    println("This is a simple library management system")
    println("Please choose if you are a 'admin' or a 'customer' :")
    val role = readln()

    if (role == "admin"){
        // Switch to admin mode
        println("You're an admin \n")
        val Interface = AdminInterface()
        Interface.execute()
    } else if (role == "customer"){
        // Switch to user mode
        println("You're a customer")
        val Interface = CustomerInterface()
        Interface.execute()
    } else {
        println("Bad input!")
    }

    println("Goodbye!")

}
