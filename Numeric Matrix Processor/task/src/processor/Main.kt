package processor

fun main() {
    menu()
}

fun newMatrix(rows : Int, columns : Int) : MutableList<MutableList<Double>> {
    val matrix = mutableListOf<MutableList<Double>>()
        for (i in 0 until rows) {
        val matrixRow = readln().split(" ").map { it.toDouble() }.toMutableList()
        if (matrixRow.size != columns) {
            return mutableListOf()
        }
        matrix.add(matrixRow)
    }
    return matrix
}

fun printMatrix(matrix : MutableList<MutableList<Double>>) {
    val isIntegerMatrix = isAllInteger(matrix)
    matrix.forEach {
        it.forEach { num ->
            print("${if (isIntegerMatrix) num.toInt() else num} ")
        }
        println()
    }
}

fun isAllInteger(matrix : MutableList<MutableList<Double>>) : Boolean {
    for (row in 0 until matrix.size) {
        for (column in 0 until matrix[row].size) {
            if (matrix[row][column] % 1.0 != 0.0) {
                return false
            }
        }
    }
    return true
}


fun menu() {
    while (true) {
        print("""1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
0. Exit
Your choice: """)
        when (readln()) {
            "1" -> optionAddMatrices()
            "2" -> optionMultiplyMatrixByConstant()
            "3" -> optionMultiplyMatrices()
            "4" -> optionTransposeMatrix()
            "0" -> return
            else -> println("The operation cannot be performed.")
        }
        println()
    }
}

fun optionTransposeMatrix() {
    val error = "The operation cannot be performed."
    println("""
1. Main diagonal
2. Side diagonal
3. Vertical line
4. Horizontal line
Your choice: """)
    val choice = readln()
    if (!choice.matches("[1234]".toRegex())) {
        println(error)
        return
    }
    val isValidRowsColumns: Regex = "\\d+ \\d+".toRegex()
    val matrixCalculator = MatrixCalculator()
    print("Enter size of matrix: ")
    val input = readln()
    if (!input.matches(isValidRowsColumns)) {
        println(error)
        return
    }
    val (rows, columns) = input.split(" ").map { it.toInt() }
    println("Enter matrix:")
    val matrix = newMatrix(rows, columns)
    if (matrix.isEmpty()) {
        println(error)
        return
    }
    println("The result is:")
    when (choice) {
        "1" -> printMatrix(matrixCalculator.diagonalTranspose(matrix))
        "2" -> printMatrix(matrixCalculator.sideDiagonalTranspose(matrix))
        "3" -> printMatrix(matrixCalculator.verticalTranspose(matrix))
        "4" -> printMatrix(matrixCalculator.horizontalTranspose(matrix))
    }
}

fun optionMultiplyMatrixByConstant() {
    val error = "The operation cannot be performed."
    val isValidRowsColumns: Regex = "\\d+ \\d+".toRegex()
    val matrixCalculator = MatrixCalculator()
    print("Enter size of matrix: ")
    var input = readln()
    if (!input.matches(isValidRowsColumns)) {
        println(error)
        return
    }
    val (rows, columns) = input.split(" ").map { it.toInt() }
    println("Enter matrix:")
    val matrix = newMatrix(rows, columns)
    if (matrix.isEmpty()) {
        println(error)
        return
    }
    print("Enter constant: ")
    input = readln()
    if (!input.matches("\\d+\\.?\\d*".toRegex())) {
        println(error)
        return
    }
    val constant = input.toDouble()
    val calculatedMatrix = matrixCalculator.multiplyMatrixByConstant(matrix, constant)
    println("The result is:")
    printMatrix(calculatedMatrix)
}

fun optionMultiplyMatrices() {
    val error = "The operation cannot be performed."
    val isValidRowsColumns: Regex = "\\d+ \\d+".toRegex()
    val matrixCalculator = MatrixCalculator()
    print("Enter size of first matrix: ")
    var input = readln()
    if (!input.matches(isValidRowsColumns)) {
        println(error)
        return
    }
    var (rows, columns) = input.split(" ").map { it.toInt() }
    println("Enter first matrix:")
    val matrix1 = newMatrix(rows, columns)
    if (matrix1.isEmpty()) {
        println(error)
        return
    }
    print("Enter size of second matrix: ")
    input = readln()
    if (!input.matches(isValidRowsColumns) || input.split(" ").map { it.toInt() }[0] != columns) {
        println(error)
        return
    }
    rows = input.split(" ").map { it.toInt() }[0]
    columns = input.split(" ").map { it.toInt() }[1]
    println("Enter second matrix:")
    val matrix2 = newMatrix(rows, columns)
    if (matrix2.isEmpty()) {
        println(error)
        return
    }
    val calculatedMatrix = matrixCalculator.multiplyMatrices(matrix1, matrix2)
    println("The result is:")
    printMatrix(calculatedMatrix)
}

fun optionAddMatrices() {
    val error = "The operation cannot be performed."
    val isValidRowsColumns: Regex = "\\d+ \\d+".toRegex()
    val matrixCalculator = MatrixCalculator()
    print("Enter size of first matrix: ")
    var input = readln()
    if (!input.matches(isValidRowsColumns)) {
        println(error)
        return
    }
    var (rows, columns) = input.split(" ").map { it.toInt() }
    println("Enter first matrix:")
    val matrix1 = newMatrix(rows, columns)
    if (matrix1.isEmpty()) {
        println(error)
        return
    }
    print("Enter size of second matrix: ")
    input = readln()
    if (!input.matches(isValidRowsColumns) ||
        input.split(" ").map { it.toInt() }[0] != rows ||
        input.split(" ").map { it.toInt() }[1] != columns) {
        println(error)
        return
    }
    rows = input.split(" ").map { it.toInt() }[0]
    columns = input.split(" ").map { it.toInt() }[1]
    println("Enter second matrix:")
    val matrix2 = newMatrix(rows, columns)
    if (matrix2.isEmpty()) {
        println(error)
        return
    }
    val calculatedMatrix = matrixCalculator.addMatrices(matrix1, matrix2)
    println("The result is:")
    printMatrix(calculatedMatrix)
}