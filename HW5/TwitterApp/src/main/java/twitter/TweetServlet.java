package twitter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/tweet/*")
public class TweetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getRequestURI());
        String[] split = req.getRequestURI().split("/");
        if (split.length == 3) {
            long l = Long.parseLong(split[2]);
            Tweet tweet = TweetService.getTweetById(l);
            List<Comment> commentList = TweetService.getCommentById(l);
            req.setAttribute("tweet", tweet);
            req.setAttribute("comments",commentList);
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
            long l = Long.parseLong(split[2]);
            String message = req.getParameter("message");
            TweetService.addComment(l, message);
            resp.sendRedirect("/tweet/" + l);
        }
    }
}