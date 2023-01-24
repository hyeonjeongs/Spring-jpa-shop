package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name= "category_id")
    private Long id;

    private String name;

    // 다대다 - 중간 테이블을 생성하는식임
    @ManyToMany
    @JoinTable(name = "catogry_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();



    // 셀프로 양방향 연관관계 (다른엔티티에서 매핑하는 것처럼 하면 됨)
    // 카테고리 쭉 계층구조로 퍼지니까 내 부모, 자식 알 수 있도록 매핑관계 설정
    // [자식 카테고리]
    @ManyToOne(fetch=FetchType.LAZY) //내부모니까 manytoone (내가 자식이니까)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // [부모 카테고리]
    // 카테고리가 여러개 자식 가질 수 있음
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) //위에있는거 연관관계
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
