import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TestCholeskyDecomposer {
    @Test
    fun decomposeIdentityMatrix() {
        val data = List(10) { MutableList(10) { 0.0 } }
        data.forEachIndexed { r, l -> l[r] = 1.0 }
        val identityMatrix = Matrix(data)
        val matrixL = CholeskyDecomposer.decompose(identityMatrix)
        assertEquals(matrixL, identityMatrix)
    }

    @Test
    fun decomposeMatrix() {
        val matrix = Matrix(
            listOf(
                mutableListOf(4.0, 12.0, -16.0),
                mutableListOf(12.0, 37.0, -43.0),
                mutableListOf(-16.0, -43.0, 98.0)
            )
        )
        val matrixL = CholeskyDecomposer.decompose(matrix)
        assertEquals(
            matrixL,
            Matrix(listOf(mutableListOf(2.0, 0.0, 0.0), mutableListOf(6.0, 1.0, 0.0), mutableListOf(-8.0, 5.0, 3.0)))
        )
    }

    @Test
    fun decomposeNonSquareMatrix() {
        val matrix = Matrix(listOf(mutableListOf(4.0, 12.0, 8.0), mutableListOf(12.0, 37.0, -43.0)))
        assertFailsWith<IllegalArgumentException> {
            CholeskyDecomposer.decompose(matrix)
        }
    }

    @Test
    fun decomposeNonSymmetricMatrix() {
        val matrix = Matrix(listOf(mutableListOf(4.0, 12.0), mutableListOf(11.0, 37.0)))
        assertFailsWith<IllegalArgumentException> {
            CholeskyDecomposer.decompose(matrix)
        }
    }

    @Test
    fun decomposeNonPositiveDefiniteMatrix() {
        val matrix = Matrix(listOf(mutableListOf(3.0, 2.0), mutableListOf(2.0, -3.0)))
        assertFailsWith<java.lang.IllegalArgumentException> {
            CholeskyDecomposer.decompose(matrix)
        }
    }

    @Test
    fun decomposeNonPositiveDefiniteMatrix2() {
        val matrix = Matrix(listOf(mutableListOf(1.0, -1.0), mutableListOf(-1.0, 1.0)))
        assertFailsWith<java.lang.IllegalArgumentException> {
            CholeskyDecomposer.decompose(matrix)
        }
    }
}