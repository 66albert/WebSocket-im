package im.ws;

import javax.servlet.http.HttpSession;

import im.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import im.model.User;
import im.service.FriendService;
import im.service.FriendTypeService;
import im.service.GroupUserService;
import im.service.UserService;

@Controller
@RequestMapping("api/friend")
public class Friend {
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserService userService;
	@Autowired
	private FriendTypeService friendTypeService;
	@Autowired
	private GroupUserService groupUserService;

	int pageSize=10;

	@RequestMapping(value = "searchFriendByFriendId", produces = "text/plain; charset=utf-8")
	public @ResponseBody String searchFriendByFriendId(int friendId) {
		return JSON.toJSONString(friendService.searchFriendByFriendId(friendId));
	}

	@RequestMapping(value = "searchFriendByRemarkName", produces = "text/plain; charset=utf-8")
	public @ResponseBody String searchFriendByRemarkName(String remarkName) {
		return JSON.toJSONString(friendService.searchFriendByRemarkName(remarkName));
	}

	@RequestMapping(value = "isFriend", produces = "text/plain; charset=utf-8")
	public @ResponseBody String isFriend(int userId, int friendId) {
		if(friendService.isFriend(userId, friendId))
			return "1";
		return "0";
	}

	@RequestMapping(value = "delFriend", produces = "text/plain; charset=utf-8")
	public @ResponseBody String delFriend(int userId, int friendId) {
		if(friendService.delFriend(userId, friendId)==1)
			return "1";
		return "0";
	}

	@RequestMapping(value = "updateOnLineStatus", produces = "text/plain; charset=utf-8")
	public @ResponseBody String updateOnLineStatus(int userId,String status) {
        try{
            redisUtils.set(userId + "_status", status);
            return "1";
        }catch(Exception e){
            e.printStackTrace();
        }
    	return "0";
	}

	@RequestMapping(value = "getOfflineMsgFromRedis")
	public @ResponseBody JSONArray getOfflineMsgFromRedis(int userId) {
        JSONArray jsonArray = new JSONArray();
        if (redisUtils.exists(userId + "_msg"))
        {
            Long len = redisUtils.llen(userId + "_msg");
            while (len > 0)
            {
                jsonArray.add(redisUtils.rpop(userId + "_msg"));
                len--;
            }
        }
        return jsonArray;
	}



	// 获取好友历史消息 FromMongo 页面
	@RequestMapping(value = "msgBoxPage", produces = "text/plain; charset=utf-8")
	public String msgBoxPage(HttpSession session,Model model) {
		User userInfo=(User) session.getAttribute("userInfo");
		int userId=userInfo.getId();
		model.addAttribute("userId",userId);
		return "js/plugins/layim/msgbox";
	}

	// yunpanSharePage
	@RequestMapping(value = "yunpanSharePage", produces = "text/plain; charset=utf-8")
	public String yunpanSharePage(HttpSession session,Model model) {
		return "js/plugins/layim/yunpanShare";
	}


}
