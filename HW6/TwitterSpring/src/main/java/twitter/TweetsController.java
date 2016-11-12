package twitter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


@Controller
@RequestMapping("/main")
public class TweetsController {


    @Resource(name="TweetService")
    private TweetService tweetService;

    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public String getTweets(Model model) {

        List<Tweet> tweets = tweetService.getAllTweets();
        model.addAttribute("tweets", tweets);
        return "tweets";
    }

    @RequestMapping(value = "/twitter", method = RequestMethod.POST)
    public String addTweet(@RequestParam(value="message", required=true) String message,
                           Model model) {

        if(!message.equals("")) {
            tweetService.addTweet(message);
            getTweets(model);
        }
        return "tweets";
    }

    @RequestMapping(value = "/twitter", method = RequestMethod.PUT)
    public String editTweet(@RequestParam(value="id", required=true) Integer id, @RequestBody String body, Model model) throws UnsupportedEncodingException {

        if(!body.equals("")) {
            body = URLDecoder.decode(body.split("&")[0].split("=")[1], "UTF-8");
            tweetService.editTweet(id, body);
            model.addAttribute("message",body);
        }
        return "tweet";
    }

    @RequestMapping(value = "/twitter", method = RequestMethod.DELETE)
    public String deleteTweet(@RequestParam(value="id", required=true) Integer id, Model model) {

        tweetService.delTweet(id);
        model.addAttribute("redirect", "/twitter");
        return "tweets";
    }
}