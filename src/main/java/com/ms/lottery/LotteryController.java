package com.ms.lottery;

import com.ms.common.BaseResponse;
import com.ms.common.GridBaseResponse;
import com.ms.pojo.UserDetail;
import com.ms.pojo.UserInvite;
import com.ms.pojo.query.UserDetailQuery;
import com.ms.pojo.query.UserInviteQuery;
import com.ms.service.IUserService;
import com.ms.service.IUserSummaryService;
import com.ms.sys.interceptor.Repeat;
import com.youguu.core.util.PageHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("lottery")
public class LotteryController {

    @Resource
    private IUserSummaryService userSummaryService;
    @Resource
    private IUserService userService;

    /**
     * 查询邀请关系
     *
     * @param uid
     * @param openId
     * @param nickName
     * @param inviteOpenId
     * @param inviteNickName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/queryUserInvite", method = RequestMethod.POST)
    @ResponseBody
    public GridBaseResponse queryUserInvite(@RequestParam(value = "uid", defaultValue = "0") int uid,
                                            @RequestParam(value = "openId", defaultValue = "") String openId,
                                            @RequestParam(value = "nickName", defaultValue = "") String nickName,
                                            @RequestParam(value = "inviteOpenId", defaultValue = "") String inviteOpenId,
                                            @RequestParam(value = "inviteNickName", defaultValue = "") String inviteNickName,
                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        GridBaseResponse rs = new GridBaseResponse();
        rs.setCode(0);
        rs.setMsg("ok");

        UserInviteQuery query = new UserInviteQuery();
        query.setUid(uid);
        query.setOpenId(openId);
        query.setNickName(nickName);
        query.setInviteOpenId(inviteOpenId);
        query.setInviteNickName(inviteNickName);
        query.setPageIndex(page);
        query.setPageSize(limit);

        PageHolder<UserInvite> pageHolder = userService.queryUserInvite(query);

        if (null != pageHolder.getList()) {
            rs.setData(pageHolder.getList());
            rs.setCount(pageHolder.getTotalCount());
        }
        return rs;
    }


    /**
     * 查询中奖记录
     *
     * @param uid
     * @param openId
     * @param thirdId
     * @param prize
     * @param nickName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/queryUserDetail", method = RequestMethod.POST)
    @ResponseBody
    public GridBaseResponse queryUserDetail(@RequestParam(value = "uid", defaultValue = "0") int uid,
                                            @RequestParam(value = "openId", defaultValue = "") String openId,
                                            @RequestParam(value = "thirdId", defaultValue = "") String thirdId,
                                            @RequestParam(value = "prize", defaultValue = "") int prize,
                                            @RequestParam(value = "nickName", defaultValue = "") String nickName,
                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        GridBaseResponse rs = new GridBaseResponse();
        rs.setCode(0);
        rs.setMsg("ok");

        UserDetailQuery query = new UserDetailQuery();
        query.setUid(uid);
        query.setOpenId(openId);
        query.setNickName(nickName);
        query.setThirdId(thirdId);
        query.setPrize(prize);
        query.setPageIndex(page);
        query.setPageSize(limit);
        PageHolder<UserDetail> pageHolder = userSummaryService.queryUserDetail(query);

        if (null != pageHolder.getList()) {
            rs.setData(pageHolder.getList());
            rs.setCount(pageHolder.getTotalCount());
        }

        return rs;
    }


    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    @Repeat
    public BaseResponse importData(String old_password, String new_password, String confirm_password) {
        BaseResponse rs = new BaseResponse();

        return rs;
    }
}
