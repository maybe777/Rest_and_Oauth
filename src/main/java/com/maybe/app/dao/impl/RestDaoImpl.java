package com.maybe.app.dao.impl;

import com.maybe.app.dao.RestDao;
import com.maybe.app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestDaoImpl implements RestDao {

    private static final String URL = "http://localhost:8080/api/user/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<?> getAll() {

        List<?> users = new ArrayList<>();

        try {
            users = restTemplate.getForEntity(URL, List.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(long id) {

        String url = URL + id;
        return getUser(url);
    }

    @Override
    public User getByName(String username) {

        String url = URL + "name/" + username;
        return getUser(url);
    }

    @Override
    public void delete(long id) {

        try {
            String url = URL + id;
            restTemplate.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(User user) {

        try {
            restTemplate.put(URL, user, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {

        try {
            restTemplate.postForEntity(URL, user, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method returns user
    private User getUser(String url) {
        User user = new User();

        try {
            user = restTemplate.getForEntity(url, User.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
