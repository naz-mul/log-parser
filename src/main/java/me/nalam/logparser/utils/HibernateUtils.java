package me.nalam.logparser.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtils {
  private static final Logger log = LoggerFactory.getLogger(HibernateUtils.class);
  private static final SessionFactory sessionFactory;

  static {
    try {
      sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      log.error("Initial SessionFactory creation failed", ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  private HibernateUtils() {}

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
