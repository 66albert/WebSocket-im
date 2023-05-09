package im.dao;

import java.util.List;
import java.util.Map;

import im.model.FriendType;
import tk.mybatis.mapper.common.Mapper;

public interface FriendTypeMapper extends Mapper<FriendType> {
	// 获取好友分组列表
	List<FriendType> getFriendTypeByUserId(int userId);

	/**
	 * 新增用户分组
	 * @return
	 */
	int addFriendType(FriendType friendType);

	int deleteFriendTypeById(Map<String,Object> params);

	FriendType findFriendTypeById(Map<String,Object> params);

	int updateFriendTypeName(Map<String,Object> params);
}
