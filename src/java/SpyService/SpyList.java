package SpyService;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class SpyList wraps a bunch of spy information into an single object.The
 * class uses a tree map structure to dynamically hold spies.The entire
 * information of spies could be retrieved both in simple string format and XML
 * string format.
 *
 * @author Michael J. McCarthy, Qifan Shi
 * @version 1.0 Last Modified: 10/24/2014
 */
public class SpyList {

    private Map tree = new TreeMap();

    private static SpyList spyList = new SpyList();

    private SpyList() {
    }

    /**
     * 
     * @return a reference of spy tree.
     */
    public static SpyList getInstance() {
        return spyList;
    }

    /**
     *
     * @param s a spy object to be added in the spy list.
     */
    public void add(Spy s) {
        tree.put(s.getName(), s);
    }

    /**
     *
     * @param s a reference to the spy object to be removed from the list.
     * @return a reference of the spy removed from the list.
     */
    public Object delete(Spy s) {
        return tree.remove(s.getName());
    }

    /**
     *
     * @param userID a String of userID
     * @return the spy reference with parameter userID. 
     */
    public Spy get(String userID) {
        return (Spy) tree.get(userID);
    }

    /**
     *
     * @return a collection of all spy information in the list.
     */
    public Collection getList() {
        return tree.values();
    }

    /**
     * 
     * @return a simple string format of all spy information.
     */
    @Override
    public String toString() {

        StringBuffer representation = new StringBuffer();
        Collection c = getList();
        Iterator sl = c.iterator();
        while (sl.hasNext()) {
            Spy spy = (Spy) sl.next();
            representation.append("Name: " + spy.getName() + " Title: " + spy.getTitle()
                    + " Location: " + spy.getLocation());
        }
        return representation.toString();
    }

    /**
     *
     * @return an XML string with all spy information.
     */
    public String toXML() {
        StringBuffer xml = new StringBuffer();
        xml.append("<spylist>\n");

        Collection c = getList();
        Iterator sl = c.iterator();
        while (sl.hasNext()) {
            Spy spy = (Spy) sl.next();
            xml.append(spy.toXML());
        }
        // Now, close
        xml.append("</spylist>");

        System.out.println("Spy list: " + xml.toString());
        return xml.toString();
    }
}
