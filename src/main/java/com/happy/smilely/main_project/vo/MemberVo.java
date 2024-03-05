package com.happy.smilely.main_project.vo;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
public class MemberVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbrNo;

    private String id;

    private String name;

    private String remark;

    @Builder
    public MemberVo(String id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }
}