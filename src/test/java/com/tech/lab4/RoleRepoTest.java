package com.tech.lab4;

import com.tech.lab4.dao.RoleDAO;
import com.tech.lab4.entities.ERoles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Lab4Application.class)
public class RoleRepoTest {

    @Autowired
    private RoleDAO roleDAO;

    @Test
    public void TestRole(){
//        if (roleDAO.getRoleByName(ERoles.ADMIN).isEmpty()){
//            return;
//        } else {
//            System.out.println("s;lfjnvlskndfvlskjndfv");
//        }

        Assertions.assertEquals("USER", roleDAO.getRoleByName(ERoles.ROLE_USER).get().getName().name());
    }
}
