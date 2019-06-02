package jpabook.model;

import jpabook.model.entity.Item;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작

            Member member = new Member();
            Order order = new Order();
            order.setMember(member);

            OrderItem item = new OrderItem();
            order.addOrderItem(item);

            em.persist(member);
            em.persist(item);
            em.persist(order);

            em.flush();
            Order order1 = em.find(Order.class, 1L);
            Member member1 = order1.getMember();

            OrderItem orderItem = order1.getOrderItems().get(0);
            Item item1 = orderItem.getItem();

            System.out.println(member1.getId());
            System.out.println(item1.getId());

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

}
