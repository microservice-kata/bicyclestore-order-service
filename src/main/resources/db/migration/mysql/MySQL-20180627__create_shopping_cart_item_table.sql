CREATE TABLE `SHOPPING_CART_ITEM` (
    `ID`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `USER_ID`    BIGINT(20)          NOT NULL,
    `BICYCLE_ID` BIGINT(20)          NOT NULL,
    `QUANTITY`   INT(11) UNSIGNED    NOT NULL,
    `CREATED_AT` TIMESTAMP(3)        NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`ID`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COMMENT = 'This is just to test how to alter comments';

