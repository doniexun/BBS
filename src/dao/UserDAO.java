package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vo.User;

import java.util.List;

public interface UserDAO  {
    public Session getSession();
    public SessionFactory getSessionFactory();
    public User validateUser(String username, String password);
    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public boolean isExistUser(String username);
    public List getAllUsersExceptSuperAdmin(User user,int currentPage,int totalItemsPerPage);
    public List getAllUsersExceptSuperAdmin(User user);
    public User getUser(int id);
    public User getUserByUsername(String username);
}
