class Hashtable {
    private val table = Array(100) { mutableListOf<String>() }

    fun add(value: String) {
        val index = hash(value)
        table[index].add(value)
    }

    fun remove(value: String) {
        val index = hash(value)
        table[index].remove(value)
    }

    fun contains(value: String): Boolean {
        val index = hash(value)
        return table[index].contains(value)
    }

    private fun hash(value: String): Int {
        var hash = 0
        for (i in value.indices) {
            hash += value[i].code * i
        }
        return hash % table.size
    }
}