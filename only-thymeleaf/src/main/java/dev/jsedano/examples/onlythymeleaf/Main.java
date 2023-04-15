package dev.jsedano.examples.onlythymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class Main {

  public static void main(String[] args) {
    var resolver = new ClassLoaderTemplateResolver();
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCharacterEncoding("UTF-8");
    resolver.setPrefix("/templates/");
    resolver.setSuffix(".html");

    var context = new Context();
    if (args.length != 0) {
      context.setVariable("first_name", args[0]);
      if (args.length > 1) {
        context.setVariable("last_name", args[1]);
      }
    }

    var templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(resolver);

    var result = templateEngine.process("index", context);
    System.out.println(result);
  }
}
