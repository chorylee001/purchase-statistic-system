var bootAlert = function (m) {
    bootbox.alert({
        message: m,
        backdrop: true
    });
}
var bootTitleAlert = function (t, m) {
    bootbox.alert({
        title: t,
        message: m,
        backdrop: true
    });
}
/**
 * 异步加载内容
 * @param url 链接
 * @constructor
 */
var LoadAjaxContent = function (url) {
    $.ajax({
        mimeType: 'text/html; charset=utf-8',
        url: url,
        type: 'POST',
        success: function (data) {
            if (data.indexOf("login_page") != -1) {
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            className: 'btn-success'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-danger'
                        }
                    },
                    message: '登录已经超时,请重新登录!',
                    callback: function (result) {
                        if (result) {
                            location.href = "/login";
                        }
                    }
                });
            } else {
                $('#main_container').html(data);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        },
        dataType: "html",
        async: true
    });
}
/**
 * 异步加载内容
 * @param url 链接
 * @constructor
 */
var LoadInnerAjaxContent = function (url, form, area) {
    var $form;
    if (form != null && '' != form && 'undefined' != form) {
        $form = form.serialize();
    }
    $.ajax({
        mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
        url: url,
        type: 'POST',
        data: $form,
        success: function (data, status, xhr) {
            if (data.indexOf("login_page") != -1) {
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            className: 'btn-success'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-danger'
                        }
                    },
                    message: '登录已经超时,请重新登录!',
                    callback: function (result) {
                        if (result) {
                            location.href = "/login";
                        }
                    }
                });
            } else {
                $("#" + area).html(data);
            }
        },
        dataType: "html",
        async: true
    });
}
/**
 * 加载Form表单
 * @param doc
 * @constructor
 */
var LoadAjaxForm = function (doc) {
    var url = doc.attr('action');
    var type = doc.attr('method');
    var data = doc.serialize();
    if (type == undefined) type = 'GET';
    $.ajax({
        mimeType: 'text/html; charset=utf-8',
        type: type,
        data: data,
        url: url,
        dataType: 'html',
        success: function (data, status, xhr) {
            if (data.indexOf("login_page") != -1) {

                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            className: 'btn-success'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-danger'
                        }
                    },
                    message: '登录已经超时,请重新登录!',
                    callback: function (result) {
                        if (result) {
                            location.href = "/login";
                        }
                    }
                });
            } else {
                $('#main_container').html(data);
            }
        }
    })
}
var LoadAjaxFileForm = function (doc) {

    var url = doc.getAttribute('action');
    var type = doc.getAttribute('method');

    var form = new FormData(doc);
    var data = form;
    if (type == undefined) type = 'GET';
    $.ajax({
        mimeType: 'text/html; charset=utf-8',
        type: type,
        data: data,
        url: url,
        dataType: 'html',
        contentType:false,
        processData:false,
        success: function (data, status, xhr) {
            if (data.indexOf("login_page") != -1) {

                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            className: 'btn-success'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-danger'
                        }
                    },
                    message: '登录已经超时,请重新登录!',
                    callback: function (result) {
                        if (result) {
                            location.href = "/login";
                        }
                    }
                });
            } else {
                $('#main_container').html(data);
            }
        },
        error:function (xhr, textStatus) {
            console.log(xhr);
            alert(textStatus);
        }
    })
}
/**
 * 加载Form表单
 * @param doc
 * @constructor
 */
var LoadAjaxForm2 = function (doc, async) {

    var url = doc.attr('action');
    var type = doc.attr('method');
    var data = doc.serialize();
    if (type == undefined) type = 'GET';
    $.ajax({
        mimeType: 'text/html; charset=utf-8',
        type: type,
        data: data,
        url: url,
        async: async,
        dataType: 'html',
        success: function (data, status, xhr) {
            if (data.indexOf("login_page") != -1) {
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            className: 'btn-success'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-danger'
                        }
                    },
                    message: '登录已经超时,请重新登录!',
                    callback: function (result) {
                        if (result) {
                            location.href = "/login";
                        }
                    }
                });
            } else {
                $('#ajax-content').html(data);
            }

        }
    })
}

