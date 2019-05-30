var webName = getWebName();

layui.config({
    base: webName + '/js/modules/',
    version: 2018011001
});

var requireModules = [
    'element',
    'form',
    'layer',
    'request',
    'form-util',
    'invite-api',
    'table-util',
    'btns',
    'authority',
    'toast',
    'table',
    'valid-login'

];

registeModule(window, requireModules, {
    'good-api': 'api/good-api'
});

//参数有顺序
layui.use(requireModules, function (
    element,
    form,
    layer,
    request,
    formUtil,
    inviteApi,
    tableUtil,
    btns,
    authority,
    toast,
    table
) {

    var $ = layui.jquery;
    var $table = table;
    var mainTable;
    var MyController = {
        init: function () {
            var navId = request.getFixUrlParams("navId");

            var totalBtns = authority.getNavBtns(navId);
            var btnObjs = btns.getBtns(totalBtns);
            MyController.pageBtns = btns.getPageBtns(btnObjs);
            MyController.switchPageBtns = btns.getSwitchPageBtns(btnObjs);

            MyController.rowBtns = btns.getRowBtns(btnObjs);
            MyController.rowSwitchBtns = btns.getSwitchBtns(MyController.rowBtns);
            MyController.rowIconBtns = btns.getIconBtns(MyController.rowBtns);

            $('#page-btns').html(btns.renderBtns(MyController.pageBtns)+btns.renderSwitchBtns(MyController.switchPageBtns));
            btns.renderLayuiTableBtns(MyController.rowIconBtns, $("#barDemo"));

            mainTable = MyController.renderTable();
            MyController.bindEvent();
        },
        getQueryCondition: function () {
            var condition = formUtil.composeData($("#condition"));
            return condition;
        },
        renderTable: function () {
            return $table.render({
                elem: '#invite-list'
                , height: 'full-100'
                , url: inviteApi.getUrl('queryUserInvite').url
                , method: 'post'
                , page: true
                , limits: [10, 50, 100, 200]
                , cols: [[
                    {type: 'numbers'},
                    // {field: 'uid', title: '用户ID', width: 100},
                    {field: 'thirdId', title: '用户ID', width: 130},
                    {field: 'openId', title: '微信OpenId', width: 200},
                    {field: 'nickName', title: '用户昵称', width: 150},
                    // {field: 'inviteUid', title: '邀请用户ID', width: 100},
                    {field: 'inviteThirdId', title: '邀请用户ID', width: 150},
                    {field: 'inviteOpenId', title: '邀请微信OpenId', width: 200},
                    {field: 'inviteNickName', title: '邀请用户昵称', width: 150},
                    {
                        field: 'status', title: '是否成交', width: 100, templet: function (d) {
                            if (d.status == 0) {
                                return '<span>未成交</span>';
                            } else if (d.status == 1) {
                                return '<span>已成交</span>';
                            } else {
                                return '<span>未知</span>';
                            }
                        }
                    },
                    {
                        field: 'cTime', title: '创建时间', width: 160, templet: function (d) {
                            return moment(d.cTime).format("YYYY-MM-DD HH:mm:ss");
                        }
                    },
                    {
                        field: 'mTime', title: '修改时间', width: 160, templet: function (d) {
                            return moment(d.mTime).format("YYYY-MM-DD HH:mm:ss");
                        }
                    }
                ]]
            });
        },

        refresh: function () {
            mainTable.reload();
        },

        import: function() {
            var url = request.composeUrl(webName + '/views/minsheng/import-deal.html');
            layer.open({
                type: 2,
                title: "导入成交用户",
                area: ['550px', '400px'],
                //offset: '10%',
                content: url,
                success: function(ly, index) {
                    // layer.iframeAuto(index);
                }
            });
        },

        bindEvent: function () {
            //点击查询按钮
            $('#search-btn').on('click', function () {
                mainTable.reload({
                    where: MyController.getQueryCondition()
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            });

            $('body').on('click', '.import', MyController.import);
        }
    };

    MyController.init();
});