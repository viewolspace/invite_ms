/**
 * 邀请好友api
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
        "queryUserInvite": {
            url: "queryUserInvite.do",
            type: "POST"
        },
        "importData": {
            url: "importData.do",
            type: "POST"
        }
    }

    var result = $.extend({}, baseApi, url);

    exports('invite-api', result);
});