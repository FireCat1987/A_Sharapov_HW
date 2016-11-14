package inno.util.validators;

import inno.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Controller
public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Post.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Post post = (Post) o;
        if(StringUtils.isEmpty(post.getTitle()) || post.getTitle().length() < 5) {
            errors.rejectValue("title",""," ошибка заголовка");
        }
        if(StringUtils.isEmpty(post.getText()) || post.getText().length() < 10) {
            errors.rejectValue("text",""," ошибка текста");
        }
    }
}
