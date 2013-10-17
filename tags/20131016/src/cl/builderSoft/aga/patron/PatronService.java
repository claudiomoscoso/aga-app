package cl.builderSoft.aga.patron;

import java.util.List;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;

public interface PatronService {
    public static String ALIAS = "SLC";
public static String CLASS_NAME = "cl.builderSoft.aga.patron.PatronServiceImpl";
    public PatronBean searchById(String id) throws BSException;

    public boolean existsById(String id) throws BSException;

//    public void saveUser(PatronBean userBean) throws BSException;

    public void setAttribute(PatronBean userBean, String attributeName, String attributeValue) throws BSProgrammerException;

    public String getAttribute(PatronBean userBean, String attributeName) throws BSProgrammerException;

    public String toXML(PatronBean user);

    public String toXML(PatronBean user, String nodeName);

    public String toXML(List users);

    public String toXML(List users, String nodeName);
}