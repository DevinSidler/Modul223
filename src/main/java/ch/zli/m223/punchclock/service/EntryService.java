package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    public EntryService() {
    }

    @Transactional 
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }

    @Transactional
    public void deleteEntry(Long id){

        Entry removeEntry = getEntryById(id);

        entityManager.remove(removeEntry);
    }

    @Transactional
    public Entry getEntryById(Long id){
      return entityManager.find(Entry.class, id);
    };

    @Transactional
    public void update(Entry entry){
        entityManager.merge(entry);
    }

}
