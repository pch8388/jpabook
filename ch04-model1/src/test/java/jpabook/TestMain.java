package jpabook;

import jpabook.model.entity.Member;
import jpabook.model.entity.Team;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestMain {
    @Test
    public void testORM_양방향() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("회원1");

        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("회원2");

        member2.setTeam(team1);
        em.persist(member2);

        tx.commit();
    }
}
