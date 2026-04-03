package com.slide1.Entity;

// 1. Import các thư viện JPA (Quan trọng nhất)
import javax.persistence.*; 
import java.util.Date; // 2. Import thư viện ngày tháng của Java

@Entity
@Table(name = "Shares")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user; // Đảm bảo lớp User.java cũng nằm trong package com.slide1.Entity

    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video video; // Đảm bảo lớp Video.java cũng nằm trong package com.slide1.Entity

    private String emails;

    @Temporal(TemporalType.DATE)
    @Column(name = "ShareDate")
    private Date shareDate = new Date();

    // Bạn phải có đầy đủ Getter và Setter thì Hibernate mới hoạt động được
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public Date getShareDate() { return shareDate; }
    public void setShareDate(Date shareDate) { this.shareDate = shareDate; }
}