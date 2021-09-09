package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Project;

@ApplicationScoped
public class ProjectService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Project createProject(Project project) {
        entityManager.persist(project);
        return project;
    }

    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        var query = entityManager.createQuery("FROM Project");
        return query.getResultList();
    }

    @Transactional
    public void deleteProject(Long id){

        Project removeProject = getProjectById(id);

        entityManager.remove(removeProject);
    }

    @Transactional
    public Project getProjectById(Long id){
        return entityManager.find(Project.class, id);
    };

    @Transactional
    public void update(Project project){
        entityManager.merge(project);
    }

}
