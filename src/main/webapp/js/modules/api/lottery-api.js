/**
 * 抽奖记录查询api
 */
var requireModules = [
    'base-url'
];

window.top.registeModule(window, requireModules);
layui.define('base-url', function (exports) {
    var $ = layui.jquery;
    var baseApi = layui['base-url'];

    var url = {
        namespace: '../lottery/',
        "queryUserDetail": {
            url: "queryUserDetail.do",
            type: "POST"
        }
    }

    var result = $.extend({}, baseApi, url);

    exports('lottery-api', result);
});