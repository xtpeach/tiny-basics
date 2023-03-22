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

    <style>
        .layui-form-label {
            width: 120px;
        }

        .layui-input-block {
            margin-left: 150px;
        }
    </style>

</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">ktr 列表</div>
                <div class="layui-card-body">

                    <!-- 数据表格 - 查询条件 -->
                    <div class="layui-fluid">
                        <div class="layui-row layui-col-space15">
                            <div class="layui-card">
                                <div class="layui-card-body">
                                    <div class="layui-form">
                                        <div class="layui-row layui-col-space10 layui-form-item">
                                            <div class="layui-col-lg3">
                                                <label class="layui-form-label">ID：</label>
                                                <div class="layui-input-block">
                                                    <input id="kettle-table-page-search-input-id"
                                                           type="text" name="id" lay-verify="required"
                                                           placeholder="请输入ID" autocomplete="off" class="layui-input">
                                                </div>
                                            </div>
                                            <div class="layui-col-lg3">
                                                <label class="layui-form-label">转化任务编码：</label>
                                                <div class="layui-input-block">
                                                    <input id="kettle-table-page-search-input-xxlJobTaskId"
                                                           type="text" name="xxlJobTaskId" lay-verify="required"
                                                           placeholder="请输入转化任务编码" autocomplete="off"
                                                           class="layui-input">
                                                </div>
                                            </div>
                                            <div class="layui-col-lg3">
                                                <label class="layui-form-label">转化文件名称：</label>
                                                <div class="layui-input-block">
                                                    <input id="kettle-table-page-search-input-ktrName"
                                                           type="text" name="ktrName" lay-verify="required"
                                                           placeholder="请输入转化文件名称" autocomplete="off"
                                                           class="layui-input">
                                                </div>
                                            </div>
                                            <div class="layui-col-lg3">
                                                <label class="layui-form-label">转化文件描述：</label>
                                                <div class="layui-input-block">
                                                    <input id="kettle-table-page-search-input-ktrDesc"
                                                           type="text" name="ktrDesc" lay-verify="required"
                                                           placeholder="请输入转化文件描述" autocomplete="off"
                                                           class="layui-input">
                                                </div>
                                            </div>
                                            <div class="layui-col-lg3">
                                                <label class="layui-form-label">创建时间：</label>
                                                <div class="layui-input-block">
                                                    <input id="kettle-table-page-search-input-createTime"
                                                           type="text" name="createTime" lay-verify="required"
                                                           placeholder="yyyy-MM-dd" autocomplete="off"
                                                           class="layui-input">
                                                </div>
                                            </div>
                                            <div class="layui-col-lg3">
                                                <label class="layui-form-label">更新时间：</label>
                                                <div class="layui-input-block">
                                                    <input id="kettle-table-page-search-input-updateTime"
                                                           type="text" name="updateTime" lay-verify="required"
                                                           placeholder="yyyy-MM-dd" autocomplete="off"
                                                           class="layui-input">
                                                </div>
                                            </div>
                                            <div class="layui-col-lg2 kettle-table-page-search-btn" style="float: right; text-align: right;">
                                                <button class="layui-btn" data-type="search">搜索</button>
                                                <button class="layui-btn layui-btn-primary" data-type="reset">重置
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 数据表格 -->
                    <table class="layui-hide" id="kettle-table-page" lay-filter="kettle-table-page"></table>

                    <!-- 数据表格 - 操作列 -->
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
    }).use(['index', 'table', 'laydate'], function () {
        var $ = layui.$
            , admin = layui.admin
            , table = layui.table
            , laydate = layui.laydate;

        // 日期选择框 - 创建时间
        laydate.render({
            elem: '#kettle-table-page-search-input-createTime'
            , range: true
        });
        // 日期选择框 - 更新时间
        laydate.render({
            elem: '#kettle-table-page-search-input-updateTime'
            , range: true
        });

        // 数据表格渲染
        table.render({
            elem: '#kettle-table-page'
            , url: '/kettle/queryByPage'
            , method: 'POST'
            , where: {
                id: $('#kettle-table-page-search-input-id').val(),
                xxlJobTaskId: $('#kettle-table-page-search-input-xxlJobTaskId').val(),
                ktrName: $('#kettle-table-page-search-input-ktrName').val(),
                ktrDesc: $('#kettle-table-page-search-input-ktrDesc').val(),
                createTime: $('#kettle-table-page-search-input-createTime').val(),
                updateTime: $('#kettle-table-page-search-input-updateTime').val()
            }
            , parseData: function (res) {
                return {
                    'code': 0, //接口状态
                    'msg': '', //提示文本
                    'count': res.data.total, //数据长度
                    'data': res.data.list    //数据列表，是直接填充进表格中的数组
                }
            }
            , cols: [[
                {field: 'id', width: 280, title: 'ID'}
                , {field: 'xxlJobTaskId', width: 280, title: '转化任务编码'}
                , {field: 'ktrName', width: 280, title: '转化文件名称'}
                , {field: 'ktrDesc', minWidth: 200, title: '转化文件描述'}
                , {field: 'ktrPath', minWidth: 300, title: '转化文件路径'}
                , {field: 'createTime', width: 180, title: '创建时间'}
                , {field: 'updateTime', width: 180, title: '更新时间'}
                // 添加操作
                , {width: 200, align: 'center', fixed: 'right', toolbar: '#kettle-table-page-operate'}
            ]]
            , page: true
        });

        // 数据表格 - 操作列监听工具条
        table.on('tool(kettle-table-page)', function (obj) {
            var data = obj.data;
            if (obj.event === 'createTask') {
                // -- create task
                // 开启加载动画
                var load = layer.load(2);
                var createTaskAjax = $.ajax({
                    type: "post",
                    data: JSON.stringify(data),
                    url: "/kettle/createTask",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        console.log("创建成功", "content", res);
                        layer.msg('创建成功！', {icon: 6});
                        //执行重载
                        table.reload('kettle-table-page', {
                            where: {
                                id: $('#kettle-table-page-search-input-id').val(),
                                xxlJobTaskId: $('#kettle-table-page-search-input-xxlJobTaskId').val(),
                                ktrName: $('#kettle-table-page-search-input-ktrName').val(),
                                ktrDesc: $('#kettle-table-page-search-input-ktrDesc').val(),
                                createTime: $('#kettle-table-page-search-input-createTime').val(),
                                updateTime: $('#kettle-table-page-search-input-updateTime').val()
                            }
                        });
                        // 关闭加载动画
                        layer.close(load);
                    },
                    error: function (err) {
                        console.log("创建失败", err);
                        layer.msg('创建失败！', {icon: 5});
                        // 关闭加载动画
                        layer.close(load);
                    },
                    complete: function (XMLHttpRequest, status) {
                        if (status == 'timeout') {
                            createTaskAjax.abort();
                            console.log("接口超时", err);
                            layer.msg('接口超时', {icon: 5});
                            // 关闭加载动画
                            layer.close(load);
                        }
                    }
                });
            } else if (obj.event === 'del') {
                layer.confirm('确定要删除嘛？', function (index) {
                    // -- delete task
                    // 开启加载动画
                    var load = layer.load(2);
                    var deleteTaskAjax = $.ajax({
                        type: "post",
                        data: JSON.stringify(data),
                        url: "/kettle/delete",
                        contentType: "application/json; charset=utf-8",
                        success: function (res) {
                            console.log("删除成功", "content", res);
                            layer.msg('删除成功！', {icon: 6});
                            //执行重载
                            table.reload('kettle-table-page', {
                                where: {
                                    id: $('#kettle-table-page-search-input-id').val(),
                                    xxlJobTaskId: $('#kettle-table-page-search-input-xxlJobTaskId').val(),
                                    ktrName: $('#kettle-table-page-search-input-ktrName').val(),
                                    ktrDesc: $('#kettle-table-page-search-input-ktrDesc').val(),
                                    createTime: $('#kettle-table-page-search-input-createTime').val(),
                                    updateTime: $('#kettle-table-page-search-input-updateTime').val()
                                }
                            });
                            // 页面删除
                            obj.del();
                            // 关闭页面
                            layer.close(index);
                            // 关闭加载动画
                            layer.close(load);
                        },
                        error: function (err) {
                            console.log("删除失败", err);
                            layer.msg('删除失败！', {icon: 5});
                            // 关闭页面
                            layer.close(index);
                            // 关闭加载动画
                            layer.close(load);
                        },
                        complete: function (XMLHttpRequest, status) {
                            if (status == 'timeout') {
                                deleteTaskAjax.abort();
                                console.log("接口超时", err);
                                layer.msg('接口超时', {icon: 5});
                                // 关闭页面
                                layer.close(index);
                                // 关闭加载动画
                                layer.close(load);
                            }
                        }
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

        // 方法集合
        var active = {
            // 表格数据搜索
            search: function () {
                console.log("search");

                table.reload('kettle-table-page', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        id: $('#kettle-table-page-search-input-id').val(),
                        xxlJobTaskId: $('#kettle-table-page-search-input-xxlJobTaskId').val(),
                        ktrName: $('#kettle-table-page-search-input-ktrName').val(),
                        ktrDesc: $('#kettle-table-page-search-input-ktrDesc').val(),
                        createTime: $('#kettle-table-page-search-input-createTime').val(),
                        updateTime: $('#kettle-table-page-search-input-updateTime').val()
                    }
                });
            }

            // 表格数据查询条件重置
            , reset: function () {
                console.log("reset");
                $('#kettle-table-page-search-input-id').val("");
                $('#kettle-table-page-search-input-xxlJobTaskId').val("");
                $('#kettle-table-page-search-input-ktrName').val("");
                $('#kettle-table-page-search-input-ktrDesc').val("");
                $('#kettle-table-page-search-input-createTime').val("");
                $('#kettle-table-page-search-input-updateTime').val("");
            }
        };

        // 绑定数据表格查询条件按钮方法
        $('.kettle-table-page-search-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>