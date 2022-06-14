//usr/bin/env jshell --show-version "$0" "$@"; exit $?


public static Function<Integer, Function<Integer, Integer>> toCurry(
    BiFunction<Integer, Integer, Integer> function) {
  return v -> v1 -> function.apply(v, v1);
}

var curriedAdd = toCurry(Math::addExact);
var curriedMultiply = toCurry(Math::multiplyExact);

System.out.format("curriedAdd(2)(10): %d\n",curriedAdd.apply(2).apply(10));
System.out.format("curriedMultiply(2)(10): %d\n",curriedMultiply.apply(2).apply(10));

var add1 = toCurry(Math::addExact).apply(1);
var multiplyBy2 = toCurry(Math::multiplyExact).apply(2);
List.of(0, 1, 2, 3, 4).stream().map(add1).map(multiplyBy2).forEach(System.out::println);


public static String wget(
    int connectionTimeout,
    int readTimeout,
    boolean followRedirects,
    String requestMethod,
    String address) {
  try {
    URL url = new URL(address);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod(requestMethod);
    con.setConnectTimeout(connectionTimeout);
    con.setReadTimeout(readTimeout);
    con.setInstanceFollowRedirects(followRedirects);
    return address + " " + con.getResponseCode();
  } catch (Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stackTrace = sw.toString();
    return address + " " + stackTrace.substring(0, stackTrace.indexOf("\n"));
  }
}



public static Function<Integer, Function<Boolean, Function<String, Function<String, String>>>>
    cwget(int v) {
  return v1 -> v2 -> v3 -> v4 -> wget(v, v1, v2, v3, v4);
}

var get = cwget(100).apply(100).apply(false).apply("GET");


{
List.of(
        "https://www.google.com",
        "https://www.wikipedia.org",
        "asdf",
        "https://docs.oracle.com/javase/10/docs/api/java/net/package-summary.html",
        "https://jsedano.dev",
        "https://raw.githubusercontent.com/center-key/clabe-validator/main/clabe.ts")
    .parallelStream()
    .map(get)
    .forEach(System.out::println);
}

/exit
