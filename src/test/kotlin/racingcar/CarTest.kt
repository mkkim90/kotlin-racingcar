package racingcar

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class Car(val name: String, position: Int = 0) {
    var position: Int = position
        private set

    init {
        require(name.length <= MAXIMUM_NAME_LENGTH) {
            "자동차의 이름을 5글자를 넘을 수 없습니다."
        }
    }

    fun move(condition: Int) {
        if (condition >= 4) {
            position++
        }
    }

    companion object {
        private const val MAXIMUM_NAME_LENGTH: Int = 5
    }
}

class CarTest {

    @ValueSource(strings = ["jason", "alex"])
    @ParameterizedTest
    fun `자동차는 이름을 가진다`(name: String) {
        val car = Car(name)
        car.name shouldBe name
    }

    @Test
    fun `자동차의 이름은 5자를 초과할 수 없다`() {
        shouldThrow<IllegalArgumentException> {
            Car("동해물과백두산이")
        }
    }

    @Test
    fun `자동차의 초기 위치를 0이다`() {
        val car = Car("jason")
        car.position shouldBe 0
    }

    @Test
    fun `자동차는 위치를 가진다`() {
        val car = Car("jason", 10)
        car.position shouldBe 10
    }

    @ValueSource(ints = [4, 5, 6, 7, 8, 9])
    @ParameterizedTest
    fun `자동차는 무작위 값이 4 이상일 경우 움직인다`(condition: Int) {
        val car = Car("jason")
        car.move(condition)
        car.position shouldBe 1
    }

    @ValueSource(ints = [0, 1, 2, 3])
    @ParameterizedTest
    fun `자동차는 무작위 값이 3 이하일 경우 정지한다`(condition: Int) {
        val car = Car("jason")
        car.move(condition)
        car.position shouldBe 0
    }
}
