package com.nixsolutions.jpa_hibernate;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.DAOFactory;
import com.nixsolution.jpa_hibernate.entity.UserRole;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

public class UserRoleDAOTest extends DBUnitConfig {

    public UserRoleDAOTest() throws IOException {
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(
                "./src/test/resources/UserRoleDataSet.xml"));
    }

    public void testGetAllCustomers() {
        Collection<UserRole> listCustomers = DAOFactory.getInstance()
                .getUserRoleService().getAll();
        assertFalse(listCustomers.isEmpty());
    }

    public void testCreateUserRole() {
        Collection<UserRole> userRoles = DAOFactory.getInstance()
                .getUserRoleService().getAll();
        UserRole userRole = new UserRole("king");
        DAOFactory.getInstance().getUserRoleService().create(userRole);
        Collection<UserRole> newUserRoles = DAOFactory.getInstance()
                .getUserRoleService().getAll();
        assertEquals(newUserRoles.size() - userRoles.size(), 1);
    }

    public void testDeleteUserRole() {
        Collection<UserRole> userRoles = DAOFactory.getInstance()
                .getUserRoleService().getAll();
        DAOFactory.getInstance().getUserRoleService().delete(2);
        Collection<UserRole> newUserRoles = DAOFactory.getInstance()
                .getUserRoleService().getAll();
        assertEquals(userRoles.size() - newUserRoles.size(), 1);
    }

    public void testUpdateUserRole() {
        UserRole userRole = DAOFactory.getInstance().getUserRoleService()
                .getById(3);
        userRole.setRoleName("USER");
        DAOFactory.getInstance().getUserRoleService().update(userRole);
        assertEquals(userRole.getRoleName(), "USER");
    }
}
