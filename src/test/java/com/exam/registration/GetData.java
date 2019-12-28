package com.exam.registration;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yhf
 * @classname GetData
 * @description TODO
 * @date 2019/12/27
 **/
class Data {
    private String name;
    private String idCardNumber;
    private String sex;
    private String address;
    private String phone;
    private String provincialExamineeNumber;
    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvincialExamineeNumber() {
        return provincialExamineeNumber;
    }

    public void setProvincialExamineeNumber(String provincialExamineeNumber) {
        this.provincialExamineeNumber = provincialExamineeNumber;
    }
}
public class GetData {
    public static void main(String[] args) throws Exception {
        toDo(getFromRemote());
    }

    private static List<Data> getFromRemote() {
        Connection conn = null;
        Statement stmt = null;
        List<Data> list = new ArrayList<>();
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection("jdbc:mysql://115.159.49.112:3306/db_examination?useSSL=false&serverTimezone=UTC", "root", "NT$8bYUqCe");

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT username, stuName, stuSex, stuSchool, stuNumber, stuTel, stuAddress FROM t_student";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String username = rs.getString("username");
                String stuName = rs.getString("stuName");
                String stuSex = rs.getString("stuSex");
                String stuSchool = rs.getString("stuSchool");
                String stuNumber = rs.getString("stuNumber");
                String stuTel = rs.getString("stuTel");
                String stuAddress = rs.getString("stuAddress");
                Data data = new Data();
                if (StringUtils.isEmpty(stuNumber)) {
                    stuNumber = "";
                } else {
                    if (stuNumber.length() > 14) {
                        stuNumber = stuNumber.substring(0,14);
                    }
                }

                if (StringUtils.isEmpty(username)) {
                    continue;
                }
                if (StringUtils.isEmpty(stuName)) {
                    stuName = "";
                }
                if (StringUtils.isEmpty(stuSex)) {
                    stuSex = "";
                }
                if (StringUtils.isEmpty(stuSchool)) {
                    stuSchool = "";
                }
                if (StringUtils.isEmpty(stuTel)) {
                    stuTel = "";
                } else {
                    if (stuTel.length() > 11) {
                        stuTel = stuTel.substring(0,11);
                    }
                }
                if (StringUtils.isEmpty(stuAddress)) {
                    stuAddress = "";
                }
                data.setAddress(stuAddress);
                data.setIdCardNumber(username);
                data.setName(stuName);
                data.setPhone(stuTel);
                data.setProvincialExamineeNumber(stuNumber);
                data.setSex(stuSex);
                data.setSchool(stuSchool);
                list.add(data);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return list;
    }

    private static void toDo (List<Data> list) throws Exception {
        // 注册 JDBC 驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 打开链接
        System.out.println("连接数据库...");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exam_registration?useSSL=false&serverTimezone=UTC", "root", "");

        // 执行查询
        System.out.println(" 实例化Statement对象...");
        Statement stmt = conn.createStatement();
        String sql;
        int i = 0;
        for (Data data : list) {
            String str = String.format("%2d", i);
            if (data.getAddress().length() < 5) {
                continue;
            }
            String name = data.getAddress().substring(0, 3);
            sql = "insert into site(`name`, code, address) values ('"+name+"','"+str+"' ,'"+data.getAddress()+"')";


//            sql = "insert into student(id_card_number, `name`, password, salt, sex," +
//                    " phone, address, school, provincial_examinee_number, login_time, create_time)" +
//                    " values ('" + data.getIdCardNumber() + "', '" + data.getName() + "','03b7b6b68290652315ecc5da08b7700f','51c60','"
//                    + data.getSex() + "', '"+data.getPhone()+"','"+data.getAddress()+"','"+data.getSchool()+"','"+data.getProvincialExamineeNumber()+"',NOW(),NOW())";
            try {
                stmt.execute(sql);
                System.out.println(i++);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stmt.close();
        conn.close();
    }
}
