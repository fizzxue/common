-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES ('1', NULL, '带一万兵', '2019-10-09 07:22:00', NULL);
INSERT INTO `permission`
VALUES ('2', NULL, '进出国家机密图书馆', '2019-10-09 07:22:30', NULL);

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES ('1', '将军', '一将终成万古枯', '2019-10-09 07:17:04', NULL);
INSERT INTO `role`
VALUES ('2', '军师', '运筹帷幄之中，决胜千里之外', '2019-10-09 07:17:41', NULL);

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission`
VALUES ('1', '1', '1', '2019-10-09 07:22:46', NULL);
INSERT INTO `role_permission`
VALUES ('2', '2', '2', '2019-10-09 07:22:51', NULL);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('1', 'zgl', '5cabbd53f08375ab065420eb9cfd4dbb', '诸葛亮', 1, '2019-10-09 06:44:25', '2019-10-09 06:44:25');
INSERT INTO `user`
VALUES ('2', 'zy', '5cabbd53f08375ab065420eb9cfd4dbb', '赵云', 1, '2019-10-09 06:47:42', NULL);

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES ('1', '1', '2', '2019-10-09 07:19:23', NULL);
INSERT INTO `user_role`
VALUES ('2', '2', '1', '2019-10-09 07:19:34', NULL);
