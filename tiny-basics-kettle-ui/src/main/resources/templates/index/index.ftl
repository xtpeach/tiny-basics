<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Kettle</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
    <link rel="shortcut icon" href="../../favicon.png" type="image/x-icon">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">kettle 列表</div>
                <div class="layui-card-body">
                    <table class="layui-hide" id="kettle-table-page"></table>
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
    }).use(['index', 'table'], function(){
        var table = layui.table;

        table.render({
            elem: '#kettle-table-page'
            ,url: '/kettle/queryByPage'
            ,parseData: function (res) {
                return {
                    'code': 0, //接口状态
                    'msg': '', //提示文本
                    'count': res.data.total, //数据长度
                    'data': res.data.list    //数据列表，是直接填充进表格中的数组
                }
            }
            ,cols: [[
                {field:'id', width:280, title: 'ID', sort: true}
                ,{field:'createTime', width:180, title: '创建时间'}
                ,{field:'updateTime', width:180, title: '更新时间', sort: true}
                ,{field:'ktrName', width:280, title: '转化文件名称'}
                ,{field:'ktrPath', title: '转化文件路径', minWidth: 300}
                ,{field:'xxlJobTaskId', width:280, title: '转化任务编码', sort: true}
                // 添加操作

            ]]
            ,page: true
        });
    });
</script>
</body>
</html>