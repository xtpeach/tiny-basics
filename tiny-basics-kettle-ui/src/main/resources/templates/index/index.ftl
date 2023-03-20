<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Kettle</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
    <link rel="shortcut icon" href="../../favicon.png" type="image/x-icon">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">ktr 列表</div>
                <div class="layui-card-body">
                    <table class="layui-hide" id="kettle-table-page" lay-filter="kettle-table-page"></table>

                    <script type="text/html" id="kettle-table-page-operate">
                        <a class="layui-btn layui-btn-radius layui-btn-primary layui-btn-xs"
                           lay-event="createTask">创建任务</a>
                        <a class="layui-btn layui-btn-radius layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-radius layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function () {
        var $ = layui.$
            , admin = layui.admin
            , table = layui.table;

        table.render({
            elem: '#kettle-table-page'
            , url: '/kettle/queryByPage'
            , parseData: function (res) {
                return {
                    'code': 0, //接口状态
                    'msg': '', //提示文本
                    'count': res.data.total, //数据长度
                    'data': res.data.list    //数据列表，是直接填充进表格中的数组
                }
            }
            , cols: [[
                {field: 'id', width: 280, title: 'ID', sort: true}
                , {field: 'createTime', width: 180, title: '创建时间'}
                , {field: 'updateTime', width: 180, title: '更新时间', sort: true}
                , {field: 'ktrName', width: 280, title: '转化文件名称'}
                , {field: 'ktrDesc', minWidth: 200, title: '转化文件描述'}
                , {field: 'ktrPath', minWidth: 300, title: '转化文件路径'}
                , {field: 'xxlJobTaskId', width: 280, title: '转化任务编码', sort: true}
                // 添加操作
                , {width: 200, align: 'center', fixed: 'right', toolbar: '#kettle-table-page-operate'}
            ]]
            , page: true
        });

        //监听工具条
        table.on('tool(kettle-table-page)', function (obj) {
            console.log(obj);
            var data = obj.data;
            if (obj.event === 'createTask') {
                // -- create task
                // $.ajax({
                //     type: "post",
                //     data: JSON.stringify(data.field),
                //     url: "http://einvitation.xtpeach.com/xtpeach/einvitation/save/content",
                //     contentType: "application/json; charset=utf-8",
                //     success: function (res) {
                //         console.log("保存成功", "content", res);
                //         layer.msg('保存成功！', {icon: 6});
                //         form.val('content', {
                //             "id": res.id
                //         });
                //         layer.close(load);
                //     },
                //     error: function (err) {
                //         console.log("保存失败", err);
                //         layer.msg('保存失败！', {icon: 5});
                //         layer.close(load);
                //     }
                // });

                //执行重载
                table.reload('kettle-table-page', {
                    // where: field
                });

                // 提示成功
                layer.alert('创建成功', {
                    icon: 1,
                    shadeClose: true,
                    title: '提示'
                });
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    // 页面删除
                    obj.del();
                    // 关闭页面
                    layer.close(index);
                    // 执行重载
                    table.reload('kettle-table-page', {
                        // where: field
                    });
                });
            } else if (obj.event === 'edit') {
                // 编辑
                layer.open({
                    title: '编辑'
                    , type: 1
                    , skin: 'layui-layer-rim'
                    , shadeClose: true
                    , area: admin.screen() < 2 ? ['80%', '300px'] : ['700px', '500px']
                    , content: '<div style="padding: 20px;">放入任意HTML</div>'
                });
            }
        });

    });
</script>
</body>
</html>