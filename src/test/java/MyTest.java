import com.alibaba.fastjson.JSON;
import com.demo.BunkType;
import com.demo.BunkTypeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * User: lanxinghua
 * Date: 2019/10/17 17:54
 * Desc: 测试Mybatis源码
 */
public class MyTest {
    public static void main(String[] args) throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            BunkTypeMapper bunkTypeMapper = sqlSession.getMapper(BunkTypeMapper.class);
            List<BunkType> result = bunkTypeMapper.listBunkType("99226558");
            System.out.println(JSON.toJSONString(result));
        }finally {
            sqlSession.close();
        }
    }
}
