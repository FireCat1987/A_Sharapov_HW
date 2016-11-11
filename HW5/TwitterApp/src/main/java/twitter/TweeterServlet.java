package twitter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet(urlPatterns = "/twitter")
public class TweeterServlet extends HttpServlet {

    private TweetService service;

    @Override
    public void init() throws ServletException {
        service = new TweetService();
        super.init();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String line = reader.readLine();
        if(!line.equals("")) {
            line = URLDecoder.decode(line.split("&")[0].split("=")[1], "UTF-8");
            service.editTweet(Integer.parseInt(req.getParameter("id")), line);
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(line);
        } else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Изменение не удалось, входящая строка пуста! Пожалуйста обновите страницу");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        List<Tweet> tweets = service.getAllTweets();
        req.setAttribute("tweets", tweets);
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("backcolor")) {
                req.setAttribute(cookie.getName(), cookie.getValue());
            }
        }
        req.getRequestDispatcher("/tweets.jsp").forward(req, resp);


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String line = reader.readLine();
        if(!line.equals("")) {
            System.out.println("Delete tweet id " + Integer.parseInt(line));
            service.delTweet(Integer.parseInt(line));
        }
        resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        resp.getWriter().write("/twitter");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String message = req.getParameter("message");
        if(!message.equals("")) {
            service.addTweet(message);
            resp.sendRedirect("/twitter");
        }
    }
}