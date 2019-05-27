package com.mmolegion.core.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.Map;

@Repository
public abstract class GenericDao {

    private static final Logger logger = LogManager.getLogger(GenericDao.class);

    @SuppressWarnings("unchecked")
    <E> List<E> executeQuery(EntityManager em, String query, Class<E> clazz) {
        Query qry = em.createQuery(query, clazz);

        return qry.getResultList();
    }

    @SuppressWarnings("unchecked")
    <E> List<E> executeQuery(EntityManager em, String query, Map<String, Object> params, Class<E> clazz) {
        Query qry = em.createQuery(query, clazz);
        setQueryParams(qry, params);

        return qry.getResultList();
    }

    int executeUpdate(EntityManager em, String query, Map<String, Object> params) {
        Query qry = em.createQuery(query);
        setQueryParams(qry, params);

        return qry.executeUpdate();
    }

    private void setQueryParams(Query query, Map<String, Object> params) {
        logger.debug("Setting query parameters.");

        if(params != null) {
            for(Map.Entry<String, Object> entry: params.entrySet()) {
                logger.debug("Key: " + entry.getKey() + " | Value: " + entry.getValue());
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }
}
