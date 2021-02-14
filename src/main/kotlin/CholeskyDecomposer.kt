import kotlin.math.sqrt

class CholeskyDecomposer {
    companion object {
        private fun checkIsSymmetricMatrix(x: Matrix) {
            val mat = x.data
            val n = x.getShape().first
            checkDimension(x)
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (i == j) continue else if (mat[i][j] != mat[j][i]) throw IllegalArgumentException("Matrix is not symmetric")
                }
            }
        }

        private fun checkDimension(x: Matrix) {
            val matrixShape = x.getShape()
            if (matrixShape.first != matrixShape.second) {
                throw IllegalArgumentException("Matrix is not square")
            }
        }

        fun decompose(x: Matrix): Matrix {
            checkIsSymmetricMatrix(x)
            val mat = x.data
            val n = x.getShape().first
            val matL = MutableList(n) { MutableList(n) { 0.0 } }
            for (i in 0 until n) {
                val matRowLi = matL[i]
                var d = 0.0
                for (j in 0 until i) {
                    val matRowLj = matL[j]
                    var s = 0.0
                    for (k in 0 until j) {
                        s += matRowLi[k] * matRowLj[k]
                    }
                    s = (mat[i][j] - s) / matL[j][j]
                    matRowLi[j] = s
                    d += s * s
                }
                d = mat[i][i] - d
                if (d <= 0) {
                    throw IllegalArgumentException("Matrix is not positive definite.")
                }
                matL[i][i] = sqrt(d)
                for (j in i + 1 until n) {
                    matL[i][j] = 0.0
                }
            }
            return Matrix(matL)
        }
    }
}