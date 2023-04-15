package dev.jsedano.examples.nospringwebthymeleaf;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

final class RequestProcessor implements Runnable {
  static final String CRLF = "\r\n";
  private Socket socket;

  private TemplateEngine templateEngine;

  // Constructor
  public RequestProcessor(Socket socket, TemplateEngine templateEngine) throws Exception {
    this.socket = socket;
    this.templateEngine = templateEngine;
  }

  // Implement the run() method of the Runnable interface.
  public void run() {
    try {
      processRequest(this.socket);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void processRequest(Socket request) throws Exception {
    System.out.println("Debug: got new client " + request.toString());
    BufferedReader br =
        new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

    StringBuilder requestBuilder = new StringBuilder();
    String line;
    int contentLength = 0;

    while (!(line = br.readLine()).isBlank()) {
      requestBuilder.append(line + "\r\n");
      if (line.toLowerCase().startsWith("content-length")) {
        contentLength = Integer.parseInt(line.split(":")[1].trim());
      }
    }

    StringBuilder requestBodyBuilder = new StringBuilder();
    if (contentLength > 0) {
      int read;
      while ((read = br.read()) != -1) {
        requestBodyBuilder.append((char) read);
        if (requestBodyBuilder.length() == contentLength) break;
      }
      requestBuilder.append("\r\n" + requestBodyBuilder);
    }

    String stringRequest = requestBuilder.toString();
    System.out.println(stringRequest);

    String fileName =
        stringRequest.substring(stringRequest.indexOf('/'), stringRequest.indexOf(".html") + 5);
    String reversedString = stringRequest.substring(stringRequest.lastIndexOf('=') + 1);

    DataOutputStream os = new DataOutputStream(this.socket.getOutputStream());

    // Construct the response message.

    String statusLine = null;
    String contentTypeLine = null;

    statusLine = "HTTP/1.1 200 OK" + CRLF;
    contentTypeLine = "Content-type: text/html" + CRLF;

    // Send the status line.
    os.writeBytes(statusLine);

    // Send the content type line.
    os.writeBytes(contentTypeLine);

    // Send a blank line to indicate the end of the header lines.
    os.writeBytes(CRLF);
    Context context = new Context();
    context.setVariable(
        "reversedString", new StringBuilder(reversedString.replace('+', ' ')).reverse().toString());
    os.writeBytes(templateEngine.process(fileName, context));

    // Close streams and socket.
    os.close();
    br.close();
    socket.close();
  }
}
