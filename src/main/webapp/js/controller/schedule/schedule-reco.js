var webName = getWebName();

layui.config({
    base: webName + '/js/modules/',
    version: 2018011001
});

var requireModules = [
    'form',
    'form-util',
    'request',
    'schedule-api',
    'role&authority-api',
    'toast',
    'laydate'

];

registeModule(window, requireModules, {
    'role&authority-api': 'api/role&authority-api'
});

layui.use(requireModules, function(
    form,
    formUtil,
    ajax,
    scheduleApi,
    roleApi,
    toast,
    laydate
) {
    var $ = layui.jquery;
    var f = layui.form;
    var param = ajax.getAllUrlParam();

    if(!$.isEmptyObject(param)) {
        formUtil.renderData($('#schedule-reco-form'), param);
    }

    laydate.render({
        elem: '#scheduleTime',
        type: 'datetime',
        range: '到',
        format: 'yyyy-MM-dd HH:mm:ss'
    });

    f.on('submit(schedule-reco-form)', function(data) {
        ajax.request(scheduleApi.getUrl('recommendSchedule'), data.field, function() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            parent.list.refresh();
            toast.success('修改成功');
        });
        return false;
    });
});