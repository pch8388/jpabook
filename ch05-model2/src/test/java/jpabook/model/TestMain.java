package jpabook.model;

import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class TestMain {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    @BeforeClass
    public static void setUp() {
        //엔티티 매니저 팩토리 생성
        emf = Persistence.createEntityManagerFactory("jpabook");
        em = emf.createEntityManager(); //엔티티 매니저 생성
    }

    @Before
    public void before() {
        tx = em.getTransaction(); //트랜잭션 기능 획득
        tx.begin();
    }

    @After
    public void after() {
        tx.commit();
    }

    @Test
    public void save() {
        Member member = new Member();
        Order order = new Order();
        order.setMember(member);

        OrderItem item = new OrderItem();
        order.addOrderItem(item);

        em.persist(member);
        em.persist(item);
        em.persist(order);

        em.flush();

        assertEquals(em.find(Member.class, 1L).getName(), member.getName());
    }
}
