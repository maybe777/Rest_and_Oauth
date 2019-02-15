package com.maybe.app.dao;

import com.maybe.app.model.User;
import java.util.List;

public interface RestDao {

    List getAll();

    User getById(long id);

    User getByName(String username);

    void delete(long id);

    void edit(User user);

    void add(User user);

}
