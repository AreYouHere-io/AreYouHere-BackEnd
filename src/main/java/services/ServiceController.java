package services;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import model.Model;
import model.Session;
import model.Student;
import org.springframework.web.bind.annotation.*;

// GreetingController below handles GET requests for /greeting
// by returning a new instance of the Greeting class:
@RestController
public class ServiceController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    //    http://localhost:8080,
    @CrossOrigin
    @RequestMapping(path = "/")
    public String get() {
        return "Home Page \nThere are " + Model.getInstance().getNumOfStudent() + " students!";
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = RestAPIConstants.GET_STUDENTS)
    @ResponseBody
    public Set<Student> getAllStudent() {
        return Model.getInstance().getStudents();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = RestAPIConstants.GET_DUM_SESSION)
    @ResponseBody
    public Session getDumpSession() {
        return Model.getInstance().getDummySession();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = RestAPIConstants.RESET_SESSION)
    @ResponseBody
    public Response resetDumpSession() {
        Model.getInstance().getDummySession().resetSession();
        Response response = new Response();
        response.setStatus(true);
        response.setMessage("Reset done");
        return response;
    }

    @CrossOrigin
    @RequestMapping(value = RestAPIConstants.GET_KEY, method = RequestMethod.GET)
    @ResponseBody
    public Response generateKey() {
        Model.getInstance().genPrivateKey();
        Response response = new Response();
        response.setStatus(true);
        response.setMessage(Model.getInstance().getPrivateKey());
        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value=RestAPIConstants.STUDENT_CHECKIN)
    @ResponseBody
    public Response studentCheckin(@RequestBody String studentIdAndKey) {
        Response response = new Response();
        response.setStatus(false);
        String[] ss = studentIdAndKey.split("\\?");
        if (ss.length == 2) {
            boolean ok = Model.getInstance().isMatchedKey(ss[1]);
            if (ok) {
                response.setStatus(Model.getInstance().getDummySession().setAttendant(ss[0], true));
            }
        }
        if ( response.isStatus() ) response.setMessage("You are HERE");
        else response.setMessage("You are NOT here");

        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value=RestAPIConstants.PROF_CHECKIN)
    @ResponseBody
    public Response ProfCheckin(@RequestBody String studentId) {
        Response response = new Response();
        boolean added = Model.getInstance().getDummySession().setAttendant(studentId, true);
        response.setStatus(added);
        if ( added ) response.setMessage("Sucessfully");
        else response.setMessage("You are not here");
        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = RestAPIConstants.UPDATE_SESSION)
    @ResponseBody
    public Response updateSession(@RequestBody Session session) {
        Model.getInstance().updateSession(session);
        Response response = new Response();
        response.setStatus(true);
        response.setMessage("DONE");
        return response;
    }
}
