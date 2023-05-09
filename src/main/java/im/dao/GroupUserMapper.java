package im.dao;

import im.model.GroupUser;
import im.vo.SNSUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GroupUserMapper extends Mapper<GroupUser> {

    List<String> getSimpleMemberByGroupId(int groupId);

    List<SNSUser> getMemberByGroupId(int groupId);

    int deleteGroupUser(Map<String,Object> params);

    int addGroupUser(Map<String,Object> params);

    int deleteGroupUserById(Map<String,Object> params);

    int updateGroupRemark(Map<String,Object> params);
}
