import models.Post;
import models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

public class UserTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    /**
     * mybatis表关联一对多
     * @param args
     */
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int userid = 1;
            User user = session.selectOne("userMapper.getUser", userid);
            System.out.println("username: "+user.getUsername()+", mobile: " + user.getMobile());
            List<Post> posts = user.getPosts();
            for(Post p : posts) {
                System.out.println("Title:" + p.getTitle());
                System.out.println("Content:" + p.getContent());
            }
        } finally {
            session.close();
        }
    }
}
