package com.nixsolutions.jpa_hibernate;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public class DBUnitConfig extends DBTestCase {
    protected Session session;

    public DBUnitConfig() throws IOException {
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "org.postgresql.Driver");
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:postgresql://localhost:5432/postgres");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                "postgres");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                "");
    }

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        super.setUp();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        throw new IllegalArgumentException(
                "Need data set for the next test: " + this.getClass()
                        .getSimpleName());
    }

    @After
    protected void tearDown() throws Exception {
        session.close();
        super.tearDown();
    }
}