/**
 * 异步加载内容
 * @param url 链接
 * @param type 请求类型
 * @param form 请求参数
 * @constructor
 */
var LoadAjaxFormContent = function ($form, type, url) {
    $.ajax({
        url: url,
        type: type,
        data: $form.serializeArray(),
        dataType: "html",
        traditional: true,
        async: false,
        cache: true,
        success: function (data, status, xhr) {
            if (data.indexOf("login_page") != -1) {
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            className: 'btn-success'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-danger'
                        }
                    },
                    message: '登录已经超时,请重新登录!',
                    callback: function (result) {
                        if (result) {
                            location.href = "/login";
                        }
                    }
                });
            } else {
                $('#main_container').html(data);
            }
        }
    });
}

var LoadAjaxData = function (url) {
    var result;
    $.ajax({
        url: url,
        type: 'POST',
        success: function (data) {
            result = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        },
        dataType: "JSON",
        async: false,
    });
    return result;
}

var LoadFormAjaxData = function (doc) {
    var result;

    var url = doc.attr('action');
    var type = doc.attr('method');
    var data = doc.serialize();
    if (type == undefined) type = 'GET';

    $.ajax({
        url: url,
        type: 'POST',
        data:data,
        success: function (data) {
            result = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        },
        dataType: "JSON",
        async: false,
    });
    return result;
}
var loadDownload = function (url) {
    window.location.href = url;
}
var LoadAjaxHTML = function (html) {
    $('#main_container').html(html);
}
/**
 * 手动初始化ajax链接
 * 对link-button添加click事件
 */
var initLink = function () {
    $('.link-button').on('click', function (e) {
        if ($(this).hasClass('ajax-link')) {
            e.preventDefault();
            var url = $(this).attr('href');
            if ($(this).hasClass('delete-button')){
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            className: 'btn-success'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-danger'
                        }
                    },
                    message: '确定要删除吗?',
                    callback: function (result) {
                        if (result) {
                            if (url != undefined) {
                                window.location.hash = url;
                                LoadAjaxFormContent($('#mainForm'),'POST',url);
                            }
                        }else{
                            return ;
                        }
                    }
                });
            }else if (url != undefined) {
                window.location.hash = url;
                LoadAjaxContent(url);
            } else {
                LoadAjaxForm($('#mainForm'));
            }
        }
    });
}
var clickAjaxLink = function () {
    /*$(".check-out-form").click(function(){

        var flag = window.confirm('是否启用暂存数据功能，以便您下次操作？');//此操作将删除该单位下的所有用户\n你确定要进行删除操作？
        if (!flag) return false;
        var history=[];
        window.localStorage.formHistory='';
        for(var i=0;i<$('form input').length;i++){
            if($($('form input')[i])[0].type=='text'){
                history.push({"text":$($('form input')[i]).val()})
            }else if($($('form input')[i])[0].type=='radio'){
                history.push({"radio":$($('form input')[i]).attr('checked') ? 'checked' :''})
            }else if($($('#savehistory input')[i])[0].type=='checkbox'){
                history.push({"checkbox":$($('form input')[i]).attr('checked') ? 'checked' :''})
            }
        }
    })*/
}

