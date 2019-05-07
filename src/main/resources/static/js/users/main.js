/*!
 * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function () {
    var _pageSize;

    function getUserByName(pageIndex, pageSize) {
        $.ajax({
            url: "/users",
            contentType: "application/json",
            data: {
                "async": true,
                "pageIndex": pageIndex,
                "pageSize": pageSize,
                "name": $("#searchName").val()
            },
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                alert("error");
            }
        })
    }

    // 分页
    $.tbpage("#mainContainer", function (pageIndex, pageSize) {
        getUserByName(pageIndex, pageSize);
        _pageSize = pageSize;
    });

    // 搜索
    $("#searchNameBtn").click(function () {
        getUserByName(0, _pageSize);
    });

    // 获取添加用户的页面
    $("#addUser").click(function () {
        $("#flipFlop").modal('show');
        $.ajax({
            url: "/users/add",
            success: function (data) {
                $("#userFormContainer").html(data);
            },
            error: function () {
                alert("error");
            }
        })
    });

    // 编辑用户
    $("#rightContainer").on("click", ".blog-edit-user", function () {
        $.ajax({
            url: "/users/edit/" + $(this).attr("userId"),
            success: function (data) {
                $("#userFormContainer").html(data);
            },
            error: function () {

            }
        })
    });

    $("#submitEdit").click(function () {
        $.ajax({
            url: "users",
            type: "POST",
            data: $("#userForm").serialize(),
            success: function (data) {
                if (data.success) {
                    getUserByName(0, _pageSize);
                    $("#flipFlop").modal('hide');
                    $("#userForm")[0].reset();
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error~");
            }
        })
    });


    // 删除用户
    $("#rightContainer").on("click", ".blog-delete-user", function () {

        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            url: "/users/" + $(this).attr("userId"),
            type: "DELETE",
            beforeSend: function(req) {
                // csrf 防护
                req.setRequestHeader(csrfToken, csrfHeader);
            },
            success: function (data) {
                if (data.success) {
                    getUserByName(0, _pageSize);
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error~");
            }

        })

    });

});