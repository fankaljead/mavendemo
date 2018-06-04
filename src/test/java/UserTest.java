import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.IUser;
import models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class UserTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(IUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
           IUser iUser = session.getMapper(IUser.class);
           User user = iUser.getUserByID(2);
            if(user!=null){
                String userInfo = "名字："+user.getName()+", 所属部门："+user.getDept()+", 主页："+user.getWebsite();
                System.out.println(userInfo);
                System.out.println(
                        JSONArray.toJSONString(user)
                );
            }
        } finally {
            session.close();
        }
    }
}
