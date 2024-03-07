package com.happy.smilely.main_project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1631133647L;

    public static final QUser user = new QUser("user");

    public final StringPath birthDt = createString("birthDt");

    public final StringPath deviceOs = createString("deviceOs");

    public final StringPath fcmToken = createString("fcmToken");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastAuthDt = createDateTime("lastAuthDt", java.time.LocalDateTime.class);

    public final StringPath mobileNo = createString("mobileNo");

    public final DateTimePath<java.time.LocalDateTime> privacyDt = createDateTime("privacyDt", java.time.LocalDateTime.class);

    public final StringPath privacyYn = createString("privacyYn");

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

