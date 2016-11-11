package twitter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/main")
public class TweetsController {
    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name="tweetService")
    private TweetService tweetService;

    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public String getTweets(Model model) {
        logger.debug("Received request to show all persons");
        List<Tweet> tweets = tweetService.getAllTweets();
        model.addAttribute("tweets", tweets);
        return "tweets";
    }
}