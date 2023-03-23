<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>表单元素</title>
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
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">转换文件描述</div>
                <div class="layui-card-body layui-row layui-col-space10">
                    <div class="layui-col-md12">
                        <textarea id="edit-textarea" placeholder="请输入" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>

            <div class="edit-btn" style="text-align: right;">
                <button class="layui-btn" data-type="commit">确定</button>
                <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
            </div>
        </div>
    </div>
</div>


<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    var editId = "${id!}";
    var ktrDesc = "${ktrDesc!}";
    console.log("editId:" + editId);

    layui.config({
        base: '../../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function () {
        var $ = layui.$
            ,index = parent.layer.getFrameIndex(window.name);

        if (editId == "") {
            layer.msg('未查找到对象！', {icon: 5});
        } else {
            $("#edit-textarea").val(ktrDesc);
        }

        // 按钮：方法集合
        var active = {
            // 提交编辑字段
            commit: function () {
                console.log("commit");
                var data = {
                    "id": editId,
                    "ktrDesc": $("#edit-textarea").val()
                };

                var load = layer.load(2);
                var editTaskAjax = $.ajax({
                    type: "post",
                    data: JSON.stringify(data),
                    url: "/kettle/update",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        console.log("编辑成功", "content", res);
                        layer.msg('编辑成功！', {icon: 6});
                        // 关闭加载动画
                        layer.close(load);
                        parent.layui.$('#kettle-table-page-search-btn-search').click()
                        parent.layer.close(index);
                    },
                    error: function (err) {
                        console.log("编辑失败", err);
                        layer.msg('编辑失败！', {icon: 5});
                        // 关闭加载动画
                        layer.close(load);
                    },
                    complete: function (XMLHttpRequest, status) {
                        if (status == 'timeout') {
                            editTaskAjax.abort();
                            console.log("接口超时", err);
                            layer.msg('接口超时', {icon: 5});
                            // 关闭加载动画
                            layer.close(load);
                        }
                    }
                });
            }

            // 重置输入
            , reset: function () {
                console.log("reset");
                $('#edit-textarea').val("");
            }
        };

        // 绑定数据表格查询条件按钮方法
        $('.edit-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>