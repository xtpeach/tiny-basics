$(function () {

    // init date
    $.ajax({
        type: "POST",
        url: base_url + "/jobregistry/list",
        dataType: "json",
        success: function (data) {
            // console.log("实例信息：", data);
            if (typeof data != "null" && typeof data != "undefined") {
                $("#executor_list").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#executor_list").append(addInstanceHtml(data[i]));
                }
            }
        },
    });

    function addInstanceHtml(instance) {
        var markColor = "bg-green";
        if (instance.calculateWeight > 60) {
            markColor = "bg-yellow";
        }
        if (instance.calculateWeight > 85) {
            markColor = "bg-red";
        }
        var instanceHtml = "<div class=\"col-md-3 col-sm-6 col-xs-12\">"
            + "<div class=\"info-box "+ markColor +"\">"
            + "<span class=\"info-box-icon\"><i class=\"fa ion-ios-shuffle-strong\"></i></span>"
            + "<div class=\"info-box-content\">"
            + "<span class=\"info-box-text\">" + instance.registryGroup + "</span>"
            + "<span class=\"info-box-number\">" + instance.calculateWeight + "%</span>"
            + "<div class=\"progress\">"
            + "<div class=\"progress-bar\" style=\"width: 100%\"></div>"
            + "</div>"
            + "<span class=\"progress-description\">" + instance.registryKey + "</span>"
            + "</div></div></div>";

        return instanceHtml;
    }

});

