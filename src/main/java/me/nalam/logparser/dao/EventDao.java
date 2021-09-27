package me.nalam.logparser.dao;

import java.util.List;
import java.util.Optional;
import me.nalam.logparser.entities.Event;
import me.nalam.logparser.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDao implements Dao<Event> {

  private final Logger log = LoggerFactory.getLogger(EventDao.class);

  @Override
  public Optional<Event> get(long id) {
    throw new UnsupportedOperationException("Operation not supported yet");
  }

  @Override
  public Optional<Event> get(String id) {
    throw new UnsupportedOperationException("Operation not supported yet");
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Event> getAll() {
    throw new UnsupportedOperationException("Operation not supported yet");
  }

  @Override
  public void save(Event event) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try {
      session.save(event);
      transaction.commit();
    } catch (HibernateException e) {
      transaction.rollback();
      log.error(e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  @Override
  public void update(Event event, String[] params) {
    throw new UnsupportedOperationException("Operation not supported yet");
  }

  @Override
  public void delete(Event event) {
    throw new UnsupportedOperationException("Operation not supported yet");
  }
}
