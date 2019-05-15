package jpabook.start;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestJpa {
    private Member memberA;
    private Member memberB;

    @Before
    public void setUp() {
        memberA = new Member();
        memberA.setId("id1");
        memberA.setAge(22);
        memberA.setUsername("user1");

        memberB = new Member();
        memberB.setId("id2");
        memberB.setAge(25);
        memberB.setUsername("user2");
    }

    @Test
    public void registJpa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(memberA);
        em.persist(memberB);

        transaction.commit();
    }

    @Test
    public void updateJpa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Member member1 = em.find(Member.class, "id1");

        member1.setUsername("hi");
        member1.setAge(22);

        transaction.commit();
    }

    @Test
    public void deleteJpa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Member member1 = em.find(Member.class, "id1");
        em.remove(member1);
        Member member2 = em.find(Member.class, "id2");
        em.remove(member2);

        transaction.commit();
    }
}
