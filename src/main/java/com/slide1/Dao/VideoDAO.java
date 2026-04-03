package com.slide1.Dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

// Import đúng package Entity và Service của Hiếu
import com.slide1.Entity.Video;
import com.slide1.Entity.Favorite;
import com.slide1.Entity.Share;
import com.slide1.Services.XJPA; 

public class VideoDAO {
    // Gọi trực tiếp từ lớp XJPA bạn vừa gửi
    private EntityManager em = XJPA.getEntityManager();

    @Override
    protected void finalize() throws Throwable {
        // Không đóng em ở đây vì XJPA dùng chung 1 instance tĩnh
        super.finalize();
    }

    // 2. Tìm các video có title chứa từ khóa
    public List<Video> findByKeyword(String keyword) {
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :keyword";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    // 3. Truy vấn 10 video được yêu thích nhiều nhất
    public List<Video> findTop10Favorite() {
        String jpql = "SELECT f.video FROM Favorite f GROUP BY f.video ORDER BY COUNT(f) DESC";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setFirstResult(0);
        query.setMaxResults(10);
        return query.getResultList();
    }

    // 4. Tìm các video không được ai thích
    public List<Video> findNotFavorited() {
        String jpql = "SELECT v FROM Video v WHERE v.favorites IS EMPTY";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    // 5. Tìm video được chia sẻ trong năm 2024
    public List<Video> findSharedIn2024() {
        String jpql = "SELECT s.video FROM Share s WHERE YEAR(s.shareDate) = 2024 ORDER BY s.shareDate DESC";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    // 6. Truy vấn thông tin chia sẻ tổng hợp
    public List<Object[]> reportSharedVideo() {
        String jpql = "SELECT s.video.title, COUNT(s), MIN(s.shareDate), MAX(s.shareDate) " +
                      "FROM Share s GROUP BY s.video.title";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }
}