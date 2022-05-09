package persistence.impl;

import model.Order;
import org.hibernate.SessionFactory;
import persistence.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final SessionFactory sessionFactory;

    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order getOne(Integer integer) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void add(Order entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Order entity) {

    }
}
