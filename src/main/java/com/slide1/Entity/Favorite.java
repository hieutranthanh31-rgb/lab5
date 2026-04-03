package com.slide1.Entity;


import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter


@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video video;
    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id") // Sửa từ Email thành Id
    private User user;
    @Temporal(TemporalType.DATE)
    private Date likedDate;
}
    

