package com.blogspot.lifeinide.clibernate.respository;

import com.blogspot.lifeinide.clibernate.model.BaseEnity;
import org.hibernate.Query;

/**
 * Base repository interface. Implement your own repositories using:
 * <pre>{@code
 * @literal@Repository
 * class MyRespository extends BaseRepository {
 * }
 * }</pre>
 *
 * @author l0co@wp.pl
 */
public interface IRepository<T extends BaseEnity> {

	T get(long id);

	Long save(T object);

	void delete(T object);

	void deleteAll();

	Query findAll();

}
