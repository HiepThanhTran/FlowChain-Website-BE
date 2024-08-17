package com.fh.scm.repository.implement;

import com.fh.scm.pojo.Category;
import com.fh.scm.pojo.Shipper;
import com.fh.scm.repository.ShipperRepository;
import com.fh.scm.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class ShipperRepositoryImplement implements ShipperRepository {

    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(factory.getObject()).getCurrentSession();
    }

    @Override
    public Shipper get(UUID id) {
        Session session = this.getCurrentSession();

        return session.get(Shipper.class, id);
    }

    @Override
    public void insert(Shipper shipper) {
        Session session = this.getCurrentSession();
        session.save(shipper);
    }

    @Override
    public void update(Shipper shipper) {
        Session session = this.getCurrentSession();
        session.update(shipper);
    }

    @Override
    public void delete(UUID id) {
        Session session = this.getCurrentSession();
        Shipper shipper = session.get(Shipper.class, id);
        session.delete(shipper);
    }

    @Override
    public void softDelete(UUID id) {
        Session session = this.getCurrentSession();
        Shipper shipper = session.get(Shipper.class, id);
        shipper.setActive(false);
        session.update(shipper);
    }

    @Override
    public void insertOrUpdate(Shipper shipper) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(shipper);
    }

    @Override
    public Long count() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Shipper> root = criteria.from(Shipper.class);

        criteria.select(builder.count(root));
        Query<Long> query = session.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public Boolean exists(UUID id) {
        Session session = this.getCurrentSession();
        Shipper shipper = session.get(Shipper.class, id);

        return shipper != null;
    }

    @Override
    public List<Shipper> getAll(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Shipper> criteria = builder.createQuery(Shipper.class);
        Root<Shipper> root = criteria.from(Shipper.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("isActive"), true));

        if (params != null) {
            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", name)));
            }
        }

        criteria.select(root).where(predicates.toArray(Predicate[]::new));
        Query<Shipper> query = session.createQuery(criteria);
        Pagination.paginator(query, params);

        return query.getResultList();
    }
}