package com.ms.lottery;

import com.ms.common.GridBaseResponse;
import com.ms.common.UploadResponse;
import com.ms.pojo.User;
import com.ms.pojo.UserDetail;
import com.ms.pojo.UserInvite;
import com.ms.pojo.query.UserDetailQuery;
import com.ms.pojo.query.UserInviteQuery;
import com.ms.service.IUserService;
import com.ms.service.IUserSummaryService;
import com.youguu.core.util.PageHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
    public GridBaseResponse queryUserInvite(@RequestParam(value = "openId", defaultValue = "") String openId,
                                            @RequestParam(value = "nickName", defaultValue = "") String nickName,
                                            @RequestParam(value = "inviteOpenId", defaultValue = "") String inviteOpenId,
                                            @RequestParam(value = "inviteNickName", defaultValue = "") String inviteNickName,
                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        GridBaseResponse rs = new GridBaseResponse();
        rs.setCode(0);
        rs.setMsg("ok");

        UserInviteQuery query = new UserInviteQuery();
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
    public GridBaseResponse queryUserDetail(@RequestParam(value = "openId", defaultValue = "") String openId,
                                            @RequestParam(value = "thirdId", defaultValue = "") String thirdId,
                                            @RequestParam(value = "prize", defaultValue = "0") int prize,
                                            @RequestParam(value = "nickName", defaultValue = "") String nickName,
                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        GridBaseResponse rs = new GridBaseResponse();
        rs.setCode(0);
        rs.setMsg("ok");

        UserDetailQuery query = new UserDetailQuery();
        if (prize > 0) {
            query.setPrize(prize);
        }
        query.setOpenId(openId);
        query.setNickName(nickName);
        query.setThirdId(thirdId);
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
    public UploadResponse importData(@RequestParam(value = "file", required = false) MultipartFile file) {
        UploadResponse rs = new UploadResponse();

        if (null != file) {
            try {
                InputStream inputStream = file.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8"); // 建立一个输入流对象reader
                BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                List<String> openIdList = new ArrayList<>();
                openIdList.add(br.readLine());
                while (br.ready()) {
                    openIdList.add(br.readLine());
                }

                int success = 0;
                for (String openId : openIdList) {
                    User user = userService.getUserByOpenId(openId);
                    if (null == user) {
                        continue;
                    }
                    int count = userService.updateUserCommit(user.getUid());
                    if (count == 2) {
                        success++;
                    }
                }

                rs.setTotal(openIdList.size());
                rs.setSuccess(success);

                rs.setStatus(true);
                rs.setMsg("上传成功");
            } catch (Exception e) {
                rs.setStatus(false);
                rs.setMsg("服务器异常");
            }
        } else {
            rs.setStatus(false);
            rs.setMsg("文件为空");
        }

        return rs;
    }
}
