create database labmanagement;


create table apply_equ
(
    id            bigint auto_increment comment 'id'
        primary key,
    state         tinyint                            not null comment '状态 0 未维修，1 维修中，2已维修',
    applicant_id  bigint                             not null comment '申请人id',
    lab_number    bigint                             not null comment '实验室编号',
    error_message text                               null comment '维修信息',
    fixed_message text                               null comment '已维修后信息',
    is_delete     tinyint  default 0                 not null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    comment '设备维护申请表';

create index apply_equ_id_lab_number_index
    on apply_equ (id, lab_number);

create table apply_lab
(
    id           bigint auto_increment comment '主键'
        primary key,
    type         tinyint  default 0                 not null comment '申请人类别 0 学生， 1 教师',
    applicant_id bigint                             not null comment '申请人id',
    lab_number   bigint                             not null comment '实验室编号',
    state        tinyint                            not null comment '状态 0 未审核，1 已审核',
    massage      text                               null comment '申请信息',
    is_delete    tinyint  default 0                 not null comment '逻辑删除',
    create_time  datetime default CURRENT_TIMESTAMP not null,
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    comment '实验室申请表';

create table lab
(
    id          bigint auto_increment comment '主键'
        primary key,
    number      bigint                             not null comment '实验室编号',
    name        varchar(255)                       not null comment '实验室名称',
    type        tinyint  default 0                 not null comment '实验室类型 0 软件，1 硬件，2 网络',
    equ_count   bigint                             not null comment '设备数量',
    admin_id    bigint                             not null comment '管理员id',
    is_delete   tinyint  default 0                 not null comment '逻辑删除',
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    comment '实验室';

create index `id,adminID`
    on lab (id, admin_id);

create table schedule
(
    id          bigint auto_increment comment '主键'
        primary key,
    semester    varchar(255)                       not null comment '学期',
    section     varchar(255)                       not null comment '节次',
    lab_number  bigint                             not null comment '实验室编号',
    name        varchar(255)                       not null comment '课名',
    classes     varchar(255)                       not null comment '班级',
    teacher_id  bigint                             not null comment '任课教师',
    week        tinyint                            not null comment '星期',
    weeks       tinyint                            not null comment '周次',
    is_delete   tinyint                            not null comment '逻辑删除',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建文档',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
);

create index schedule_classes_index
    on schedule (classes);

create index semester
    on schedule (semester);

create table user
(
    id          bigint unsigned auto_increment comment '主键'
        primary key,
    account     bigint                             not null comment '学号 工号',
    password    varchar(255)                       not null comment '密码',
    name        varchar(255)                       not null comment '用户名',
    role        tinyint  default 1                 not null comment '角色 0 管理员，1 学生，2 教师，3 实验员',
    reputation  varchar(255)                       null comment ' 职称',
    major       varchar(255)                       null comment '专业',
    classes     varchar(255)                       null comment '班级',
    is_delete   tinyint  default 0                 not null comment '逻辑删除',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    salt        varchar(255)                       null comment '盐值',
    constraint user_pk
        unique (account)
);

create index user_id_classes_index
    on user (id, classes);

