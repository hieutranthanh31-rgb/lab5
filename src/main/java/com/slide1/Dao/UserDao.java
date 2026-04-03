package com.slide1.Dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.slide1.Entity.User;
import com.slide1.Services.XJPA;

public class UserDao {
    private EntityManager em = XJPA.getEntityManager();

    public User findById(String id) {
        return em.find(User.class, id);
    }

    // --- BỔ SUNG CHO BÀI 2 ---
    public User findByIdOrEmail(String usernameOrEmail) {
        try {
            // Câu lệnh JPQL: Tìm user có id bằng tham số HOẶC email bằng tham số
            String jpql = "SELECT u FROM User u WHERE u.id = :idOrEmail OR u.email = :idOrEmail";
            
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("idOrEmail", usernameOrEmail);
            
            // Trả về kết quả duy nhất tìm được
            return query.getSingleResult();
        } catch (Exception e) {
            // Nếu không tìm thấy (NoResultException) thì trả về null để Servlet xử lý báo lỗi
            return null;
        }
    }
}