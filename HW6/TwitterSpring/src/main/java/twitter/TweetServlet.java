package twitter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/tweet/*")
public class TweetServlet extends HttpServlet {
    private TweetService service;

    @Override
    public void init() throws ServletException {
        service = new TweetService();
        super.init();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String line = reader.readLine();
        if(!line.equals("")) {
            System.out.println("Delete comment id " + Integer.parseInt(line));
            service.delComment(Integer.parseInt(line));
        }
        System.out.println(req.getRequestURI());
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("/twitter");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getRequestURI());
        String[] split = req.getRequestURI().split("/");
        if (split.length == 3) {
            int l = Integer.parseInt(split[2]);
            Tweet tweet = service.getTweetById(l);
            List<Comment> commentList = service.getCommentsById(l);
            req.setAttribute("tweet", tweet);
            req.setAttribute("comments",commentList);
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("backcolor")) {
                    req.setAttribute(cookie.getName(), cookie.getValue());
                }
            }
            req.getRequestDispatcher("/tweet.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/tweeter");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String[] split = req.getRequestURI().split("/");
        if (split.length == 3) {
            int l = Integer.parseInt(split[2]);
            String message = req.getParameter("message");
            service.addComment(message, l);
            resp.sendRedirect("/tweet/" + l);
        }
    }
}