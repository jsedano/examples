package dev.jsedano.examples.nospringwebthymeleaf;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

  public static void main(String[] args) throws Exception {
    // From Java 10, you can use var instead of declaring the type explicitly
    var resolver = new ClassLoaderTemplateResolver();
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCharacterEncoding("UTF-8");
    resolver.setPrefix("/templates/");
    resolver.setSuffix(".html");

    var templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(resolver);

    // Set the port number.
    int port = 7890;
    ServerSocket welcomeSocket = new ServerSocket(port);

    while (true) {
      Socket connectionSocket = welcomeSocket.accept();

      // Construct an object to process the HTTP request message.
      RequestProcessor request = new RequestProcessor(connectionSocket, templateEngine);

      // Create a new thread to process the request.
      Thread thread = new Thread(request);

      // Start the thread.
      thread.start();
    }
  }
}
