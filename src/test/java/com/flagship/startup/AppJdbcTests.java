package com.flagship.startup;

import com.flagship.startup.entity.ComData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.sql.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppJdbcTests {

    private static final Logger log = LoggerFactory.getLogger(AppJdbcTests.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() throws Exception {
        int refNo = 111;
        ComData resultData = (ComData) jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                //String storedProc = "{SP_getMsgByRefno(?,?)}";// 调用的sql
                String storedProc = "CALL SP_getMsgByRefno(?,?)";
                CallableStatement cs = con.prepareCall(storedProc);
                cs.setInt(1, refNo);
                cs.registerOutParameter(2, java.sql.Types.VARCHAR);// 注册输出参数 返回类型
                return cs;
            }
        }, new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                ComData data = new ComData();
                cs.execute();
                //ResultSet rs = cs.getResultSet();
                //rs = cs.getResultSet();
                //ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.toString());
                    int refNo = rs.getInt(1);
                    String memberName = rs.getString(2);
                    BigDecimal amt = rs.getBigDecimal(3);
                    data.setRefNo(refNo);
                    data.setMemberName(memberName);
                    data.setAmt(amt);
                }
                rs.close();
                return data;
            }
        });

        System.out.println(resultData);
    }
}
