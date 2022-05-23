package processor

class MatrixCalculator {

    fun addMatrices(matrixA: MutableList<MutableList<Double>>,
                    matrixB: MutableList<MutableList<Double>>) : MutableList<MutableList<Double>> {
        val calculatedMatrix = mutableListOf<MutableList<Double>>()
        for (row in 0 until matrixA.size) {
            val matrixRow = mutableListOf<Double>()
            for (column in 0 until matrixA[row].size) {
                matrixRow.add(matrixA[row][column] + matrixB[row][column])
            }
            calculatedMatrix.add(matrixRow)
        }
        return calculatedMatrix
    }

    fun multiplyMatrices(matrixA: MutableList<MutableList<Double>>,
                         matrixB: MutableList<MutableList<Double>>) : MutableList<MutableList<Double>> {
        val calculatedMatrix = mutableListOf<MutableList<Double>>()
        val rowWidth = matrixB[0].size
        val columnHeight = matrixA.size
        for (matrixARow in 0 until columnHeight) {
            val matrixRow = mutableListOf<Double>()
            for (matrixBColumn in 0 until rowWidth) {
                var sum = 0.0
                for (i in 0 until matrixA[0].size) {
                    sum += matrixA[matrixARow][i] * matrixB[i][matrixBColumn]
                }
                matrixRow.add(sum)
            }
            calculatedMatrix.add(matrixRow)
        }
        return calculatedMatrix
    }

    fun multiplyMatrixByConstant(matrix: MutableList<MutableList<Double>>,
                                 constant: Double) : MutableList<MutableList<Double>> {
        val calculatedMatrix = mutableListOf<MutableList<Double>>()
        matrix.forEach {
            val matrixRow = mutableListOf<Double>()
            it.forEach { num -> matrixRow.add(num * constant) }
            calculatedMatrix.add(matrixRow) }
        return calculatedMatrix
    }

    private fun flipMatrix(matrix: MutableList<MutableList<Double>>) : MutableList<MutableList<Double>> {
        val flippedMatrix = mutableListOf<MutableList<Double>>()
        for (column in 0 until matrix[0].size) {
            val matrixRow = mutableListOf<Double>()
            for (row in 0 until matrix.size) {
                matrixRow.add(matrix[row][column])
            }
            flippedMatrix.add(matrixRow)
        }
        return flippedMatrix
    }

}