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
    'lottery-api',
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
    lotteryApi,
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

            mainTable = MyController.renderTable();
            MyController.bindEvent();
        },
        getQueryCondition: function () {
            var condition = formUtil.composeData($("#condition"));
            return condition;
        },
        renderTable: function () {
            return $table.render({
                elem: '#lottery-list'
                , height: 'full-100'
                , url: lotteryApi.getUrl('queryUserDetail').url
                , method: 'post'
                , page: true
                , limits: [10, 50, 100, 200]
                , cols: [[
                    {type: 'numbers'},
                    // {field: 'uid', title: '用户ID', width: 100},
                    {field: 'openId', title: '微信OpenId', width: 300},
                    {field: 'thirdId', title: '用户ID', width: 150},
                    {field: 'nickName', title: '用户昵称', width: 200},
                    {
                        field: 'prize', title: '是否中奖', width: 100, templet: function (d) {
                            if (d.prize == 0) {
                                return '<span>未中奖</span>';
                            } else {
                                return '<span>中奖</span>';
                            }
                        }
                    },
                    {
                        field: 'cTime', title: '创建时间', width: 160, templet: function (d) {
                            return moment(d.cTime).format("YYYY-MM-DD HH:mm:ss");
                        }
                    }
                ]]
            });
        },

        refresh: function () {
            mainTable.reload();
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
        }
    };

    MyController.init();
});