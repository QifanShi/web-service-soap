package SpyService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * The SpyService class provides web service to clients.Service includes add a
 * new spy to spy list, delete and existing spy from the current spy list, and
 * retrieve all spy information in simple string format or XML string.
 *
 * @author Qifan Shi
 * @version 1.0 Last Modified: 10/24/2014
 */
@WebService(serviceName = "SpyService")
public class SpyService {

    private SpyList spylist;

    /**
     * initiate a new spy list.
     */
    public SpyService() {
        this.spylist = SpyList.getInstance();
    }

    /**
     * Web service operation: add a new spy in spy list.
     *
     * @param name the name of the spy
     * @param title the title of the spy
     * @param location the location of the spy
     * @param password the password used by the spy
     * @return information indicating the result of add operation.
     */
    @WebMethod(operationName = "addSpy")
    public String addSpy(@WebParam(name = "name") String name,
            @WebParam(name = "title") String title,
            @WebParam(name = "location") String location,
            @WebParam(name = "password") String password) {

        String returnstr = "";
        // check if the spy with the given name exists.
        if (spylist.get(name) == null) {
            Spy newspy = new Spy(name, title, location, password);
            spylist.add(newspy);
            returnstr = "Spy " + name + " was added to the list";
        } else {
            returnstr = "Spy " + name + " was already in the list";
        }
        return "Spy " + name + " was added to the list";
    }

    /**
     * Web service operation: delete a spy from existing spy list.
     *
     * @param name the name of the spy
     * @return the result string indicating whether a spy is removed or not.
     */
    @WebMethod(operationName = "deleteSpy")
    public String deleteSpy(@WebParam(name = "name") String name) {

        String returnstr = "";
        // check if the spy is in the list.
        if (spylist.get(name) == null) {
            returnstr = "Spy " + name + " was not in the list";
        } else {
            spylist.delete(new Spy(name));
            returnstr = "Spy " + name + " was removed from the list";
        }
        return returnstr;
    }

    /**
     * Web service operation: retrieve spies information in simple string.
     *
     * @return a string contains all spies information.
     */
    @WebMethod(operationName = "getList")
    public String getList() {
        return spylist.toString();
    }

    /**
     * Web service operation: retrieve spies information in XML string.
     *
     * @return an XML string contains all spies information.
     */
    @WebMethod(operationName = "getListAsXML")
    public String getListAsXML() {
        return spylist.toXML();
    }
}
