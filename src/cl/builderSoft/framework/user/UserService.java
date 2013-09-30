package cl.builderSoft.framework.user;

import java.util.List;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;

public interface UserService {
    public static String ALIAS = "USR";
public static String CLASS_NAME = "cl.builderSoft.framework.user.UserServiceImpl";
    public UserBean searchByLogin(String login) throws BSException;

    public UserBean searchById(String id) throws BSException;

    public boolean existsByLogin(String login) throws BSException;

    public boolean existsById(String id) throws BSException;

    public List listByRol(String rol) throws BSException;

    public void saveUser(UserBean userBean) throws BSException;

    public void setAttribute(UserBean userBean, String attributeName, String attributeValue) throws BSProgrammerException;

    public String getAttribute(UserBean userBean, String attributeName) throws BSProgrammerException;

    public String toXML(UserBean user);

    public String toXML(UserBean user, String nodeName);

    public String toXML(List users);

    public String toXML(List users, String nodeName);
}