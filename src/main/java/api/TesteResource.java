package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class TesteResource {
    @GET
    @Produces("text/plain;charset=UTF-8")
    public String getClichedMessage() {
        return "Hello World";
    }

}
