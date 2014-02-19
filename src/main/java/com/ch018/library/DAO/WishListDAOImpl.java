package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.WishList;

/**
 * 
 * @author win7
 */
@Component
public class WishListDAOImpl implements WishListDAO {

	private static Logger log = LogManager.getLogger(WishListDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addWish(WishList wish) {
		try {
			sessionFactory.getCurrentSession().save(wish);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void deleteWish(WishList wish) {
		try {
			sessionFactory.getCurrentSession().delete(wish);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public Collection<WishList> getAllWishes() {
		ArrayList<WishList> wish = new ArrayList<WishList>();
		try {
			wish.addAll(sessionFactory.getCurrentSession()
					.createCriteria(WishList.class).list());
		} catch (Exception e) {
			log.error(e);
		}
		return wish;
	}

	@Override
	public WishList getWishById(int id) {
		WishList wish = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			wish = (WishList) session.get(WishList.class, id);
		} catch (Exception e) {
			log.error(e);
		}
        session.clear();        
		return wish;
	}

    @Override
    public ArrayList<WishList> getWishesByPerson(int personId) {
       ArrayList<WishList> wish = new ArrayList<WishList>();
       try {
              wish.addAll(sessionFactory.getCurrentSession()
                  .createCriteria(WishList.class)
                  .add(Restrictions.eq("person.id", personId)).list());
       } catch (Exception e) {
               log.error(e);
           System.out.println(e);
       }
       return wish;
    }

    @Override
    public void deleteWishById(int id) {
      try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery("deleteWish")
					.setInteger("id", id);
			int g = query.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
    }

    @Override
    public boolean bookExistInWishList(int bookId, int personId) {
        boolean exist=true;
        try {
             Query query = sessionFactory.getCurrentSession()
                          .createQuery("FROM WishList W WHERE W.person.id=:person And W.book.id=:book")
                          .setParameter("person", personId)
                          .setParameter("book", bookId);
           
                 if (query.list().isEmpty()) 
                        exist = false;
                 
        } catch (Exception e){
            log.error(e);
        }
        return exist;
    }

	@Override
	public ArrayList<WishList> getWishesByPerson(String personEmail) {
		ArrayList<WishList> wish = new ArrayList<WishList>();
	        try {
	             wish.addAll(sessionFactory.getCurrentSession()
                         .createCriteria(WishList.class)
	                 .add(Restrictions.eq("person.email", personEmail)).list());
	           
	       } catch (Exception e) {
	           log.error(e.getMessage());
	       }
	       return wish;
	}

    @Override
    public WishList getWishWithoutId(int bookId, int personId) {
		WishList w = new WishList();
		ArrayList<WishList> wish = new ArrayList<WishList>();
		try {
			wish.addAll(sessionFactory.getCurrentSession()
					.createCriteria(WishList.class)
					.add(Restrictions.eq("person.id", personId))
					.add(Restrictions.eq("book.id", bookId)).list());
			w = wish.get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return w;
    }
    
    @Override
    public long getCountByPerson(String name) {
    	long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(*) from WishList W where W.person.email=:name")
					.setString("name", name);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return count;
    }

}
