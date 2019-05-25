var webName = getWebName();

layui.config({
	base: webName + '/js/modules/',
    version: 2018011001
});

var requireModules = [
	'form',
	'form-util',
	'request',
	'invite-api',
	'toast',
	'upload'
];

registeModule(window, requireModules, {
	'good-api': 'api/good-api'
});

layui.use(requireModules, function(
	form,
	formUtil,
	ajax,
	inviteApi,
	toast,
	upload
	) {
	var $ = layui.jquery;
	var f = layui.form;

	upload.render({
		elem: '#logoBtn'
		,url: inviteApi.getUrl('importData').url
		,accept: 'file'
		,size: 1024 //最大允许上传的文件大小kb
		,before: function(obj){
			//预读本地文件
			layer.load(0, {
				shade: 0.5
			});
		}
		,done: function(res){
			layer.closeAll('loading');
			if(res.status == false){
				return layer.msg('上传失败');
			} else {
				$('#total').val(res.total);
				$('#success').val(res.success);
				toast.msg("上传成功");
			}
		}
		,error: function(){
			layer.closeAll('loading');
			return layer.msg('数据请求异常');
		}
	});

});