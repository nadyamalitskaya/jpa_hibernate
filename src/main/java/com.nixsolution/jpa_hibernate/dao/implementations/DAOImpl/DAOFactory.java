package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl.*;
import com.nixsolution.jpa_hibernate.dao.interfaces.IDAOFactory;

public class DAOFactory implements IDAOFactory {
    private static IDAOFactory factory;

    private DAOFactory() {
    }

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }

        return factory;
    }

    @Override
    public AuthorServiceImpl getAuthorService() {
        return new AuthorServiceImpl();
    }

    @Override
    public UserRoleServiceImpl getUserRoleService() {
        return new UserRoleServiceImpl();
    }

    @Override
    public UserLoginServiceImpl getUserLoginService() {
        return new UserLoginServiceImpl();
    }

    @Override
    public SystemServiceImpl getSystemUserService() {
        return new SystemServiceImpl();
    }

    @Override
    public BookServiceImpl getBookService() {
        return new BookServiceImpl();
    }

    @Override
    public CompositionCommentServiceImpl getCompositionCommentService() {
        return new CompositionCommentServiceImpl();
    }

    @Override
    public GenreServiceImpl getGenreService() {
        return new GenreServiceImpl();
    }

    @Override
    public ItemServiceImpl getItemService() {
        return new ItemServiceImpl();
    }

    @Override
    public StatusCommentServiceImpl getStatusCommentService() {
        return new StatusCommentServiceImpl();
    }

    @Override
    public OrderStatusServiceImpl getOrderStatusService() {
        return new OrderStatusServiceImpl();
    }

    @Override
    public UserOrderServiceImpl getUserOrderService() {
        return new UserOrderServiceImpl();
    }

    @Override
    public ItemStatusServiceImpl getItemStatusService() {
        return new ItemStatusServiceImpl();
    }
}