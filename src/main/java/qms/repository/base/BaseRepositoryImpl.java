package qms.repository.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {
	private final EntityManager entityManager;

	// 父类没有不带参数的构造方法，这里手动构造父类
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	// 通过EntityManager来完成查询	
	@SuppressWarnings("unchecked")
	public List<Object[]> listBySQL(String sql) {
		return entityManager.createNativeQuery(sql).getResultList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> findListByNativeSql(String sql, Class<?> clzss){
		 Query query = entityManager.createNativeQuery(sql);
		 query.unwrap(NativeQueryImpl.class).setResultTransformer(new BoTransformerAdapter(clzss)); 
		 return query.getResultList();

	}
}
