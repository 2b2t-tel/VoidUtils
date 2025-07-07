package net.utils.voidutils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.file.Files;

public class Website {

    public static void startServer(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            // Root page
            server.createContext("/", new MainHandler());

            // Static file handler for everything else
            server.createContext("/files", new StaticFileHandler());

            server.setExecutor(null);
            server.start();

            System.out.println("[VoidUtils] Website started on http://localhost:" + port + "/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Homepage
    static class MainHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = "<html><head><title>" + VoidUtils.WEBSITE_TITLE + "</title></head>" +
                    "<body style='background-color:#111; color:#ccc; font-family:monospace; text-align:center; padding:50px;'>" +
                    "<h1 style='color:#c00; font-size:36px;'>" + VoidUtils.WEBSITE_TITLE + "</h1>" +
                    "<p style='font-size:18px; margin-top:20px;'>" + VoidUtils.WEBSITE_MESSAGE + "</p>" +
                    "<p style='color:#666; font-size:12px; margin-top:40px;'>Running on Bukkit Beta 1.7.3</p>" +
                    "<p><a href='/files/index.html' style='color:#aaa;'>Visit custom index.html</a></p>" +
                    "</body></html>";

            exchange.sendResponseHeaders(200, html.length());
            OutputStream os = exchange.getResponseBody();
            os.write(html.getBytes());
            os.close();
        }
    }

    // Static file serving handler
    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File webroot = new File(VoidUtils.instance.getDataFolder(), "webroot");
            if (!webroot.exists()) webroot.mkdirs();

            String path = URLDecoder.decode(exchange.getRequestURI().getPath().replace("/files", ""), "UTF-8");
            File requested = new File(webroot, path);

            if (!requested.exists() || requested.isDirectory()) {
                String notFound = "404 - File Not Found";
                exchange.sendResponseHeaders(404, notFound.length());
                exchange.getResponseBody().write(notFound.getBytes());
                exchange.getResponseBody().close();
                return;
            }

            String contentType = Files.probeContentType(requested.toPath());
            if (contentType == null) contentType = "application/octet-stream";

            byte[] data = Files.readAllBytes(requested.toPath());
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, data.length);
            OutputStream os = exchange.getResponseBody();
            os.write(data);
            os.close();
        }
    }
}