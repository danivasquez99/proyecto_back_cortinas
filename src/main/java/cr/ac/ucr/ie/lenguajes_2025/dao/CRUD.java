package cr.ac.ucr.ie.lenguajes_2025.dao;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import java.util.LinkedList;

/**
 *
 * @author Daniel
 */
public interface CRUD<T> {
    public abstract LinkedList<T> getAll();
    public abstract void insert(T t);
    public abstract void update(T t);
    public abstract void deleteById(Integer t);
    public abstract T findById(Integer t);
}
