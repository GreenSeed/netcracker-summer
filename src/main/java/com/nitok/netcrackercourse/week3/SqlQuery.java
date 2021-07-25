package com.nitok.netcrackercourse.week3;

public class SqlQuery {
    public String buildQuery(int maxDolg, String group) {
        return "INSERT INTO T_GroupSelected SELECT id_Student, firstName, lastName, id_Group FROM T_Student WHERE id_Group='" + group + "' and dolgCount < " + maxDolg;
    }
}
