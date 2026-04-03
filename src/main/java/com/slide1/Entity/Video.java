package com.slide1.Entity;


import java.util.List;


import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "videos")
public class Video {
    @Id
    private String id;
    private String title;
    private String poster;
    private String descirption;
    private boolean active;
    private int views; 
    @OneToMany(mappedBy = "video")
    private List<Favorite> favorites;

}
