package chap4.info.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import chap4.info.dao.GenericDAO;
import chap4.info.util.HibernateUtil;

public class GenericHibernateDAO<T, ID extends Serializable> 
{
	// 保持实体（持久）对象类的类型
    private Class<T> persistentClass;

	// 构造方法，传入参数为实体对象类型
    public GenericHibernateDAO(Class<T> persistentClass) 
    {
        this.persistentClass = persistentClass;
}

	// 得到持久化对象的类型
    public Class<T> getPersistentClass() 
    {
        return persistentClass;
    }
    
     /**
     * 得到当前线程的Session对象的实例
     * 
     * @return
     */
    protected Session getSession() 
    {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
	 * 通过ID来得到实体对象
	 * 
	 * @param id 实体对象的标识符
	 * @param lock 使用的锁模式
	 * @return 该主键值对应的实体对象
	 */
    @SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) 
    {
        T entity;
        if (lock)
            entity = (T) getSession().get(getPersistentClass(), id, LockMode.UPGRADE);
        else
            entity = (T) getSession().get(getPersistentClass(), id);

        return entity;
    }

    /**
     * 得到所有的实体对象
     * 
     * @return 所有的实体对象
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() 
    {
        return findByCriteria();
    }

    /**
     * 根据对象的属性和属性的值来得到满足条件的实体对象
     * 
     * @param exampleInstance 查询的条件
     * @return 满足条件的实体对象
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance) 
    {
        return findByCriteria( Example.create(exampleInstance) );
    }

    /**
     * 将实体对象持久化
     * 
     * @param entity 需要进行持久化操作的实体对象
     * @return 持久化的实体对象
     */
    @SuppressWarnings("unchecked")
    public T makePersistent(T entity) 
    {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     * 将实体对象变为瞬态
     * 
     * @param entity 需要转变为瞬态的实体对象
     */
    public void makeTransient(T entity) 
    {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) 
    {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) 
		{
		    crit.add(c);
		}
		return crit.list();
	}

         
}
