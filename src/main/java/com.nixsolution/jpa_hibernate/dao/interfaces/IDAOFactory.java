package com.nixsolution.jpa_hibernate.dao.interfaces;

import com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl.*;

public interface IDAOFactory {

    AuthorServiceImpl getAuthorService();

    UserRoleServiceImpl getUserRoleService();

    UserLoginServiceImpl getUserLoginService();

    SystemServiceImpl getSystemUserService();

    BookServiceImpl getBookService();

    CompositionCommentServiceImpl getCompositionCommentService();

    GenreServiceImpl getGenreService();

    ItemServiceImpl getItemService();

    StatusCommentServiceImpl getStatusCommentService();

    OrderStatusServiceImpl getOrderStatusService();

    UserOrderServiceImpl getUserOrderService();

    ItemStatusServiceImpl getItemStatusService();
}
