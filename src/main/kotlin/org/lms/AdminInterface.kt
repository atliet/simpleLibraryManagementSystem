package org.lms

import java.io.File
import java.io.InputStream

class AdminInterface {

    fun execute() {

        // Read the booklist file and place them in a mutable list
        val csvStream: InputStream? = this.javaClass.getResourceAsStream("/books.csv")
        var books = csvStream?.let { readBooksfile(it) }

        /*
        NOTE: Since this is merely a project for learning and testing, we are modifying the file in the resources.
        This, however cannot be done dynamically during the running of the program, so we use a mutable list to
        store the latest desired version during running and save that version to the file just before exit.
         */

        val startQuestion = """What do you want to do?
            | 1: See a list of books in the library
            | 2: Add a book into the list of books
            | 3: Remove a book from the list of books
            | Please choose by typing number
            | If you wish to quit, type 'quit'
        """.trimMargin()

        println("\n")
        println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - -")
        println(" This is admin interface for the libary management system ")
        println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - -")
        while (true) {
            println("\n")
            if (books != null) {
                println("Current amount of books in library: ${books.size}")
            }
            println("\n")
            println(startQuestion)
            val choice = readln()
            println("\n")
            if (choice == "1") {
                println("Printing all the books in the library:")
                if (books != null) {
                    for (book: Book in books) {
                        println("${book.name} by ${book.author} from ${book.year}")
                    }
                }
            } else if (choice == "2") {
                println("Please give name of book:")
                var name = readln()
                println("Please give author of book:")
                var author = readln()
                println("Please give year of book:")
                var year = readln()
                books?.add(Book(name, author, year))
            } else if (choice == "3") {
                println("Please give name of book:")
                var name = readln()
                println("Please give author of book:")
                var author = readln()
                println("Please give year of book:")
                var year = readln()
                var bookToBeRemoved = Book(name, author, year)
                if (books != null) {
                    if (books.contains(bookToBeRemoved)) {
                        books.remove(bookToBeRemoved)
                    } else {
                        println("Book not in library!")
                    }
                }
            } else if (choice == "quit") {
                // Write the current version of the booklist to the file
                if (books != null){
                File("./src/main/resources/books.csv").bufferedWriter().use { out ->
                    books.forEach {
                        out.write("${it.name};${it.author};${it.year}")
                        out.newLine()
                    }
                }
                    break
                } else {
                    println("Bad input!")
                }
            }
        }
    }

    /**
     * Read and return all lines from the spesified file as a list of Book-objects
     */
    private fun readBooksfile(inputStream: InputStream): MutableList<Book> {
        return inputStream.bufferedReader().lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (name, author, year) = it.split(';', ignoreCase = false, limit = 3)
                Book(name.trim(), author.trim(), year.trim())
            }.toMutableList()
    }
}
