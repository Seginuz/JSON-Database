import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (x, y) -> {
        long product = x;
        for (long i = x; i < y; i++) {
            product *= i + 1;
        }
        return product;
    };
}