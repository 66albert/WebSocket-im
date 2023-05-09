package im.dao;

import java.util.List;
import java.util.Map;

import im.model.User;
import im.utils.ZtreeNode;
import im.vo.SNSUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

    SNSUser findSNSUserByUserID(int userID);

    int updateUserSign(User user);

    void uploadAvatar(User user);

    List<User> findUserList(Map<String,Object> params);

    List<ZtreeNode> findOrgUnitList(Map<String,Object> map);

    List<Map<String,Object>> finduserIsexistGroup(Map<String,Object> params);

    int addUser(Map<String,Object> params);
}
