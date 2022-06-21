package dev.jsedano.curry.examples;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class CurriedMath {

  public static Function<Integer, Function<Integer, Integer>> toCurry(
      BiFunction<Integer, Integer, Integer> function) {
    return v -> v1 -> function.apply(v, v1);
  }

  public static void main(String[] args) {
    var curriedAdd = toCurry(Math::addExact);

    System.out.format("curriedAdd(2)(10): %d\n", curriedAdd.apply(2).apply(10));

    var curriedMultiply = toCurry(Math::multiplyExact);

    System.out.format("curriedMultiply(2)(10): %d\n", curriedMultiply.apply(2).apply(10));

    var add1 = curriedAdd.apply(1);
    var multiplyBy2 = curriedMultiply.apply(2);

    List.of(0, 1, 2, 3, 4).stream().map(add1).map(multiplyBy2).forEach(System.out::println);
  }
}
