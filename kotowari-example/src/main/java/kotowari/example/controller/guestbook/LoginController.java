package kotowari.example.controller.guestbook;

import enkan.collection.Parameters;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import enkan.data.Session;
import enkan.util.HttpResponseUtils;
import kotowari.component.TemplateEngine;
import kotowari.example.dao.CustomerDao;
import kotowari.example.entity.Customer;
import kotowari.example.model.LoginPrincipal;

import javax.inject.Inject;

import static enkan.util.BeanBuilder.builder;
import static kotowari.routing.UrlRewriter.redirect;

/**
 * @author kawasima
 */
public class LoginController {
    @Inject
    private DomaProvider daoProvider;

    @Inject
    private TemplateEngine templateEngine;

    public HttpResponse loginForm(Parameters params) {
        return templateEngine.render("guestbook/login",
                "url", params.get("url"));
    }

    public HttpResponse login(Parameters params) {
        CustomerDao dao = daoProvider.getDao(CustomerDao.class);
        String email = params.get("email");
        Customer customer = dao.loginByPassword(email, params.get("password"));
        if (customer == null) {
            return templateEngine.render("guestbook/login");
        } else {
            Session session = new Session();
            session.setAttribute("principal", new LoginPrincipal(email));
            return builder(redirect(GuestbookController.class, "list", HttpResponseUtils.RedirectStatusCode.SEE_OTHER))
                    .set(HttpResponse::setSession, session)
                    .build();
        }
    }
}
