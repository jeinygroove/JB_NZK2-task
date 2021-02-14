class Matrix(var data: List<MutableList<Double>>) {
    var numOfRows: Int
    var numOfColumns: Int

    init {
        if (data.isEmpty()) {
            throw IllegalArgumentException("Matrix can not be empty.")
        }
        this.numOfRows = data.size
        this.numOfColumns = data[0].size
    }

    fun getShape(): Pair<Int, Int> = Pair(this.numOfRows, this.numOfColumns)

    fun showMatrix() {
        for (i in 0 until this.numOfRows) {
            for (j in 0 until this.numOfColumns) {
                print(this.data[i][j])
                print(" ")
            }
            println()
        }
    }

    override fun equals(other: Any?): Boolean = (other is Matrix) && (this.data == other.data)

    override fun hashCode(): Int {
        return this.data.hashCode()
    }
}