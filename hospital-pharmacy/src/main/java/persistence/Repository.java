package persistence;

import java.util.List;

public interface Repository<ID, E> {
    E getOne(ID id);
    List<E> getAll();
    void add(E entity);
    void delete(ID id);
    void update(E entity);
}
