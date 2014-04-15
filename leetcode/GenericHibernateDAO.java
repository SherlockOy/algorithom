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
	// ����ʵ�壨�־ã������������
    private Class<T> persistentClass;

	// ���췽�����������Ϊʵ���������
    public GenericHibernateDAO(Class<T> persistentClass) 
    {
        this.persistentClass = persistentClass;
}

	// �õ��־û����������
    public Class<T> getPersistentClass() 
    {
        return persistentClass;
    }
    
     /**
     * �õ���ǰ�̵߳�Session�����ʵ��
     * 
     * @return
     */
    protected Session getSession() 
    {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
	 * ͨ��ID���õ�ʵ�����
	 * 
	 * @param id ʵ�����ı�ʶ��
	 * @param lock ʹ�õ���ģʽ
	 * @return ������ֵ��Ӧ��ʵ�����
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
     * �õ����е�ʵ�����
     * 
     * @return ���е�ʵ�����
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() 
    {
        return findByCriteria();
    }

    /**
     * ���ݶ�������Ժ����Ե�ֵ���õ�����������ʵ�����
     * 
     * @param exampleInstance ��ѯ������
     * @return ����������ʵ�����
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance) 
    {
        return findByCriteria( Example.create(exampleInstance) );
    }

    /**
     * ��ʵ�����־û�
     * 
     * @param entity ��Ҫ���г־û�������ʵ�����
     * @return �־û���ʵ�����
     */
    @SuppressWarnings("unchecked")
    public T makePersistent(T entity) 
    {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     * ��ʵ������Ϊ˲̬
     * 
     * @param entity ��Ҫת��Ϊ˲̬��ʵ�����
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
