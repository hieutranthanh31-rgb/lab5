package com.slide1;

import javax.persistence.EntityManager;
import com.slide1.Services.XJPA;
import com.slide1.Entity.User;
import com.slide1.Entity.Video;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        // Tắt các thông báo kỹ thuật của Hibernate
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        System.setProperty("org.jboss.logging.provider", "jdk");

        try {
            EntityManager em = XJPA.getEntityManager();

            System.out.println("--- KET QUA KIEM TRA GIAI DOAN 1 ---");
            System.out.println("");

            // 1. Truy vấn và hiển thị danh sách 5 Users
            List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
            System.out.println(">> Tim thay " + users.size() + " nguoi dung trong he thong:");
            for (User u : users) {
                System.out.println("   [User]: " + u.getFullName() + " (" + u.getEmail() + ")");
            }

            System.out.println("");

            // 2. Truy vấn và hiển thị danh sách 5 Videos
            List<Video> videos = em.createQuery("SELECT v FROM Video v", Video.class).getResultList();
            System.out.println(">> Tim thay " + videos.size() + " video trong danh sach:");
            for (Video v : videos) {
                System.out.println("   [Video]: " + v.getTitle());
            }

            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println(">> TRANG THAI: HOAN THANH GIAI DOAN 1!");
            System.out.println(">> XAC NHAN: DU LIEU DA THONG SUOT TU DATABASE DEN JAVA.");

            em.close();
        } catch (Exception e) {
            System.out.println(">> LOI: Vui long kiem tra lai ket noi hoac du lieu trong HeidiSQL!");
        }
    }
}