var resetStore = function () {

    var localMsg;
    if (window.localStorage.formHistory) {
        localMsg = JSON.parse(window.localStorage.formHistory);
    }
    if (localMsg && localMsg.length >= 1) {
        var realIndex = 0;
        for (var i = 0; i < $('form input').length; i++) {
            if ($($('form input')[i])[0].type == 'text') {
                $($('form input')[i]).val(localMsg[realIndex].text)
                realIndex++;
            } else if ($($('form input')[i])[0].type == 'radio') {
                $($('form input')[i]).prop('checked', localMsg[realIndex].radio)
                realIndex++;
            } else if ($($('form input')[i])[0].type == 'checkbox') {
                $($('form input')[i]).prop('checked', localMsg[realIndex].checkbox)
                realIndex++;
            }
        }
    }
}
/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * 例子：
 * (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 * */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return format;
}
/**
 * 检查文件
 * @param e 文件对象
 * @returns {boolean}
 */
var checkExcelFile = function (e) {

    var val = e.name;
    var exec = val.substr(val.lastIndexOf(".")).toLowerCase();
    if (exec != ".xlsx" && exec != ".xls") {
        bootAlert("您选择的的文件格式有误，请上传Excel文件!");
        return false;
    }
    var fileMaxsize = 100 * 1024 * 1024;
    if (e.size > fileMaxsize) {
        bootAlert("文件不允许超过100M,请调整后上传!");
        return false;
    }
    return true;
}
var checkWAVFile = function (e) {

    var val = e.name;
    var exec = val.substr(val.lastIndexOf(".")).toLowerCase();
    if (exec != ".wav") {
        bootAlert("您选择的的话术文件格式有误，请上传WAV格式文件!");
        return false;
    }
    var fileMaxsize = 100 * 1024 * 1024;
    if (e.size > fileMaxsize) {
        bootAlert("文件不允许超过100M,请调整后上传!");
        return false;
    }
    return true;
}

/**
 * 单个文件上传
 * @param e要上传的文件
 * @param url 服务器url
 */
function singleUpload(inputName, file, url) {

    var formData = new FormData();
    formData.set(inputName, file);
    var result;
    $.ajax({
        type: "POST",
        url: url,
        dataType: "JSON",
        crossDomain: true,
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        timeout : 30000,
        success: function (data) {
            result = data;
        },error:function(xhr, status){
            if(status=='timeout'){
                bootAlert("上传超时,请重试!");
            }else{
                bootAlert("文件上传失败,请稍后重试!");
            }
        },complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
            if(status=='timeout'){
                bootAlert("上传超时,请重试!");
            }
        }
    });
    return result;
}

+ function ($) {

    //slide menu operate
    $(".sidebar-menu .ajax-link").click(function (e) {
        if ($(this).attr('href') == '#') {
            e.preventDefault();
        }
        if ($(this).hasClass('ajax-link')) {
            e.preventDefault();
            var url = $(this).attr('href');
            window.location.hash = url;
            LoadAjaxContent(url);
        }
    });
    /**
     * 对ajax-link的一种补充机制
     * 非treeview下的功能菜单.
     */
    $(document).on("click", ".ajax-link.inner-link", function (e) {
        if ($(this).attr('href') == '#') {
            e.preventDefault();
        }
        if ($(this).hasClass('inner-link')) {
            e.preventDefault();
            var url = $(this).attr('href');
            window.location.hash = url;
            LoadAjaxContent(url);
        }
    });


    var ajax_url = location.hash.replace(/^#/, '');
    if (ajax_url.length < 1) {
        ajax_url = '/dashboard/index';
    }
    LoadAjaxContent(ajax_url);
    $(document).ajaxStart(function () {
        Pace.restart()
    })
    $('.link-button').on('click', function (e) {
        if ($(this).hasClass('ajax-link')) {
            e.preventDefault();
            var url = $(this).attr('href');
            if (url != undefined) {
                window.location.hash = url;
                LoadAjaxContent(url);
            } else {
                LoadAjaxForm($('#mainForm'));
            }
        }else if ($(this).hasClass('download-link')) {
            e.preventDefault();
            var url = $(this).attr('href');
            if (url != undefined) {
                loadDownload(url);
            } else {
                LoadAjaxForm($('#mainForm'));
            }
        }
    });
}(jQuery)