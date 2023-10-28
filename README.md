# bookmyshow
Building Book My Show system

![BookMyShowUML drawio](https://github.com/yeswanthkumargurram/bookmyshow/assets/36203051/0f869d41-d9cb-442f-baac-cd2cc7c19b2d)

SEAT TYPE - for seat type we choose class instead of ENUM because for every theater seat types will be different(gold,premium,platinum,.....) Hence here seat types a not fixed and huge, going with class seat type and a string object to store the type

Features - features would be almost same among theaters and they will be limited too(dolby, 2d,3d imax,....) to if something is limited we can go with ENUM

Should seat have status attribute?
NO, because same seat number will be shared with many shows and present in many theaters.

here spring handles the creation of tables and handles cardinality/relation by providing certain annotations
